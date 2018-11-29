package com.example.joelruhe.myapplication.authentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.joelruhe.myapplication.R;
import com.example.joelruhe.myapplication.activities.DatabaseActivity;

import static java.util.Calendar.getInstance;

public class LoginActivity extends AppCompatActivity {
    EditText EmailEt, PasswordEt;
    TextView Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EmailEt = (EditText)findViewById(R.id.textEmail);
        PasswordEt = (EditText)findViewById(R.id.textPassword);
        Register = (TextView)findViewById((R.id.register));

        String linkText = "Don't have an " +
                " yet?\n Register here now!";

        SpannableString ss = new SpannableString(linkText);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        };

        ss.setSpan(clickableSpan, 36, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Register.setText(ss);
        Register.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void OnLogin(View view){
        String email = EmailEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        DatabaseActivity databaseActivity = new DatabaseActivity(this);
        databaseActivity.execute(type, email, password);
    }
}