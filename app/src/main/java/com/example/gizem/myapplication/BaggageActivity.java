package com.example.gizem.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Gizem on 25.05.2016.
 */
public class BaggageActivity extends AppCompatActivity {

    String language;
    int languageCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_baggage);

        ListView listView = (ListView) findViewById(R.id.listViewBaggage);

        Intent i = getIntent();
        languageCode = i.getIntExtra("languageCode",0);
        language = i.getStringExtra("language");
        i.removeExtra("languageCode");
        String languageToLoad = "default";
        if(!language.equals("English")) {

            if (language.equals("Spanish")) {
                languageToLoad = "es";
            }
            else if (language.equals("Turkish")) {
                languageToLoad = "tr";
            }
            else if (language.equals("German")) {
                languageToLoad = "de";
            }
            else if (language.equals("Hindi")) {
                languageToLoad = "hi";
            }
            else if (language.equals("Indonesian")) {
                languageToLoad = "in";
            }
            else if (language.equals("Dutch")) {
                languageToLoad = "nl";
            }
        }
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        ArrayList<String> myStringArray = new ArrayList<String>();
        myStringArray.add(getResources().getString(R.string.timeAndPlace));
        myStringArray.add(getResources().getString(R.string.baggageLost));
        myStringArray.add(getResources().getString(R.string.returnMainMenu));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myStringArray);

        listView.setAdapter(adapter);
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                switch (position) {
                    // time and place
                    case 0:

                        try {
                            new ClientService().execute(position + 1,languageCode,7);
                        }
                        catch (Exception e) {

                        }
                        break;
                    // baggage lost
                    case 1:
                        try {
                            new ClientService().execute(position + 1,languageCode,8);
                        }
                        catch (Exception e) {

                        }
                        break;
                    // return to main menu
                    case 2:
                        Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
                        i.putExtra("language",language);
                        startActivity(i);
                        break;
                }

            }
        };

        listView.setOnItemClickListener(mMessageClickedHandler);
    }
}
