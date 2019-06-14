package com.cursoapp.cadprodutos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.cursoapp.cadprodutos.BDHelper.ClientesBd;
import com.cursoapp.cadprodutos.model.Clientes;

import java.util.ArrayList;

public class MainActivityClientes extends AppCompatActivity {

    ListView lista;
    ClientesBd bdHelper;
    ArrayList<Clientes> listview_Clientes;
    Clientes cliente;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_clientes2);

        Button btnCadastrar = (Button) findViewById(R.id.btn_Cadastrar);
        btnCadastrar.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityClientes.this, FormularioClientes.class);
                startActivity(intent);
            }
        });

        lista = (ListView) findViewById(R.id.listview_Clientes);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Clientes clienteEscolhido = (Clientes) adapter.getItemAtPosition(position);

                Intent i = new Intent(MainActivityClientes.this, FormularioClientes.class);
                i.putExtra("cliente-escolhido", clienteEscolhido);
                startActivity(i);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                cliente = (Clientes) adapter.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar Este Produto");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                bdHelper = new ClientesBd(MainActivityClientes.this);
                bdHelper.deletarClientes(cliente);
                bdHelper.close();
                carregarCliente();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarCliente();
    }

    public void carregarCliente(){
        bdHelper = new ClientesBd(MainActivityClientes.this);
        listview_Clientes = bdHelper.getLista();
        bdHelper.close();

        if (listview_Clientes != null){
            adapter = new ArrayAdapter<Clientes>(MainActivityClientes.this, android.R.layout.simple_list_item_1, listview_Clientes);
            lista.setAdapter(adapter);
        }
        //  finish();
    }
}
