package com.example.myapplication382;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication382.datas.MyDbHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    public TextView textView;
    ProgressBar progressBar;
    Button loginn,signupp,signoutt,detectt;
    TextView textView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar=findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.INVISIBLE);
        loginn=findViewById(R.id.button3);
        signupp=findViewById(R.id.button2);
        signoutt=findViewById(R.id.button7);
        detectt=findViewById(R.id.button);
        textView1=findViewById(R.id.textView9);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null)
        {
            signoutt.setVisibility(View.INVISIBLE);
            detectt.setVisibility(View.INVISIBLE);
            textView1.setText("Login/Register first");

        }

        else
        {
            loginn.setVisibility(View.INVISIBLE);
            signupp.setVisibility(View.INVISIBLE);
            textView1.setText(" ");
        }







        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        textView=findViewById(R.id.info);
        if (user!=null)
        {
            textView.setText(user.getEmail());
        }
        else
        {
            textView.setText("Login/Register first!");
        }
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }




    public void detect(View view)
    {
        Intent intent=new Intent(this,detectbluetooth.class);
        startActivity(intent);

    }


    public void login(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            Intent intent = new Intent(this, loogin.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "User already logged in. Signout first to login with a diferent ID", Toast.LENGTH_SHORT).show();
        }
    }


    public void register(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null)
        {
            Intent intent = new Intent(this, reegister.class);
            startActivity(intent);
        }
        else
         {
            Toast.makeText(this, "User already logged in. Signout first to register with a new ID", Toast.LENGTH_SHORT).show();
         }
    }


    public void signout(View view)
    {

        progressBar.setVisibility(View.VISIBLE);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null)
        {
            FirebaseAuth.getInstance().signOut();
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(this,"Signed out successfully",Toast.LENGTH_SHORT).show();
        }

        else
        {   progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(this,"No ID found",Toast.LENGTH_SHORT).show();
        }

        textView.setText("Login/Register first!");
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }








}