//TODO Convertire tutto ad operazioni asincrone
//TODO Capire che non si può convertire tutto ad operazioni asincrone

package com.school.mrind.flightsim;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

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

        activityMainBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<User> usr = DB.getAppDatabase(FlightSim.this).userDao().loadAllByClassnum(parent.getSelectedItem().toString());
                for(User us : usr){
                    activityMainBinding.editText.append(us.nome);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
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
        activityMainBinding.spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classtype));
    }

    public void nuke(){
        DB.getAppDatabase(this).userDao().deleteAll();
    }
}
