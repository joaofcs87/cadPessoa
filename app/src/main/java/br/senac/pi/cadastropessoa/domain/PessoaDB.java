package br.senac.pi.cadastropessoa.domain;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Aluno on 23/11/2015.
 */
public class PessoaDB extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    private static final String NOME_BANCO = "pessoa.sqlite";
    private static final int VERSAO_BANCO = 1;

    public PessoaDB(Context context) {

        //context, nome_banco, factory, versão
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "Criação da tabela pessoas");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS pessoas(" +
                "_id integer primary key autoincrement," +
                "nome text," +
                "sobrenome text," +
                "sexo text)");

        Log.d(TAG, "Tabela criada!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



}
