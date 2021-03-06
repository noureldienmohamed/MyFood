package com.example.nour.myfood;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.nour.myfood.model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {
TextView food_name,food_price,food_description;
ImageView food_image;
CollapsingToolbarLayout collapsingToolbarLayout;
FloatingActionButton btnCart;
ElegantNumberButton numberButton;
String foodId="";
FirebaseDatabase database;
DatabaseReference foods;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        database=FirebaseDatabase.getInstance();
        foods=database.getReference("Food");
        food_name=findViewById(R.id.food_name);
        food_price=findViewById(R.id.food_price);
        food_description=findViewById(R.id.food_description);
        food_image=findViewById(R.id.img_food);
        btnCart=findViewById(R.id.btnCart);
        numberButton=findViewById(R.id.number_button);
        collapsingToolbarLayout=findViewById(R.id.collasing);

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        if(getIntent()!=null){
            foodId=getIntent().getStringExtra("FoodId");
        if (!foodId.isEmpty())
        {
            getDetailFood(foodId);
        }
        }


    }

    private void getDetailFood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Food food = dataSnapshot.getValue(Food.class);
                Picasso.with(getBaseContext()).load(food.getImage())
                        .into(food_image);
                collapsingToolbarLayout.setTitle(food.getName());
                food_price.setText(food.getPrice());
                food_name.setText(food.getName());
                food_description.setText(food.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
