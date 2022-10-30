package com.example.foodin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditFood extends AppCompatActivity {

    private TextInputEditText foodNameEdit,foodPriceLarge,foodPriceRegular,foodDescription,foodImg;
    private Button updateFood, deleteFood;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String foodID;
    private foodRVModal foodRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);
        firebaseDatabase= FirebaseDatabase.getInstance();
        foodNameEdit=findViewById(R.id.idEdtFoodName);
        foodDescription=findViewById(R.id.idEdtFoodDetails);
        foodPriceRegular=findViewById(R.id.idEdtFoodPriceR);
        foodPriceLarge=findViewById(R.id.idEdtFoodPriceL);
        foodImg=findViewById(R.id.idEdtFoodImgUrl);
        updateFood=findViewById(R.id.buttonUpdate);
        deleteFood=findViewById(R.id.buttonDelete);
        loadingPB=findViewById(R.id.idPBLoading);
        foodRVModal=getIntent().getParcelableExtra("foodin");
        if(foodRVModal!=null){
            foodNameEdit.setText(foodRVModal.getFoodName());
            foodDescription.setText(foodRVModal.getFoodDetails());
            foodPriceRegular.setText(foodRVModal.getFoodPriceR());
            foodPriceLarge.setText(foodRVModal.getFoodPriceL());
            foodImg.setText(foodRVModal.getFoodImgUrl());
            foodID=foodRVModal.getFoodID();
        }

        databaseReference=firebaseDatabase.getReference("foodin").child(foodID);
        updateFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String foodName=foodNameEdit.getText().toString();
                String foodDetails=foodDescription.getText().toString();
                String foodPriceR=foodPriceRegular.getText().toString();
                String foodPriceL=foodPriceLarge.getText().toString();
                String foodImgURL=foodImg.getText().toString();

                Map<String,Object> map=new HashMap<>();
                map.put("foodname",foodName);
                map.put("foodDetails",foodDetails);
                map.put("foodPriceR",foodPriceR);
                map.put("foodPriceL",foodPriceL);
                map.put("foodImgUrl",foodImgURL);
                map.put("foodID",foodID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditFood.this, "FoodItem Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditFood.this,MainActivity.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditFood.this, "Fail to Updated", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        deleteFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFoodItem();
            }
        });

    private void deleteFoodItem() {
        databaseReference.removeValue();
            Toast.makeText(this, "FoodItem Deleted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditFood.this,MainActivity.class));
        }

    }
}