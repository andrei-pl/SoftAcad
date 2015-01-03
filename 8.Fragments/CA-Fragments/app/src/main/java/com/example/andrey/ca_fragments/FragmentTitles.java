package com.example.andrey.ca_fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Andrey on 22.12.2014 г..
 */
public class FragmentTitles extends ListFragment {
    boolean mDualPane;
    int mCurCheckPosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> titles = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, Constants.TITLES);
        setListAdapter(titles);
// Check to see if we have a frame in which to embed the details fragment directly in the containing UI.
        // detailsFrame won't be null at the first time the phone is switched to landscape mode.
        View detailsFrame = getActivity().findViewById(R.id.details);
        Log.d(Constants.TAG, "detailsFrame " + detailsFrame);

        // Check that a view exists and is visible.
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
        Log.d(Constants.TAG, "mDualPane " + mDualPane);

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("currentChoice", 0);
        }

        // Highlight the selected item in the list view .
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        if (mDualPane) {
//            showDetails(mCurCheckPosition);
        } else {
            getListView().setItemChecked(mCurCheckPosition, true);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
