package com.cesar2m.seccion01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.btn = (Button) findViewById(R.id.btnMain);

        //btn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "Se Acepta con Interfaz",Toast.LENGTH_SHORT).show();
    }
}