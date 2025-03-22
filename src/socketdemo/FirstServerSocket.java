package socketdemo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FirstServerSocket {

    public static void main(String[] args) {
        // Server erstellen und an Port binden
        System.out.println("Server wird hochgefahren");

        try (ServerSocket serverSocket = new ServerSocket(1111)) {

            while (true) { // Server läuft ewig (Ausnahme: Exception, herunterfahren)
                System.out.println("Warte auf client");
                try (Socket client = serverSocket.accept();
                     // Kanal zum Schreiben geöffnet
                     BufferedWriter bw = new BufferedWriter(
                             new OutputStreamWriter(client.getOutputStream())
                     )
                ) {

                    bw.write("Hallo Client");
                    bw.flush(); // !!!!!!!

                    System.out.println("Daten an Client gesendet");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Connection closed");
        }
    }
}
