package com.example.ap2.Templates;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.ap2.Dao.UsuariosDao;
import com.example.ap2.Model.Usuario;

public abstract class TemplatePrincipal extends AppCompatActivity {


    protected abstract void abrirAreaAlunos();
    protected abstract void abrirAreaProdutos();
    protected abstract void abrirFormularioAluno();
    protected abstract void abrirFormularioProduto();

    protected Usuario getUsuarioLogado(String email, String senha){

        Usuario retorno = new Usuario();

        for(Usuario user : UsuariosDao.getListaUsuarios()){
            if(user.getEmail().equals(email) && user.getSenha().equals(senha)){
                retorno = user;
            }
        }

        return retorno;

    }

    protected void alert(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected abstract void initComponents();
    protected abstract void logout();

}
