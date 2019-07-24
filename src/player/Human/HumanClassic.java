package player.Human;
import game.WorldModel;
import game.action.*;

import java.util.Scanner;

public class HumanClassic extends Human {
    private Action action;
    public HumanClassic(String name) {
        super(name);
    }

    @Override
    public Action nextAction(WorldModel worldModel) {
        Scanner scanner = new Scanner(System.in);
        int act = scanner.nextInt();
        System.out.println("enter position:");
        int position;
        if (act==1){
            position = scanner.nextInt();
            action = new Move(worldModel.getCurrentPosition(worldModel.getTurn()%2),position);
        }else if (act==2){
            position = scanner.nextInt();
            System.out.println("enter direction:");
            System.out.println("1. horizontal");
            System.out.println("2. vertical");
            int direction = scanner.nextInt();
            if (direction == 1)
                action = new Block(ActionType.BLOCK,position,Direction.HORIZENTAL );
            else if (direction == 2)
                    action = new Block(ActionType.BLOCK,position,Direction.VERTICAL);
        }
        return action;
    }
}
