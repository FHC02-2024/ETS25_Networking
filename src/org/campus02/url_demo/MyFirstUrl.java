package org.campus02.url_demo;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class MyFirstUrl {

    public static void main(String[] args) {

        // URL erzeugen
        try {
            URL myUrl = new URL("https://www.google.de");

            // openStream öffnen und mit BufferedReader lesen
            try (BufferedReader br = new BufferedReader
                         (new InputStreamReader(myUrl.openStream()));
                 BufferedWriter bw = new BufferedWriter(
                         new FileWriter("data/google.html")
                 )
            ) {
                // Zeile für Zeile lesen

                String line;
                while ((line = br.readLine()) != null) {
                    // System.out.println(line);

                    // schreiben
                    bw.write(line);
                    bw.newLine();
                }
                bw.flush(); // !!!!!!!!
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
