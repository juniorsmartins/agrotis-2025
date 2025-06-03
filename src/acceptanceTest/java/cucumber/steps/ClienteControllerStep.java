package cucumber.steps;

import com.agrotis_2025.infrastructure.adapter.in.dto.request.ClienteDtoRequest;
import com.agrotis_2025.infrastructure.adapter.in.dto.request.PropriedadeDtoRequest;
import com.agrotis_2025.infrastructure.adapter.in.dto.response.ClienteDtoResponse;
import com.agrotis_2025.infrastructure.adapter.out.persistence.ClienteRepository;
import com.agrotis_2025.infrastructure.adapter.out.persistence.PropriedadeRepository;
import com.agrotis_2025.infrastructure.adapter.out.persistence.entity.PropriedadeEntity;
import cucumber.config.ConstantsTest;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ClienteControllerStep {

    private static RequestSpecification requestSpecification;

    @Autowired
    private DataSource dataSource;

    @LocalServerPort
    int port;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    private ClienteDtoRequest clienteDtoRequest;

    private ClienteDtoResponse clienteDtoResponse;

    private Response response;

    @Before
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .addHeader(ConstantsTest.HEADER_PARAM_ORIGIN, ConstantsTest.ORIGIN_AGROTIS)
                .setBasePath(ConstantsTest.PATH_CHALLENGE_CLIENTE)
                .setPort(port)
                .build();
    }

    @Dado("ambiente de teste ativado para Agrotis2025")
    public void ambiente_de_teste_ativado_para_agrotis2025() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        var count = jdbcTemplate
                .queryForObject("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES", Integer.class);
        assertThat(count).isNotNull();
    }

    @Dado("cadastros de Propriedades disponíveis no banco de dados")
    public void cadastros_de_propriedades_disponiveis_no_banco_de_dados(io.cucumber.datatable.DataTable dataTable) {
        propriedadeRepository.deleteAll();

        List<Map<String, String>> dados = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : dados) {

            PropriedadeEntity entity = new PropriedadeEntity();
            entity.setNome(row.get("nome"));

            propriedadeRepository.save(entity);
        }
    }

    @Dado("um ClienteDtoRequest, com nome {string} e dataInicial {string} e dataFinal {string} e observações {string}, e ProprietarioDtoRequest, com nome {string}")
    public void um_cliente_dto_request_com_nome_e_data_inicial_e_data_final_e_observações_e_proprietario_dto_request_com_nome(
            String nome, String dataInicial, String dataFinal, String observacoes, String nomePropriedade) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        var propriedadeEntity = propriedadeRepository.findByNome(nomePropriedade).get();
        var propriedadeDtoRequest = new PropriedadeDtoRequest(propriedadeEntity.getPropriedadeId());

        clienteDtoRequest = new ClienteDtoRequest(
                nome, LocalDate.parse(dataInicial, formatter), LocalDate.parse(dataFinal, formatter), propriedadeDtoRequest, observacoes);
        assertThat(clienteDtoRequest).isNotNull();
    }

    @Quando("a requisição Post for feita no ClienteController")
    public void a_requisicao_post_for_feita_no_cliente_controller() {

        response = RestAssured
                .given().spec(requestSpecification)
                .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                .body(clienteDtoRequest)
                .when()
                .post();

        assertThat(response).isNotNull();
    }

    @Entao("receber resposta HTTP {int} do ClienteController")
    public void receber_resposta_http_do_cliente_controller(Integer status) {

        assertEquals(status, response.getStatusCode());
    }

    @Entao("com Cliente, com nome {string} e dataInicial {string} e dataFinal {string} e observações {string}, e Proprietario, com nome {string}, no body da resposta do ClienteController")
    public void com_cliente_com_nome_e_data_inicial_e_data_final_e_observacoes_e_proprietario_com_nome_no_body_da_resposta_do_cliente_controller(
            String nome, String dataInicial, String dataFinal, String observacoes, String nomePropriedade) {

        clienteDtoResponse = response.as(ClienteDtoResponse.class);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertThat(clienteDtoResponse.clienteId()).isNotNull();
        assertThat(clienteDtoResponse.nome()).isEqualTo(nome);
        assertThat(clienteDtoResponse.dataInicial()).isEqualTo(LocalDate.parse(dataInicial, formatter));
        assertThat(clienteDtoResponse.dataFinal()).isEqualTo(LocalDate.parse(dataFinal, formatter));
        assertThat(clienteDtoResponse.propriedade().propriedadeId()).isNotNull();
        assertThat(clienteDtoResponse.propriedade().nome()).isEqualTo(nomePropriedade);
        assertThat(clienteDtoResponse.observacoes()).isEqualTo(observacoes);
    }

    @Entao("o Cliente corretamente cadastrado no banco de dados, com nome {string} e dataInicial {string} e dataFinal {string} e observações {string}")
    public void o_cliente_corretamente_cadastrado_no_banco_de_dados_com_nome_e_data_inicial_e_data_final_e_observacoes(
            String nome, String dataInicial, String dataFinal, String observacoes) {

        var entity = clienteRepository.findById(clienteDtoResponse.clienteId()).get();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertThat(entity.getNome()).isEqualTo(nome);
        assertThat(entity.getDataInicial()).isEqualTo(LocalDate.parse(dataInicial, formatter));
        assertThat(entity.getDataFinal()).isEqualTo(LocalDate.parse(dataFinal, formatter));
        assertThat(entity.getObservacoes()).isEqualTo(observacoes);
    }


}

