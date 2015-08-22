package com.example.sasho.homework3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class BookList extends Activity implements ListView.OnItemClickListener{
    ArrayList<Book> allBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        // get all books in preferences
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.booksPrefName), Context.MODE_PRIVATE);
        String booksSaved = sharedPref.getString(getString(R.string.books_db), "");

        // convert bookx in array list and give them to the adapter
        allBooks = new Gson().fromJson(booksSaved, new TypeToken<ArrayList<Book>>(){}.getType());
        BooksAdapter booksAdp = new BooksAdapter(allBooks, this);

        // fill list with books
        ListView booksList = (ListView) findViewById(R.id.allBooksList);
        booksList.setAdapter(booksAdp);
        booksList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent bookDetails = new Intent(BookList.this, BookDetails.class);

        bookDetails.putExtra(getString(R.string.book_title),     allBooks.get(position).getTitle());
        bookDetails.putExtra(getString(R.string.book_publisher), allBooks.get(position).getPublisher());
        bookDetails.putExtra(getString(R.string.book_writer),    allBooks.get(position).getWriter());
        bookDetails.putExtra(getString(R.string.book_isbn),      allBooks.get(position).getISBN());
        bookDetails.putExtra(getString(R.string.book_year),      String.valueOf(allBooks.get(position).getYear()));
        bookDetails.putExtra(getString(R.string.book_lang),      allBooks.get(position).getLanguage());
        bookDetails.putExtra(getString(R.string.book_desc),      allBooks.get(position).getDescription());

        startActivity(bookDetails);
    }
}
