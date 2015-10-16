package com.toreo.torero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Gracias extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gracias);
        Intent receivingIntent = getIntent();
        Button buttonFinish = (Button) findViewById(R.id.ButtonAceptarGracias);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent finishIntent =new Intent(Gracias.this, MainActivity.class);
                finishIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(finishIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gracias, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        // do nothing
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
