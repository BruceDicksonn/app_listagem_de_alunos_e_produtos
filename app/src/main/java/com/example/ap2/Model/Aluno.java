package com.example.ap2.Model;

public class Aluno {

    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String iDoCriador;

    public Aluno(){}

    public Aluno(String nome, String tel, String email){
        this.nome = nome;
        this.telefone = tel;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getIdDoCriador() {
        return iDoCriador;
    }

    public void setIdDoCriador(String idDoCriador) {
        this.iDoCriador = idDoCriador;
    }

    @Override
    public String toString() {
        return "\nNome: "+nome
              +"\n\nFone: "+telefone
              +"\n\nEmail: "+email+"\n";
    }
}
