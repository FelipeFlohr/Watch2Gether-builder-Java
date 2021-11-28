package com.felipeflohr.w2gbuilder.seleniumrelated;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadTxtFile {

    String txtFilePath;

    public ReadTxtFile(String txtFilePath){
        this.txtFilePath = txtFilePath;
    }

    public ArrayList<String> getVideoURLs() throws IOException {
        ArrayList<String> links = new ArrayList<>();

        File txt = new File(txtFilePath);
        Scanner scanner = new Scanner(txt);

        while(scanner.hasNext()){
            links.add(scanner.nextLine());
        }
        scanner.close();
        return links;
    }

}
