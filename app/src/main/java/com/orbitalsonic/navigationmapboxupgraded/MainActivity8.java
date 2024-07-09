package com.orbitalsonic.navigationmapboxupgraded;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
    }
    public void goToNextPage6(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}