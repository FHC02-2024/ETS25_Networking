package pingpong;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PingPongServer {

    public static void main(String[] args) {

        // server erstellen
        try (ServerSocket serverSocket = new ServerSocket(1111)) {
            // server soll ewig laufen
            while (true) {
                System.out.println("server wartet auf client");
                // server soll auf verbindung warten
                // Kanäle für lesen und schreiben öffnen
                try (Socket client = serverSocket.accept();
                     BufferedReader br = new BufferedReader(
                             new InputStreamReader(client.getInputStream())
                     );
                     PrintWriter pw = new PrintWriter(
                             new OutputStreamWriter(client.getOutputStream())
                     )
                ) {
                    System.out.println("Client hat sich verbunden");
                    // zuerst lesen
                    String input;
                    while ((input = br.readLine()) != null) {
                        // wenn ping -> pong
                        // wenn pong -> ping
                        // alles andere -> error meldung

                        if (input.equalsIgnoreCase("exit")) {
                            pw.println("Good Bye");
                            pw.flush();
                            System.out.println("Client hat Verbindung beendet");
                            break;
                        }

                        switch (input) {
                            case "PONG":
                                pw.println("--> PING");
                                break;
                            case "PING":
                                pw.println("--> PONG");
                                break;
                            default:
                                pw.println("--> Error");
                        }

                        pw.flush(); // !!!!

                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
