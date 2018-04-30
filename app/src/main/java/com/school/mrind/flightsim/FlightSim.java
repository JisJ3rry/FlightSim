package com.school.mrind.flightsim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.school.mrind.flightsim.DBFiles.DB;
import com.school.mrind.flightsim.DBFiles.User;

public class FlightSim extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_sim);
    }

    public void generate(View view){
        TextView tv = (TextView)findViewById(R.id.editText);
        tv.setText("Cyka Blyat");
    }

    public static User addUser(final DB db, User user){
        db.userDao().insertAll(user);
        return user;
    }
}
