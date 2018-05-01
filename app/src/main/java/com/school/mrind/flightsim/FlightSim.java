//TODO Convertire tutto ad operazioni asincrone
//TODO Capire che non si può convertire tutto ad operazioni asincrone
//TODO Aggiungere commenti a tutto
//TODO Aggiungere link di dove ho copiato il codice
//TODO Rassegnarsi e non commentare niente

package com.school.mrind.flightsim;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.school.mrind.flightsim.DBFiles.DB;
import com.school.mrind.flightsim.DBFiles.DBInitializer;
import com.school.mrind.flightsim.DBFiles.User;
import com.school.mrind.flightsim.databinding.ActivityMainBinding;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            //nuke();
            generate(activityMainBinding);
        });

        activityMainBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<User> usr = DB.getAppDatabase(FlightSim.this).userDao().loadAllByClassnum(parent.getSelectedItem().toString());
                for(User us : usr){
                    activityMainBinding.editText.append(us.getStudentnum() + "   " + us.nome + (us.isInterrogato() ? "    Sì" : "    No"));
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

    private void generate(ActivityMainBinding activityMainBinding){
        ArrayList<String> nomi = new ArrayList<>();
        ArrayList<Integer> numeri = new ArrayList<>();
        List<User> usr = DB.getAppDatabase(this).userDao().loadAllByClassnum(activityMainBinding.spinner.getSelectedItem().toString());
        for(User user : usr){
            if(!user.isInterrogato()){
                nomi.add(user.getNome());
                numeri.add(user.getStudentnum());
            }
        }
        byte[] bt = new byte[6];
        bt = activityMainBinding.editText2.getText().toString().getBytes(Charset.defaultCharset());
        long result = 0;
        for (int i = 0; i < 8; i++) {
            result <<= 8;
            result |= (bt[i] & 0xFF);
        }
        Random rnd = new Random(result);
        int blyat = rnd.nextInt(nomi.size());
        activityMainBinding.editText.setText("");
        activityMainBinding.editText.append(nomi.get(blyat) + numeri.get(blyat));
    }

    public void nuke(){
        DB.getAppDatabase(this).userDao().deleteAll();
    }

    private void openGitHub(){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MrIndeciso/FlightSim")));
    }
}
