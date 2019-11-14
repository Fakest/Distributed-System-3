public class Controller {
    public static void main(String args[]) {
        Server s1 = new Server(7000, "Server 1", 7001);
        Server s2 = new Server(7001, "Server 2", 7002);
        Server s3 = new Server(7002, "Server 3", 7000);
    }
}
