package com.school.mrind.flightsim;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.school.mrind.flightsim.databinding.ActivitySettingsBinding;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActivitySettingsBinding asb = DataBindingUtil.setContentView(this, R.layout.activity_settings);

        asb.button4.setOnClickListener((View v) ->{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MrIndeciso/FlightSim")));
        });

        asb.button3.setOnClickListener((View v) ->{
            finish();
            startActivity(new Intent(Settings.this, FlightSim.class));
        });

        asb.button6.setOnClickListener((View v) ->{

        });
    }
}
