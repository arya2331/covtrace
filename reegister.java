package com.example.myapplication382;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class reegister extends AppCompatActivity {
    EditText name;
    EditText username;
    EditText email;
    EditText password1;
    EditText password2;
    EditText pin;
    EditText mac;
    EditText phone;
    private FirebaseAuth firebaseAuth;
    Button button;
    FirebaseFirestore db;
    ProgressBar progressBar;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reegister);

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        pin = findViewById(R.id.pin);
        phone = findViewById(R.id.phone);
        button = findViewById(R.id.button4);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        database = FirebaseDatabase.getInstance();
        mac=findViewById(R.id.mac);
    }


    public void switch_to_login(View view) {
        Intent intent = new Intent(this, loogin.class);
        startActivity(intent);
    }


    public void reg(View view) {
        progressBar.setVisibility(View.VISIBLE);
        final String Name = name.getText().toString();
        final String Username = username.getText().toString();
        final String Email = email.getText().toString();
        String Password = password1.getText().toString();
        final String PIN = pin.getText().toString();
        final String MAC = mac.getText().toString();
        final String Phone = phone.getText().toString();


        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful())
                {

                    // Create a new user with a first and last name



                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    Map<String, Object> user = new HashMap<>();
                    user.put("Name", Name);
                    user.put("Username",Username);
                    user.put("Email",Email);
                    user.put("PIN",PIN);
                    user.put("MAC Address",MAC);
                    user.put("Phone",Phone);



// Add a new document with a generated ID
                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(reegister.this,"Welcome "+ Name+ " Successfully registered! Proceed to login",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(reegister.this, loogin.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(reegister.this,"User details uploading failed. Try again",Toast.LENGTH_SHORT).show();
                                }
                            });




                }

                else

                 {

                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(reegister.this, "User already exists. Proceed to login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(reegister.this, loogin.class));


                }
            }
        });
    }
}



