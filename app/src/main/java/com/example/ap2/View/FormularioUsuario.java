package com.example.ap2.View;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ap2.Dao.UsuariosDao;
import com.example.ap2.Model.Aluno;
import com.example.ap2.Dao.AlunoDao;
import com.example.ap2.Model.Usuario;
import com.example.ap2.R;
import com.example.ap2.Templates.TemplateFormulario;

public class FormularioUsuario extends TemplateFormulario {

    private EditText editNome, editSenha, editEmail;
    private ImageView iconSenha;
    private Button btncadastro;
    private final UsuariosDao dao = new UsuariosDao();
    private Usuario usuarioCadastrado = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);
        initComponents();

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Novo Usuário");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btncadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(verificarInputs()){

                    String nome = editNome.getText().toString();
                    String email = editEmail.getText().toString();
                    String senha = editSenha.getText().toString();

                    Usuario usuarioCriado = new Usuario(nome,email,senha);
                    usuarioCadastrado = usuarioCriado;

                    dao.salvar(usuarioCriado);

                    //alert("Usuário salvo com sucesso!");
                    abrirTelaPrincipal();

                }

            }
        });

        // inicializar com senha escondida
        iconSenha.setImageDrawable(getResources().getDrawable(R.drawable.olho_fechado));
        editSenha.setInputType(129);

        iconSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int type = 0;

                Drawable olhoAberto  = getResources().getDrawable(R.drawable.olho_aberto);
                Drawable olhoFechado = getResources().getDrawable(R.drawable.olho_fechado);

                type = editSenha.getInputType() == 129 ? 144 : 129;

                if(type == 129) {
                    iconSenha.setImageDrawable(olhoFechado);
                } else {
                    iconSenha.setImageDrawable(olhoAberto);
                }

                editSenha.setInputType(type);

            }
        });

    }

    @Override
    protected void abrirTelaPrincipal(){

        Intent intent = new Intent(this, Principal.class);
        Bundle args = new Bundle();

        String  n = usuarioCadastrado.getNome(),
                e = editEmail.getText().toString(),
                s = editSenha.getText().toString();


        args.putString("nome",n);
        args.putString("email",e);
        args.putString("senha",s);

        intent.putExtras(args);

        startActivity(intent);
        finish();


    }

    @Override
    protected void abrirFormularioLogin(){

        startActivity(new Intent(this, FormularioLogin.class));
        finish();

    }

    @Override
    protected boolean verificarInputs(){

        String n = editNome.getText().toString();
        String e = editEmail.getText().toString();
        String s = editSenha.getText().toString();

        if(n.isEmpty() && e.isEmpty() && s.isEmpty()){
            alert("Preencha os campos vazios!");
            return false;
        } else if(n.isEmpty()){
            alert("Preencha o campo de nome");
            return false;
        } else if(e.isEmpty()){
            alert("Preencha o campo de email");
            return false;
        } else if(s.isEmpty()){
            alert("Preencha o campo de senha");
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
                abrirFormularioLogin();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initComponents(){

        editNome = findViewById(R.id.editNomeUsuario);
        editEmail = findViewById(R.id.editEmailUsuario);
        editSenha = findViewById(R.id.editSenhaUsuario);
        btncadastro = findViewById(R.id.btnCadastrarUsuario);
        iconSenha = findViewById(R.id.iconSenhaUsuario);

    }


    @Override
    protected void abrirFormularioCadastro() {

    }

    @Override
    protected void abrirAreaAlunos() {

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

}