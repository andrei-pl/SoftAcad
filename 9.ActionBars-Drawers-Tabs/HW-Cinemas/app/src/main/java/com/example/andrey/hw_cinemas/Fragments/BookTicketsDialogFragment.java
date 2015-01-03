package com.example.andrey.hw_cinemas.Fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andrey.hw_cinemas.Models.Cinema;
import com.example.andrey.hw_cinemas.Models.Movie;
import com.example.andrey.hw_cinemas.R;

import java.util.ArrayList;
import java.util.HashMap;

public class BookTicketsDialogFragment extends DialogFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Movie movie;

    private Spinner spinner;
    private EditText edtSeats;
    private TextView txtCurrentSeats;
    private Button btnBookTickets;

    private HashMap<String, Integer> cinemasSeats;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public BookTicketsDialogFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_book_tickets_dialog, null);

        cinemasSeats = new HashMap<String, Integer>();
        spinner = (Spinner) rootView.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        edtSeats = (EditText) rootView.findViewById(R.id.edtSeats);
        txtCurrentSeats = (TextView) rootView.findViewById(R.id.txtCurrentSeats);
        btnBookTickets = (Button) rootView.findViewById(R.id.btnBookTicketsDialog);
        btnBookTickets.setOnClickListener(this);

        ArrayList<String> cinemas = new ArrayList<String>();
        for (Cinema cinema : movie.getCinemas().keySet()){
            cinemas.add(cinema.getName());
            cinemasSeats.put(cinema.getName(), movie.getCinemas().get(cinema));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, cinemas);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        SharedPreferences pref = getActivity().getSharedPreferences("userInfo", 0);
        String user = pref.getString("user", null);

        String seatsString = edtSeats.getText().toString();
        try {
            reserveTickets(user, seatsString);
        } catch (Exception ex){
            Toast.makeText(getActivity(), "Enter valid number please.", Toast.LENGTH_LONG).show();
            return;
        }
        dismiss();
    }

    private void reserveTickets(String user, String seatsString) {
        int seatsNum = Integer.valueOf(seatsString);
        int seatsForReserve = Integer.valueOf(edtSeats.getText().toString());
        if(seatsForReserve > 0 && seatsForReserve <= seatsNum ) {
            String movieName = movie.getName();
            String cinemaName = String.valueOf(spinner.getSelectedItem());
            txtCurrentSeats.setText(String.valueOf(seatsNum - seatsForReserve));
            String text = "User " + user + " has reserved " + seatsString + " tickets for " + movieName + " movie at " + cinemaName + " cinema";
            Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "Invalid number of seats", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String cinemaName = String.valueOf(spinner.getSelectedItem());
        txtCurrentSeats.setText(String.valueOf(cinemasSeats.get(cinemaName)));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
