package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private EditText Nev;
    private EditText Orszag;
    private EditText Lakossag;
    private Button btnVissza;
    private Button btnFelvesz;
    private DBHelper adatbazis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();
        btnVissza.setOnClickListener(view -> {
            Intent vissza=new Intent(InsertActivity.this,MainActivity.class);
            startActivity(vissza);
            finish();
        });
        btnFelvesz.setOnClickListener(view -> {
            String nev=Nev.getText().toString().trim();
            String orszag=Orszag.getText().toString().trim();
            String lakossagString=Lakossag.getText().toString().trim();
            if (nev.isEmpty()||orszag.isEmpty()||lakossagString.isEmpty()){
                Toast.makeText(getApplicationContext(), "Minden mező kitöltése kötelező",
                        Toast.LENGTH_SHORT).show();
            }else{
                int lakossag=Integer.parseInt(lakossagString);
                if (lakossag>0){
                    adatbazis.rogzites(nev,orszag,lakossag);
                    Toast.makeText(getApplicationContext(), "Sikeres rögzítés",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Nem lehet nulla",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void init(){
        Nev=findViewById(R.id.et_nev);
        Orszag=findViewById(R.id.et_orszag);
        Lakossag=findViewById(R.id.et_lakossag);
        btnFelvesz=findViewById(R.id.btn_felvesz);
        btnVissza=findViewById(R.id.btn_vissza);
        adatbazis = new DBHelper(this);
    }
}