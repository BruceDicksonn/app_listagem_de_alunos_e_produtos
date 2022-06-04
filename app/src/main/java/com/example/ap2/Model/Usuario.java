package com.example.ap2.Model;

import com.example.ap2.Dao.AlunoDao;
import com.example.ap2.Dao.ProdutoDao;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String idUsuario;
    private String nome;
    private String email;
    private String senha;
    private List<String> produtosSalvos = new ArrayList<>();
    private List<String> alunoSalvos = new ArrayList<>();

    public Usuario(){
        int id = (int)(Math.random() * 999999999 ) + 1;
        setIdUsuario(String.valueOf(id));
    }

    public Usuario(String[] dados){
        this.nome = dados[0];
        this.email = dados[1];
        this.senha = dados[2];
        int id = (int)(Math.random() * 999999999 ) + 1;
        setIdUsuario(String.valueOf(id));
    }

    public Usuario(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        int id = (int)(Math.random() * 999999999 ) + 1;
        setIdUsuario(String.valueOf(id));
    }

    public List<String> getProdutosSalvos() {
        return produtosSalvos;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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

            if(alunosDao.size() > 0 && getAlunoSalvos().size() > 0) {


                for (int i = 0; i < alunosDao.size(); i++) {

                    if(alunosDao.get(i).getIdDoCriador().equals(getIdUsuario())){

                        listaRetornada.add(alunosDao.get(i));

                    }

                }

            }


        return new ArrayList<>(listaRetornada);

    }

    public List<Produto> toListaProduto(){

        List<Produto> produtosDao = ProdutoDao.getProdutos();
        List<Produto> listaRetornada = new ArrayList<>();

        if(produtosDao.size() > 0 && getProdutosSalvos().size() > 0) {

            for (int i = 0; i < produtosDao.size(); i++) {

                if(produtosDao.get(i).getIdDoCriador().equals(getIdUsuario())){

                    listaRetornada.add(produtosDao.get(i));

                }
            }

        }


        return new ArrayList<>(listaRetornada);

    }

}
