package com.toreo.torero;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONException;
import org.json.JSONObject;

import io.conekta.tokenizer.Tokenizer;
import io.conekta.tokenizer.TokenizerCallback;
import com.conekta.Token;
import com.conekta.Error;



public class Pagar extends Activity {
    private static final String username = "zwerltic@gmail.com";
    private static final String password = "1916zweRltic";
    private static  String name;
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
        deleg= receivingIntent.getExtras().getString("delegacion");
        juzgado = receivingIntent.getExtras().getString("juzgado");
    }

    private void sendMail(String email, String subject, String messageBody) {
        Session session = createSessionObject();

        try {
            Message message = createMessage(email, subject, messageBody, session);
            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private Message createMessage(String email, String subject, String messageBody, Session session) throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("mailsmtp@zwerltic.com", "Torero App"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, email));
        message.setSubject(subject);
        message.setText(messageBody);
        return message;
    }

    private Session createSessionObject() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void tokenizeCard(View view) {
        Tokenizer conekta = new Tokenizer("key_EfsX62HbiTSNsuHr5q6xv2Q", this);
        EditText nameText = (EditText) this.findViewById(R.id.nameText);
        EditText numberText = (EditText) this.findViewById(R.id.numberText);
        EditText monthText = (EditText) this.findViewById(R.id.monthText);
        EditText yearText = (EditText) this.findViewById(R.id.yearText);
        EditText cvcText = (EditText) this.findViewById(R.id.cvcText);
        try {
            JSONObject card = new JSONObject(
                    "{'card':" +
                            "{" +
                            "'name': '"+ String.valueOf(nameText.getText()) + "'," +
                            "'number': '"+ String.valueOf(numberText.getText()).trim() + "'," +
                            "'exp_month': '"+ String.valueOf(monthText.getText()).trim() + "'," +
                            "'exp_year': '"+ String.valueOf(yearText.getText()).trim() + "'," +
                            "'cvc': '"+ String.valueOf(cvcText.getText()).trim() + "'" +
                            "}" +
                            "}");
            conekta.tokenizeCard(card,
                    new TokenizerCallback() {
                        public void success(final Token token) {
                            // TODO: Send token to your web service to create the charge∫
                            String email = "jose.tlacuilo@gmail.com";
                            String subject = "New Request de " + name;
                            String message = token.id + "\nNombre:      " + name +
                                                        "\nApellido:    " + last +
                                                        "\nCalle:       " + street +
                                                        "\nNumero:      " + numb +
                                                        "\nColonia:     " + colonia +
                                                        "\nDelegacion:  " + deleg +
                                                        "\nJuzgado:     " + juzgado;
                            sendMail(email, subject, message);


                        }

                        public void failure(Exception error) {
                            // TODO: Output the error in your app
                            String result = null;
                            if (error instanceof Error)
                                result = ((Error) error).message_to_purchaser;
                            else
                                result = error.getMessage();
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class SendMailTask extends AsyncTask<Message, Void, Void> {
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
        protected Void doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
