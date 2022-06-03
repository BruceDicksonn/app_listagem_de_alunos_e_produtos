package com.example.ap2.View;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ap2.Dao.ProdutoDao;
import com.example.ap2.Dao.UsuariosDao;
import com.example.ap2.Model.Aluno;
import com.example.ap2.Dao.AlunoDao;
import com.example.ap2.Model.Produto;
import com.example.ap2.Model.Usuario;
import com.example.ap2.R;

public class FormularioProduto extends AppCompatActivity {

    private EditText editNome, editDescricao, editPreco;
    private Button btnSalvarProduto;
    private final ProdutoDao produtoDao = new ProdutoDao();
    private Usuario usuarioLogado = new Usuario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_produto);
        initComponents();

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Novo Produto");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // pegando os dados passados pela tela principal
        Bundle bundle = getIntent().getExtras();
        String nomeUsuario = (String) bundle.get("nome");
        String emailUsuario = (String) bundle.get("email");
        String senhaUsuario = (String) bundle.get("senha");

        //recuperando o usuario logado
        usuarioLogado = getUsuarioLogado(emailUsuario,senhaUsuario);


        btnSalvarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(verificarInputs()){

                    String nome = editNome.getText().toString();
                    String desc = editDescricao.getText().toString();
                    String preco = editPreco.getText().toString();

                    Produto produtoCriado = new Produto(nome, desc , Double.parseDouble(preco));
                    produtoDao.salvar(produtoCriado);

                    alert("Produto salvo com sucesso!");

                    finish();

                }

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

    private boolean verificarInputs(){

        String n = editNome.getText().toString();
        String d = editDescricao.getText().toString();
        String p = editPreco.getText().toString();

        if(n.isEmpty() && d.isEmpty() && p.isEmpty()){
            alert("Preencha os campos vazios!");
            return false;
        } else if(n.isEmpty()){
            alert("Preencha o campo de nome");
            return false;
        } else if(d.isEmpty()){
            alert("Preencha o campo de descrição");
            return false;
        } else if(p.isEmpty()){
            alert("Preencha o campo de preço");
            return false;
        }


        return true;
    }

    private void alert(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initComponents(){
        editNome = findViewById(R.id.edit_nomeProduto);
        editDescricao = findViewById(R.id.edit_descricaoProduto);
        editPreco = findViewById(R.id.edit_precoProduto);
        btnSalvarProduto = findViewById(R.id.btn_salvarProduto);
    }
}