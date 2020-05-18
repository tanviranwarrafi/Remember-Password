package com.example.rememberpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private CheckBox rememberPassword;
    private Button login;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        rememberPassword = findViewById(R.id.remember);
        login = findViewById(R.id.login);

        String PREFS_NAME = "PrefsFile";
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        SharedPreferences sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (sp.contains("username")) {
            String user = sp.getString("username", "not found");
            username.setText(user.toString());
        }
        if (sp.contains("password")){
            String pass = sp.getString("password", "not found");
            password.setText(pass.toString());
        }
        if (sp.contains("check")){
            Boolean bool = sp.getBoolean("check", false);
            rememberPassword.setChecked(bool);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = username.getText().toString();
                String pass_word = password.getText().toString();

                if(user_name.equals("tanvir") && pass_word.equals("rafi")) {

                    if (rememberPassword.isChecked()){

                        Boolean boolIsChecked = rememberPassword.isChecked();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username.getText().toString());
                        editor.putString("password", password.getText().toString());
                        editor.putBoolean("check", boolIsChecked);
                        editor.apply();
                    } else {
                        sharedPreferences.edit().clear().apply();
                    }

                    Toast.makeText(getApplicationContext(), "username: " + user_name+ " & password: " + pass_word, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoggedInActivity.class);
                    startActivity(intent);
                    username.getText().clear();
                    password.getText().clear();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Login Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        startActivity(homeIntent);
        finish();
        System.exit(0);
    }
}
