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

import com.example.ap2.Dao.ProdutoDao;
import com.example.ap2.Dao.UsuariosDao;
import com.example.ap2.Model.Usuario;
import com.example.ap2.R;

import java.util.ArrayList;

public class ListaProdutos extends AppCompatActivity {

    private ListView listViewProdutos;
    private FloatingActionButton actionButton;
    private static final ProdutoDao dao = new ProdutoDao();
    private Usuario usuarioLogado = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        initComponents();

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Lista Produtos");
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

                abrirFormularioProduto();

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void abrirFormularioProduto(){

        Intent intent = new Intent(this,FormularioProduto.class);
        Bundle args = new Bundle();

        args.putString("email",usuarioLogado.getEmail());
        args.putString("senha",usuarioLogado.getSenha());

        intent.putExtras(args);
        startActivity(intent);

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

    private void abrirTelaPrincipal(){

        Intent intent = new Intent(this, Principal.class);
        Bundle args = new Bundle();

        String  n = usuarioLogado.getNome(),
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

    private void logout(){

        Intent intent = new Intent(this,FormularioLogin.class);
        Bundle args = new Bundle();

        args.putString("email",usuarioLogado.getEmail());
        args.putString("senha",usuarioLogado.getSenha());

        intent.putExtras(args);

        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_produto, menu);
        return super.onCreateOptionsMenu(menu);
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
            case R.id.menu_cadastrarProduto:
                abrirFormularioProduto();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*
         * A partir do momento em que abrirmos a activity Formulario, ela estará no topo da pilha das Activitys.
         * Mesmo dando um finish(), ela só deixará de ser mostrada, mas continuará no topo.
         * Ou seja, quando chamarmos o onResume(), estaremos retomando as funcionalidades da activity anterior,
         * logo teremos a lista com os dados cadastrados.
         *
         *
         * */
        configuraLista();
    }

    private void configuraLista(){
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usuarioLogado.toListaProduto());
        listViewProdutos.setAdapter(adapter);
    }

    private void initComponents(){
        listViewProdutos = findViewById(R.id.listViewProdutos);
        actionButton = findViewById(R.id.floatingActionButton);
    }
}