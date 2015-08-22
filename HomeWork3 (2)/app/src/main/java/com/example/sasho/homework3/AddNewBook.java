package com.example.sasho.homework3;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class AddNewBook extends Activity implements View.OnClickListener{
    EditText title;
    EditText publisher;
    EditText writer;
    EditText isbn;
    EditText year;
    EditText language;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);

        init();
    }

    private void init(){
        title = (EditText) findViewById(R.id.titleET);
        publisher = (EditText) findViewById(R.id.publisherET);
        writer = (EditText) findViewById(R.id.writtenByET);
        isbn = (EditText) findViewById(R.id.isbnET);
        year = (EditText) findViewById(R.id.yearET);
        language = (EditText) findViewById(R.id.langET);
        description = (EditText) findViewById(R.id.descET);

        Button btn = (Button) findViewById(R.id.saveNewBookBtn);
        btn.setOnClickListener(this);

        btn = (Button) findViewById(R.id.cancelNewBookBtn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveNewBookBtn:
                saveNewBook();
                finish();
                break;
            case R.id.cancelNewBookBtn:
                finish();
                break;
        }
    }

    void saveNewBook(){
        Book newBook = new Book();
        Gson gson = new Gson();

        // set new book
        newBook.setTitle(this.title.getText().toString());
        newBook.setPublisher(this.publisher.getText().toString());
        newBook.setWriter(this.writer.getText().toString());
        newBook.setISBN(this.isbn.getText().toString());
        newBook.setYear(Integer.parseInt(this.year.getText().toString()));
        newBook.setLanguage(this.language.getText().toString());
        newBook.setDescription(this.description.getText().toString());

        // get all books from preferences (they saved as json format)
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.booksPrefName), Context.MODE_PRIVATE);
        String booksSaved = sharedPref.getString(getString(R.string.books_db), null);

        // convert json to array list
        ArrayList<Book> allBooks = gson.fromJson(booksSaved, new TypeToken<ArrayList<Book>>(){}.getType());
        if(allBooks == null)
            allBooks = new ArrayList<Book>();

        // add new book to array
        allBooks.add(newBook);

        // save array in preferences
        SharedPreferences.Editor prefEdit = sharedPref.edit();
        String allBooksJsonFormat = gson.toJson(allBooks);
        prefEdit.putString(getString(R.string.books_db), allBooksJsonFormat);
        prefEdit.commit();
    }
}
