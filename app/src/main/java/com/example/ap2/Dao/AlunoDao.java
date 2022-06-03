package com.example.ap2.Dao;

import com.example.ap2.Model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDao {

    private final static List<Aluno> listaAlunos = new ArrayList<>();

    public void salvar(Aluno aluno){

       aluno.setId(listaAlunos.size() + 1);
       listaAlunos.add(aluno);

    }

    public boolean atualizar(Aluno aluno){
        return false;
    }

    public boolean remover(Aluno aluno){
        return false;
    }

    public static List<Aluno> getAlunos(){
        return  new ArrayList<>(listaAlunos);
    }

    public static void limparListaAlunos(){
        listaAlunos.clear();
    }

}
