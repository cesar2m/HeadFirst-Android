package com.cesar2m.secc01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView txtVw;
    private Button btnGoShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtVw = (TextView) findViewById(R.id.txtVwInit);
        btnGoShare = findViewById(R.id.btnGoShare);

        btnGoShare.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intentSecondToThird  = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intentSecondToThird);

            }
        });
/*

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("usrName", usrName);
        startActivity(intent);

 */

        //Tomar los datos del intent
        Bundle bundle = getIntent().getExtras();
        if(null != bundle && null != bundle.getString("usrName")
                && !bundle.getString("usrName").trim().isEmpty()){

            txtVw.setText("Wellcome " + bundle.getString("usrName"));
            Toast.makeText(SecondActivity.this, "Usuario " + bundle.getString("usrName") , Toast.LENGTH_SHORT).show();
        }else{
            txtVw.setText("Proporcione su nombre pro favor." );
            Toast.makeText(SecondActivity.this, "Sin nombre de usuario.", Toast.LENGTH_SHORT).show();
        }

    }
}