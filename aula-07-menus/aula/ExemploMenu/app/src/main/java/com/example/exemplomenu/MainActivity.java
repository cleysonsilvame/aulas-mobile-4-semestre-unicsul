package com.example.exemplomenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int opcao = item.getItemId();

        if (opcao == R.id.idopc1) {
            Intent intent = new Intent(this, Sobre.class);
            startActivity(intent);
        }

        if (opcao == R.id.idopc2) {
            finish();
        }

        if (opcao == R.id.idopc2) {

        }

        return super.onOptionsItemSelected(item);
    }
}