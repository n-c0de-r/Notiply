package com.imi_gma.notiply.Controls;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *  Controls the data written persistently to device storage.
 */
public class Persistence {
    public static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 1;

    private static final String DIRECTORY_PATH = "/Pictures/Notiply";
    private static final String SAVE_FILE_NAME = "/page_";
    private static final String FILE_EXTENSION = ".png";

    private final File subDirectory;

    private static Persistence instance = null;

    /**
     * Constructor which finds the sub-directory to be exported to.
     */
    private Persistence() {
        // get the output storage directory and find the sub-directory.
        File storageDirectory = Environment.getExternalStorageDirectory();
        subDirectory = new File(storageDirectory.toString() + DIRECTORY_PATH);
    };

    /**
     * Singleton Pattern
     * @return  Instance of Persistence
     */
    public static Persistence getInstance() {
        if (instance == null) {
            instance = new Persistence();
        }
        return(instance);
    }

    /**
     * Saves image to a file and returns the path to it
     * @param bitmap    the image to save
     * @return String   save path
     */
    public String saveImage(Bitmap bitmap) {
        // if the sub-directory was created successfully
        if (createDirectory()) {
            // create a new file for the bitmap
            SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd_HHmmss", Locale.US);
            Date now = new Date();
            String filePath = SAVE_FILE_NAME + formatter.format(now) + FILE_EXTENSION;
            File image = new File(subDirectory, filePath);

            // save to storage
            outputToFileStream(image, bitmap);

            // return the path to the saved image to Main for Toast message.
            return DIRECTORY_PATH + filePath;
        }
        return null;
    }

    public void saveUserData(SharedPreferences sp, String name) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", name);
        editor.commit();
        editor.apply();
    }

    public String getUserData(SharedPreferences sp) {
        String s = sp.getString("name", "");
        if (s==null || s.equals("")) {
            s=android.os.Build.MODEL;
        }
        return s;
    }

    /**
     * Creates the sub-directory if it does not already exist.
     * @return boolean - whether the sub-directory exists.
     */
    private boolean createDirectory () {
        // if the sub-directory does not exist, create it
        if (!subDirectory.exists())
            return subDirectory.mkdir();
        // return true if it already exists
        return true;
    }

    /**
     * Outputs an input bitmap to a given output FileOutputStream.
     * @param image - the output image file.
     * @param bitmap - the bitmap representation of a drawing.
     */
    private void outputToFileStream (File image, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        try {
            // compress the bitmap and link it to the output stream
            fileOutputStream = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);

            // flush and close the output stream.
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            // throw an error message
        }
    }
}
