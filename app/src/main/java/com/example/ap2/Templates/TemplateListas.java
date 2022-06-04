package com.example.ap2.Templates;
import android.support.v7.app.AppCompatActivity;


import com.example.ap2.Dao.UsuariosDao;
import com.example.ap2.Model.Usuario;

public abstract class TemplateListas extends AppCompatActivity {

    protected Usuario getUsuarioLogado(String email, String senha){

        Usuario retorno = new Usuario();

        for(Usuario user : UsuariosDao.getListaUsuarios()){
            if(user.getEmail().equals(email) && user.getSenha().equals(senha)){
                retorno = user;
            }
        }

        return retorno;

    }

    protected abstract void abrirTelaPrincipal();

    protected abstract void abrirFormularioAluno();

    protected abstract void abrirFormularioProduto();

    protected abstract void logout();

    protected abstract void configuraLista();

    protected abstract void initComponents();
}
