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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RegisterActivity extends AppCompatActivity {

    EditText nameText,lastNameText,emailText,pwdText;
    Button btn;
    FirebaseAuth mAuth;
    DatabaseReference myRef;
    Member member;
    long maxid=0;



    public void login(View view){

        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        nameText = findViewById(R.id.FirstName);
        lastNameText = findViewById(R.id.LastName);
        emailText = findViewById(R.id.editEmailID);
        pwdText = findViewById(R.id.editPassword);
        member = new Member();
        btn = findViewById(R.id.Register);

        myRef = FirebaseDatabase.getInstance().getReference().child("Member");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailText.getText().toString().trim();
                String pwd = pwdText.getText().toString().trim();
                String fname = nameText.getText().toString().trim();
                String lname = lastNameText.getText().toString().trim();

                member.setFname(fname);
                member.setLName(lname);

                myRef.child(String.valueOf(maxid+1)).setValue("member");
                Toast.makeText(RegisterActivity.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();


                mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
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
