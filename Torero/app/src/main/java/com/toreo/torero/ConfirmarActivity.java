package com.toreo.torero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class ConfirmarActivity extends Activity {

    TextView name;
    TextView lastName;
    TextView street;
    TextView colonia;
    TextView delegacion;
    TextView juzgado;
    TextView delegJuzgado;
    TextView nombJuzgado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar);
        Intent solicitaIntent = getIntent();
        name = (TextView) findViewById(R.id.DisplayTextName);
        name.setText(solicitaIntent.getExtras().getString("name"));
        lastName = (TextView) findViewById(R.id.DisplayTextLast);
        lastName.setText(solicitaIntent.getExtras().getString("last"));
        street = (TextView) findViewById(R.id.DisplayTextStreet);
        street.setText(solicitaIntent.getExtras().getString("street"));
        colonia = (TextView) findViewById(R.id.DisplayTextColonia);
        colonia.setText(solicitaIntent.getExtras().getString("colonia"));
        delegacion = (TextView) findViewById(R.id.DisplayTextDelegacion);
        delegacion.setText(solicitaIntent.getExtras().getString("delegacion"));
        juzgado = (TextView) findViewById(R.id.DisplayTextJuzgado);
        juzgado.setText(solicitaIntent.getExtras().getString("juzgado"));
        delegJuzgado = (TextView) findViewById(R.id.DisplayTextDelegJuzgado);
        delegJuzgado.setText(solicitaIntent.getExtras().getString("delegacionDeLista"));
        nombJuzgado = (TextView) findViewById(R.id.DisplayTextNombJuzgado);
        nombJuzgado.setText(solicitaIntent.getExtras().getString("numeroLista"));
        final String body = name.getText().toString() + lastName.getText().toString() + street.getText().toString()
                        + colonia.getText().toString() + delegacion.getText().toString() + juzgado.getText().toString()
                            + delegJuzgado.getText().toString() + nombJuzgado.getText().toString();
        final Button send = (Button) this.findViewById(R.id.ButtonSendPayment);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                Mail m = new Mail("zwerltic@gmail.com", "1916zweRltic");

                String[] toArr = {"jose.tlacuilo@gmail.com", "zwerltic@gmail.com"};
                m.setTo(toArr);
                m.setFrom("zwerltic@gmail.com");
                m.setSubject("Toreo Prueba");
                m.setBody(body);

                try {
                    if(m.send()) {
                        Toast.makeText(ConfirmarActivity.this, "Email was sent successfully.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ConfirmarActivity.this, "Email was not sent.", Toast.LENGTH_LONG).show();
                    }
                } catch(Exception e) {
                    //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                    Log.e("MailApp", "Could not send email", e);
                }

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
