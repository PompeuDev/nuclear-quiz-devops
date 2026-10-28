package br.com.fiap.nuclearquiz.controller;

import br.com.fiap.nuclearquiz.dto.PerguntaRequest;
import br.com.fiap.nuclearquiz.dto.PerguntaResponse;
import br.com.fiap.nuclearquiz.model.Tema;
import br.com.fiap.nuclearquiz.service.PerguntaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/perguntas")
@Tag(name = "Perguntas", description = "Banco de perguntas educativas sobre tecnologia nuclear")
public class PerguntaController {

    private final PerguntaService service;

    public PerguntaController(PerguntaService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cadastra uma nova pergunta")
    public ResponseEntity<PerguntaResponse> criar(@Valid @RequestBody PerguntaRequest request) {
        PerguntaResponse criada = service.criar(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(criada.id()).toUri();
        return ResponseEntity.created(location).body(criada);
    }

    @GetMapping
    @Operation(summary = "Lista as perguntas (opcionalmente filtrando por tema)")
    public List<PerguntaResponse> listar(@RequestParam(required = false) Tema tema) {
        return service.listar(tema);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma pergunta pelo id")
    public PerguntaResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/busca")
    @Operation(summary = "Busca perguntas pelo texto do enunciado")
    public List<PerguntaResponse> buscarPorEnunciado(@RequestParam String termo) {
        return service.buscarPorEnunciado(termo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma pergunta")
    public PerguntaResponse atualizar(@PathVariable Long id, @Valid @RequestBody PerguntaRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove uma pergunta")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
