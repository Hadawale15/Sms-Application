package com.example.smsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText num,mes;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = findViewById(R.id.Mobile_No_id1);
        mes = findViewById(R.id.Sms_id1);
        send = findViewById(R.id.send_button_id1);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int permission= ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.SEND_SMS);
                if (permission== PackageManager.PERMISSION_GRANTED){
                    SendMethod();
                }else
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},0);
                }
            }
        });

    }

    private void SendMethod() {
        String NUMBER=num.getText().toString().trim();
        String MESSAGE=mes.getText().toString().trim();

        if(!NUMBER.isEmpty()&&!MESSAGE.isEmpty()){
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(NUMBER,null,MESSAGE,null,null);
            Toast.makeText(MainActivity.this, "Message Sent", Toast.LENGTH_SHORT).show();
            num.setText("");

            mes.setText("");
        }else
            Toast.makeText(MainActivity.this, "Please enter a detils", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:
                if (grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    SendMethod();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "You Don't have permission", Toast.LENGTH_SHORT).show();
                }
        }
    }
}