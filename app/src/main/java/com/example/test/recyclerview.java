package com.example.test;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerview extends AppCompatActivity {

    ArrayList<Test> test= new ArrayList<>();
    int [] testimage = {R.drawable.baseline_account_circle_24,
            R.drawable.baseline_add_call_24 ,
            R.drawable.baseline_add_location_alt_24 ,
            R.drawable.baseline_add_a_photo_24, R.drawable.baseline_access_time_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recyclerview);
        RecyclerView recyclerView = findViewById(R.id.mrview);

        setUptest();

        trv_Adapter adapter = new trv_Adapter(this, test);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    private void setUptest(){
        String [] testname = getResources().getStringArray(R.array.name1);

        for (int i=0 ; i<testname.length ; i++){
            test.add(new Test(testname[i] , testimage[i]));
        }
    }
}