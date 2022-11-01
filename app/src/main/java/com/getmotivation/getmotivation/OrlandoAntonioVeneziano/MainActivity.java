package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

//import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.MainActivity.setAuthLoginNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ArrayList<Boolean> valSwitch;
        ArrayList<String> chiaviSpListMain = new ArrayList<String>();
        valSwitch = new ArrayList<>();
        //setto nomi chiavi, per recuperare booleani precedenti
        chiaviSpListMain.add("value1");
        chiaviSpListMain.add("value2");
        chiaviSpListMain.add("value3");
        chiaviSpListMain.add("value4");
        chiaviSpListMain.add("value5");
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("save", MODE_PRIVATE);
        for (int i = 0; i < chiaviSpListMain.size(); i++) {
            valSwitch.add(sharedPreferences.getBoolean(chiaviSpListMain.get(i), false));

        }
        for(int i=0; i<valSwitch.size();i++){
            if(valSwitch.get(i)){
                startActivity(new Intent(getApplicationContext(), Home.class));
            }  //se tutti i controlli (login già eseguito e switch già selezionati in passato) vanno bene così si va direttamente alla home activity
        }

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contenitore, new SetMotivatorFragment())
                    .commit();
    }

//    @Override
//    public void navigateTo(Fragment fragment) {
//        FragmentTransaction mTransaction = getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.contenitore, fragment);
//        mTransaction.commit();
//    }

    @Override
    public void onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }


}