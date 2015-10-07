package com.toreo.torero;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar);
        Intent receivingIntent = getIntent();
        name = receivingIntent.getExtras().getString("name");
        last = receivingIntent.getExtras().getString("last");
        street = receivingIntent.getExtras().getString("street");
        numb = receivingIntent.getExtras().getString("number");
        colonia = receivingIntent.getExtras().getString("colonia");
        deleg = receivingIntent.getExtras().getString("delegacion");
        juzgado = receivingIntent.getExtras().getString("juzgado");
    }


    public void tokenizeCard(View view) {
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
                            Log.d("ERROR: ", result);
                        }


                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private class postTask extends AsyncTask<Token, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Pagar.this, "Porfavor espere", "Procesando pago...", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Intent sendingIntent = new Intent(Pagar.this, Gracias.class);
            startActivity(sendingIntent);
        }

        @Override
        protected Void doInBackground(Token... token) {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://torero-tlacuilo.rhcloud.com/api/charge");
            String postString;
            JSONObject object = new JSONObject();
            try {

                object.put("token", token[0].id);
                object.put("name", name);
                object.put("last", last);
                object.put("street", street);
                object.put("number", numb);
                object.put("colonia", colonia);
                object.put("deleg", deleg);
                object.put("juzgado", juzgado);
                //object.put("itinerante", itinerante);
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
                Log.d(result, "from POST request");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}