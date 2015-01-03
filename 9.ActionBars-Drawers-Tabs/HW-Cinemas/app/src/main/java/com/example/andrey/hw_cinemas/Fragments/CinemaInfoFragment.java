package com.example.andrey.hw_cinemas.Fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andrey.hw_cinemas.CinemaActivity;
import com.example.andrey.hw_cinemas.Models.Cinema;
import com.example.andrey.hw_cinemas.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CinemaInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CinemaInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CinemaInfoFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "section_number";

    private ImageView picCinema1;
    private ImageView picCinema2;
    private TextView txtCinemaName;
    private TextView txtCinemaAddress;
    private Button btnAvailableMovies;
    private Cinema cinema;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment CinemaInfoFragment.
     */

    public static CinemaInfoFragment newInstance(String param1, Cinema cinema) {
        CinemaInfoFragment fragment = new CinemaInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, 2);
        fragment.cinema = cinema;
        fragment.setArguments(args);
        return fragment;
    }

    public CinemaInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cinema_info, container, false);

        this.txtCinemaName = (TextView) rootView.findViewById(R.id.txtCinemaNameInfo);
        this.txtCinemaName.setText(this.cinema.getName());
        this.txtCinemaAddress = (TextView) rootView.findViewById(R.id.txtCinemaAddressInfo);
        this.txtCinemaAddress.setText(this.cinema.getAddress());
        this.picCinema1 = (ImageView) rootView.findViewById(R.id.picCinema1);
        this.picCinema1.setImageBitmap(this.cinema.getPicCinema1());
        this.picCinema2 = (ImageView) rootView.findViewById(R.id.picCinema2);
        this.picCinema2.setImageBitmap(this.cinema.getPicCinema2());
        this.btnAvailableMovies = (Button) rootView.findViewById(R.id.btnAvailableMovies);
        btnAvailableMovies.setOnClickListener(this);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }

        ((CinemaActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_PARAM1));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        if(viewId == R.id.btnAvailableMovies){
            Fragment fragment = MoviesFragment.newInstance("section_number", this.cinema.getMovies(), this.cinema);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
