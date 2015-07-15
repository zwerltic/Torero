package com.toreo.torero;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * Simple fragments taken from MainActivity default placeholderfragments
 */
public class Solicita extends Fragment {

    /* The fragment argument representing the section number for this
            * fragment.
    */
    private static final String ARG_SECTION_NUMBER = "section_number";
    // This final can affect the reset function of this section
    EditText nameField;
    EditText lastField;
    EditText streetField;
    EditText coloniaField;
    EditText delegField;
    EditText juzgadoField;
    Button buttonSumbit;
    Spinner delegSpinner;
    Spinner numSpinner;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Solicita newInstance(int sectionNumber) {
        Solicita fragment = new Solicita();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Solicita() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_solicita, container, false);
        nameField = (EditText) rootView.findViewById(R.id.EditTextName);
        lastField = (EditText) rootView.findViewById(R.id.EditTextLast);
        streetField = (EditText) rootView.findViewById(R.id.EditTextStreet);
        coloniaField = (EditText) rootView.findViewById(R.id.EditTextColonia);
        delegField = (EditText) rootView.findViewById(R.id.EditTextDelegacion);
        juzgadoField = (EditText) rootView.findViewById(R.id.EditTextJuzgado);
        delegSpinner = (Spinner) rootView.findViewById(R.id.spinnerDelegacion);
        numSpinner = (Spinner) rootView.findViewById(R.id.spinnerJuzgado);
        buttonSumbit = (Button) rootView.findViewById(R.id.ButtonSendForm);
        buttonSumbit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = nameField.getText().toString();
                String lastName = lastField.getText().toString();
                String street = streetField.getText().toString();
                String colonia = coloniaField.getText().toString();
                String delegacion = delegField.getText().toString();
                String juzgado = juzgadoField.getText().toString();
                String delegSpin = delegSpinner.getSelectedItem().toString();
                String numSpin = numSpinner.getSelectedItem().toString();
                Intent paymentChange = new Intent(getActivity(), ConfirmarActivity.class);
                paymentChange.putExtra("name", name);
                paymentChange.putExtra("last", lastName);
                paymentChange.putExtra("street", street);
                paymentChange.putExtra("colonia", colonia);
                paymentChange.putExtra("delegacion", delegacion);
                paymentChange.putExtra("juzgado", juzgado);
                paymentChange.putExtra("delegacionDeLista", delegSpin);
                paymentChange.putExtra("numeroLista", numSpin);
                startActivity(paymentChange);
            }

        });
        return rootView;
    }
}
