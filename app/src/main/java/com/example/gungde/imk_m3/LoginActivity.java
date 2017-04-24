package com.example.gungde.imk_m3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btn_login);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPassword.getText().toString();
                login(email, pass);
            }
        });
    }

    private void login(String email, String pass) {
        if (email.equals("") || pass.equals("")) {
            Toast.makeText(this, "Fill Can't be Blank", Toast.LENGTH_SHORT).show();
        } else if (email.equals("system") && pass.equals("system")) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        setAlertDialogBuilder("Info", "Anda yakin ingin keluar?");
    }

    private void setAlertDialogBuilder(String title, String message) {
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog = builder.create();
        alertDialog.show();
    }

}
