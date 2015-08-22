package com.example.sasho.homework3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillSharedPreferences();

        Button btn = (Button) findViewById(R.id.addNewBookBtn);
        btn.setOnClickListener(this);

        btn = (Button) findViewById(R.id.browseAllBooksBtn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent nextActivity = null;

        switch(v.getId()){
            case R.id.addNewBookBtn:
                nextActivity = new Intent(MainActivity.this, AddNewBook.class);
                break;
            case R.id.browseAllBooksBtn:
                nextActivity = new Intent(MainActivity.this, BookList.class);
                break;
            default: break;
        }

        if(nextActivity != null)
            startActivity(nextActivity);
    }

    private void fillSharedPreferences(){
        Gson gson = new Gson();

        // get all books from preferences (they saved as json format)
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.booksPrefName), Context.MODE_PRIVATE);
        String booksSaved = sharedPref.getString(getString(R.string.books_db), null);

        // convert json to array list
        ArrayList<Book> allBooks = gson.fromJson(booksSaved, new TypeToken<ArrayList<Book>>(){}.getType());
        if(allBooks == null){
            allBooks = new ArrayList<Book>();

            for(int i = 0; i < 10; i++){
                Book newBook = new Book();

                // set new book
                newBook.setTitle("Title" + String.valueOf(i+1));
                newBook.setPublisher("Publisher" + String.valueOf(i+1));
                newBook.setWriter("Writer" + String.valueOf(i+1));
                newBook.setISBN("ISBN" + String.valueOf(i+1));
                newBook.setYear(i*1000);
                newBook.setLanguage("Language" + String.valueOf(i+1));
                newBook.setDescription("Description" + String.valueOf(i+1));

                // add new book to array
                allBooks.add(newBook);
            }

            // save array in preferences
            SharedPreferences.Editor prefEdit = sharedPref.edit();
            String allBooksJsonFormat = gson.toJson(allBooks);
            prefEdit.putString(getString(R.string.books_db), allBooksJsonFormat);
            prefEdit.commit();
        }
    }
}
