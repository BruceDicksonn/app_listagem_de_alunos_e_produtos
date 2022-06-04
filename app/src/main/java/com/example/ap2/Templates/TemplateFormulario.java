package com.example.ap2.Templates;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ap2.Dao.UsuariosDao;
import com.example.ap2.Model.Usuario;

public abstract class TemplateFormulario extends AppCompatActivity {

    @Override
    public abstract boolean onCreateOptionsMenu(Menu menu);

    @Override
    public abstract boolean onOptionsItemSelected(@NonNull MenuItem item);

    protected abstract void alert(String message);

    public abstract void initComponents();

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

    protected abstract void abrirAreaAlunos();

    protected abstract void abrirAreaProdutos();

    protected abstract boolean validarEmailESenha();

    protected abstract void limparInputs();


}
