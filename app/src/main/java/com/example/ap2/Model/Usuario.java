package com.example.ap2.Model;

import com.example.ap2.Dao.AlunoDao;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private List<String> produtosSalvos = new ArrayList<>();
    private List<String> alunoSalvos = new ArrayList<>();

    public Usuario(){

    }

    public Usuario(String[] dados){
        this.nome = dados[0];
        this.email = dados[1];
        this.senha = dados[2];
    }

    public Usuario(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public List<String> getProdutosSalvos() {
        return produtosSalvos;
    }

    public void setProdutosSalvos(List<String> produtosSalvos) {
        this.produtosSalvos = produtosSalvos;
    }

    public List<String> getAlunoSalvos() {
        return alunoSalvos;
    }

    public void setAlunoSalvos(List<String> alunoSalvos) {
        this.alunoSalvos = alunoSalvos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Aluno> toListaAluno(){

        List<Aluno> alunosDao = AlunoDao.getAlunos();
        List<Aluno> listaRetornada = new ArrayList<>();

            if(alunosDao.size() > 0) {


                for (int i = 0; i < alunosDao.size(); i++) {

                    String nomeDao = alunosDao.get(i).getNome();

                    if (nomeDao.equals(getAlunoSalvos().get(i))) {

                        listaRetornada.add(alunosDao.get(i));

                    }
                }

            }


        return new ArrayList<>(listaRetornada);

    }

}
