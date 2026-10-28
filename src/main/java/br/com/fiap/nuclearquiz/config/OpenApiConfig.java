package br.com.fiap.nuclearquiz.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI nuclearQuizOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NucleAR Quiz API")
                        .version("1.0.0")
                        .description("API do NucleAR Quiz: perguntas e competições para desmistificar "
                                + "a tecnologia nuclear e mostrar suas aplicações pacíficas "
                                + "(medicina, agricultura, indústria e geração de energia).")
                        .contact(new Contact().name("Turma 2TDS - Challenge FIAP 2026")));
    }
}
