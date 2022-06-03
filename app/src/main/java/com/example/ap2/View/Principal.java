package com.example.ap2.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ap2.Dao.UsuariosDao;
import com.example.ap2.Model.Usuario;
import com.example.ap2.R;
import com.example.ap2.View.ListaAlunos;
import com.example.ap2.View.ListaProdutos;

public class Principal extends AppCompatActivity {

    private CardView aluno, produto;
    private Usuario usuarioLogado = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        initComponents();

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Principal");
        setSupportActionBar(toolbar);

        // pegando os dados passados pelo login
        Bundle bundle = getIntent().getExtras();
        String nomeUsuario = (String) bundle.get("nome");
        String emailUsuario = (String) bundle.get("email");
        String senhaUsuario = (String) bundle.get("senha");

        //recuperando o usuario logado
        usuarioLogado = getUsuarioLogado(emailUsuario,senhaUsuario);

        alert("Bem-vindo, " + nomeUsuario + " " + String.valueOf(usuarioLogado.getAlunoSalvos().size()));

        aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 abrirAreaAlunos();
            }
        });

        produto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirAreaProdutos();
            }
        });

    }

    private Usuario getUsuarioLogado(String email, String senha){

        Usuario retorno = new Usuario();

        for(Usuario user : UsuariosDao.getListaUsuarios()){
            if(user.getEmail().equals(email) && user.getSenha().equals(senha)){
                retorno = user;
            }
        }

        return retorno;

    }

    private void alert(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sair:
                logout();
            break;
            case R.id.menu_cadastrarAluno:
                abrirFormularioAluno();
            break;
            case R.id.menu_cadastrarProduto:
                abrirFormularioProduto();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout(){

        Intent intent = new Intent(this,FormularioLogin.class);
        Bundle args = new Bundle();

        args.putString("email",usuarioLogado.getEmail());
        args.putString("senha",usuarioLogado.getSenha());

        intent.putExtras(args);

        startActivity(intent);

    }

    private void abrirFormularioAluno(){
        Intent intent = new Intent(this,FormularioAlunoActivity.class);
        Bundle args = new Bundle();

        args.putString("email",usuarioLogado.getEmail());
        args.putString("senha",usuarioLogado.getSenha());

        intent.putExtras(args);

        startActivity(intent);
    }

    private void abrirFormularioProduto(){

        Intent intent = new Intent(this,FormularioProduto.class);
        Bundle args = new Bundle();

        args.putString("email",usuarioLogado.getEmail());
        args.putString("senha",usuarioLogado.getSenha());

        intent.putExtras(args);

        startActivity(intent);
    }

    private void abrirAreaAlunos(){

        Intent intent = new Intent(this,ListaAlunos.class);
        Bundle args = new Bundle();

        args.putString("email",usuarioLogado.getEmail());
        args.putString("senha",usuarioLogado.getSenha());

        intent.putExtras(args);

        startActivity(intent);

        finish();

    }

    private void abrirAreaProdutos(){
        Intent intent = new Intent(this,ListaProdutos.class);
        Bundle args = new Bundle();

        args.putString("email",usuarioLogado.getEmail());
        args.putString("senha",usuarioLogado.getSenha());

        intent.putExtras(args);

        startActivity(intent);
    }

    private void initComponents(){
        aluno = findViewById(R.id.cardAreaAluno);
        produto = findViewById(R.id.cardAreaProduto);
    }
}