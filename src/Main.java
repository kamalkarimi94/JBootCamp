import java.util.Scanner;
import game.*;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        Game game = null;
        int type = m.startUpMenu();
        if (type==1){
            game = new ClassicGame();
        }else if (type==2)
            game = new TournamentClassic();
        game.setup();
        game.run();
    }



    private int startUpMenu(){
        while (true) {
            System.out.println("Select game type:");
            System.out.println("1. Classic");
            System.out.println("2. Tournament of classic");
            Scanner scanner = new Scanner(System.in);
            int type = scanner.nextInt();
            if (type==1 || type==2)
                return type;
            else
                System.out.println("Enter valid type");
        }



        
    }
}
