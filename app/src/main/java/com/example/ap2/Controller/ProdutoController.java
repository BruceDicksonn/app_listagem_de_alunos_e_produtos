package com.example.ap2.Controller;

import com.example.ap2.Dao.AlunoDao;
import com.example.ap2.Dao.ProdutoDao;
import com.example.ap2.Model.Aluno;
import com.example.ap2.Model.Produto;
import com.example.ap2.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    public static List<String> getProdutos(Usuario usuario){

        List<String> retorno = new ArrayList<>();

        for(Produto produto : ProdutoDao.getProdutos()){ // recupera os produtos cadastrados em produtosDao

            if(produto.getIdDoCriador().equals(usuario.getIdUsuario())){

                retorno.add(produto.getNome());

            }

        }

        return  retorno;

    }

}
