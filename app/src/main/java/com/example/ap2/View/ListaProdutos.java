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
import com.example.ap2.R;

public class ListaProdutos extends AppCompatActivity {

    private ListView listViewProdutos;
    private FloatingActionButton actionButton;
    private static final ProdutoDao dao = new ProdutoDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        initComponents();

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Lista Produtos");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                abrirFormularioProduto();

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void abrirFormularioProduto(){
        startActivity(new Intent(getApplicationContext(), FormularioProduto.class));
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
                finish();
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

    private void logout(){
        startActivity(new Intent(this,FormularioLogin.class));
        finish();
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
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.getProdutos());
        listViewProdutos.setAdapter(adapter);
    }

    private void initComponents(){
        listViewProdutos = findViewById(R.id.listViewProdutos);
        actionButton = findViewById(R.id.floatingActionButton);
    }
}