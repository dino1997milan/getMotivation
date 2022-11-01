package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

//import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.MainActivity.setAuthLoginNull;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordDimenticata extends AppCompatActivity implements View.OnClickListener {

    private TextView buttonBack;
    private EditText email;
    private MaterialButton buttonResetPassword;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_dimenticata);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        email = findViewById(R.id.email_password_dimenticata);
        buttonResetPassword = findViewById(R.id.button_reset);
        buttonBack = findViewById(R.id.button_back);


        buttonResetPassword.setOnClickListener(this);
        buttonBack.setOnClickListener(this); // qua rendo la textView cliccabile per dare enfasi sul tasto che permette di cambiare password

        auth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_back)
        {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        else  if(view.getId() == R.id.button_reset)
        {if(email.getText().toString().length()==0){
            Toast.makeText(PasswordDimenticata.this, "Inserire una email!", Toast.LENGTH_SHORT).show();
        }
            else{
            resetPassword(email.getText().toString());}
        }
    }

    private void resetPassword(final String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(PasswordDimenticata.this, "La password è stata inviata alla mail: "+email, Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(PasswordDimenticata.this, "Invio password fallito", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(PasswordDimenticata.this, LoginActivity.class);
        //setAuthLoginNull();
        startActivity(a);
    }
}
