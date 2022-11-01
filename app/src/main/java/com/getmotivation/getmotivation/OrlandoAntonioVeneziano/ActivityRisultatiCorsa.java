package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class ActivityRisultatiCorsa extends AppCompatActivity {
//    SharedPreferences sharedPreferences;
//    TextView superamentoObPassi,superamentoObKm,superamentoObKcal,risultatoPassi,risultatoKm,risultatoKcal;
//    MaterialButton home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risultati_corsa);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contenitoreRisultati, new FragmentRisultatiCorsa())
                .commit();

//        risultatoPassi = findViewById(R.id.testoRisultatoPassi);
//        risultatoKm = findViewById(R.id.testoRisultatoDistanza);
//        risultatoKcal = findViewById(R.id.testoRisultatoKcal);
//        superamentoObKcal = findViewById(R.id.obbiettivoRaggiuntoKcal);
//        superamentoObKm = findViewById(R.id.obbiettivoRaggiuntoKm);
//        superamentoObPassi = findViewById(R.id.obbiettivoRaggiuntoPassi);
//        home = findViewById(R.id.tornaAllaHome);
//        sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);
//        int obPassi = sharedPreferences.getInt("obbiettivo passi",10000);
//        float obKm = sharedPreferences.getFloat("obbiettivo km",3);
//        int obKcal =sharedPreferences.getInt("obbiettivo kcal",200);
//        int passi = parseInt(sharedPreferences.getString("risultato passi","0"));
//        float km = parseFloat(sharedPreferences.getString("risultato km","0"));
//        float kcal = parseFloat(sharedPreferences.getString("risultato kcal","0"));
//
//
//        double km1= (double)km;
//        km1 = Math.ceil(km1*1000)/1000;
//        double kcal1= (double)kcal;
//        kcal1 = Math.ceil(kcal1*1000)/1000;
//        risultatoPassi.setText("passi: "+passi);
//        risultatoKm.setText("km: "+km1);
//        risultatoKcal.setText("kcal: "+kcal1);
//
//        if(passi>obPassi){
//            superamentoObPassi.setVisibility(View.VISIBLE);
//        }
//        if(km>obKm){
//            superamentoObKm.setVisibility(View.VISIBLE);
//        }
//        if(kcal>obKcal){
//            superamentoObKcal.setVisibility(View.VISIBLE);
//        }
//
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ActivityRisultatiCorsa.this,Home.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }
}