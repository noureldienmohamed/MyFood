package com.example.nour.myfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nour.myfood.Interface.ItemClickListener;
import com.example.nour.myfood.ViewHolder.FoodViewHolder;
import com.example.nour.myfood.model.Category;
import com.example.nour.myfood.model.Food;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class foodlist extends AppCompatActivity {
RecyclerView recyclerView ;
RecyclerView.LayoutManager layoutManager;
FirebaseDatabase database;
DatabaseReference foodList;
String categoryId="" ;
FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);
        database=FirebaseDatabase.getInstance();
        foodList=database.getReference("Food");
        recyclerView=findViewById(R.id.recycle_food);
       // recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
     if (getIntent()!=null)
         categoryId=getIntent().getStringExtra("categoryId");
     if (!categoryId.isEmpty() && (categoryId != null)){
     loadListFood(categoryId);
     }
    }

    private void loadListFood(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,
                R.layout.food_item,
                FoodViewHolder.class,
                foodList.orderByChild("MenuId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
          viewHolder.food_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.food_image);
                final Food local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean islongClick)
                    {
                        Intent foodDetails = new Intent(foodlist.this,FoodDetail.class);
                        foodDetails.putExtra("FoodId",adapter.getRef(position).getKey());
                        startActivity(foodDetails);
                    }
                });
            }
        };
      //  Log.d("TAG",""+adapter.getItemCount());
        recyclerView.setAdapter(adapter);
    }
}
