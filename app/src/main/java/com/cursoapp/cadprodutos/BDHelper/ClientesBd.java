package com.cursoapp.cadprodutos.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cursoapp.cadprodutos.model.Clientes;

import java.util.ArrayList;

public class ClientesBd extends SQLiteOpenHelper {
    private static final String DATABASE = "bdclientes";
    private static final int VERSION = 1;

    public ClientesBd(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cliente = "CREATE TABLE clientes(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nomecliente TEXT NOT NULL, endereco TEXT NOT NULL);";
        db.execSQL(cliente);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String cliente = "DROP TABLE IF EXISTS clientes";
        db.execSQL(cliente);
    }

    //CREATE
    public void salvarCliente(Clientes cliente){
        ContentValues values = new ContentValues();

        values.put("nomecliente", cliente.getNomeCliente());
        values.put("endereco", cliente.getEndereco());

        getWritableDatabase().insert("clientes", null, values);
    }

    //UPDATE
    public void alterarCliente(Clientes cliente){
        ContentValues values = new ContentValues();

        values.put("nomecliente", cliente.getNomeCliente());
        values.put("endereco", cliente.getEndereco());

        String [] args = {cliente.getId().toString()};
        getWritableDatabase().update("clientes",values,"id=?",args);

    }

    //DELETE
    public void deletarClientes(Clientes cliente){
        String [] args = {cliente.getId().toString()};
        getWritableDatabase().delete("clientes","id=?",args);
    }

    //READ
    public ArrayList<Clientes> getLista(){
        String [] columns = {"id", "nomecliente", "endereco"};
        Cursor cursor = getWritableDatabase().query("clientes",columns, null, null,null,null,null,null );
        ArrayList<Clientes> clientes = new ArrayList<Clientes>();

        while (cursor.moveToNext()){
            Clientes cliente = new Clientes();
            cliente.setId(cursor.getLong(0));
            cliente.setNomeCliente(cursor.getString(1));
            cliente.setEndereco(cursor.getString(2));

            clientes.add(cliente);
        }
        return clientes;
    }
}
