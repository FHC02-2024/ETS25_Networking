package org.campus02.url_demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MyFirstUrl {

    public static void main(String[] args) {

        // URL erzeugen
        try {
            URL myUrl = new URL("https://www.campus02.at");

            // openStream öffnen und mit BufferedReader lesen
            try (BufferedReader br = new BufferedReader
                         (new InputStreamReader(myUrl.openStream()))) {
                // Zeile für Zeile lesen

                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
