package com.abogadoDigital.toreado;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Simple fragments taken from MainActivity default placeholderfragments
 */
public class Instrucciones extends Fragment {

    /* The fragment argument representing the section number for this
            * fragment.
    */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Instrucciones newInstance(int sectionNumber) {
        Instrucciones fragment = new Instrucciones();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Instrucciones() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(com.abogadoDigital.toreado.R.layout.fragment_instrucciones, container, false);
        return rootView;
    }
}