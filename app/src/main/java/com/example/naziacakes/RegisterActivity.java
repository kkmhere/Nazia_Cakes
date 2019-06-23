package com.example.naziacakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText ea,eb,ec,ed;
    Button btn;
    FirebaseAuth mAuth;


    public void login(View view){

        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        ea = findViewById(R.id.FirstName);
        eb = findViewById(R.id.LastName);
        ec = findViewById(R.id.editEmailID);
        ed = findViewById(R.id.editPassword);


        btn = findViewById(R.id.Register);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = eb.getText().toString().trim();
                String pwd = ed.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(RegisterActivity.this, "You have been registered successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        } else {

                            Toast.makeText(RegisterActivity.this, "Error in registry", Toast.LENGTH_SHORT).show();

                        }

                    }

                });

            }
        });

    }
}
