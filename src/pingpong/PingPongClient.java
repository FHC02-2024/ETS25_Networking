package pingpong;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class PingPongClient {

    public static void main(String[] args) {

        // auf server verbinden
        try (Socket server = new Socket("localhost", 1111);
             BufferedWriter bw = new BufferedWriter(
                     new OutputStreamWriter(server.getOutputStream())
             );
             BufferedReader br = new BufferedReader(
                     new InputStreamReader(server.getInputStream())
             );
             BufferedReader cli = new BufferedReader(
                     new InputStreamReader(System.in)
             )
        ) {
            // von der Kommandozeile lesen
            String line;
            while ((line = cli.readLine()) != null) {
                // an server senden
                bw.write(line);
                bw.newLine(); // !!!!!
                bw.flush(); // !!!!!

                System.out.println(line + " -> an server gesendet");

                if (line.equalsIgnoreCase("EXIT")) {
                    break;
                }

                // antwort lesen
                String response = br.readLine();
                System.out.println("response = " + response);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
