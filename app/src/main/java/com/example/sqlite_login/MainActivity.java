package com.example.sqlite_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText userName, password;
    Button entrar, registarse;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.userPassword);

        entrar = findViewById(R.id.btnIngresar);
        registarse = findViewById(R.id.btnRegistarse);

        DB = new DBHelper(this);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(MainActivity.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUserPass = DB.checkUserPassword(user, pass);
                    if (checkUserPass == true){
                        Toast.makeText(MainActivity.this, "Acceso correcto", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Calculadora.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Acceso fallido :(", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), RegistrarActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Limpia los campos de texto al volver a esta actividad
        userName.setText("");
        password.setText("");
    }
}