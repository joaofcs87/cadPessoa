package br.senac.pi.cadastropessoa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.senac.pi.cadastropessoa.domain.PessoaDB;

public class MainActivity extends AppCompatActivity {

    //joaoCod
    PessoaDB pessoaDB;
    SQLiteDatabase db;
    private String sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //joaoCod - instanciando PessoaDB
        pessoaDB = new PessoaDB(this);

        findViewById(R.id.btnSalvar).setOnClickListener(cadastrarPessoa());
        findViewById(R.id.btnListar).setOnClickListener(listarPessoa());

    }

    private View.OnClickListener cadastrarPessoa() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //abrindo o banco
                db = pessoaDB.getWritableDatabase();

                EditText edtNome = (EditText) findViewById(R.id.edtNome);
                EditText edtSobrenome = (EditText) findViewById(R.id.edtSobrenome);
                RadioGroup radioSexo = (RadioGroup) findViewById(R.id.radioSexo);
                RadioButton radioFeminino = (RadioButton) findViewById(R.id.radioFeminino);
                RadioButton radioMasculino = (RadioButton) findViewById(R.id.radioMasculino);

                boolean checkedMasculino = radioMasculino.isChecked();
                boolean checkedFeminino = radioFeminino.isChecked();

                if(checkedFeminino){
                    sexo = "Feminino";
                }else if (checkedMasculino){
                    sexo = "Masculino";
                }else {
                    sexo = "Indefinido";
                }


                ContentValues values = new ContentValues();

                values.put("nome", edtNome.getText().toString());
                values.put("sobrenome", edtSobrenome.getText().toString());
                values.put("sexo", sexo);

                long inserir = db.insert("pessoas", null, values);

                if (inserir != 0) {
                    Toast.makeText(getApplicationContext(), R.string.cad_ok, Toast.LENGTH_LONG).show();

                    edtNome.setText("");
                    edtSobrenome.setText("");
                    edtNome.requestFocus();

                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.cad_erro), Toast.LENGTH_LONG).show();
                }

            }
        };
    }

    private View.OnClickListener listarPessoa(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListaPessoaActivity.class);
                startActivity(intent);
            }
        };
    }

}
