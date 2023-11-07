package com.example.sqlite_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Calculadora extends AppCompatActivity {

    EditText num1, num2;
    TextView resultado;
    RadioButton radioSuma, radioResta, radioMultiplicacion, radioDivision;
    double  numero1, numero2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        //Vincular id XML
        num1 = (EditText) findViewById(R.id.txtNum1);
        num2 = (EditText) findViewById(R.id.txtNum2);

        radioSuma = (RadioButton) findViewById(R.id.radio_suma);
        radioResta = (RadioButton) findViewById(R.id.radio_resta);
        radioMultiplicacion = (RadioButton) findViewById(R.id.radio_multiplicacion);
        radioDivision = (RadioButton) findViewById(R.id.radio_division);

        resultado = (TextView) findViewById(R.id.txtResultado);
    }

    private double sumar (double numero1, double numero2){
        return numero1 + numero2;
    }

    private double restar (double numero1, double numero2){
        return numero1 - numero2;
    }

    private double multiplicar (double numero1, double numero2){
        return numero1 * numero2;
    }

    private double dividir (double numero1, double numero2){
        return numero1 / numero2;
    }

    public void onClick(View view){
        if (view.getId() == R.id.btnCalcular){
            validar();
        }
    }

    public void validar(){
        numero1= Double.parseDouble(num1.getText().toString());
        numero2= Double.parseDouble(num2.getText().toString());

        if (radioSuma.isChecked()){
            resultado.setText("El resultado de la suma es: " + sumar(numero1, numero2));
        } else if (radioResta.isChecked()){
            resultado.setText("El resultado de la resta es: " + restar(numero1, numero2));
        } else if (radioDivision.isChecked()){
            resultado.setText("El resultado de la división es: " + dividir(numero1, numero2));
        } else if (radioMultiplicacion.isChecked()){
            resultado.setText("El resultado de la multiplicación es: " + multiplicar(numero1, numero2));
        }
    }

}