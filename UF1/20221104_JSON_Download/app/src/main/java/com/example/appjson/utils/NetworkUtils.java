package com.example.appjson.utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class NetworkUtils {

    public static String getJSon(File jsonFolder, String fileName, String url) {

        BufferedOutputStream fos = null;
        try
        {
            File arxiuCache = new File(jsonFolder, fileName);
            fos = new BufferedOutputStream(new FileOutputStream(arxiuCache));

            BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            ByteArrayOutputStream  stream = new ByteArrayOutputStream();

             byte dataBuffer[] = new byte[1024];
             int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                  fos.write(dataBuffer, 0, bytesRead);
                stream.write(dataBuffer, 0, bytesRead);
            }
            return new String(stream.toByteArray());
        } catch (Exception e) {
            Log.e("XXX","Error descarregant JSON", e);
            return null;
        }
        finally {
            if(fos!=null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    Log.e("XXX","Error tancant FOS", e);
                }
            }

        }
    }
}
