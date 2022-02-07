import java.util.Scanner;
public class PlayLadderAndSnake {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] playersTemp = {"Player 1", "Player 2", "Player 3", "Player 4"};

        for(int attempt = 0; attempt < 4; attempt++) {
        	System.out.println("Please Enter Numbers of Players (between 2 and 4): ");
            int numPlay = scan.nextInt();
            if (numPlay >= 2 && numPlay <=4){
            	for(int i =0; i < numPlay; i++) {
                    System.out.print("Please enter Player " +(i+1)+ "'s name:");
                    playersTemp[i] = scan.next();
                }
            	
                LadderAndSnake g1 = new LadderAndSnake(numPlay);
                g1.setPlayers(numPlay);
                
                System.out.println("\n\nLet's pick player order!");
                String[] pOrder = g1.playerOrder(playersTemp[0], playersTemp[1], playersTemp[2], playersTemp[3]);
                System.out.println("FINAL PLAYER ORDER : ");
                for(int i =0; i < numPlay; i++) {
                    System.out.print((i+1) + ". " + pOrder[i] + "		");
                }
                System.out.println("\n");

            	g1.play(pOrder);
                break;
            }
            else {
                System.out.println("Invalid!");
            }
        }
        System.out.println("\nThanks for playing Snake and Ladders!!");
        scan.close();
    }
}
