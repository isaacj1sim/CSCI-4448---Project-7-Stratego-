import java.util.Scanner;
import java.awt.Point;
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

    public String getString(){
        return scan.next();
    }

    public Point getPoint(){
        boolean safe = false;
        int x = -1;
        int y = -1;
        while(!safe){
            String s = scan.next();
            if(!s.isEmpty() && s.matches(".*\\d.*")){
                int index = Integer.parseInt(s);
                x = index / 10; //get row
                y = index % 10; //get column
                if(x < 10 && x > -1 && y < 10 && y > -1){
                    safe = true;
                }
            }
        }
        return new Point(x,y);
    }
}
