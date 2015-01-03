package com.example.andrey.hw_cinemas;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;

import com.example.andrey.hw_cinemas.Fragments.CinemaInfoFragment;
import com.example.andrey.hw_cinemas.Fragments.CinemasFragment;
import com.example.andrey.hw_cinemas.Fragments.MovieInfoFragment;
import com.example.andrey.hw_cinemas.Fragments.NavigationDrawerFragment;
import com.example.andrey.hw_cinemas.Models.Cinema;
import com.example.andrey.hw_cinemas.Models.Movie;

import java.util.ArrayList;

public class CinemaActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, ActionBar.TabListener {

    private final String USER_INFO = "userInfo";
    private final String LOGED = "loged";
    public static ArrayList<Cinema> cinemas;
    private Cinema currentCinema;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createCinemasList();
        setContentView(R.layout.activity_cinema);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        currentCinema = null;

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    private void createCinemasList(){
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.cinemaplovdiv1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.cinemaplovdiv2);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.cinemasofia1);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.cinemasofia2);
        Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.cinemaburgas1);
        Bitmap bitmap6 = BitmapFactory.decodeResource(getResources(), R.drawable.cinemaburgas2);
        Bitmap bitmap7 = BitmapFactory.decodeResource(getResources(), R.drawable.fastandfurious);
        Bitmap bitmap8 = BitmapFactory.decodeResource(getResources(), R.drawable.bulgar);
        Bitmap bitmap9 = BitmapFactory.decodeResource(getResources(), R.drawable.despicableme2);
        ArrayList<String> casts = new ArrayList<String>();
        casts.add("Johny Depp");
        casts.add("Cameron Dias");
        casts.add("other");

        Movie movie1 = new Movie("Fast and Furious", bitmap7, casts);
        Movie movie2 = new Movie("Bulgar the movie", bitmap8, casts);
        Movie movie3 = new Movie("Despicable me 2", bitmap9, casts);

        Cinema cinema1 = new Cinema(casts, "Plovdiv", bitmap1, bitmap2, "Cinema Plovdiv");
        cinema1.setMovies(movie1, 20);
        cinema1.setMovies(movie2, 30);
        Cinema cinema2 = new Cinema(casts, "Sofia", bitmap3, bitmap4, "Cinema Sofia");
        cinema2.setMovies(movie3, 25);
        cinema2.setMovies(movie2, 15);
        cinema2.setMovies(movie1, 10);
        Cinema cinema3 = new Cinema(casts, "Bourgas", bitmap5, bitmap6, "Cinema Bourgas");
        cinema3.setMovies(movie1, 12);
        cinema3.setMovies(movie3, 20);

        cinemas = new ArrayList<Cinema>();
        cinemas.add(cinema1);
        cinemas.add(cinema2);
        cinemas.add(cinema3);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        // I'll be glad if you explain why this is not working
        // "Fragment cannot be converted to fragment" when is not in the CinemaActivity body

        if(position == 0){
            Fragment fragment = CinemasFragment.newInstance("section_number", 1, cinemas);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        } else if (position == cinemas.size() + 1){
            SharedPreferences pref = getSharedPreferences("userInfo", 0);
            SharedPreferences.Editor edit = pref.edit();
            edit.putString(LOGED, "false");
            edit.commit();
        }
// else {
//            Fragment fragment = CinemaInfoFragment.newInstance("section_number", cinemas.get(position - 1));
//            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.container, fragment)
//                    .commit();
//        }

        if(position > 0 && position <= cinemas.size()){
            currentCinema = cinemas.get(position - 1);
        } else {
            currentCinema = null;
        }
    }

    public void onSectionAttached(int number) {

        if (number == 1) {
            mTitle = getString(R.string.title_section1);
        } else if(number > 1 && number <= cinemas.size() + 1){
            mTitle = cinemas.get(number - 2).getName();
        } else {
            mTitle = getString(R.string.title_section3);
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    public void setActionBarTabs(){
        if(currentCinema != null) {
            ActionBar actionBarTab = getSupportActionBar();

            actionBarTab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            actionBarTab.setDisplayShowTitleEnabled(true);

            actionBarTab.removeAllTabs();

            for (Movie movie : currentCinema.getMovies()){
                ActionBar.Tab tab = actionBarTab.newTab();
                tab.setText(movie.getName());
                tab.setTabListener(this);
                actionBarTab.addTab(tab);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            SharedPreferences pref = getSharedPreferences(USER_INFO, 0);

            String isLoged = pref.getString(LOGED, null);

            if(isLoged == null || isLoged.equals("false")){
                Intent intent = new Intent(CinemaActivity.this, LoginActivity.class);

                startActivity(intent);
            }

            getMenuInflater().inflate(R.menu.cinema, menu);
            restoreActionBar();
            setActionBarTabs();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Fragment fragment = MovieInfoFragment.newInstance("section_number", currentCinema.getMovies().get(tab.getPosition()), currentCinema);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
