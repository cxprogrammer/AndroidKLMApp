package com.example.gizem.myapplication;

import android.hardware.Camera;
import android.util.Log;

import com.google.android.gms.vision.face.Face;

/**
 * Created by Gizem on 7.06.2016.
 */
public class MyFaceDetectionListener implements Camera.FaceDetectionListener {


    @Override
    public void onFaceDetection(Camera.Face[] faces, Camera camera) {
        if (faces.length > 0){
            Log.d("FaceDetection", "face detected: "+ faces.length +
                    " Face 1 Location X: " + faces[0].rect.centerX() +
                    "Y: " + faces[0].rect.centerY() );
        }
    }


}
