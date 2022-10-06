package com.example.exercicio08;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    public static final String TABLE = "contatos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


    public void inserir(View v) {
        HelperDB helperDB = null;
        SQLiteDatabase writableDatabase = null;
        try {
            helperDB = new HelperDB(getApplicationContext());
            writableDatabase = helperDB.getWritableDatabase();

            EditText nome = (EditText) findViewById(R.id.nome);
            EditText celular = (EditText) findViewById(R.id.cel);
            EditText email = (EditText) findViewById(R.id.email);

            String nomeValue = nome.getText().toString();
            String celularValue = celular.getText().toString();
            String emailValue = email.getText().toString();


            if (nomeValue.isEmpty() || celularValue.isEmpty() || emailValue.isEmpty()) {
                showToast("Por favor, preencha os dados.");
                return;
            }

            ContentValues contentValues = new ContentValues();
            contentValues.put("nome", nomeValue);
            contentValues.put("celular", celularValue);
            contentValues.put("email", emailValue);

            long id = writableDatabase.insert(TABLE, null, contentValues);

            if (id == -1) {
                showToast("Não foi possível inserir");
                return;
            }

            showToast("Inserido com sucesso.");
        } catch (Exception ex) {
            showToast("Erro ao inserir no banco dados");
        } finally {
            if (writableDatabase != null) writableDatabase.close();
            if (helperDB != null) helperDB.close();
        }
    }

    public void listar(View v) {
        HelperDB helperDB = null;
        SQLiteDatabase readableDatabase = null;

        String output = "\nContatos cadastrados:\n\n";

        try {
            helperDB = new HelperDB(this);
            readableDatabase = helperDB.getReadableDatabase();

            Cursor cursor = readableDatabase.query(TABLE,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );

            while (cursor.moveToNext()) {
                String nome = cursor.getString(0);
                String celular = cursor.getString(1);
                String email = cursor.getString(2);

                output += MessageFormat.format("{0}, {1}, {2}\n\n", nome, celular, email);
            }

            TextView lista = ((TextView) findViewById(R.id.lista));
            lista.setText(output);
        } catch (Exception ex) {
            showToast("Erro ao ler o banco de dados");
        } finally {
            if (readableDatabase != null) readableDatabase.close();
            if (helperDB != null) helperDB.close();
        }
    }

    public void alterar(View v) {
        HelperDB helperDB = null;
        SQLiteDatabase writableDatabase = null;
        try {
            helperDB = new HelperDB(getApplicationContext());
            writableDatabase = helperDB.getWritableDatabase();

            EditText nome = (EditText) findViewById(R.id.nome);
            EditText celular = (EditText) findViewById(R.id.cel);
            EditText email = (EditText) findViewById(R.id.email);

            String nomeValue = nome.getText().toString();
            String celularValue = celular.getText().toString();
            String emailValue = email.getText().toString();


            if (nomeValue.isEmpty() || celularValue.isEmpty() || emailValue.isEmpty()) {
                showToast("Por favor, preencha os dados.");
                return;
            }

            ContentValues contentValues = new ContentValues();
            contentValues.put("celular", celularValue);
            contentValues.put("email", emailValue);

            String where = "nome LIKE ?";
            String[] whereArgs = { nomeValue };

            long id = writableDatabase.update(TABLE, contentValues, where, whereArgs);

            if (id == 0) {
                showToast("Não foi possível atualizar o contato: " + nomeValue);
                return;
            }

            showToast("Contato atualizado com sucesso.");
        } catch (Exception ex) {
            showToast("Erro ao atualizar no banco dados");
        } finally {
            if (writableDatabase != null) writableDatabase.close();
            if (helperDB != null) helperDB.close();
        }
    }

    public void excluir(View v) {
        HelperDB helperDB = null;
        SQLiteDatabase writableDatabase = null;
        try {
            helperDB = new HelperDB(getApplicationContext());
            writableDatabase = helperDB.getWritableDatabase();

            EditText nome = (EditText) findViewById(R.id.nome);

            String nomeValue = nome.getText().toString();


            if (nomeValue.isEmpty()) {
                showToast("Por favor, preencha o nome.");
                return;
            }

            String where = "nome LIKE ?";
            String[] whereArgs = { nomeValue };

            long id = writableDatabase.delete(TABLE,  where, whereArgs);

            if (id == 0) {
                showToast("Não foi possível deletar o contato: " + nomeValue);
                return;
            }

            showToast("Contato atualizado com sucesso.");
        } catch (Exception ex) {
            showToast("Erro ao atualizar no banco dados");
        } finally {
            if (writableDatabase != null) writableDatabase.close();
            if (helperDB != null) helperDB.close();
        }
    }
}
