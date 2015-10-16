package com.toreo.torero;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Simple fragments taken from MainActivity default placeholderfragments
 */
public class Acerca extends Fragment {

    /* The fragment argument representing the section number for this
            * fragment.
    */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Acerca newInstance(int sectionNumber) {
        Acerca fragment = new Acerca();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Acerca() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_acerca, container, false);
        String[] textArray = getResources().getStringArray(R.array.Delegaciones);
        int finId = getResources().getIdentifier("Delegaciones", "array", getActivity().getPackageName());

        return rootView;
    }
}

