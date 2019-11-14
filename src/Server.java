import java.net.*;

import static java.lang.Thread.sleep;

public class Server{

	ServerSocket s;
	Socket client;
	Connection c;
	public Server(int thisPort, String nextHost, int nextPort){


		// create the socket the server will listen to
		try {
			s = new ServerSocket(thisPort);
		}
		catch (java.io.IOException e) {
			System.out.println(" " + e);
			System.exit(1);
		}

		System.out.println("Server is listening ....");


		// OK, now listen for connections and create them
		//code fails because the server only listens once for the connection then terminates, a while true will fix this
		while(true) {
			try {
				client = s.accept();
				System.out.println("SERVER:  connection accepted\n\n");

				// create a separate thread to service the client
				c = new Connection(client, thisPort, nextHost, nextPort);
				c.start();

				try {
					Thread.sleep(500);
				} catch (Exception e) {
					System.out.print(e);
				}
			} catch (java.io.IOException e) {
				System.out.println(e);
			}
		}
	}

	public static void main(String argv[]) {
		if ((argv.length < 3) || (argv.length > 3)){
			System.out.println("Usage: [this port][next host][next port]");
			System.out.println("Only "+argv.length+" parameters entered");
			System.exit (1) ;
		}
		int this_port = Integer.parseInt (argv[0]);
		String next_host = argv[1];
		int next_port = Integer.parseInt (argv[2]);
		Server ring_host = new Server(this_port, next_host, next_port);
	} // end main
}