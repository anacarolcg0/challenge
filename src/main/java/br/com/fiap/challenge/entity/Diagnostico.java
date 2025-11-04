package br.com.fiap.challenge.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
    @Table(name = "tab_diagnostico")
    public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modeloMoto;
    private int anoMoto;
    private String descricaoProblema;
    private String status = "PENDENTE";

    private LocalDateTime dataCriacao = LocalDateTime.now();

    public Diagnostico() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModeloMoto() {
        return modeloMoto;
    }

    public void setModeloMoto(String modeloMoto) {
        this.modeloMoto = modeloMoto;
    }

    public int getAnoMoto() {
        return anoMoto;
    }

    public void setAnoMoto(int anoMoto) {
        this.anoMoto = anoMoto;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
