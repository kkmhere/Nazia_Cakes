package com.example.naziacakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetActivity extends AppCompatActivity {

    EditText e1;
    Button b1;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        auth = FirebaseAuth.getInstance();


        e1 = findViewById(R.id.email);

        b1 = findViewById(R.id.sendEmail);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String forget = e1.getText().toString().trim();

                auth.sendPasswordResetEmail(forget).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(ForgetActivity.this, "We have sent you link to reset your password", Toast.LENGTH_SHORT).show();
                        }

                        else{

                            Toast.makeText(ForgetActivity.this, "Failed to sent resend email", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });
    }
}
