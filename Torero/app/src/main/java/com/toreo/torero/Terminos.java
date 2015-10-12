package com.toreo.torero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class Terminos extends Activity {

    TextView terminosScroll;
    TextView avisoScroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminos);
        final Intent receivingIntent = getIntent();
        terminosScroll = (TextView)findViewById(R.id.termScroll);
        avisoScroll = (TextView)findViewById(R.id.aviScroll);
        terminosScroll.setMovementMethod(new ScrollingMovementMethod());
        terminosScroll.setText(Html.fromHtml(getString(R.string.terminos_condiciones)));
        avisoScroll.setMovementMethod(new ScrollingMovementMethod());
        final Button send = (Button) this.findViewById(R.id.ButtonAceptarTerminos);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent sendingIntent = new Intent(Terminos.this, Pagar.class);
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
        getMenuInflater().inflate(R.menu.menu_terminos, menu);
        return true;
    }


    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();
        finish();
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
