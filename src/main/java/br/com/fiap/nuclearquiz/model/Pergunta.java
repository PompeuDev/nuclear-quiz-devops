package br.com.fiap.nuclearquiz.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "perguntas")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String enunciado;

    @Column(name = "alternativa_a", nullable = false, length = 200)
    private String alternativaA;

    @Column(name = "alternativa_b", nullable = false, length = 200)
    private String alternativaB;

    @Column(name = "alternativa_c", nullable = false, length = 200)
    private String alternativaC;

    @Column(name = "alternativa_d", nullable = false, length = 200)
    private String alternativaD;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private Alternativa respostaCorreta;

    @Column(nullable = false, length = 1000)
    private String explicacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Tema tema;

    public Pergunta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getAlternativaA() {
        return alternativaA;
    }

    public void setAlternativaA(String alternativaA) {
        this.alternativaA = alternativaA;
    }

    public String getAlternativaB() {
        return alternativaB;
    }

    public void setAlternativaB(String alternativaB) {
        this.alternativaB = alternativaB;
    }

    public String getAlternativaC() {
        return alternativaC;
    }

    public void setAlternativaC(String alternativaC) {
        this.alternativaC = alternativaC;
    }

    public String getAlternativaD() {
        return alternativaD;
    }

    public void setAlternativaD(String alternativaD) {
        this.alternativaD = alternativaD;
    }

    public Alternativa getRespostaCorreta() {
        return respostaCorreta;
    }

    public void setRespostaCorreta(Alternativa respostaCorreta) {
        this.respostaCorreta = respostaCorreta;
    }

    public String getExplicacao() {
        return explicacao;
    }

    public void setExplicacao(String explicacao) {
        this.explicacao = explicacao;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
}
