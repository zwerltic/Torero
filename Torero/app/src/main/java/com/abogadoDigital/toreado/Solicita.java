package com.abogadoDigital.toreado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    public static String savedName;
    public static String savedLast;
    public static String savedStreet;
    public static String savedNumero;
    public static String savedColonia;
    public static String savedDelegacion;
    public static String savedJuzgado;
    public static String savedItinerante;
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
        savedName       = "";
        savedLast       = "";
        savedStreet     = "";
        savedNumero     = "";
        savedColonia    = "";
        savedDelegacion = "";
        savedJuzgado    = "";
        savedItinerante = "";
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(com.abogadoDigital.toreado.R.layout.fragment_solicita, container, false);
        ArrayAdapter adapterDeleg = ArrayAdapter.createFromResource(getActivity(), com.abogadoDigital.toreado.R.array.Delegaciones, com.abogadoDigital.toreado.R.layout.spinner_item);
        ArrayAdapter adapterItinerantes = ArrayAdapter.createFromResource(getActivity(), com.abogadoDigital.toreado.R.array.Itinerantes, com.abogadoDigital.toreado.R.layout.spinner_item);
        adapterDeleg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterItinerantes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameField = (EditText) rootView.findViewById(com.abogadoDigital.toreado.R.id.EditTextName);
        lastField = (EditText) rootView.findViewById(com.abogadoDigital.toreado.R.id.EditTextLast);
        streetField = (EditText) rootView.findViewById(com.abogadoDigital.toreado.R.id.EditTextStreet);
        numField = (EditText) rootView.findViewById(com.abogadoDigital.toreado.R.id.EditTextNumb);
        coloniaField = (EditText) rootView.findViewById(com.abogadoDigital.toreado.R.id.EditTextColonia);
        delegSpinner = (Spinner) rootView.findViewById(com.abogadoDigital.toreado.R.id.spinnerDelegacion);
        juzgadoSpinner = (Spinner) rootView.findViewById(com.abogadoDigital.toreado.R.id.spinnerJuzgado);
        itineranteSpinner = (Spinner) rootView.findViewById(com.abogadoDigital.toreado.R.id.spinnerItinerante);
        delegSpinner.setAdapter(adapterDeleg);
        itineranteSpinner.setAdapter(adapterItinerantes);
        buttonSumbit = (Button) rootView.findViewById(com.abogadoDigital.toreado.R.id.ButtonSendForm);
        delegSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String firstChoice = delegSpinner.getSelectedItem().toString().replaceAll("\\s", "").replaceAll("[^A-Za-z\\s]", "");
                Log.d("This is the returned string", firstChoice);
                int fid = getResources().getIdentifier(firstChoice, "array", getActivity().getBaseContext().getPackageName());
                ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), fid, com.abogadoDigital.toreado.R.layout.spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                juzgadoSpinner.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        buttonSumbit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                savedName += nameField.getText().toString();
                savedLast += lastField.getText().toString();
                savedStreet += streetField.getText().toString();
                savedNumero += numField.getText().toString();
                savedColonia += coloniaField.getText().toString();
                savedDelegacion += delegSpinner.getSelectedItem().toString();
                savedJuzgado += juzgadoSpinner.getSelectedItem().toString();
                savedItinerante += itineranteSpinner.getSelectedItem().toString();
                Intent sendingIntent = new Intent(getActivity(), ConfirmarActivity.class);
                startActivity(sendingIntent);
            }

        });
        return rootView;
    }
}
