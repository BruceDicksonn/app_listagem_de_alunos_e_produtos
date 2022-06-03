package com.example.ap2.Model;

import java.text.NumberFormat;
import java.util.Locale;

public class Produto {

    private String nome;
    private String descricao;
    private String preco;

    public Produto(){

    }

    public Produto(String nome, String descricao, Double preco){
        this.nome = nome;
        this.descricao = descricao;
        this.preco = monetary(preco);
    }

    public String monetary(Double value){
        return NumberFormat.getCurrencyInstance(new Locale("pt","br")).format(value);
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

    public String getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = monetary(preco);
    }

    @Override
    public String toString() {
        return "\nNome: " + nome
              +"\n\nDescricao: " + descricao
              +"\n\nPreço: " + preco
              +"\n";
    }
}
