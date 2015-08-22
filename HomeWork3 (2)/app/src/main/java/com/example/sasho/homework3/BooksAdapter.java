package com.example.sasho.homework3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sasho on 9.12.2014 г..
 */
public class BooksAdapter extends BaseAdapter {
    ArrayList<Book> booksList = new ArrayList<Book>();
    private Context context;

    public BooksAdapter(ArrayList<Book> booksList, Context context) {
        this.booksList = booksList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return booksList.size();
    }

    @Override
    public Book getItem(int position) {
        return booksList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Book currBook = getItem(position);
        TextView bookTitle;
        TextView bookDescription;

        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.books_list_item, parent, false);

            bookTitle       = (TextView) convertView.findViewById(R.id.singleBookTitle);
            bookDescription = (TextView) convertView.findViewById(R.id.singleBookDescription);

            convertView.setTag(R.id.singleBookTitle, bookTitle);
            convertView.setTag(R.id.singleBookDescription, bookDescription);
        }
        else {
            bookTitle       = (TextView) convertView.getTag(R.id.singleBookTitle);
            bookDescription = (TextView) convertView.getTag(R.id.singleBookDescription);
        }

        bookTitle.setText(currBook.getTitle());
        bookDescription.setText(currBook.getDescription());

        return convertView;
    }
}
