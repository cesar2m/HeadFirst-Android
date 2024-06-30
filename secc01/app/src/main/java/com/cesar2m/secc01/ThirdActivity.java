package com.cesar2m.secc01;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.renderscript.RenderScript;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton btnPhone;
    private ImageButton btnWeb;
    private ImageButton btnCamera;

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {

                    if(result.getResultCode() == Activity.RESULT_OK){

                        Intent data = result.getData();
                        Log.d("TAG Mio","Ejecutanod actividad: " + data);
                        Log.i("TAG Mio","Ejecutanod actividad" + data) ;



                    }else{

                        Log.e("TAG Miooo","ERROR: Ejecutanod actividad: " + result.getResultCode());

                    }
                }
            }
    );

    private final int PHONE_CALL_CODE = 100;
    private final int RECORD_AUDIO_USE_CODE = 200;
    private final int CONTACTS_USE_CODE = 300;
    private final int CAMERA_USE_CODE = 400;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextWeb = findViewById(R.id.editTextWeb);
        btnPhone = findViewById(R.id.imageButtonPhone);
        btnWeb = findViewById(R.id.imageButtonTextWeb);
        btnCamera = findViewById(R.id.btnCamera);

        btnPhone.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String strPhoneNumber =  editTextPhone.getText().toString();

                if(null != strPhoneNumber){
                    //comprobar versión actual de Android que estamos corriendo.

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},PHONE_CALL_CODE );
                    }else{
                        OlderVersions(strPhoneNumber);
                    }

                }
            }

            private void OlderVersions(String strPhoneNumber){

                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + strPhoneNumber));

                if(CheckPermissions(Manifest.permission.CALL_PHONE)){

                    startActivity(intentCall);
                }else{

                    Toast.makeText(ThirdActivity.this, "Acceso declinado", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO_USE_CODE);
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, CONTACTS_USE_CODE);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode ){
            case PHONE_CALL_CODE:

                String permission = permissions[0];
                int result = grantResults[0];

                if(permission.equals(Manifest.permission.CALL_PHONE)){

                    if(result == PackageManager.PERMISSION_GRANTED){
                        //Concedio
                        String phoneNum = editTextPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNum));
                        someActivityResultLauncher.launch(intentCall);
                        //startActivity(intentCall);
                        //Toast.makeText(this, "Permiso concedido.", Toast.LENGTH_SHORT).show();

                    }else{
                        //No concedio
                        Toast.makeText(this, "Permiso NO concedido.", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case RECORD_AUDIO_USE_CODE:

                for (int i= 0; i < permissions.length; i++) {

                    if(permissions[i].equals(Manifest.permission.RECORD_AUDIO)){

                        if(grantResults[i] == PackageManager.PERMISSION_GRANTED ){

                            try {

                                Intent intentRecordAudio = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                                someActivityResultLauncher.launch(intentRecordAudio);

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                }

                break;

            case CONTACTS_USE_CODE:

                for (int i= 0; i < permissions.length; i++) {

                    if(permissions[i].equals(Manifest.permission.READ_CONTACTS)){

                        if(grantResults[i] == PackageManager.PERMISSION_GRANTED ){

                            try {

                                Intent intentReadContacts = new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI);
                                someActivityResultLauncher.launch(intentReadContacts);

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;

        }

    }

    private boolean CheckPermissions(String permission){

        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}