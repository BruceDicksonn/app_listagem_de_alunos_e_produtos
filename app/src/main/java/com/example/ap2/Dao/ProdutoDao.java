package com.example.ap2.Dao;

import com.example.ap2.Model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

    private final static List<Produto> listaProdutos = new ArrayList<>();

    public void salvar(Produto produto){

        listaProdutos.add(produto);

    }

    public boolean atualizar(Produto produto){
        return false;
    }

    public boolean remover(Produto produto){
        return false;
    }

    public List<Produto> getProdutos(){
        return  new ArrayList<>(listaProdutos);
    }

}
