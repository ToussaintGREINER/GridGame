import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static String input;
    public static conctactServ link = new conctactServ();

    public static void main(String[] args) throws IOException {
        link.startConnection("10.0.2.4", 8880);
        scannerLoop();
    }



    public static void scannerLoop() throws IOException {
        Scanner scanneur = new Scanner(System.in);
        input = scanneur.nextLine();
        link.sendMessage(input);
        scannerLoop();
    }
}