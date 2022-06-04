package com.example.ap2.Templates;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ap2.Dao.UsuariosDao;
import com.example.ap2.Model.Usuario;

public abstract class TemplateFormulario extends AppCompatActivity {

    protected void alert(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected abstract void initComponents();

    protected abstract boolean verificarInputs();

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
    protected abstract void abrirFormularioCadastro();
    protected abstract void abrirFormularioLogin();
    protected abstract void abrirAreaAlunos();
    protected abstract void abrirAreaProdutos();
    protected abstract boolean validarEmailESenha();
    protected abstract void limparInputs();


}
