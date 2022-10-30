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

public class AddFood extends AppCompatActivity {

    private TextInputEditText foodNameEdit,foodPriceLarge,foodPriceRegular,foodDescription,foodImg;
    private Button addFoodbtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String foodID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        foodNameEdit=findViewById(R.id.idEdtFoodName);
        foodDescription=findViewById(R.id.idEdtFoodDetails);
        foodPriceRegular=findViewById(R.id.idEdtFoodPriceR);
        foodPriceLarge=findViewById(R.id.idEdtFoodPriceL);
        foodImg=findViewById(R.id.idEdtFoodImgUrl);
        addFoodbtn=findViewById(R.id.addfoodbtn);
        loadingPB=findViewById(R.id.idPBLoading);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("food");

        addFoodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String foodName=foodNameEdit.getText().toString();
                String foodDetails=foodDescription.getText().toString();
                String foodPriceR=foodPriceRegular.getText().toString();
                String foodPriceL=foodPriceLarge.getText().toString();
                String foodImgURL=foodImg.getText().toString();
                foodID = foodName;
                foodRVModal foodRVModal = new foodRVModal(foodName,foodDetails,foodPriceR,foodPriceL,foodImgURL,foodID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.child(foodID).setValue(foodRVModal);
                        Toast.makeText(AddFood.this, "Food Item added..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddFood.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddFood.this, "Error is"+ error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}