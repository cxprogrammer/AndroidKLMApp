package com.example.gizem.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.camera2.CameraAccessException;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Gizem on 27.05.2016.
 */
public class ScanActivity extends AppCompatActivity{

    Ticket ticket;
    ArrayList<Ticket> tickets;
    String scanResult;

    public ScanActivity() {
        tickets = new ArrayList<Ticket>();
        tickets.add(new Ticket("s545","Turkish","KL1682",new Date(2016,07,01,11,55),"B11"));
        tickets.add(new Ticket("s542","German","KL1682",new Date(2016,07,01,11,55),"B11"));
        tickets.add(new Ticket("s5213","Hindi","KL1682",new Date(2016,07,01,11,55),"B11"));
        tickets.add(new Ticket("s5543","English","KL1682",new Date(2016,07,01,11,55),"B11"));
        tickets.add(new Ticket("s5548","Spanish","KL1682",new Date(2016,07,01,11,55),"B11"));
        tickets.add(new Ticket("s5315","Arabic","KL1682",new Date(2016,07,01,11,55),"B11"));
        tickets.add(new Ticket("s5316","Dutch","KL1682",new Date(2016,07,01,11,55),"B11"));
        tickets.add(new Ticket("s5317","Indonesian","KL1682",new Date(2016,07,01,11,55),"B11"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
    }

    public void onClickScanCode(View view) {
        Intent intent = new Intent(
                "com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);

    }

    public String encodeImg(ImageView img) {
        String key = null;


        return key;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {


                scanResult = data.getStringExtra("SCAN_RESULT"); // This will contain your scan result
                String format = data.getStringExtra("SCAN_RESULT_FORMAT");


            }
        }
        String language = null;
        language = search(scanResult);

        if(language != null) {
            Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
            i.putExtra("language", language);
            startActivity(i);
        }

    }


    public String search(String id) {

        for(int i = 0; i < tickets.size(); i++) {
            if(tickets.get(i).getId().equals(id))
                return tickets.get(i).getLanguage();
        }

        return null;
    }

    public String queryDatabase(String id) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("customers"));
        String language = null;
        String line;

        while((line = in.readLine()) != null)
        {
            if(line == id) {
                if((language = in.readLine()) == null) {
                    break;
                }
            }
            in.readLine();
        }
        in.close();

        return language;
    }
}
