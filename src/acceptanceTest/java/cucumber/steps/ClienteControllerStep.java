package cucumber.steps;

import com.agrotis_2025.infrastructure.adapter.in.dto.request.ClienteDtoRequest;
import com.agrotis_2025.infrastructure.adapter.in.dto.response.ClienteDtoResponse;
import com.agrotis_2025.infrastructure.adapter.out.persistence.ClienteRepository;
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

    @Dado("um ClienteDtoRequest, com nome {string} e observações {string}")
    public void um_cliente_dto_request_com_nome_e_observacoes(String nome, String observacoes) {

        clienteDtoRequest = new ClienteDtoRequest(nome, observacoes);
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

    @Entao("com body na resposta, com {string} e observações {string}, do ClienteController")
    public void com_body_na_resposta_com_e_observacoes_do_cliente_controller(String nome, String observacoes) {

        clienteDtoResponse = response.as(ClienteDtoResponse.class);

        assertThat(clienteDtoResponse.clienteId()).isNotNull();
        assertThat(clienteDtoResponse.nome()).isEqualTo(nome);
        assertThat(clienteDtoResponse.observacoes()).isEqualTo(observacoes);
    }

    @Entao("o Cliente corretamente cadastrado no banco de dados, com nome {string} e observações {string}")
    public void o_cliente_corretamente_cadastrado_no_banco_de_dados_com_nome_e_observacoes(String nome, String observacoes) {

        var entity = clienteRepository.findById(clienteDtoResponse.clienteId()).get();

        assertThat(entity.getNome()).isEqualTo(nome);
        assertThat(entity.getObservacoes()).isEqualTo(observacoes);
    }
}

