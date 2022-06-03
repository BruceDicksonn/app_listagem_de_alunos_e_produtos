package com.example.ap2.Model;

public class Aluno {

    private String nome;
    private String telefone;
    private String email;

    public Aluno(){}

    public Aluno(String nome, String tel, String email){
        this.nome = nome;
        this.telefone = tel;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return nome;
    }
}
