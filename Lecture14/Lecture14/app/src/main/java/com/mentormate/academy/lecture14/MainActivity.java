package com.mentormate.academy.lecture14;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private String savedString = "ASD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("SAVED_STRING", "new value");
        editor.commit();
        if (sp.contains("SAVED_STRING")) {
            savedString = sp.getString("SAVED_STRING", "");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    What is the Content Resolver?
    The Content Resolver is the single, global instance in your application that provides access to your
    (and other applications') content providers. The Content Resolver behaves exactly as its name implies:
    it accepts requests from clients, and resolves these requests by directing them to the content provider
    with a distinct authority. To do this, the Content Resolver stores a mapping from authorities to
    Content Providers. This design is important, as it allows a simple and secure means of accessing other
    applications' Content Providers.
    The Content Resolver includes the CRUD (create, read, update, delete) methods corresponding to the
    abstract methods (insert, query, update, delete) in the Content Provider class. The Content Resolver
    does not know the implementation of the Content Providers it is interacting with (nor does it need to know);
    each method is passed an URI that specifies the Content Provider to interact with.

    What is a Content Provider?
    Whereas the Content Resolver provides an abstraction from the application's Content Providers,
    Content Providers provide an abstraction from the underlying data source (i.e. a SQLite database).
    They provide mechanisms for defining data security (i.e. by enforcing read/write permissions) and offer
    a standard interface that connects data in one process with code running in another process.
    Content Providers provide an interface for publishing and consuming data, based around a simple URI
    addressing model using the content:// schema. They enable you to decouple your application layers from the
    underlying data layers, making your application data-source agnostic by abstracting the underlying data source.
    */

    public void deleteAllBirthdays (View view) {
        // Delete all the records and the table of the database provider
        int count = getContentResolver().delete(Constants.CONTENT_URI, null, null);
        Toast.makeText(this, count + " records are deleted.", Toast.LENGTH_LONG).show();
    }

    public void addBirthday(View view) {
        // Add a new birthday record
        ContentValues values = new ContentValues();
        values.put(Constants.DB_NAME, ((EditText)findViewById(R.id.name)).getText().toString());
        values.put(Constants.DB_BIRTHDAY, ((EditText)findViewById(R.id.birthday)).getText().toString());
        Uri uri = getContentResolver().insert(Constants.CONTENT_URI, values);
        Toast.makeText(this, uri.toString() + " inserted!", Toast.LENGTH_LONG).show();
    }

    public void showAllBirthdays(View view) {

        // Show all the birthdays sorted by friend's name
        Cursor c = getContentResolver().query(Constants.CONTENT_URI, null, null, null, "name");
        String result = "Results:";
        if (!c.moveToFirst()) {
            Toast.makeText(this, result+" no content yet!", Toast.LENGTH_LONG).show();
        } else {
            do{
                result = result + "\n" + c.getString(c.getColumnIndex(Constants.DB_NAME)) +
                        " with id " +  c.getString(c.getColumnIndex(Constants.DB_ID)) +
                        " has birthday: " + c.getString(c.getColumnIndex(Constants.DB_BIRTHDAY));
            } while (c.moveToNext());
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }
    }
}
