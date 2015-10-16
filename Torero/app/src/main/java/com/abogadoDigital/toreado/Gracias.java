package com.abogadoDigital.toreado;

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
        setContentView(com.abogadoDigital.toreado.R.layout.activity_gracias);
        Intent receivingIntent = getIntent();
        Button buttonFinish = (Button) findViewById(com.abogadoDigital.toreado.R.id.ButtonAceptarGracias);
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
        getMenuInflater().inflate(com.abogadoDigital.toreado.R.menu.menu_gracias, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }


}
