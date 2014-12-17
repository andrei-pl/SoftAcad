package com.example.andrey.mycars;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;


public class NewCarActivity extends ActionBarActivity implements View.OnClickListener{

    private static final int CAMERA_REQUEST = 1888;

    EditText edtModel;
    EditText edtYear;
    EditText edtCondition;
    Button btnAdd;
    Button addPhoto;
    ImageView image;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car);

        edtModel = (EditText) findViewById(R.id.edtModel);
        edtYear = (EditText) findViewById(R.id.edtYear);
        edtCondition = (EditText) findViewById(R.id.edtCondition);
        image = (ImageView) findViewById(R.id.imgPhoto);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        addPhoto = (Button) findViewById(R.id.btnTakePhoto);
        addPhoto.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_car, menu);
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

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Intent intent = new Intent();

        if(viewId == R.id.btnTakePhoto){

            intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST);

        } else if(viewId == R.id.btnAdd) {
            if (this.isValidInfo()) {
                SaveInfoIntoParse();

                intent = new Intent(NewCarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("image", photo);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        try{
            Bitmap bm = (Bitmap) savedInstanceState.getParcelable("image");
            image.setImageBitmap(bm);
            photo = bm;
        } catch (Exception ex) {
        }
    }

    private void SaveInfoIntoParse() {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 20, stream);
        byte[] byteArray = stream.toByteArray();
        ParseFile parseImage = new ParseFile("image.jpg", byteArray);

        String model = edtModel.getText().toString();
        String year = edtYear.getText().toString();
        String condition = edtCondition.getText().toString();


        Parse.initialize(this, "sCCxWbG55eT387mS6E2Drim79L1FLX2D5OCWTLYA", "qTBkWsIihPxk2l9qeNLu2YIawgjen3XgaHrV96Vr");
        ParseObject testObject = new ParseObject("MyCars");
        testObject.put("model", model);
        testObject.put("year", year);
        testObject.put("condition", condition);
        testObject.put("image", parseImage);
        testObject.saveInBackground();
    }

    private boolean isValidInfo(){
        String modelText = edtModel.getText().toString();
        String yearText = edtYear.getText().toString();
        String conditionText = edtCondition.getText().toString();

        if(modelText.trim().equals("") || yearText.trim().equals("") || conditionText.trim().equals("") ){
            Toast.makeText(NewCarActivity.this, "Every field must not be empty or whitespace", Toast.LENGTH_SHORT).show();
            return false;
        } else if (modelText.length() < 2 || modelText.length() > 15){
            Toast.makeText(NewCarActivity.this, "Car model must be between 2 and 15 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else if (conditionText.trim().length() < 3 || conditionText.trim().length() > 15){
            Toast.makeText(NewCarActivity.this, "Condition text must be between 3 and 15 characters.", Toast.LENGTH_SHORT).show();
            return false;
        } else if(yearText.trim().length() != 4 || !yearText.matches("\\d+")){
            Toast.makeText(NewCarActivity.this, "Wrong year buddy. Try again!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (yearText.matches("\\d+")) {
            int yearInt = Integer.parseInt(yearText);

            if(yearInt < 1900 || yearInt > 2015){
                Toast.makeText(NewCarActivity.this, "Wrong year buddy. Try again!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        if (image.getDrawable() == null){
            Toast.makeText(NewCarActivity.this, "Give me some picture!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(photo);
        }
    }
}
