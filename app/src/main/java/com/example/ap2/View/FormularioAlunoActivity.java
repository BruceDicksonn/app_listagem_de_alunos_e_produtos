package com.example.ap2.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ap2.Controller.AlunoController;
import com.example.ap2.Dao.UsuariosDao;
import com.example.ap2.Model.Aluno;
import com.example.ap2.Dao.AlunoDao;
import com.example.ap2.Model.Usuario;
import com.example.ap2.R;
import com.example.ap2.Templates.TemplateFormulario;

import java.util.ArrayList;

public class FormularioAlunoActivity extends TemplateFormulario {

    private EditText editNome, editTelefone, editEmail;
    private Button btnSalvar;
    private final AlunoDao alunoDao = new AlunoDao();
    private Usuario usuarioLogado = new Usuario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_aluno);
        initComponents();

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Novo Aluno");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // pegando os dados passados pela tela principal
        Bundle bundle = getIntent().getExtras();
        String emailUsuario = (String) bundle.get("email");
        String senhaUsuario = (String) bundle.get("senha");

        //recuperando o usuario logado
        usuarioLogado = getUsuarioLogado(emailUsuario,senhaUsuario);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(verificarInputs()){

                    String nome = editNome.getText().toString();
                    String email = editEmail.getText().toString();
                    String fone = editTelefone.getText().toString();

                    Aluno alunoCriado = new Aluno(nome,fone,email);
                    alunoCriado.setIdDoCriador(usuarioLogado.getIdUsuario());

                    alunoDao.salvar(alunoCriado);

                    // atualiza a lista de alunos cadastrados por esse usuário
                    usuarioLogado.setAlunoSalvos(AlunoController.getAlunos(usuarioLogado));

                    //atualiza os dados de um Usuario existente no UsuariosDao
                    UsuariosDao.atualizarListaDeDadosUsuario(usuarioLogado);

                    alert("Aluno salvo com sucesso!");

                    abrirAreaAlunos();

                }

            }
        });

    }

    @Override
    protected void abrirAreaAlunos(){

        Intent intent = new Intent(this,ListaAlunos.class);
        Bundle args = new Bundle();

        args.putString("email",usuarioLogado.getEmail());
        args.putString("senha",usuarioLogado.getSenha());

        intent.putExtras(args);

        startActivity(intent);

    }

    @Override
    protected boolean verificarInputs(){

        String n = editNome.getText().toString();
        String e = editEmail.getText().toString();
        String t = editTelefone.getText().toString();

        if(n.isEmpty() && e.isEmpty() && t.isEmpty()){
            alert("Preencha os campos vazios!");
            return false;
        } else if(n.isEmpty()){
            alert("Preencha o campo de nome");
            return false;
        } else if(e.isEmpty()){
            alert("Preencha o campo de email");
            return false;
        } else if(t.isEmpty()){
            alert("Preencha o campo de telefone");
            return false;
        } else if(!e.contains("@gmail.com")){
            alert("Digite um email válido");
            return false;
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                abrirAreaAlunos();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initComponents(){
        editNome = findViewById(R.id.edit_nome);
        editTelefone = findViewById(R.id.edit_telefone);
        editEmail = findViewById(R.id.edit_email);
        btnSalvar = findViewById(R.id.btn_salvar);
    }


    @Override
    protected void abrirAreaProdutos() {

    }

    @Override
    protected boolean validarEmailESenha() {
        return false;
    }

    @Override
    protected void limparInputs() {

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

}