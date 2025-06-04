package cucumber.steps;

import com.agrotis_2025.infrastructure.adapter.in.filter.PropriedadeFiltroDto;
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

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class PropriedadeControllerStep {

    private static RequestSpecification requestSpecification;

    @Autowired
    private DataSource dataSource;

    @LocalServerPort
    int port;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    private Response response;

    private PropriedadeFiltroDto propriedadeFiltroDto;

    @Before
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .addHeader(ConstantsTest.HEADER_PARAM_ORIGIN, ConstantsTest.ORIGIN_AGROTIS)
                .setBasePath(ConstantsTest.PATH_CHALLENGE_PROPRIEDADE)
                .setPort(port)
                .build();
    }

    @Dado("ambiente de teste ativado para Agrotis2025 de Propriedade")
    public void ambiente_de_teste_ativado_para_agrotis2025_de_propriedade() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        var count = jdbcTemplate
                .queryForObject("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES", Integer.class);
        assertThat(count).isNotNull();
    }

    @Dado("cadastros de Propriedades disponíveis no banco de dados de Propriedade")
    public void cadastros_de_propriedades_disponiveis_no_banco_de_dados_de_propriedade(io.cucumber.datatable.DataTable dataTable) {

        clienteRepository.deleteAll();
        propriedadeRepository.deleteAll();

        List<Map<String, String>> dados = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : dados) {

            PropriedadeEntity entity = new PropriedadeEntity();
            entity.setNome(row.get("nome"));

            propriedadeRepository.save(entity);
        }
    }

    @Quando("uma requisição Get for feita, com nome {string} npo filtro, no método search do PropriedadeController")
    public void uma_requisicao_get_for_feita_com_nome_npo_filtro_no_metodo_search_do_propriedade_controller(String nome) {

        response = RestAssured
                .given().spec(requestSpecification)
                .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                .queryParam("nome", nome)
                .when()
                .get();

        assertThat(response).isNotNull();
    }

    @Entao("receber resposta HTTP {int} do PropriedadeController")
    public void receber_resposta_http_do_propriedade_controller(Integer status) {

        assertEquals(status, response.getStatusCode());
    }
}

