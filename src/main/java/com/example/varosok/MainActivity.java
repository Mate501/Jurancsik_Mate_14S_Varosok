package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnUj;
    private Button btnKeres;
    private EditText bevitel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnKeres.setOnClickListener(v->{
            String orszag=bevitel.getText().toString().trim();
            String Nev=bevitel.getText().toString().trim();
            String Lakossag=bevitel.getText().toString().trim();
            String ID=bevitel.getText().toString().trim();
            if (orszag.equals("") ||orszag.isEmpty() || Nev.equals("") ||Nev.isEmpty() || Lakossag.equals("") ||Lakossag.isEmpty() ||ID.equals("") ||ID.isEmpty()){
                Toast.makeText(this, "A mezőt nem lehet üresen hagyni", Toast.LENGTH_SHORT).show();
            }else {
                Intent kereses=new Intent(MainActivity.this,SearchResultActivity.class);
                kereses.putExtra("orszag",orszag);
                kereses.putExtra("Nev",Nev);
                kereses.putExtra("Lakosssag",Lakossag);
                kereses.putExtra("ID",ID);
                startActivity(kereses);
                finish();
            }
        });
        btnUj.setOnClickListener(v->{
                Intent felvetel=new Intent(MainActivity.this,InsertActivity.class);
                startActivity(felvetel);
                finish();
        });
    }
    private void init(){
        btnUj=findViewById(R.id.btn_uj);
        btnKeres=findViewById(R.id.btn_kereses);
        bevitel=findViewById(R.id.bevitel);
    }
}