package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;


import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText input_password;
    TextInputEditText input_email;
    MaterialButton buttonLogin;
    Button salta;
    TextView buttonRegistrazione, buttonPasswordDimenticata;

    private static FirebaseAuth authLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buttonLogin = findViewById(R.id.button_login);
        buttonPasswordDimenticata = findViewById(R.id.button_password_dimenticata);
        input_password = findViewById(R.id.login_password);
        input_email = findViewById(R.id.login_email);
        buttonRegistrazione = findViewById(R.id.button_registrati);
        salta = findViewById(R.id.button_salta);
        input_password.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        salta.setOnClickListener(this);
        buttonRegistrazione.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
        buttonPasswordDimenticata.setOnClickListener(this);

        authLogin = FirebaseAuth.getInstance(); //in questo modo creiamo un'istanza di FirebaseAuth

        //Se l'autenticazione è già stata effettuata in precedenza -> Si entra nell'applicazione
        if (authLogin.getCurrentUser() != null)
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        //qua ho utilizzato l'istanza della classe FirebaseAuth per valutare se l'utente era già stato loggato, se con un precedente uso dell'app era già stata fatta l'autenticazione, allora all'apertura l'utente viene subito mandato alla MainActivity
    }
    //nota per me stesso: se dalle schermate della password dimenticata dovesse tornare col tasto back nuovamente dentro l'app gestisci il signOut a dovere
    // oppure molto meglio se si impone che il tasto back della mainActivity porta direttamente alla schermata home del telefono
    // in questo modo si evita che premendo back dalla mainActivity si torna a schermate Login,Registrazione, PasswordDimenticata restando loggati, ulteriori pressioni del tasto back da queste schermate farebbero tornare in sessione (avrebbe poco senso)
    //per eseguire il signOut:
    // auth.signOut();

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_password_dimenticata) {
            startActivity(new Intent(LoginActivity.this, PasswordDimenticata.class));
            finish();
        }else if (view.getId() == R.id.button_salta) {
            Toast.makeText(LoginActivity.this,"questa funzione va implementata sistemando il codice condiviso con dei dati personali",Toast.LENGTH_SHORT).show();
            // DOPO AVER MESSO I TUOI DATI PUOI RIMUOVERE TOST E COMPILARE IL RESTO DEL CODICE CHE VEDI QUI

            //authLogin.signInWithEmailAndPassword("deviInserireUnaTuaEmail.com","laTuaPassword");
            //SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
            //editor.putBoolean("salta",true);
            //editor.apply();
            //startActivity(new Intent(LoginActivity.this, MainActivity.class));
            //finish();
        } else if (view.getId() == R.id.button_registrati) {
            startActivity(new Intent(LoginActivity.this, ActivityRegistrazione.class));
            finish();
        } else if (view.getId() == R.id.button_login) {
            if (input_password.getText().length() == 0 | input_email.getText().length() == 0){
                Toast.makeText(LoginActivity.this, "Inserire dati nei campi mancanti!", Toast.LENGTH_SHORT).show();
            } //senza questo controllo il sistema mi farebbe abortire l'applicazione
            else{
                loginUser(input_email.getText().toString(), input_password.getText().toString());
            }
        }
    }
    private void loginUser(String email, final String password) {
        authLogin.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                Toast.makeText(LoginActivity.this, "La lunghezza della password deve essere almeno di 6 caratteri", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Errore:"+ task.getException(), Toast.LENGTH_SHORT).show();
                            } //qui ho preferito stampare a video un messaggio di errore autogenerato da getException(), per fare comunque in modo che l'utente si renda conto della presenza di errori nell'inserimento dei dati
                        } else {
                            SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                            editor.putBoolean("salta",false);
                            editor.apply();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }
                });

    }
//gestisco la pressione del tasto che mi fa tornare indietro per ritornare al menu home del cellulare
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
    //nota personale: nella classe ComponentActivity ci sono molti altri metodi che possono tornare utili, fai la ricerca su app G.D.


}

