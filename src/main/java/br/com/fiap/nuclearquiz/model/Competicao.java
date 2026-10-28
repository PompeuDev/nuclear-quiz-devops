package br.com.fiap.nuclearquiz.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "competicoes")
public class Competicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Tema tema;

    @Column(nullable = false)
    private LocalDateTime dataEncerramento;


    @Column(nullable = false)
    private Integer quantidadeQuestoes;


    @Column(nullable = false)
    private Integer tempoTotalSegundos;

    @Column(nullable = false, length = 100)
    private String premioPrimeiroLugar;

    @Column(nullable = false, length = 100)
    private String premioSegundoLugar;

    @Column(nullable = false, length = 100)
    private String premioTerceiroLugar;

    public Competicao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public LocalDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDateTime dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public Integer getQuantidadeQuestoes() {
        return quantidadeQuestoes;
    }

    public void setQuantidadeQuestoes(Integer quantidadeQuestoes) {
        this.quantidadeQuestoes = quantidadeQuestoes;
    }

    public Integer getTempoTotalSegundos() {
        return tempoTotalSegundos;
    }

    public void setTempoTotalSegundos(Integer tempoTotalSegundos) {
        this.tempoTotalSegundos = tempoTotalSegundos;
    }

    public String getPremioPrimeiroLugar() {
        return premioPrimeiroLugar;
    }

    public void setPremioPrimeiroLugar(String premioPrimeiroLugar) {
        this.premioPrimeiroLugar = premioPrimeiroLugar;
    }

    public String getPremioSegundoLugar() {
        return premioSegundoLugar;
    }

    public void setPremioSegundoLugar(String premioSegundoLugar) {
        this.premioSegundoLugar = premioSegundoLugar;
    }

    public String getPremioTerceiroLugar() {
        return premioTerceiroLugar;
    }

    public void setPremioTerceiroLugar(String premioTerceiroLugar) {
        this.premioTerceiroLugar = premioTerceiroLugar;
    }
}
