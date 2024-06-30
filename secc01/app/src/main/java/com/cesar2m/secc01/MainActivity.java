package com.cesar2m.secc01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAceptar = (Button) findViewById(R.id.button);
        btnAceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clicBtnAceptar();
            }
        });
    }


    public void clicBtnAceptar(){
        EditText txt = (EditText) findViewById(R.id.editTextTextPersonName);
        String usrName = txt.getText().toString();

        Toast.makeText(MainActivity.this, "Gracias " + usrName, Toast.LENGTH_SHORT).show();

        //Acceder al sigueinte activity
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("usrName", usrName);
        startActivity(intent);


    }

}