package com.chakhle.www.locationtracker;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class writeToFile {
    private File sdCard,outFile;
    private FileOutputStream fos;

    public writeToFile()
    {
        try {
            sdCard = Environment.getExternalStorageDirectory();
            outFile = new File(sdCard, "Vendor.csv");
            fos = new FileOutputStream(outFile, true);
        }
        catch (FileNotFoundException e){
        e.printStackTrace();
        }
    }
    public void write(String outStream)
    {
        try {
            OutputStreamWriter out = new OutputStreamWriter(fos);
            out.append(outStream);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
