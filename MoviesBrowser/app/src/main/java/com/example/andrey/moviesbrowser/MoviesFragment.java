package com.example.andrey.moviesbrowser;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.andrey.moviesbrowser.Adapters.MoviesAdapter;
import com.example.andrey.moviesbrowser.Models.MovieInfo;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoviesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesFragment extends Fragment {
    private String sectionNumber;

    private OnFragmentInteractionListener mListener;
    private ArrayList<MovieInfo> movies;

    private ListView listViewMovies;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviesFragment newInstance(String sectionNumber, int number) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        fragment.sectionNumber = sectionNumber;
        args.putInt(sectionNumber, number);
        fragment.setArguments(args);
        return fragment;
    }

    public MoviesFragment() {
        this.movies = new ArrayList<MovieInfo>();
    }

    private void loadMovies() {
        (new GetAsyncResult()).execute(null, null, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        listViewMovies = (ListView) rootView.findViewById(R.id.listMovies);


        final ArrayList<MovieInfo> classifiedsArray = this.movies;
        loadMovies();

        MoviesAdapter adapter = new MoviesAdapter(getActivity(), R.layout.movie_view, this.movies);
        listViewMovies.setAdapter(adapter);

        listViewMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = MovieInfoFragment.newInstance("section_number", movies.get(position));
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
            }
        });

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
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(sectionNumber));
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

    private class GetAsyncResult extends AsyncTask<Void, Void, ArrayList<MovieInfo>>{

        private final String moviesUrl = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=9yy23ujvh42vj5gujrm8gbuu&q=%3Bandroid%3D";
        private ArrayList<MovieInfo> moviesList;
        private JSONArray moviesJSONObj = null;

        @Override
        protected ArrayList<MovieInfo> doInBackground(Void... params) {
            moviesList = new ArrayList<MovieInfo>();

            BufferedReader in = null;
            JSONArray result = null;
            HttpClient httpclient = new DefaultHttpClient();
            String command = "";

            URI url = null;
            try {
                url = new URI(moviesUrl);
                HttpGet getResult = new HttpGet(url);

                HttpResponse response = httpclient.execute(getResult);
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent()));

                String line = in.readLine();
                StringBuilder sb = new StringBuilder();

                while (line != null) {
                    sb.append(line);
                    line = in.readLine();
                }

                JSONObject jsonObj = new JSONObject(sb.toString());
                moviesJSONObj = jsonObj.getJSONArray("movies");

                for (int i = 0; i < moviesJSONObj.length(); i++){
                    String id;
                    String title;
                    String year;
                    String rating;
                    String runtime;
                    JSONObject postersObj;
                    String posterStr;

                    JSONObject jsonObject = moviesJSONObj.getJSONObject(i);

                    id = jsonObject.get("id").toString();
                    title = jsonObject.get("title").toString();
                    year = jsonObject.get("year").toString();
                    rating = jsonObject.get("mpaa_rating").toString();
                    runtime = jsonObject.get("runtime").toString();

                    postersObj = jsonObject.getJSONObject("posters");
                    posterStr = postersObj.get("original").toString();

                    MovieInfo currentMovie = new MovieInfo(id, title, year, rating, runtime, posterStr);

                    moviesList.add(currentMovie);
                    Log.d("Success", "Success");
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
        }
            return moviesList;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieInfo> movieInfos) {
            movies = movieInfos;
            if((FragmentActivity) getActivity() != null){
                MoviesAdapter adapter = new MoviesAdapter(getActivity(), R.layout.movie_view, movieInfos);
                listViewMovies.setAdapter(adapter);
            }
        }
    }

}
