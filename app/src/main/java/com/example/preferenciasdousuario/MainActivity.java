package com.example.preferenciasdousuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    // Atributos
    private Button botaoSalvar;
    private TextInputEditText editNome;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoSalvar = findViewById(R.id.botaoSalvar);
        editNome = findViewById(R.id.editNome);
        textResultado = findViewById(R.id.textResultado);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("Arquivo_preferencia", 0);
                SharedPreferences.Editor editor = preferences.edit();

                //Validar o nome
                if ( editNome.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(), "Preencha o nome", Toast.LENGTH_SHORT ).show();
                }else {
                    String nome = editNome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit();
                    textResultado.setText("Olá " + nome);
                }
            }
        });

        //Recuperar dados Salvos
        SharedPreferences preferences = getSharedPreferences("Arquivo_preferencia", 0);

        //Validar se temos o nome em preferencias
        if ( preferences.contains("nome") ){
            String nome = preferences.getString("nome","Usuario nao definido");
            textResultado.setText("Ola, " + nome);
        }else {
            textResultado.setText("Ola, Usuario nao definido");
        }


    }
}