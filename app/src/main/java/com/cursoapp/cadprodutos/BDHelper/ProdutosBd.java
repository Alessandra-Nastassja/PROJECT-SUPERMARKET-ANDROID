package com.cursoapp.cadprodutos.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cursoapp.cadprodutos.model.Proodutos;

import java.util.ArrayList;

public class ProdutosBd extends SQLiteOpenHelper {
    private static final String DATABASE = "bdprodutos";
    private static final int VERSION = 1;

    public ProdutosBd(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String produto = "CREATE TABLE produtos(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nomeproduto TEXT NOT NULL, descricao TEXT NOT NULL, quantidade INTEGER);";
        db.execSQL(produto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String produto = "DROP TABLE IF EXISTS produtos";
        db.execSQL(produto);
    }

    //CREATE
    public void salvarProduto(Proodutos produto){
        ContentValues values = new ContentValues();

        values.put("nomeproduto", produto.getNomeProduto());
        values.put("descricao", produto.getDescricao());
        values.put("quantidade",produto.getQuantidade());

        getWritableDatabase().insert("produtos", null, values);
    }

    //UPDATE
    public void alterarProduto(Proodutos produto){
        ContentValues values = new ContentValues();

        values.put("nomeproduto",produto.getNomeProduto());
        values.put("descricao",produto.getDescricao());
        values.put("quantidade",produto.getQuantidade());

        String [] args = {produto.getId().toString()};
        getWritableDatabase().update("produtos",values,"id=?",args);

    }

    //DELETE
    public void deletarProduto(Proodutos produto){
        String [] args = {produto.getId().toString()};
        getWritableDatabase().delete("produtos","id=?",args);
    }

    //READ
    public ArrayList<Proodutos> getLista(){
        String [] columns = {"id", "nomeproduto", "descricao", "quantidade"};
        Cursor cursor = getWritableDatabase().query("produtos",columns, null, null,null,null,null,null );
        ArrayList<Proodutos> produtos = new ArrayList<Proodutos>();

        while (cursor.moveToNext()){
            Proodutos produto = new Proodutos();
            produto.setId(cursor.getLong(0));
            produto.setNomeProduto(cursor.getString(1));
            produto.setDescricao(cursor.getString(2));
            produto.setQuantidade(cursor.getInt(3));

            produtos.add(produto);
        }
        return produtos;
    }
}
