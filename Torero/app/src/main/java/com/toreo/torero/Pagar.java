package com.toreo.torero;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;


import io.conekta.tokenizer.Tokenizer;
import io.conekta.tokenizer.TokenizerCallback;
import com.conekta.Token;
import com.conekta.Error;



public class Pagar extends Activity {
    private static String name;
    private static String last;
    private static String street;
    private static String numb;
    private static String colonia;
    private static String deleg;
    private static String juzgado;
    private static String itinerante;

    Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar);
        Intent receivingIntent = getIntent();
        payButton = (Button) findViewById(R.id.buttonPay);
//        name = receivingIntent.getExtras().getString("name");
//        last = receivingIntent.getExtras().getString("last");
//        street = receivingIntent.getExtras().getString("street");
//        numb = receivingIntent.getExtras().getString("number");
//        colonia = receivingIntent.getExtras().getString("colonia");
//        deleg = receivingIntent.getExtras().getString("delegacion");
//        juzgado = receivingIntent.getExtras().getString("juzgado");
//        itinerante = receivingIntent.getExtras().getString("itinerante");
    }


    public void tokenizeCard(View view) {
        payButton.setEnabled(false);
        Tokenizer conekta = new Tokenizer("key_QVrGEJP5CoXKybaBM78LkgA", this);
        EditText nameText = (EditText) this.findViewById(R.id.nameText);
        EditText numberText = (EditText) this.findViewById(R.id.numberText);
        EditText monthText = (EditText) this.findViewById(R.id.monthText);
        EditText yearText = (EditText) this.findViewById(R.id.yearText);
        EditText cvcText = (EditText) this.findViewById(R.id.cvcText);
        Log.d("Did the tokenizer", "Yeah!");
        try {
            JSONObject card = new JSONObject(
                    "{'card':" +
                            "{" +
                            "'name': '" + String.valueOf(nameText.getText()) + "'," +
                            "'number': '" + String.valueOf(numberText.getText()).trim() + "'," +
                            "'exp_month': '" + String.valueOf(monthText.getText()).trim() + "'," +
                            "'exp_year': '" + String.valueOf(yearText.getText()).trim() + "'," +
                            "'cvc': '" + String.valueOf(cvcText.getText()).trim() + "'" +
                            "}" +
                            "}");
            conekta.tokenizeCard(card,
                    new TokenizerCallback() {
                        public void success(final Token token) {
                            // TODO: Send token to your web service to create the charge∫
                            try {
                                new postTask().execute(token);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }

                        public void failure(Exception error) {
                            // TODO: Output the error in your app
                            String result = null;
                            if (error instanceof Error)
                                result = ((Error) error).message_to_purchaser;
                            else
                                result = error.getMessage();
                            AlertDialog alertDialog;
                            alertDialog = new AlertDialog.Builder(Pagar.this).create();
                            alertDialog.setTitle("Hubo un problema");
                            alertDialog.setMessage(result);
                            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alertDialog.show();
                            payButton.setEnabled(true);
                            Log.d("ERROR: ", result);
                        }


                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
   }


    private class postTask extends AsyncTask<Token, Void, Void> {
        private ProgressDialog progressDialog;

        private String theResponse;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Pagar.this, "Porfavor espere", "Procesando pago...", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            if (theResponse.equals("Success")) {
                Intent sendingIntent = new Intent(Pagar.this, Gracias.class);
                startActivity(sendingIntent);
            } else {
                payButton.setEnabled(true);
                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(Pagar.this).create();
                alertDialog.setTitle("Hubo un problema");
                alertDialog.setMessage(theResponse);
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        }

        @Override
        protected Void doInBackground(Token... token) {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://torero-tlacuilo.rhcloud.com/api/charge");
            String postString;
            JSONObject object = new JSONObject();
            try {

                object.put("token", token[0].id);
                object.put("name", Solicita.savedName);
                object.put("last", Solicita.savedLast);
                object.put("street", Solicita.savedStreet);
                object.put("number", Solicita.savedNumero);
                object.put("colonia", Solicita.savedColonia);
                object.put("deleg", Solicita.savedDelegacion);
                object.put("juzgado", Solicita.savedJuzgado);
                object.put("itinerante", Solicita.savedItinerante);
            } catch (Exception ex) {

            }
            try {
                postString = object.toString();
//                String postString = "token=" + token[0].id + "&name=" + name + "&last=" + last + "&street=" + street + "&number=" + numb + "&colonia=" + colonia
//                        + "&deleg=" + deleg + "&juzgado=" + juzgado + "&itinerante=" /*+ itinerante */;
                Log.d("This is the passing string", postString);
                HttpEntity entity = new StringEntity(postString, "UTF-8");
                post.setEntity(entity);
                post.setHeader("Content-type", "application/json");
                HttpResponse response = client.execute(post);
                String result = EntityUtils.toString(response.getEntity());
                theResponse = result;
                Log.d(result, "from POST request");
            } catch (IOException e) {
                theResponse = "No pudimos enviar el pago";
                e.printStackTrace();
            }
            return null;
        }
    }

}