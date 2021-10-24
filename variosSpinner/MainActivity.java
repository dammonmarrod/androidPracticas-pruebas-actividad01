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
    private Spinner sp1;
    private Spinner sp2;
    private ArrayAdapter<CharSequence> adapterDivisas;
    private ArrayAdapter<CharSequence> adapterEuro;


    /*Valores de las divisas*/
    private  double dolar = 1.16010;;
    private double libra = 0.84380;
    private double yen = 132.55;
    private double canadiense = 1.43510 ;
    private double australiano = 1.56290 ;
    private double sol = 4.5626 ;
    private  double peso = 115.01 ;
    private  double rupia = 87.035 ;
    private  double yuan = 7.4654;
    private  double shequel = 3.7383 ;


    /*Variable para calcular el cambio*/
    private double conversion;

    /*Variables para saber la selección de los Spinner*/
    private String selectionItem1;
    private String selectionItem2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Utilizar el Spinner con la lista desplegable del tipo de monedas*/
        /*El adapter es el mismo en el caso de querer mostrar todas las monedas*/
        adapterDivisas = ArrayAdapter.createFromResource(this,
                R.array.divisas_array, android.R.layout.simple_spinner_item);
        adapterDivisas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /*Muestro el mismo adapter en los dos spinner*/
        sp1 = (Spinner)findViewById(R.id.spMoneda);
        sp1.setAdapter(adapterDivisas);//Mostramos el spinner con el array con los valores
        sp1.setOnItemSelectedListener(this);
        //Por defecto el Item seleccionado es Euro
        /*Evitamos el error de que no se seleccione el primer Spinner en ningún momento por el usuario y no se registra*/
        selectionItem1="Euro";

        sp2 = (Spinner)findViewById(R.id.spMoneda2);
        sp2.setAdapter(adapterDivisas);//Mostramos el spinner con el array con los valores
        sp2.setOnItemSelectedListener(this); //Es un escuchador de Eventos???
    }//Fin onCreate

    /*Asociado a la clase Spinner*/
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        /*Obtengo el item seleccionado del Spinner 1*/
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectionItem1= parent.getItemAtPosition(position).toString();//Obtengo el valor seleccionado
                Toast.makeText(getApplicationContext(), "Seleccion spinner1: " + selectionItem1, Toast.LENGTH_LONG).show();
            }
            // Debido a que el AdapterView es una abstract class, onNothingSelected debe ser tambien definido
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /*Obtengo el item del Spinner 2*/
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectionItem2= parent.getItemAtPosition(position).toString();//Obtengo el valor seleccionado
                Toast.makeText(getApplicationContext(), "Seleccion spinner2: " + selectionItem2, Toast.LENGTH_LONG).show();
            }
            // Debido a que el AdapterView es una abstract class, onNothingSelected debe ser tambien definido
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }//Fin onItemSelected()

    /*Asociado a la clase Spinner*/
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {//Si no se ha seleccionado nada

    }//Fin onNothingSelected()

    /*Boton para convertir las monedas de un tipo a otro y mostrarlo */
    public void btConvertir(View view) {
        /*Obtenemos el valor introducido*/
        editText= (EditText) findViewById(R.id.etMonedaIntroducida);
        textView = (TextView)findViewById(R.id.tvResultado);

        /*Verificamos que se introdujo una cantidad*/
        if(!editText.getText().toString().isEmpty()){/*En caso de que no este vacio*/
            Toast.makeText(getApplicationContext(), "Cantidad introducida: " + editText.getText().toString(), Toast.LENGTH_LONG).show();

            /*Obtengo el item seleccionado del Spinner 1*/
            sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectionItem1= parent.getItemAtPosition(position).toString();//Obtengo el valor seleccionado
                    Toast.makeText(getApplicationContext(), "Seleccion spinner1: " + selectionItem1, Toast.LENGTH_LONG).show();

                    /*Muestro en el Spinner2 todas las monedas*/
                    sp2.setAdapter(adapterDivisas);//Mostramos el spinner con el array con los valores

                    /*Borramos la conversión anterior*/
                    textView.setText("0.0" );
                }
                // Debido a que el AdapterView es una abstract class, onNothingSelected debe ser tambien definido
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            boolean seleccionEuro=selectionItem1.equalsIgnoreCase("Euro");//El Euro esta seleccionado

            Toast.makeText(getApplicationContext(), "¿Euro Seleccionado? " + seleccionEuro, Toast.LENGTH_LONG).show();

            if(seleccionEuro){//Si ha introducido Euros en el primer Spinner

                Toast.makeText(getApplicationContext(), "Euro Dentro del if shwitch item", Toast.LENGTH_LONG).show();
                //Obtengo el valor introducido en el EditText por el usuario
                double entradaEuros=Double.parseDouble(editText.getText().toString());

                Toast.makeText(getApplicationContext(), "¿Moneda seleccionada Spinner2? " + selectionItem2, Toast.LENGTH_LONG).show();


                /*Switch-case para realizar el cambio según la opción seleccionada*/
                switch(selectionItem2) {
                    case "Dolar USD" :
                        conversion= entradaEuros*dolar;
                        break;
                    case "Libra esterlina GBP" :
                        conversion= entradaEuros*libra;
                        break;
                    case "Yen japones JPY" :
                        conversion= entradaEuros*yen;
                        break;
                    case "Dólar Canadiense CAD" :
                        conversion= entradaEuros*canadiense;
                        break;
                    case "Dólar Australiano AUD" :
                        conversion= entradaEuros*australiano;
                        break;
                    case "Sol peruano PEN" :
                        conversion= entradaEuros*sol;
                        break;
                    case "Peso argentino ARS" :
                        conversion= entradaEuros*peso;
                        break;
                    case "Rupia India INR" :
                        conversion= entradaEuros*rupia;
                        break;
                    case "Yuan chino CNY" :
                        conversion= entradaEuros*yuan;
                        break;
                    case "Shequel israelí ILS" :
                        conversion= entradaEuros*shequel;
                        break;
                    case "Euro" :
                        Toast.makeText(getApplicationContext(), "No es posible convertir Euro a Euro", Toast.LENGTH_LONG).show();
                        break;
                }

                /*Mostramos en el textView la moneda convertida*/
                textView.setText(Double.toString(conversion) );

            }else{//Si en el primer Spinner NO SE HA SELECCIONADO EURO
                /*Muestro en el Spinner2 solo la opción de euro*/
                adapterEuro = ArrayAdapter.createFromResource(this,
                        R.array.euro, android.R.layout.simple_spinner_item);
                adapterEuro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp2.setAdapter(adapterEuro);//Mostramos el spinner con el array con los valores


                Toast.makeText(getApplicationContext(), "No se ha seleccionado Euro", Toast.LENGTH_LONG).show();
                //Obtengo el valor introducido en el EditText por el usuario
                double entradaMonedaExtranjera=Double.parseDouble(editText.getText().toString());

                /*Switch-case para realizar el cambio según la opción seleccionada*/
                switch(selectionItem1) {
                    case "Dolar USD" :
                        conversion= entradaMonedaExtranjera/dolar;
                        break;
                    case "Libra esterlina GBP" :
                        conversion= entradaMonedaExtranjera/libra;
                        break;
                    case "Yen japones JPY" :
                        conversion= entradaMonedaExtranjera/yen;
                        break;
                    case "Dólar Canadiense CAD" :
                        conversion= entradaMonedaExtranjera/canadiense;
                        break;
                    case "Dólar Australiano AUD" :
                        conversion= entradaMonedaExtranjera/australiano;
                        break;
                    case "Sol peruano PEN" :
                        conversion= entradaMonedaExtranjera/sol;
                        break;
                    case "Peso argentino ARS" :
                        conversion= entradaMonedaExtranjera/peso;
                        break;
                    case "Rupia India INR" :
                        conversion= entradaMonedaExtranjera/rupia;
                        break;
                    case "Yuan chino CNY" :
                        conversion= entradaMonedaExtranjera/yuan;
                        break;
                    case "Shequel israelí ILS" :
                        conversion= entradaMonedaExtranjera/shequel;
                        break;
                }
                /*Mostramos en el textView la moneda convertida*/
                textView.setText(Double.toString(conversion) );

            }// if(seleccionEuro)

        } else {//En caso de que no se haya introducido ninguna cantidad en el editText
            Toast.makeText(getApplicationContext(), "Ingresa la cantidad a convertir en Euros.", Toast.LENGTH_LONG).show();
        }

    }
}
