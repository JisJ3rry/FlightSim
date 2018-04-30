//TODO Convertire tutto ad operazioni asincrone
//

package com.school.mrind.flightsim;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

    private void selectclass(ActivityMainBinding activityMainBinding){
        DB db = DB.getAppDatabase(this);
        List<User> tot = db.userDao().getAll();
        ArrayList<String> classtype = new ArrayList<String>();
        boolean shish = false;
        for(User usr : tot){
            for(String str : classtype){
                if(usr.getClassname() == str){
                    shish = true;
                }
            }
            if(!shish){
                classtype.add(usr.getClassname());
            }
            shish=false;
        }
        activityMainBinding.spinner2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  classtype));
    }

    public void generate(View view){
        TextView tv = findViewById(R.id.editText);
        tv.setText("Blyatdotcom");
    }

    public static User addUser(final DB db, User user){
        db.userDao().insertAll(user);
        return user;
    }

    public void nuke(){
        DB.getAppDatabase(this).userDao().deleteAll();
    }
}
