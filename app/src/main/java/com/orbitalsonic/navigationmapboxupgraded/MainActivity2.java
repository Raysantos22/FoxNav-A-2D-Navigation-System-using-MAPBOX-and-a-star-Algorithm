package com.orbitalsonic.navigationmapboxupgraded;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    // Method to handle the click event of the Next button
    public void goToNextPage(View view) {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}

