package br.com.fiap.nuclearquiz.controller;

import br.com.fiap.nuclearquiz.dto.CompeticaoRequest;
import br.com.fiap.nuclearquiz.dto.CompeticaoResponse;
import br.com.fiap.nuclearquiz.service.CompeticaoService;
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
@RequestMapping("/api/competicoes")
@Tag(name = "Competições", description = "Gestão das competições temáticas (perfil administrador)")
public class CompeticaoController {

    private final CompeticaoService service;

    public CompeticaoController(CompeticaoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cria uma competição")
    public ResponseEntity<CompeticaoResponse> criar(@Valid @RequestBody CompeticaoRequest request) {
        CompeticaoResponse criada = service.criar(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(criada.id()).toUri();
        return ResponseEntity.created(location).body(criada);
    }

    @GetMapping
    @Operation(summary = "Lista todas as competições")
    public List<CompeticaoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/em-andamento")
    @Operation(summary = "Lista as competições em andamento (ainda não encerradas)")
    public List<CompeticaoResponse> listarEmAndamento() {
        return service.listarEmAndamento();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma competição pelo id")
    public CompeticaoResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/busca")
    @Operation(summary = "Busca competições pelo texto do nome")
    public List<CompeticaoResponse> buscarPorNome(@RequestParam String termo) {
        return service.buscarPorNome(termo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma competição")
    public CompeticaoResponse atualizar(@PathVariable Long id, @Valid @RequestBody CompeticaoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove uma competição")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
