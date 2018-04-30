//TODO Convertire tutto ad operazioni asincrone
//

package com.school.mrind.flightsim;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.school.mrind.flightsim.DBFiles.DB;
import com.school.mrind.flightsim.DBFiles.DBInitializer;
import com.school.mrind.flightsim.DBFiles.User;
import com.school.mrind.flightsim.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class FlightSim extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        DBInitializer.populateAsync(DB.getAppDatabase(this));
        selectclass(activityMainBinding);

        activityMainBinding.button2.setOnClickListener((View v) -> {
            nuke();
        });
    }

    private void selectclass(ActivityMainBinding activityMainBinding) {
        List<User> tot = DB.getAppDatabase(this).userDao().getAll();
        ArrayList<String> classtype = new ArrayList<String>();
        boolean shish = false;
        for (User usr : tot) {
            for (String str : classtype) {
                if (usr.getClassname() == str) {
                    shish = true;
                }
            }
            if (!shish) {
                classtype.add(usr.getClassname());
            }
            shish = false;
        }
        ArrayList<String> blyat = new ArrayList<String>();
        SpinnerAdapter Blyat = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, blyat);
        activityMainBinding.spinner.setAdapter(Blyat);
        activityMainBinding.spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classtype));
    }

    public void nuke(){
        DB.getAppDatabase(this).userDao().deleteAll();
    }
}
