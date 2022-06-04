package com.example.ap2.View;

import android.content.Intent;
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

import com.example.ap2.Controller.AlunoController;
import com.example.ap2.Controller.ProdutoController;
import com.example.ap2.Dao.ProdutoDao;
import com.example.ap2.Dao.UsuariosDao;
import com.example.ap2.Model.Aluno;
import com.example.ap2.Dao.AlunoDao;
import com.example.ap2.Model.Produto;
import com.example.ap2.Model.Usuario;
import com.example.ap2.R;
import com.example.ap2.Templates.TemplateFormulario;

public class FormularioProduto extends TemplateFormulario {

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
                    produtoCriado.setIdDoCriador(usuarioLogado.getIdUsuario());

                    produtoDao.salvar(produtoCriado);

                    // atualiza a lista de produtos cadastrados por esse usuário
                    usuarioLogado.setProdutosSalvos(ProdutoController.getProdutos(usuarioLogado));

                    //atualiza os dados de um Usuario existente no UsuariosDao
                    UsuariosDao.atualizarListaDeDadosUsuario(usuarioLogado);

                    alert("Produto salvo com sucesso!");

                    abrirAreaProdutos();

                }

            }
        });

    }

    @Override
    protected void abrirAreaProdutos(){
        Intent intent = new Intent(this,ListaProdutos.class);
        Bundle args = new Bundle();

        args.putString("email",usuarioLogado.getEmail());
        args.putString("senha",usuarioLogado.getSenha());

        intent.putExtras(args);

        startActivity(intent);

    }

    @Override
    protected boolean verificarInputs(){

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initComponents(){
        editNome = findViewById(R.id.edit_nomeProduto);
        editDescricao = findViewById(R.id.edit_descricaoProduto);
        editPreco = findViewById(R.id.edit_precoProduto);
        btnSalvarProduto = findViewById(R.id.btn_salvarProduto);
    }

    @Override
    protected void abrirTelaPrincipal() {

    }

    @Override
    protected void abrirFormularioCadastro() {

    }

    @Override
    protected void abrirFormularioLogin() {

    }

    @Override
    protected void abrirAreaAlunos() {

    }

    @Override
    protected void limparInputs() {

    }

    @Override
    protected boolean validarEmailESenha() {
        return false;
    }

}