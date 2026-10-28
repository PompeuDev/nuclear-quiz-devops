package br.com.fiap.nuclearquiz.repository;

import br.com.fiap.nuclearquiz.model.Competicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CompeticaoRepository extends JpaRepository<Competicao, Long> {


    List<Competicao> findByNomeContainingIgnoreCase(String termo);


    List<Competicao> findByDataEncerramentoAfter(LocalDateTime agora);
}
