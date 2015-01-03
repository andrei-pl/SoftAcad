package com.example.andrey.hw_cinemas.Fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.andrey.hw_cinemas.Adapters.CinemaAdapter;
import com.example.andrey.hw_cinemas.CinemaActivity;
import com.example.andrey.hw_cinemas.Models.Cinema;
import com.example.andrey.hw_cinemas.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CinemasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CinemasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CinemasFragment extends Fragment {

    private ArrayList<Cinema> cinemas;
    private GridView gridView;
    private static CinemasFragment fragment = null;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "section_number";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment CinemasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CinemasFragment newInstance(String param1, int number, ArrayList<Cinema> cinemas) {
        if(fragment == null) {
            fragment = new CinemasFragment();
            Bundle args = new Bundle();
            args.putInt(param1, number);
            fragment.setArguments(args);
            fragment.cinemas = cinemas;
        }
        return fragment;
    }

    public CinemasFragment() {
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cinemas, container, false);

        gridView = (GridView) rootView.findViewById(R.id.moviesGrid);

        final ArrayList<Cinema> cinemaList = cinemas;

        CinemaAdapter adapter = new CinemaAdapter(getActivity(), R.layout.cinema_view, cinemas);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = CinemaInfoFragment.newInstance("section_number", cinemaList.get(position));
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
            }
        });

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
