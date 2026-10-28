package br.com.fiap.nuclearquiz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NuclearQuizApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveListarPerguntasDoSeed() throws Exception {
        mockMvc.perform(get("/api/perguntas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    void deveBuscarPerguntaPorTextoSemDiferenciarMaiusculas() throws Exception {
        mockMvc.perform(get("/api/perguntas/busca").param("termo", "RADIOTERAPIA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void deveRetornar404QuandoPerguntaNaoExiste() throws Exception {
        mockMvc.perform(get("/api/perguntas/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.mensagem").exists());
    }

    @Test
    void deveRetornar400ComListaDeCamposInvalidos() throws Exception {
        mockMvc.perform(post("/api/competicoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"\",\"quantidadeQuestoes\":2}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.campos").isArray());
    }

    @Test
    void deveCriarCompeticaoEcalcularTempoTotal() throws Exception {
        String json = """
                {
                  "nome": "Quiz Indústria Nuclear",
                  "descricao": "Aplicações da tecnologia nuclear na indústria.",
                  "tema": "INDUSTRIA",
                  "dataEncerramento": "2099-12-31T23:59:00",
                  "quantidadeQuestoes": 8,
                  "premioPrimeiroLugar": "Boné Amazul",
                  "premioSegundoLugar": "Bottom Amazul",
                  "premioTerceiroLugar": "Adesivo Amazul"
                }
                """;

        mockMvc.perform(post("/api/competicoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tempoTotalSegundos").value(240))
                .andExpect(jsonPath("$.encerrada").value(false));
    }
}
