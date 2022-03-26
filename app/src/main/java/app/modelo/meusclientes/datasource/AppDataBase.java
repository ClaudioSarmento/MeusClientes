package app.modelo.meusclientes.datasource;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.modelo.meusclientes.api.AppUtil;
import app.modelo.meusclientes.datamodel.ClienteDataModel;
import app.modelo.meusclientes.model.Cliente;


public class AppDataBase extends SQLiteOpenHelper {

    public static final String DB_NAME =  "MeusClientes.sqlite";
    public static final int DB_VERSION = 1;
    SQLiteDatabase db;

    public AppDataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        Log.d(AppUtil.TAG,"AppDataBase: Criando Banco de Dados");
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ClienteDataModel.criarTabela());
        Log.d(AppUtil.TAG,"onCreate: Tabela Cliente "+ ClienteDataModel.criarTabela());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    /**
     * Método para incluir dados no banco de dados
     * @return
     */
    public boolean insert(String tabela, ContentValues dados){
        boolean retorno = false;
        db = getWritableDatabase();
        try{
            retorno = db.insert(tabela,null,dados) > 0 ;
        }catch (Exception e){
            Log.e(AppUtil.TAG,e.getMessage());
        }finally {
            //db.close();
        }
        return retorno;
    }

    /**
     * Método para deletar dados no banco de dados
     */
    public boolean deleteById(String tabela, int id){
        boolean retorno = false;
        db = getWritableDatabase();
        try{
            retorno = db.delete(tabela,"id = ?",new String[] {String.valueOf(id)}) > 0;
        }catch(Exception e){
            Log.e(AppUtil.TAG,e.getMessage());
        }finally {
           // db.close();
        }
        return retorno;
    }

    /**
     * Método para alterar dados no banco de dados
     */
    public boolean update(String tabela, ContentValues dados){
        boolean retorno = false;
        db = getWritableDatabase();
        try{
            retorno = db.update(tabela, dados,"id = ?", new String[] {String.valueOf(dados.get("id"))}) > 0 ;
        }catch (Exception e){
            Log.e(AppUtil.TAG,e.getMessage());
        }finally {
            //db.close();
        }
        return retorno;
    }

    /**
     * Método para listar dados no banco de dados
     */
    public List<Cliente> getAllClientes(String tabela){
        db = getReadableDatabase();

        List<Cliente> clientes = new ArrayList<>();
        Cliente obj;
        String sql = "SELECT * FROM "+tabela;

        Cursor cursor; // recebe os dados da consulta

        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do{
                obj = new Cliente();
                obj.setId(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.ID)));
                obj.setNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.NOME)));
                obj.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));
                clientes.add(obj);

            }while (cursor.moveToNext());
        }

        return clientes;
    }

}
