package com.example.andrey.hw_cinemas;

import android.app.SharedElementCallback;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends ActionBarActivity implements View.OnClickListener {
    private final String USER_INFO = "userInfo";
    private final String USER = "user";
    private final String PASS = "pass";
    private final String CHECKED = "checked";
    private final String LOGED = "loged";

    private EditText edtUser;
    private EditText edtPass;
    private EditText edtConfPass;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUser = (EditText) findViewById(R.id.edtRegLogin);
        edtPass = (EditText) findViewById(R.id.edtRegPass);
        edtConfPass = (EditText) findViewById(R.id.edtRegConfPass);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

        if(viewId == R.id.btnRegister) {
            if(isAllFieldsCorrect()) {
                intent = new Intent(RegisterActivity.this, CinemaActivity.class);
            }
        }

        startActivity(intent);
    }

    private boolean isAllFieldsCorrect() {
        String user = edtUser.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        String confirmPass = edtConfPass.getText().toString().trim();

        if(user.equals("")){
            Toast.makeText(this, "Enter valid username", Toast.LENGTH_SHORT);
            return false;
        } else if (pass.equals("")){
            Toast.makeText(this, "Enter valid password", Toast.LENGTH_SHORT);
            return false;
        } else if (confirmPass.equals("") || !pass.equals(confirmPass)){
            Toast.makeText(this, "Enter same password again", Toast.LENGTH_SHORT);
            return false;
        }

        SharedPreferences pref = getSharedPreferences(USER_INFO, 0);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(USER, user);
        edit.putString(PASS, pass);
        edit.putString(CHECKED, "false");
        edit.putString(LOGED, "true");
        edit.commit();

        return true;
    }
}
