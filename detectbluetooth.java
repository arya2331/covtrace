package com.example.myapplication382;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class detectbluetooth extends AppCompatActivity {
    public static final String MSG="package";

    private static final int REQUEST_ENABLE_BT = 99;
    ListView listView,listView1;
    List<String> devices;
    ArrayAdapter<String> arrayAdapter;
    BluetoothAdapter bluetoothAdapter;
    DatabaseHelper myDb;
    List<String> res;
    Button button;
    private ScheduledExecutorService scheduleTaskExecutor;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detectbluetooth);

        listView = findViewById(R.id.listview);

        devices = new ArrayList<>();
        myDb=new DatabaseHelper(this);
        textView=findViewById(R.id.textView4);
        textView.setText("Discovering");
        button=findViewById(R.id.button9);
        scheduleTaskExecutor= Executors.newScheduledThreadPool(1);


        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
        }

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }







        res= myDb.getAll();
        ArrayAdapter arrayAdapter2=new ArrayAdapter<String>(getApplicationContext() ,android.R.layout.simple_list_item_1,res);






        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run() {

                start(button);
            }

        }, 0, 1, TimeUnit.MINUTES);





        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run() {

                Intent dIntent =  new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                dIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                startActivity(dIntent);

            }

        }, 0, 300, TimeUnit.SECONDS);



    }


    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress();         // MAC address

                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String strDate = dateFormat.format(date);


                myDb.insertData(deviceName,deviceHardwareAddress,strDate);






                devices.add("Device: " +deviceName + "\n" + '\n' +"MAC Address: " + deviceHardwareAddress + "\n" + "\n" + strDate);

                arrayAdapter=new ArrayAdapter<String>(getApplicationContext() ,android.R.layout.simple_list_item_1,devices);
                listView.setAdapter(arrayAdapter);
                int j=devices.size();
                String g=Integer.toString(j);
                textView.setText("Total " + g +" results found");



            }
        }
    };






    public void click(View view) {

        Intent intent=new Intent(this,pastDetections.class);
        intent.putStringArrayListExtra(MSG, (ArrayList<String>) res);
        startActivity(intent);
    }



    public void start(View view) {




                bluetoothAdapter.startDiscovery();
                // Register for broadcasts when a device is discovered.
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                registerReceiver(receiver, filter);








    }













}

