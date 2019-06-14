package com.cursoapp.cadprodutos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cursoapp.cadprodutos.BDHelper.ClientesBd;
import com.cursoapp.cadprodutos.model.Clientes;

public class FormularioClientes extends AppCompatActivity {
    EditText editText_NomeCliente, editText_Endereco;
    Button btn_Poliform;
    Clientes editarCliente, cliente;
    ClientesBd bdHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_clientes);

        cliente = new Clientes();
        bdHelper = new ClientesBd(FormularioClientes.this);

        Intent intent = getIntent();
        editarCliente = (Clientes) intent.getSerializableExtra("cliente-escolhido");

        editText_NomeCliente = (EditText) findViewById(R.id.editText_NomeCliente);
        editText_Endereco = (EditText) findViewById(R.id.editText_Endereco);

        btn_Poliform = (Button) findViewById(R.id.btn_Poliform);

        if (editarCliente !=null){
            btn_Poliform.setText("Modificar Cliente");

            editText_NomeCliente.setText(editarCliente.getNomeCliente());
            editText_Endereco.setText(editarCliente.getEndereco());

            cliente.setId(editarCliente.getId());


        }else{
            btn_Poliform.setText("Cadastrar Novo Cliente");
        }

        btn_Poliform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliente.setNomeCliente(editText_NomeCliente.getText().toString());
                cliente.setEndereco(editText_Endereco.getText().toString());

                if(btn_Poliform.getText().toString().equals("Cadastrar Novo Cliente")){
                    bdHelper.salvarCliente(cliente);
                    bdHelper.close();
                }else{
                    bdHelper.alterarCliente(cliente);
                    bdHelper.close();
                }
            }
        });

    }
}
