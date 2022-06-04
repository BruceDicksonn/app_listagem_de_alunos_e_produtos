package com.example.ap2.View;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ap2.Controller.AlunoController;
import com.example.ap2.Dao.AlunoDao;
import com.example.ap2.Dao.UsuariosDao;
import com.example.ap2.Model.Usuario;
import com.example.ap2.R;
import com.example.ap2.Templates.TemplateFormulario;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FormularioLogin extends TemplateFormulario {

    private TextView cadastrarUsuario;
    private EditText email,senha;
    private ImageView iconSenha;
    private Button btnLogin;
    private List<Usuario> listaUsuariosCadastrados = new ArrayList<>();
    private UsuariosDao dao = new UsuariosDao();
    private Usuario usuarioLogado = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_login);
        initComponents();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validarEmailESenha()){

                    abrirTelaPrincipal();
                    limparInputs();

                    }
                }
        });

        cadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFormularioCadastro();
            }
        });

        // inicializar com senha escondida
        iconSenha.setImageDrawable(getResources().getDrawable(R.drawable.olho_fechado));
        senha.setInputType(129);

        // podendo mudar em tempo de execução
        iconSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int type = 0;

                Drawable olhoAberto  = getResources().getDrawable(R.drawable.olho_aberto);
                Drawable olhoFechado = getResources().getDrawable(R.drawable.olho_fechado);

                type = senha.getInputType() == 129 ? 144 : 129;

                if(type == 129) {
                    iconSenha.setImageDrawable(olhoFechado);
                } else {
                    iconSenha.setImageDrawable(olhoAberto);
                }

                senha.setInputType(type);

            }
        });

    }

    @Override
    protected void onResume() {

        listaUsuariosCadastrados = dao.getListaUsuarios();
        usuarioLogado.setAlunoSalvos(AlunoController.getAlunos(usuarioLogado));

        super.onResume();
    }

    @Override
    protected void abrirFormularioCadastro(){

        startActivity(new Intent(this,FormularioUsuario.class));
        limparInputs();
        finish();

    }

    @Override
    protected boolean validarEmailESenha(){

        String e = email.getText().toString();
        String s = senha.getText().toString();

        boolean userEncontrado = false;
        boolean emailExistente = false;
        boolean senhaExistente = false;

        if(e.isEmpty() || s.isEmpty()){

            alert("Preencha os campos vazios!");
            return false;

        } else {

            for (int i = 0; i < listaUsuariosCadastrados.size(); i++) {

                    Usuario usuario = listaUsuariosCadastrados.get(i);

                    if(e.equals(usuario.getEmail())){
                        emailExistente = true;
                    }

                    if(s.equals(usuario.getSenha())){
                        senhaExistente = true;
                    }

                    if (emailExistente && senhaExistente ) {

                        usuarioLogado.setNome(usuario.getNome());
                        usuarioLogado.setEmail(usuario.getEmail());
                        usuarioLogado.setSenha(usuario.getSenha());

                        userEncontrado = true;

                        break;

                    } else if(emailExistente && !senhaExistente ){

                        emailExistente = true;
                        senhaExistente = false;

                    } else {

                        emailExistente = false;
                        senhaExistente = false;


                    }

            }

        }

        if(emailExistente && !senhaExistente){

            alert("Email ou senha estão incorretos");
            return false;

        } else {

            if (userEncontrado) {

                return true;

            } else {

                alert("Usuário inexistente");
                return false;

            }

        }

    }

    @Override
    protected void limparInputs(){
        email.setText("");
        senha.setText("");
        senha.clearFocus();
    }

    @Override
    protected void abrirTelaPrincipal(){

        Intent intent = new Intent(this, Principal.class);
        Bundle args = new Bundle();

        String n = usuarioLogado.getNome(),
               e = email.getText().toString(),
               s = senha.getText().toString();

        ArrayList<String> alunos = (ArrayList<String>) usuarioLogado.getAlunoSalvos();
        ArrayList<String> produtos = (ArrayList<String>) usuarioLogado.getProdutosSalvos();


        args.putString("nome",n);
        args.putString("email",e);
        args.putString("senha",s);
        args.putStringArrayList("alunosSalvos", alunos);
        args.putStringArrayList("produtosSalvos", produtos);

        intent.putExtras(args);

        startActivity(intent);


    }

    @Override
    protected void initComponents(){
        email = findViewById(R.id.txtEmail);
        senha = findViewById(R.id.txtSenha);
        iconSenha = findViewById(R.id.iconSenha);
        btnLogin = findViewById(R.id.btnLogin);
        listaUsuariosCadastrados = dao.getListaUsuarios();
        cadastrarUsuario = findViewById(R.id.cadastrarUsuario);
    }

    @Override
    protected boolean verificarInputs() {
        return false;
    }

    @Override
    protected void abrirAreaProdutos() {

    }

    @Override
    protected void abrirFormularioLogin() {

    }

    @Override
    protected void abrirAreaAlunos() {

    }
}