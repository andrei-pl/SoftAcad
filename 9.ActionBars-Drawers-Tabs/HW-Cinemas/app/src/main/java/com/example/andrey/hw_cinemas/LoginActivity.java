package com.example.andrey.hw_cinemas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener {
    private final String USER_INFO = "userInfo";
    private final String USER = "user";
    private final String PASS = "pass";
    private final String CHECKED = "checked";
    private final String LOGED = "loged";

    private EditText edtUser;
    private EditText edtPass;
    private CheckBox chkRemember;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = (EditText) findViewById(R.id.edtloginUser);
        edtPass = (EditText) findViewById(R.id.edtLoginPass);
        chkRemember = (CheckBox) findViewById(R.id.chkLoginRemember);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnRegister = (Button) findViewById(R.id.btnLoginRegister);
        btnRegister.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String user = getValueFromPreferences(USER_INFO, USER);
        String pass = getValueFromPreferences(USER_INFO, PASS);
        String autoLogin = getValueFromPreferences(USER_INFO, CHECKED);
        String loged = getValueFromPreferences(USER_INFO, LOGED);

        if(user != null && pass != null){

            if(autoLogin != null && autoLogin.equals("true") && loged != null && loged.equals("true")){
                edtUser.setText(user);
                edtPass.setText(pass);
                btnLogin.callOnClick();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

        if(viewId == R.id.btnLogin){

            if(!isAllFieldsCorrect()){
                return;
            }

            intent = new Intent(LoginActivity.this, CinemaActivity.class);

        } else if (viewId == R.id.btnLoginRegister){
            intent = new Intent(LoginActivity.this, RegisterActivity.class);
        }

        startActivity(intent);
    }

    private String getValueFromPreferences(String name, String key){
        SharedPreferences pref = getSharedPreferences(name, 0);

        return pref.getString(key, null);
    }

    private boolean isAllFieldsCorrect() {
        String user = edtUser.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        boolean isChecked = chkRemember.isChecked();

        if(user.equals("")){
            Toast.makeText(this, "Enter valid username", Toast.LENGTH_SHORT).show();
            return false;
        } else if (pass.equals("")){
            Toast.makeText(this, "Enter valid password", Toast.LENGTH_SHORT).show();
            return false;
        } else if(!user.equals(getValueFromPreferences(USER_INFO, "user"))){

            Toast.makeText(this, "Invalid username", Toast.LENGTH_SHORT).show();
            return false;
        } else if(!pass.equals(getValueFromPreferences(USER_INFO, "pass"))){

            Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show();
            return false;
        }

        SharedPreferences pref = getSharedPreferences(USER_INFO, 0);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(CHECKED, String.valueOf(isChecked));
        edit.putString(LOGED, "true");
        edit.commit();

        return true;
    }
}
