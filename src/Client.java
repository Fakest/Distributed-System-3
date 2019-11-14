import java.net.*;
import java.io.*;

public class Client {

    public Client() {
        while (true) {
            try {
                Socket s = new Socket("127.0.0.1", 7000);

                // clear out file used by ring nodes
                System.out.println("Clearing record.txt file");
                try {
                    // create fileWriter -false = new file so clear contents
                    FileWriter fw_id = new FileWriter("record.txt", false);
                    // that's it -all now cleaned up
                    fw_id.close();
                } catch (java.io.IOException e) {
                    System.err.println("Exception inclearing file: main: " + e);
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String args[]) {
        Client client1 = new Client();
    }

}
