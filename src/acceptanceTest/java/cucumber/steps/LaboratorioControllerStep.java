package cucumber.steps;

import com.agrotis_2025.infrastructure.adapter.in.dto.response.LaboratorioDtoResponse;
import com.agrotis_2025.infrastructure.adapter.in.dto.response.PropriedadeDtoResponse;
import com.agrotis_2025.infrastructure.adapter.out.persistence.ClienteRepository;
import com.agrotis_2025.infrastructure.adapter.out.persistence.LaboratorioRepository;
import com.agrotis_2025.infrastructure.adapter.out.persistence.entity.LaboratorioEntity;
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

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LaboratorioControllerStep {

    private static RequestSpecification requestSpecification;

    @Autowired
    private DataSource dataSource;

    @LocalServerPort
    int port;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    private Response response;

    @Before
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .addHeader(ConstantsTest.HEADER_PARAM_ORIGIN, ConstantsTest.ORIGIN_AGROTIS)
                .setBasePath(ConstantsTest.PATH_CHALLENGE_LABORATORIO)
                .setPort(port)
                .build();
    }

    @Dado("ambiente de teste ativado para Agrotis2025 de Laboratorio")
    public void ambiente_de_teste_ativado_para_agrotis2025_de_laboratorio() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        var count = jdbcTemplate
                .queryForObject("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES", Integer.class);
        assertThat(count).isNotNull();
    }

    @Dado("cadastros de Propriedades disponíveis no banco de dados de Laboratorio")
    public void cadastros_de_propriedades_disponiveis_no_banco_de_dados_de_laboratorio(io.cucumber.datatable.DataTable dataTable) {

        clienteRepository.deleteAll();
        laboratorioRepository.deleteAll();

        List<Map<String, String>> dados = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : dados) {

            var entity = new LaboratorioEntity();
            entity.setNome(row.get("nome"));

            laboratorioRepository.save(entity);
        }
    }

    @Quando("uma requisição Get for feita, com nome {string} npo filtro, no método search do LaboratorioController")
    public void uma_requisicao_get_for_feita_com_nome_npo_filtro_no_metodo_search_do_laboratorio_controller(String nome) {

        response = RestAssured
                .given().spec(requestSpecification)
                .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                .queryParam("nome", nome)
                .when()
                .get();

        assertThat(response).isNotNull();
    }

    @Entao("receber resposta HTTP {int} do LaboratorioController")
    public void receber_resposta_http_do_laboratorio_controller(Integer status) {

        assertEquals(status, response.getStatusCode());
    }

    @Entao("a resposta contém apenas laboratorios com o nome {string} do LaboratorioController")
    public void a_resposta_contem_apenas_laboratorios_com_o_nome_do_laboratorio_controller(String nome) {

        List<LaboratorioDtoResponse> content = response.jsonPath()
                .getList("content", LaboratorioDtoResponse.class);

        assertThat(content).isNotEmpty();
        assertThat(content)
                .allMatch(dto -> dto.nome().equals(nome))
                .extracting(LaboratorioDtoResponse::nome)
                .containsOnly(nome);
    }

}

