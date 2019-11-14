import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.Random;

public class Connection extends Thread{
	
	private Socket	outputLine;	
	
	int thisPort;
	String nextHost;
	int nextPort;
	public Connection(Socket s, int thisPort, String nextHost, int nextPort){
		outputLine = s;
		this.thisPort = thisPort;
		this.nextHost = nextHost;
		this.nextPort = nextPort;
	}
 	
	
	public void run() {
	
		// getOutputStream returns an OutputStream object	
		// allowing ordinary file IO over the socket.

		//  ... record snap-shot on text file (our shared resource)
		try {System.out.println("Writing to file: record.txt" );
			Date timestmp = new Date() ;
			String timestamp = timestmp.toString() ;
			// Next create fileWriter -true means writer *appends*
			FileWriter fw_id = new FileWriter("record.txt",true);
			// Create PrintWriter -true = flush buffer on each println // println means adds a newline (as distinct from print)
			PrintWriter pw_id = new PrintWriter(fw_id, true) ;
			pw_id.println ("Record from ring node on host " + "127.0.0.1" + ", port number "  + "5155" + ", is " +timestamp);

			pw_id.close() ;
			fw_id.close() ;
		}
		catch (java.io.IOException e) {
			System.out.println("Error writing to file: "+e);
		}try {sleep (3000);}
		catch (
				java.lang.InterruptedException e) { System.out.println("sleep failed: "+e);
		}

		try {
			Socket s = new Socket(nextHost, nextPort);
			if(s.isConnected()){
				System.out.println("Socket to next node(" + nextHost + " : " + nextPort + ") connected OK");
			}else{
				System.out.println("**Socket to next ring node ("+nextHost+ " : " + nextPort + ") failed to connect");
			}
			try{
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			s.close();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (s.isClosed()){
				System.out.println("Socket to next ring (" + nextHost + " : " + nextPort + ") is now closed");
			}else {
				System.out.println("Socket to next ring (" + nextHost + " : " + nextPort + ") is still open!");
			}
		}
		catch (java.io.IOException e) {
			System.out.println(" " + e);
			System.exit(1);
		}

		System.out.println("Server is listening ....");
	}
}
