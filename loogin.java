package com.example.myapplication382;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loogin extends AppCompatActivity {

    EditText email, password;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loogin);

        email = findViewById(R.id.mail);
        password = findViewById(R.id.pass);
        mAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void switch_to_register(View view) {
        Intent intent = new Intent(this, reegister.class);
        startActivity(intent);
    }

    public void login(View view) {


            final String Email = email.getText().toString();
            String Password = password.getText().toString();


            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(loogin.this, "Successfully logged in with " + Email, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(loogin.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });

        }

}