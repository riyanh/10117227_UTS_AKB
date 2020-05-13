package com.example.a10117227_uts_akb;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */

// Tanggal Pengerjaan   : 9-Mei-2020
// Nim                  : 10117227
// Nama                 : Mohamad Riyan Hidayat
// Kelas                : IF-7
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Profile");
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        Button button = (Button) rootView.findViewById(R.id.contact);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactProfile();
            }
        });


        return rootView;
    }

    public void contactProfile() {
        Intent intent = new Intent(getActivity(), ContactProfile.class);
        startActivity(intent);
    }

}
