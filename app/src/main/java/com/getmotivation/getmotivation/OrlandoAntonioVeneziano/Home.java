package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static java.lang.Integer.parseInt;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    //AudioManager mAudioManager;
    //public static MediaPlayer suoni;  //
    //public static Application application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        String email = String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getEmail());
//        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("save",MODE_PRIVATE).edit();
//        editor.putString("email",email);
//        editor.apply();
        //application = getApplication();

        getSupportActionBar().hide();
        Log.d("HomeActivity",String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid()));  //questo metodo sarà fondamentale per creare tabelle differenti in relazione ai diversi utenti loggati
        Log.d("HomeActivity",String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getEmail()));
//        mAudioManager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contenitore_home, new FragmentMenuTendina())
                //.addToBackStack(null)
                .commit();
    }
//    public static void play(View v, int id){
//        if(suoni==null){
//            suoni =MediaPlayer.create(v.getContext(), id);
//        }
//        suoni.start();
//    }

    //nota personale: devi tenere in considerazione che la navigazione nel menu a tendina non è gestita da navigateTo quindi bisogna in qualche modo aggiungere i fragment di navigation nel BackStack
    @Override
    public void onBackPressed() {  //in precedenza ho provato a gestire il BackStack del NavController dichiarandolo statico, però dopo diversi tentativi, mi è sembrata una scelta ardua
//l'unica cosa un pò spiacevole a mio avviso che ho deciso comunque di non gestire è il layout del FragmentLogout che compare se si preme il tasto back in presenza dell'AlertDialog, comunque per render meno spiacevole questo problema ho aggiunto un tasto
        //in questo modo l'utente ha 3 opzioni per tornare alla Home, dal Menu a tendina, col tasto del FragmentLogout e col tasto back del cellulare. (questo ovviamente solo se si dovesse scegliere di premere back in presenza dell'alertDialog anzicchè il tasto negativo "No")

        // questo approccio andrebbe bene col FragmentManager, non può andar bene con NavController perchè non il metodo per visualizzare quanti elementi ci sono nel BackStack non restituisce un intero
        //int count = FragmentMenuTendina.navController.getBackStackEntryCount();
        //if (count == 1) {
        Intent a = new Intent(getApplicationContext(), Home.class);
        startActivity(a);

        //} //else {
        //  FragmentMenuTendina.navController.popBackStack();
        //}
    }

}
