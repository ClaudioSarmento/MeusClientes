package app.modelo.meusclientes.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.util.List;

import app.modelo.meusclientes.api.AppUtil;
import app.modelo.meusclientes.datamodel.ClienteDataModel;
import app.modelo.meusclientes.datasource.AppDataBase;
import app.modelo.meusclientes.model.Cliente;


public class ClienteController extends AppDataBase implements ICrud<Cliente>{
    ContentValues dadoDoOjeto;

    public ClienteController(Context context) {
        super(context);
        Log.d(AppUtil.TAG,"ClienteController: Conectado");
    }

    @Override
    public boolean incluir(Cliente obj) {

        dadoDoOjeto = new ContentValues();
        // Key, Value

        //dadoDoOjeto.put(ClienteDataModel.ID,obj.getId());
        dadoDoOjeto.put(ClienteDataModel.NOME,obj.getNome());
        dadoDoOjeto.put(ClienteDataModel.EMAIL,obj.getEmail());
        return insert(ClienteDataModel.TABELA, dadoDoOjeto);

    }

    @Override
    public boolean alterar(Cliente obj) {
        dadoDoOjeto = new ContentValues();
        // Key, Value

        dadoDoOjeto.put(ClienteDataModel.ID,obj.getId());
        dadoDoOjeto.put(ClienteDataModel.NOME,obj.getNome());
        dadoDoOjeto.put(ClienteDataModel.EMAIL,obj.getEmail());
        return update(ClienteDataModel.TABELA,dadoDoOjeto);
    }

    @Override
    public boolean deletar(int id){
        return deleteById(ClienteDataModel.TABELA,id);
    }

    @Override
    public List<Cliente> listar() {
        return getAllClientes(ClienteDataModel.TABELA);
    }


}
