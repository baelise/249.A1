import java.util.Scanner;
public class PlayLadderAndSnake {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int players;
        for(int attempt = 0; attempt < 4; attempt++ )
        {
        	System.out.println("Please Enter Numbers of Players (between 2and 4): ");
            int userIn = scan.nextInt();
            if (userIn >= 2 && userIn <=4){
                players = userIn;
                LadderAndSnakeRetry g1 = new LadderAndSnakeRetry(4);
            	g1.play();
                break;
            }
            else {
                System.out.println("Invlaid!");
            }
        }
        System.out.println("Game is terminated");
        scan.close();
    }
}
