package com.example.aplicaciondivisas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Utilizar el Spinner con la lista desplegable del tipo de monedas*/
        Spinner sp = (Spinner) findViewById(R.id.spMoneda);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.divisas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /*Boton para convertir las monedas de un tipo a otro y mostrarlo */
    public void btConvertir(View view) {
        /*Obtenemos el valor introducido*/
        EditText editText= (EditText) findViewById(R.id.etMonedaIntroducida);
        double entradaEuros=Double.parseDouble(editText.getText().toString());
        double dolar = 1.16010;

        double conversion= entradaEuros*dolar;

        TextView textView = (TextView)findViewById(R.id.tvResultado);
        textView.setText(Double.toString(conversion));

    }
