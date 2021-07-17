import java.util.Scanner;
public class Input{

    private Scanner scan;
    private static Input instance = null;

    private Input() {
        scan = new Scanner(System.in);
    }

    public static synchronized Input getInstance() {
        if (instance == null) {
            instance = new Input();
        }
        return instance;
    }

    public int getInt() {
        while (!scan.hasNextInt()) scan.next();
        return scan.nextInt();
    }

}
