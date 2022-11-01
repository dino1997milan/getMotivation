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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityRegistrazione extends AppCompatActivity implements View.OnClickListener {

    //TextInputLayout layoutPassword, layoutEmail;
    MaterialButton buttonRegistrazione;
    TextInputEditText input_password;
    TextView buttonLogin;
    TextInputEditText input_email;
    TextView buttonPasswordDimenticata;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buttonRegistrazione = findViewById(R.id.button_registrazione_reg_xml);
        buttonLogin = findViewById(R.id.button_login_regestrazione_xml);
        buttonPasswordDimenticata = findViewById(R.id.button_password_dimenticata_registrazione_xml);
        input_email = findViewById(R.id.email_registrazione);
        input_password = findViewById(R.id.password_registrazione);
        input_password.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        buttonRegistrazione.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
        buttonPasswordDimenticata.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_login_regestrazione_xml){
            startActivity(new Intent(ActivityRegistrazione.this, LoginActivity.class));
            finish();
        }
        else if(view.getId() == R.id.button_password_dimenticata_registrazione_xml){
            startActivity(new Intent(ActivityRegistrazione.this, PasswordDimenticata.class));
            finish();
        }
        else if(view.getId() == R.id.button_registrazione_reg_xml){
            if(input_email.getText().toString().length()==0 | input_password.getText().toString().length()==0){
                Toast.makeText(ActivityRegistrazione.this, "Inserire dati nei campi mancanti!", Toast.LENGTH_SHORT).show();
            }
            else{
                signUpUser(input_email.getText().toString(), input_password.getText().toString());
            }
        }
    }

    private void signUpUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(ActivityRegistrazione.this,"Errore: "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(ActivityRegistrazione.this, "Registrazione avvenuta con successo! ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(ActivityRegistrazione.this, LoginActivity.class);
        //setAuthLoginNull();
        startActivity(a);
    }
}
