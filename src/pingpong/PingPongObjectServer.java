package pingpong;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PingPongObjectServer {

    public static void main(String[] args) {

        // server erstellen
        try (ServerSocket serverSocket = new ServerSocket(1111)) {
            // server soll ewig laufen
            while (true) {
                System.out.println("server wartet auf client");
                // server soll auf verbindung warten
                // Kanäle für lesen und schreiben öffnen

                // text: zum lesen verwenden wir InputStreamReader
                // object: zum lesen verwenden wir ObjectInputStream

                // text: zum schreiben verwenden wir OutputStreamWriter
                // object: zum schreiben verwenden wir ObjectOutputStream

                try (Socket client = serverSocket.accept();
                     BufferedReader br = new BufferedReader(
                             new InputStreamReader(client.getInputStream())
                     );
                     ObjectOutputStream oos = new ObjectOutputStream(
                             client.getOutputStream()
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
                            oos.writeObject(new Response(3, "Good Bye"));
                            oos.flush();
                            System.out.println("Client hat Verbindung beendet");
                            break;
                        }

                        switch (input) {
                            case "PONG":
                                oos.writeObject(new Response(1, "--> PING"));
                                break;
                            case "PING":
                                oos.writeObject(new Response(1, "--> PONG"));
                                break;
                            default:
                                oos.writeObject(new Response(2, "--> ERROR"));
                        }

                        oos.flush(); // !!!!

                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
