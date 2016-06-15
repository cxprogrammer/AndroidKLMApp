package com.example.gizem.myapplication;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.telecom.RemoteConnection;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.util.List;
import java.util.UUID;

/**
 * Created by Gizem on 5.06.2016.
 */
public class ClientService extends AsyncTask<Integer,Integer,Integer>{

    /**
     * Runs the server.
     */


    protected Integer doInBackground(Integer... utl) {

        Long x = Long.MIN_VALUE;
        try {
                Socket echoSocket = new Socket("130.89.89.53", 3053);
            PrintWriter out =
                    new PrintWriter(echoSocket.getOutputStream(), true);
            out.println(utl[0]);
            out.println(utl[1]);
            echoSocket.close();

        }catch (Exception e) {

        }
        return -1;
    }

}
