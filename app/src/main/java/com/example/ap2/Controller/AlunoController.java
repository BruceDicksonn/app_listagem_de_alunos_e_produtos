package com.example.ap2.Controller;

import com.example.ap2.Dao.AlunoDao;
import com.example.ap2.Model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoController {

    public static List<String> getAlunos(){

        List<String> retorno = new ArrayList<>();

        for(Aluno aluno : AlunoDao.getAlunos()){ // recupera os alunos cadastrados em AlunosDao

            retorno.add(aluno.getNome());

        }

        return  retorno;

    }

}
