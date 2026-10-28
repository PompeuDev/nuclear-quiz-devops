package br.com.fiap.nuclearquiz.repository;

import br.com.fiap.nuclearquiz.model.Pergunta;
import br.com.fiap.nuclearquiz.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {


    List<Pergunta> findByEnunciadoContainingIgnoreCase(String termo);

    List<Pergunta> findByTema(Tema tema);
}
