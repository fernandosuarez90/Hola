package com.example.suarezpc.hola;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText Et_peso;
    private EditText Et_altura;
    private TextView tv_resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Et_peso = (EditText) findViewById(R.id.Et_peso);
        SharedPreferences prefe=getSharedPreferences("datos" , Context.MODE_PRIVATE);
        Et_peso.setText(prefe.getString("peso",""));





        Et_altura = (EditText) findViewById(R.id.Et_altura);
        SharedPreferences prefe2=getSharedPreferences("datos" , Context.MODE_PRIVATE);
        Et_altura.setText(prefe.getString("altura",""));



        tv_resultado = (TextView) findViewById(R.id.tv_resultado);

    }

    public void acercade(View view ){

        Intent i = new Intent(this, acercade.class);
        startActivity(i);

    }



        public void calcular(View view) {

            tv_resultado.setText("" + Et_peso.getText());

            String numerobase1 = Et_peso.getText().toString();
            String numeroaltura2 = Et_altura.getText().toString();
            DecimalFormat formateador  = new  DecimalFormat("0.00");


            if (numerobase1.equals("")|| numeroaltura2.equals("")) {
                this.Et_peso.setText("");
                this.Et_altura.setText("");
            }
            else {


                Double n1 = Double.parseDouble(numerobase1);
                Double n2 = Double.parseDouble(numeroaltura2);


                Double resus = n1 / (n2 * n2);


                String resultado = String.valueOf(resus);
                resultado = (formateador.format(resus));


                if (resus < 16.00) {
                    tv_resultado.setText(getString(R.string.imcds) + resultado + getString(R.string.delgs));

                }
                if (resus > 16.00 && resus <= 16.99) {
                    tv_resultado.setText(getString(R.string.imcdm) + resultado + getString(R.string.delgm));

                }
                if (resus > 17.00 && resus <= 16.49) {
                    tv_resultado.setText(getString(R.string.imcda) + resultado + getString(R.string.delga));

                }
                if (resus > 18.50 && resus <= 24.99) {
                    tv_resultado.setText(getString(R.string.imcdn) + resultado + getString(R.string.delgn));

                }
                if (resus > 25.00 && resus <= 29.99) {
                    tv_resultado.setText(getString(R.string.imcsp) + resultado + getString(R.string.sobrepeso));

                }
                if (resus >= 30.00 && resus <= 34.99) {
                    tv_resultado.setText(R.string.obt1);
                }
                if (resus >= 35.00 && resus <= 40.00) {
                    tv_resultado.setText(R.string.obt2);
                }
                if (resus > 40) {
                    tv_resultado.setText(R.string.obt3);
                } else {
                    System.out.println(" valor  es erroneo");
                }
            }
            SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);

            SharedPreferences.Editor editor=preferencias.edit();
            editor.putString("peso", Et_peso.getText().toString());
            editor.putString("altura", Et_altura.getText().toString());
            Toast t = Toast.makeText(this, R.string.datosg,
                    Toast.LENGTH_SHORT);
            t.show();
            editor.commit();
   }







    public void onClickSwitch(View v) {
        Date fecha = new Date();
        DateFormat fortmatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    "history.txt", Activity.MODE_APPEND));
            archivo.write(getString(R.string.fechah) +fortmatoFecha.format(fecha)+"\n");
            archivo.write(getString(R.string.pesoh) +Et_peso.getText().toString()+ "\n");
            archivo.write(getString(R.string.estaturah) +Et_altura.getText().toString()+ "\n");
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast t = Toast.makeText(this, R.string.datosgrabados,
                Toast.LENGTH_SHORT);
        t.show();

        Intent intent = new Intent (this, Historial.class);
        startActivity(intent);

    }

}
