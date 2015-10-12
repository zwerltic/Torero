package com.toreo.torero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class ConfirmarActivity extends Activity {

    TextView name;
    TextView lastName;
    TextView street;
    TextView numero;
    TextView colonia;
    TextView delegacion;
    TextView juzgado;
    TextView itinerante;

    public static String savedName;
    public static String savedLast;
    public static String savedStreet;
    public static String savedNumero;
    public static String savedColonia;
    public static String savedDelegacion;
    public static String savedJuzgado;
    public static String savedItinerante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar);
        final Intent receivingIntent = getIntent();
        name = (TextView) findViewById(R.id.DisplayTextName);
        lastName = (TextView) findViewById(R.id.DisplayTextLast);
        street = (TextView) findViewById(R.id.DisplayTextStreet);
        numero = (TextView) findViewById(R.id.DisplayTextNum);
        colonia = (TextView) findViewById(R.id.DisplayTextColonia);
        delegacion = (TextView) findViewById(R.id.DisplayTextDelegacion);
        juzgado = (TextView) findViewById(R.id.DisplayTextJuzgado);
        itinerante = (TextView) findViewById(R.id.DisplayTextItinerante);
        if (receivingIntent.getExtras() != null) {

            savedName = receivingIntent.getExtras().getString("name");
            savedLast = receivingIntent.getExtras().getString("last");
            savedStreet = receivingIntent.getExtras().getString("street");
            savedNumero = receivingIntent.getExtras().getString("numero");
            savedColonia = receivingIntent.getExtras().getString("colonia");
            savedDelegacion = receivingIntent.getExtras().getString("delegacion");
            savedJuzgado = receivingIntent.getExtras().getString("juzgado");
            savedItinerante = receivingIntent.getExtras().getString("itinerante");
        }
        name.setText(savedName);
        lastName.setText(savedLast);
        street.setText(savedStreet);
        numero.setText(savedNumero);
        colonia.setText(savedColonia);
        delegacion.setText(savedDelegacion);
        juzgado.setText(savedJuzgado);
        itinerante.setText(savedItinerante);
        final Button send = (Button) this.findViewById(R.id.ButtonSendPayment);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent sendingIntent = new Intent(ConfirmarActivity.this, Terminos.class);
                sendingIntent.putExtra("name", receivingIntent.getExtras().getString("name"));
                sendingIntent.putExtra("last", receivingIntent.getExtras().getString("last"));
                sendingIntent.putExtra("street", receivingIntent.getExtras().getString("street"));
                sendingIntent.putExtra("number", receivingIntent.getExtras().getString("number"));
                sendingIntent.putExtra("colonia", receivingIntent.getExtras().getString("colonia"));
                sendingIntent.putExtra("delegacion", receivingIntent.getExtras().getString("delegacion"));
                sendingIntent.putExtra("juzgado", receivingIntent.getExtras().getString("juzgado"));
                startActivity(sendingIntent);


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
