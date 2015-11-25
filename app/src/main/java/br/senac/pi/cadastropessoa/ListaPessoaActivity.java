package br.senac.pi.cadastropessoa;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import javax.sql.DataSource;

import br.senac.pi.cadastropessoa.domain.PessoaDB;

public class ListaPessoaActivity extends AppCompatActivity {

    //joaoCod
    private CursorAdapter dataSource;
    private SQLiteDatabase database;
    private static final String dados[] = {"nome", "sobrenome"};
    ListView listView;
    PessoaDB pessoaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoa);

        //joaoCod
        pessoaDB = new PessoaDB(this);
        database = pessoaDB.getWritableDatabase();
        findViewById(R.id.btnExibir).setOnClickListener(exibirListaPessoa());
        listView = (ListView) findViewById(R.id.listViewPessoa);

    }

    //joaoCod
    private View.OnClickListener exibirListaPessoa(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor pessoas = database.query("pessoas", dados, null, null, null, null, null);

                if(pessoas.getCount() > 0){
                    dataSource = new SimpleCursorAdapter(ListaPessoaActivity.this, R.layout.row_pessoa, pessoas, dados, new int[]{R.id.txtViewNome, R.id.txtViewSobrenome});
                    listView.setAdapter(dataSource);
                }else{
                    Toast.makeText(getApplicationContext(),R.string.zero_registro,Toast.LENGTH_LONG).show();
                }
            }
        };
    }
}
