package com.estoque.veiculos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Modelo é obrigatório")
    @Column(nullable = false)
    private String modelo;

    @NotBlank(message = "Marca é obrigatória")
    @Column(nullable = false)
    private String marca;

    @NotNull(message = "Ano é obrigatório")
    @Min(value = 1886, message = "Ano inválido")
    @Column(nullable = false)
    private Integer ano;

    @NotBlank(message = "Cor é obrigatória")
    @Column(nullable = false)
    private String cor;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    @Column(nullable = false)
    private Double preco;

    @NotNull(message = "Quilometragem é obrigatória")
    @Min(value = 0, message = "Quilometragem não pode ser negativa")
    @Column(nullable = false)
    private Double quilometragem;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusVeiculo status;

    public Veiculo() {}

    public Veiculo(String modelo, String marca, Integer ano, String cor,
                   Double preco, Double quilometragem, StatusVeiculo status) {
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
        this.quilometragem = quilometragem;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }
}
