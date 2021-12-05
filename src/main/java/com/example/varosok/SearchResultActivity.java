package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {
    private String orszag;
    private Button btn;
    private TextView textview;
    private DBHelper adatbazis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        init();
        Cursor adatok= adatbazis.listaz();
        Boolean benne=false;
            while (adatok.moveToNext()){
                if (adatok.getString(2).equals(orszag)) {
                    benne = true;
                }
            }
            if (!benne){
                textview.setText("Nem található rekord a következő adattal: "+orszag);
            }
        if (benne){
            StringBuilder sb = new StringBuilder();
            while (adatok.moveToNext()){
                if (adatok.getString(2).equals(orszag)){
                    sb.append("ID: ").append(adatok.getInt(0));
                    sb.append(System.lineSeparator());
                    sb.append("Név: ").append(adatok.getString(1));
                    sb.append(System.lineSeparator());
                    sb.append("Ország: ").append(adatok.getString(2));
                    sb.append(System.lineSeparator());
                    sb.append("Lakosság: ").append(adatok.getInt(3));
                    sb.append(System.lineSeparator());
                    sb.append(System.lineSeparator());
                }
            }
            textview.setText(sb.toString());
        }
        btn.setOnClickListener(view -> {
            Intent vissza=new Intent(SearchResultActivity.this,MainActivity.class);
            startActivity(vissza);
            finish();
        });
    }
    private void init(){
        Bundle extras = getIntent().getExtras();
        orszag = extras.getString("orszag");
        btn=findViewById(R.id.btn);
        adatbazis=new DBHelper(this);
        textview=findViewById(R.id.textview);
    }
}