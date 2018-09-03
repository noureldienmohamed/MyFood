package com.example.nour.myfood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nour.myfood.common.Common;
import com.example.nour.myfood.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
EditText edtPhone,edtPassWord;
Button btsign_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtPhone=findViewById(R.id.edtPhone);
        edtPassWord=findViewById(R.id.edtpassWord);
        btsign_in =findViewById(R.id.btsign_in);
        final FirebaseDatabase mydata =FirebaseDatabase.getInstance();
        final DatabaseReference table_user=mydata.getReference("User");
        btsign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please Waiting...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            assert user != null;
                            if (user.getPassWord().equals(edtPassWord.getText().toString()))
                            {
                                {
                                Toast.makeText(SignIn.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                                    Intent homeintent = new Intent(SignIn.this, Home.class);
                                    Common.currentUser = user;
                                    startActivity(homeintent);
                                    finish();
                                }
                            } else {
                                Toast.makeText(SignIn.this, "Sign In failed", Toast.LENGTH_SHORT).show();
                            }
                            }else {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "User not exist in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
