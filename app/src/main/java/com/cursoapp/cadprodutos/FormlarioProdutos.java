package com.cursoapp.cadprodutos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cursoapp.cadprodutos.BDHelper.ProdutosBd;
import com.cursoapp.cadprodutos.model.Proodutos;

public class FormlarioProdutos extends AppCompatActivity {
    EditText editText_NomeProd, editText_Descricao, editText_Quantidade;
    Button btn_Poliform;
    Proodutos editarProduto, produto;
    ProdutosBd bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formlario_produtos);

        produto = new Proodutos();
        bdHelper = new ProdutosBd(FormlarioProdutos.this);

        Intent intent = getIntent();
        editarProduto = (Proodutos) intent.getSerializableExtra("produto-escolhido");

        editText_NomeProd = (EditText) findViewById(R.id.editText_NomeProd);
        editText_Descricao = (EditText) findViewById(R.id.editText_Endereco);
        editText_Quantidade =(EditText) findViewById(R.id.editText_Quantidade);

        btn_Poliform = (Button) findViewById(R.id.btn_Poliform);

        if (editarProduto !=null){
            btn_Poliform.setText("Modificar Produto");

            editText_NomeProd.setText(editarProduto.getNomeProduto());
            editText_Descricao.setText(editarProduto.getDescricao());
            editText_Quantidade.setText(editarProduto.getQuantidade()+"");

            produto.setId(editarProduto.getId());


        }else{
            btn_Poliform.setText("Cadastrar Novo Produto");
        }

        btn_Poliform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produto.setNomeProduto(editText_NomeProd.getText().toString());
                produto.setDescricao(editText_Descricao.getText().toString());
                produto.setQuantidade(Integer.parseInt(editText_Quantidade.getText().toString()));

                if(btn_Poliform.getText().toString().equals("Cadastrar Novo Produto")){
                    bdHelper.salvarProduto(produto);
                    bdHelper.close();
                }else{
                    bdHelper.alterarProduto(produto);
                    bdHelper.close();
                }
            }
        });
    }
}


