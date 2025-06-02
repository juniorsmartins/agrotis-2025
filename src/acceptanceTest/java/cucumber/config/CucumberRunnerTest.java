package cucumber.config;

import com.agrotis_2025.Agrotis2025Application;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/acceptanceTest/resources/features", "classpath:features"},
        glue = {"cucumber.config",
                "cucumber.steps"},
        plugin = {
                "pretty",
                "html:src/acceptanceTest/cucumber-reports/cucumber.html",
                "json:src/acceptanceTest/cucumber-reports/cucumber.json"
        },
        monochrome = true,
        snippets = CucumberOptions.SnippetType.UNDERSCORE,
        dryRun = false
)
@CucumberContextConfiguration
@SpringBootTest(
        classes = Agrotis2025Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class CucumberRunnerTest {

}

