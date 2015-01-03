package com.example.andrey.fragments;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.d("log", "onStart");
        }

        @Override
        public void onResume() {
            super.onResume();
            Log.d("log", "onResume");
        }

        @Override
        public void onPause() {
            super.onPause();
            Log.d("log", "onPause");
        }

        @Override
        public void onStop() {
            super.onStop();
            Log.d("log", "onStop");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d("log", "onDestroy");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            Log.d("log", "onDetach");
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.d("log", "onActivityCreated");
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            Log.d("log", "onAttach");
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d("log", "onCreate");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            Log.d("log", "onDestroyView");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            Log.d("log", "onCreateView");
            return rootView;
        }
    }
}
