package com.example.gizem.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Gizem on 28.05.2016.
 */
public class MainMenuActivity extends AppCompatActivity {

    int languageCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        final String language;
        language = i.getStringExtra("language");
        // language codes
        // english = 0
        // spanish = 1
        // turkish = 2
        // german = 3
        // hindi = 4
        // indonesian = 5
        // dutch = 6
        languageCode = 0;
        String languageToLoad = "default";
        if(!language.equals("English")) {

            if (language.equals("Spanish")) {
                try {
                    new ClientService().execute(0,languageCode,3);
                }
                catch (Exception e) {

                }
                languageToLoad = "es";
                languageCode = 1;
            }
            else if (language.equals("Turkish")) {
                languageToLoad = "tr";
                languageCode = 2;
            }
            else if (language.equals("German")) {
                languageToLoad = "de";
                languageCode = 3;
            }
            else if (language.equals("Hindi")) {
                languageToLoad = "hi";
                languageCode = 4;
            }
            else if (language.equals("Indonesian")) {
                languageToLoad = "in";
                languageCode = 5;
            }
            else if (language.equals("Dutch")) {
                languageToLoad = "nl";
                languageCode = 6;
            }
        }
        else {
            try {
                new ClientService().execute(0,languageCode,3);
            }
            catch (Exception e) {

            }
        }
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        setContentView(R.layout.activity_mainmenu);
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> myStringArray = new ArrayList<String>();
        myStringArray.add(getResources().getString(R.string.baggage));
        myStringArray.add(getResources().getString(R.string.departure));
        myStringArray.add(getResources().getString(R.string.upgrade));
        myStringArray.add(getResources().getString(R.string.transit));
        myStringArray.add(getResources().getString(R.string.flightInfo));
        myStringArray.add(getResources().getString(R.string.flightDisruptions));
        myStringArray.add(getResources().getString(R.string.logOut));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myStringArray);

        /* intros: 0
         * baggageTimePlace: 1
         * baggageLost: 2
         * departure: 3
         * directions in departure: 4
         * upgrade: 5
         * transit: 6
         * flightInfo: 7
         * flightDisruptions: 8
         * logout: 9
         */
        listView.setAdapter(adapter);
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                switch (position) {
                    // baggage
                    case 0:
                        Intent bI = new Intent(getApplicationContext(), BaggageActivity.class);
                        bI.putExtra("languageCode", languageCode);
                        bI.putExtra("language", language);
                        startActivity(bI);
                        break;
                    // departure
                    case 1:
                        try {
                            new ClientService().execute(position + 2,languageCode,2);
                            Intent i = new Intent(getApplicationContext(), DepartureActivity.class);
                            i.putExtra("languageCode", languageCode);
                            i.putExtra("language", language);
                            startActivity(i);
                        }
                        catch (Exception e) {

                        }
                        break;
                    //upgrade
                    case 2:
                        try {
                            new ClientService().execute(position + 3,languageCode,3);
                        }
                        catch (Exception e) {

                        }
                        break;
                    //transit
                    case 3:
                        try {
                            new ClientService().execute(position + 3,languageCode,3);
                        }
                        catch (Exception e) {

                        }
                        break;
                    // flight info
                    case 4:
                        try {
                            new ClientService().execute(position + 3,languageCode,3);
                        }
                        catch (Exception e) {

                        }
                        break;
                    // flight disruptions
                    case 5:
                        try {
                            new ClientService().execute(position + 3,languageCode,3);
                        }
                        catch (Exception e) {

                        }
                        break;
                    //log out
                    case 6:
                        new ClientService().execute(position + 3,languageCode,7);
                        Intent i = new Intent(getApplicationContext(), ScanActivity.class);
                        i.removeExtra("language");
                        startActivity(i);
                        break;

                }

            }
        };

        listView.setOnItemClickListener(mMessageClickedHandler);

    }

    public void initializeResolveListener() {

    }
}
