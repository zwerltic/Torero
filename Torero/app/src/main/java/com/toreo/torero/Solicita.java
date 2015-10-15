package com.toreo.torero;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    EditText numField;
    EditText coloniaField;
    Spinner delegSpinner;
    Spinner juzgadoSpinner;
    Spinner itineranteSpinner;
    Button buttonSumbit;

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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_solicita, container, false);
        ArrayAdapter adapterDeleg = ArrayAdapter.createFromResource(getActivity(), R.array.Delegaciones, R.layout.spinner_item);
        ArrayAdapter adapterItinerantes = ArrayAdapter.createFromResource(getActivity(), R.array.Itinerantes, R.layout.spinner_item);
        adapterDeleg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterItinerantes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameField = (EditText) rootView.findViewById(R.id.EditTextName);
        lastField = (EditText) rootView.findViewById(R.id.EditTextLast);
        streetField = (EditText) rootView.findViewById(R.id.EditTextStreet);
        numField = (EditText) rootView.findViewById(R.id.EditTextNumb);
        coloniaField = (EditText) rootView.findViewById(R.id.EditTextColonia);
        delegSpinner = (Spinner) rootView.findViewById(R.id.spinnerDelegacion);
        juzgadoSpinner = (Spinner) rootView.findViewById(R.id.spinnerJuzgado);
        itineranteSpinner = (Spinner) rootView.findViewById(R.id.spinnerItinerante);
        delegSpinner.setAdapter(adapterDeleg);
        juzgadoSpinner.setAdapter(adapterDeleg);
        itineranteSpinner.setAdapter(adapterDeleg);
        buttonSumbit = (Button) rootView.findViewById(R.id.ButtonSendForm);
        delegSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String firstChoice = delegSpinner.getSelectedItem().toString().replaceAll("\\s", "").replaceAll("[^A-Za-z\\s]", "");
                Log.d("This is the returned string", firstChoice);
                int fid = getResources().getIdentifier(firstChoice, "array", getActivity().getBaseContext().getPackageName());
                ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), fid, R.layout.spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                juzgadoSpinner.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        buttonSumbit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = " ";
                String last = " ";
                String street = " ";
                String numb = " ";
                String colonia = " ";
                String deleg = " ";
                String juzgado = " ";
                String itinerante = " ";
                name += nameField.getText().toString();
                last += lastField.getText().toString();
                street += streetField.getText().toString();
                numb += numField.getText().toString();
                colonia += coloniaField.getText().toString();
                deleg += delegSpinner.getSelectedItem().toString();
                juzgado += juzgadoSpinner.getSelectedItem().toString();
                itinerante += itineranteSpinner.getSelectedItem().toString();
                Intent sendingIntent = new Intent(getActivity(), ConfirmarActivity.class);
                sendingIntent.putExtra("name", name);
                sendingIntent.putExtra("last", last);
                sendingIntent.putExtra("street", street);
                sendingIntent.putExtra("numero", numb);
                sendingIntent.putExtra("colonia", colonia);
                sendingIntent.putExtra("delegacion", deleg);
                sendingIntent.putExtra("juzgado", juzgado);
                sendingIntent.putExtra("itinerante", itinerante);
                startActivity(sendingIntent);
            }

        });
        return rootView;
    }
}
