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
    /*Declaramos las variables a utilizar*/
    private TextView textView;
    private EditText editText;
    private Spinner sp;

    /*Variable para saber el valor del Spinner seleccionado*/
    private String item;
    /*Variable para calcular el cambio*/
    private double conversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Utilizar el Spinner con la lista desplegable del tipo de monedas*/
        sp = (Spinner)findViewById(R.id.spMoneda);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.divisas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);


    }

    /*Asociado a la clase Spinner*/
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Item del spinner seleccionado
        item = parent.getItemAtPosition(position).toString();

    }
    /*Asociado a la clase Spinner*/
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /*Boton para convertir las monedas de un tipo a otro y mostrarlo */
    public void btConvertir(View view) {
        /*Obtenemos el valor introducido*/
        editText= (EditText) findViewById(R.id.etMonedaIntroducida);


        /*Verificamos que se introdujo una cantidad*/
        if(!editText.getText().toString().isEmpty()){
            /*Switch-case para realizar el cambio según la opción seleccionada*/
            double entradaEuros=Double.parseDouble(editText.getText().toString());

            switch(item) {
                case "Dolar USD" :
                    double dolar = 1.16010;;
                    conversion= entradaEuros*dolar;
                    break;
                case "Libra esterlina GBP" :
                    double libra = 0.84380;
                    conversion= entradaEuros*libra;
                    break;
                case "Yen japones JPY" :
                    double yen = 132.55;
                    conversion= entradaEuros*yen;
                    break;
                case "Dólar Canadiense CAD" :
                    double canadiense = 1.43510 ;
                    conversion= entradaEuros*canadiense;
                    break;
                case "Dólar Australiano AUD" :
                    double australiano = 1.56290 ;
                    conversion= entradaEuros*australiano;
                    break;
                case "Sol peruano PEN" :
                    double sol = 4.5626 ;
                    conversion= entradaEuros*sol;
                    break;
                case "Peso argentino ARS" :
                    double peso = 115.01 ;
                    conversion= entradaEuros*peso;
                    break;
                case "Rupia India INR" :
                    double rupia = 87.035 ;
                    conversion= entradaEuros*rupia;
                    break;
                case "Yuan chino CNY" :
                    double yuan = 7.4654;
                    conversion= entradaEuros*yuan;
                    break;
                case "Shequel israelí ILS" :
                    double shequel = 3.7383 ;
                    conversion= entradaEuros*shequel;
                    break;
            }

        } else {
            Toast.makeText(getApplicationContext(), "Ingresa la cantidad a convertir en Euros.", Toast.LENGTH_LONG).show();
        }


        /*Mostramos en el textView la moneda convertida*/
        textView = (TextView)findViewById(R.id.tvResultado);
        textView.setText(Double.toString(conversion) );

    }
