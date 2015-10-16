package com.abogadoDigital.toreado;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmarActivity extends Activity {

    TextView name;
    TextView lastName;
    TextView street;
    TextView numero;
    TextView colonia;
    TextView delegacion;
    TextView juzgado;
    TextView itinerante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.abogadoDigital.toreado.R.layout.activity_confirmar);
        final Intent receivingIntent = getIntent();
        name = (TextView) findViewById(com.abogadoDigital.toreado.R.id.DisplayTextName);
        lastName = (TextView) findViewById(com.abogadoDigital.toreado.R.id.DisplayTextLast);
        street = (TextView) findViewById(com.abogadoDigital.toreado.R.id.DisplayTextStreet);
        numero = (TextView) findViewById(com.abogadoDigital.toreado.R.id.DisplayTextNum);
        colonia = (TextView) findViewById(com.abogadoDigital.toreado.R.id.DisplayTextColonia);
        delegacion = (TextView) findViewById(com.abogadoDigital.toreado.R.id.DisplayTextDelegacion);
        juzgado = (TextView) findViewById(com.abogadoDigital.toreado.R.id.DisplayTextJuzgado);
        itinerante = (TextView) findViewById(com.abogadoDigital.toreado.R.id.DisplayTextItinerante);
        name.setText("Nombre     : " + Solicita.savedName);
        lastName.setText("Apellido   : " + Solicita.savedLast);
        street.setText("Calle      : " + Solicita.savedStreet);
        numero.setText("Número     : " + Solicita.savedNumero);
        colonia.setText("Colonia    : " + Solicita.savedColonia);
        delegacion.setText("Delegación : " + Solicita.savedDelegacion);
        juzgado.setText("Juzgado    : " + Solicita.savedJuzgado);
        itinerante.setText("Itinerante : " + Solicita.savedItinerante);
        final Button send = (Button) this.findViewById(com.abogadoDigital.toreado.R.id.ButtonSendPayment);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent sendingIntent = new Intent(ConfirmarActivity.this, Terminos.class);
                startActivity(sendingIntent);


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.abogadoDigital.toreado.R.menu.menu_payment, menu);
        return true;
    }




}
