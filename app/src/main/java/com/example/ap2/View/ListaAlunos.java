package com.example.ap2.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ap2.Dao.AlunoDao;
import com.example.ap2.Dao.UsuariosDao;
import com.example.ap2.Model.Aluno;
import com.example.ap2.Model.Usuario;
import com.example.ap2.R;
import com.example.ap2.Templates.TemplateListas;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunos extends TemplateListas {

    private ListView listViewAlunos;
    private FloatingActionButton actionButton;
    private static final AlunoDao dao = new AlunoDao();
    private Usuario usuarioLogado = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        initComponents();

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Lista Alunos");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // pegando os dados passados pelo login
        Bundle bundle = getIntent().getExtras();
        String nomeUsuario = (String) bundle.get("nome");
        String emailUsuario = (String) bundle.get("email");
        String senhaUsuario = (String) bundle.get("senha");

        //recuperando o usuario logado
        usuarioLogado = getUsuarioLogado(emailUsuario,senhaUsuario);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                abrirFormularioAluno();

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void abrirFormularioAluno(){

        Intent intent = new Intent(this,FormularioAlunoActivity.class);
        Bundle args = new Bundle();

        args.putString("email",usuarioLogado.getEmail());
        args.putString("senha",usuarioLogado.getSenha());

        intent.putExtras(args);
        startActivity(intent);

    }

    @Override
    protected void abrirFormularioProduto() {}

    @Override
    protected void abrirTelaPrincipal(){

        Intent intent = new Intent(this, Principal.class);
        Bundle args = new Bundle();

        String n = usuarioLogado.getNome(),
                e = usuarioLogado.getEmail(),
                s = usuarioLogado.getSenha();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_aluno, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void logout(){

        Intent intent = new Intent(this,FormularioLogin.class);
        Bundle args = new Bundle();

        args.putString("email",usuarioLogado.getEmail());
        args.putString("senha",usuarioLogado.getSenha());

        intent.putExtras(args);

        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                abrirTelaPrincipal();
                break;
            case R.id.menu_sair:
                logout();
                break;
            case R.id.menu_cadastrarAluno:
                abrirFormularioAluno();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        /*
        * A partir do momento em que abrirmos a activity Formulario, ela estará no topo da pilha das Activitys.
        * Mesmo dando um finish(), ela só deixará de ser mostrada, mas continuará no topo.
        * Ou seja, quando chamarmos o onResume(), estaremos retomando as funcionalidades da activity anterior,
        * logo teremos a lista com os dados cadastrados.
        *
        *
        * */


        configuraLista();

        super.onResume();
    }

    @Override
    protected void configuraLista(){
        //ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.getAlunos());

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usuarioLogado.toListaAluno());
        listViewAlunos.setAdapter(adapter);

    }

    @Override
    protected void initComponents(){
        listViewAlunos = findViewById(R.id.listViewAlunos);
        actionButton = findViewById(R.id.floatingActionButton);
    }
}