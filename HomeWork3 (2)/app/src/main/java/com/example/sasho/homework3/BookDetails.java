package com.example.sasho.homework3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;


public class BookDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String bookInfo = "";

        bookInfo += "TITLE: "         + intent.getStringExtra(getString(R.string.book_title));
        bookInfo += "\nPUBLISHER: "   + intent.getStringExtra(getString(R.string.book_publisher));
        bookInfo += "\nWRITTEN BY: "  + intent.getStringExtra(getString(R.string.book_writer));
        bookInfo += "\nISBN: "        + intent.getStringExtra(getString(R.string.book_isbn));
        bookInfo += "\nYEAR: "        + intent.getStringExtra(getString(R.string.book_year));
        bookInfo += "\nLANGUAGE: "    + intent.getStringExtra(getString(R.string.book_lang));
        bookInfo += "\nDESCRIPTION: " + intent.getStringExtra(getString(R.string.book_desc));

        TextView textV = new TextView(this);
        textV.setText(bookInfo);

        setContentView(textV);
    }
}
