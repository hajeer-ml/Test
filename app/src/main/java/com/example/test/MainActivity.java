package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
      new Handler().postDelayed(() -> {
          Intent intent= new Intent(MainActivity.this , MainActivity2.class);
          startActivities(new Intent[]{intent});
          finish();
              }, 4200
      );
    }
}