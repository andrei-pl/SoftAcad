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
import android.widget.Toast;

import com.example.andrey.hw_cinemas.CinemaActivity;
import com.example.andrey.hw_cinemas.Models.Cinema;
import com.example.andrey.hw_cinemas.Models.Movie;
import com.example.andrey.hw_cinemas.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MovieInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MovieInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieInfoFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "section_number";

    private String mParam1;
    private ImageView picMovie;
    private TextView txtMovieName;
    private TextView txtAvailableSeats;
    private Button btnAvailableSeats;

    private OnFragmentInteractionListener mListener;
    private Movie movie;
    private Cinema cinema;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment MovieInfoFragment.
     */

    public static MovieInfoFragment newInstance(String param1, Movie movie, Cinema cinema) {
        MovieInfoFragment fragment = new MovieInfoFragment();
        Bundle args = new Bundle();
        args.putString(param1, movie.getName());
        fragment.setArguments(args);
        fragment.movie = movie;
        fragment.cinema = cinema;
        return fragment;
    }

    public MovieInfoFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_movie_info, container, false);

        this.picMovie = (ImageView) rootView.findViewById(R.id.moviePicInfo);
        this.picMovie.setImageBitmap(this.movie.getPicMovie());
        this.txtMovieName = (TextView) rootView.findViewById(R.id.txtMovieNameInfo);
        this.txtMovieName.setText(this.movie.getName());
        this.txtAvailableSeats = (TextView) rootView.findViewById(R.id.txtSeats);
        this.txtAvailableSeats.setText(String.valueOf(this.movie.getCinemas().get(cinema)));

        this.btnAvailableSeats = (Button) rootView.findViewById(R.id.btnBookTickets);
        this.btnAvailableSeats.setOnClickListener(this);

        return rootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
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
        if(viewId == R.id.btnBookTickets){
            FragmentManager manager = getFragmentManager();
            BookTicketsDialogFragment bookTickets = new BookTicketsDialogFragment();
            bookTickets.setMovie(movie);
            bookTickets.show(manager, "BookTicketsDialogFragment");
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
