package com.example.nour.myfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RelativeLayout;
public class MainActivity extends AppCompatActivity {
Button btsignUp,btsignIn;
TextView textSlogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btsignUp=findViewById(R.id.sign_up);
        btsignIn = findViewById(R.id.sign_in);
        textSlogan=findViewById(R.id.text_slogan);
        final RelativeLayout relativeLayout = findViewById(R.id.relativeLayout);
    btsignUp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent SignUp = new Intent(MainActivity.this,SignUp.class);
            startActivity(SignUp);
        }
    });
        btsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Signin = new Intent(MainActivity.this,SignIn.class);
                startActivity(Signin);
            }
        });
    }
}
