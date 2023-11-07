package com.example.sqlite_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarActivity extends AppCompatActivity {
    EditText userName, password, repassword;
    Button singUp;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.userPassword);
        repassword = findViewById(R.id.userPasswordConfirm);

        singUp = findViewById(R.id.btnRegister);

        DB =new DBHelper(this);

        //metodo para registro

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String repass = repassword.getText().toString().trim();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(RegistrarActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)){
                        Boolean checkUser = DB.checkUserName(user);
                        if (checkUser == false){
                            Boolean insert = DB.insertarDatos(user, pass);
                            if (insert == true){
                                Toast.makeText(RegistrarActivity.this, "Usuario agregado correctamente", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegistrarActivity.this, "Error al agregar usuario", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegistrarActivity.this, "Usuario ya existente", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegistrarActivity.this, "Contraseñas no coinciden :(", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}


//https://www.youtube.com/watch?v=yJ02XTKiuAc&ab_channel=CodingWithMe