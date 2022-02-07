//--------------------------------------------------------------------
// Elise Proulx (40125538) and Andrei Barbulescu (40208635)
// COMP 249 - Section S
// Assignment 1
// 02/07/2022
//--------------------------------------------------------------------

public class LadderAndSnake {
	// Initializing instance variabnle (number of players)
    private int players;
    // Two-Dimensional array of the snakes and ladders on the board
    // Note: Removed {80, 100} from array and hard-coded into loop in play()
    private final static int [][] board = {{1,38},{4,14},{9,31},{16,6},{21,42}
     ,{28,84},{36,44},{48,30},{51,67},{62,19},{64,60},{71,91},{93,68},{95,24}, {97,76},{98,78}};
    
    // Constructors
    public LadderAndSnake() {}
    public LadderAndSnake(LadderAndSnake las) {
        this.players = las.players;
    }
    public LadderAndSnake(int numPlayers) {
        players = numPlayers;
    }
    // Getter and Setter for players
    public int getPlayers() {
        return players;
    }
    public void setPlayers(int players) {
        this.players = players;
    }
    // Player order method: rolls for each players, checks and corrects re-rolls then organizes
    // players in order. A String array is returned with the custom names in, now, the
    // correct order
    public String[] playerOrder(String p1, String p2, String p3, String p4) {
        String[] playerString = {p1, p2, p3, p4};
        int i = 0;
        int[] playerRolls = {0, 0, 0, 0};
        while(i < getPlayers()){
            int flip = flipDice();
            System.out.println(playerString[i] + " rolled a " + flip);
            playerRolls[i] = flip; i++;
        }
	// If two players roll the same number, program rerolls for those two players
        for (int j = 0; j < getPlayers(); j++)
        {
            for (int k = j+1; k < getPlayers(); k++)
            {
                while (playerRolls[j] == playerRolls[k])
                {
                    System.out.println("\n" + playerString[j] + " and " + playerString[k] + " both rolled " + playerRolls[j] + "\nRe-roll!!");
                    int roll = flipDice();
                    System.out.println(playerString[j] + " has rolled " + roll);
                    playerRolls[j] = roll;
                    int roll2 = flipDice();
                    System.out.println(playerString[k] + " has rolled " + roll2);
                    playerRolls[k] = roll2;
                }
            }
        }
	// Bubble sort String array and int array
        for (int l = 0; l < getPlayers(); l++)
        {
            for (int m = 1+l; m < getPlayers(); m++)
            {
                if(playerRolls[m] > playerRolls[l]){
                    int temp = playerRolls[l];
                    String tomp = playerString[l];

                    playerRolls[l] = playerRolls[m];
                    playerString[l] = playerString[m];

                    playerRolls[m] = temp;
                    playerString[m] = tomp;
                }
            }
        }
        return playerString;
    }
    
    // Flip dice method: when called, return a random number between 1 and 6
    public int flipDice(){
        return (int)(Math.random()*6+1);
    }
    // Play method, executes entire game until 1 player has reached square 100
    public void play(String[] order) {
    	// Initializing boolean values for play loops
        boolean playing = true;
        boolean checkLS;
        boolean reg;
        // Initializing positions of players, all players start at 0
        int[] positions = {0, 0, 0, 0};
        // Loop will run until player has reached square 100
        while(playing) {
        	// Loop will let each player roll and move in order
            for(int i = 0; i < getPlayers(); i++) {
                checkLS = true;
                reg = true;
                // Next roll will determine how much the current player will move
                int nextRoll = flipDice();
                // Message displaying current player positions
                System.out.println("\nNEW PLAYER POSITIONS:");
                for(int x = 0; x < getPlayers(); x++)
                {
                	System.out.println(order[x] + " is at square " + positions[x] + "	");
                }
                System.out.println("\nRolling.......... ");
                System.out.println(order[i] + " rolls " + nextRoll);
                // Loop that checks if player lands on a snake or ladder square
                // Special message will be displayed and will be moved to appropriate square
                while(checkLS) {
                    for(int j = 0; j <15; j++) {
                        if(positions[i]+nextRoll == board[j][0]) {
                            // Executes if player reaches a ladder square
                        	if(positions[i] < board[j][1]) {
                                System.out.println(order[i] + " moves up to square " + (positions[i] + nextRoll));
                                System.out.println("Its a ladder! " + order[i] + " moves up to square " + board[j][1]);
                            }
                        	// Executes if player lands on a snake
                            else {
                                System.out.println(order[i] + " moves up to square " + (positions[i] + nextRoll));
                                System.out.println("Oh no a snake! " + order[i] + " moves down to square " + board[j][1]);
                            }
                        	// Setting regular play to false so that player does not also play a "regular" turn 
                            positions[i] = board[j][1];
                            reg = false;
                            break;
                        }
                    }
                    checkLS = false;
                }
                // Loop that will execute if player does NOT land on snake or ladder square
                // Next roll value will simply be added to player's position
                while(reg)
                {
                	// Handling of a over-100 roll
                    if(positions[i]+nextRoll > 100) {
                    	// Calculation for square player must move back to
                        int excess = 100-(positions[i] + nextRoll);
                        // Three if statements for snake squares that players may move back to
                        if(100+excess == 98) {
                            System.out.println(order[i] + " moves back to square 98");
                            positions[i] = 78;
                            System.out.println("Oh no a snake! " + order[i] + " moves down to square " + positions[i]);
                            break;
                        }
                        if(100+excess == 97) {
                            System.out.println(order[i] + " moves back to square 97");
                            positions[i] = 76;
                            System.out.println("Oh no a snake! " + order[i] + " moves down to square " + positions[i]);
                            break;
                        }
                        if(100+excess == 95) {
                            System.out.println(order[i] + " moves back to square 95");
                            positions[i] = 24;
                            System.out.println("Oh no a snake! " + order[i] + " moves down to square " + positions[i]);
                        }
                        // Executes if player moves back to "regular" square
                        else {
                            positions[i] = 100 + excess;
                            System.out.println(order[i] + " rolled over 100... moves back to square " + positions[i]);
                        }
                        break;
                    }
                    // Executes if player lands on square 100 or 80 (landing on square 80 is an automatic win)
                    if(positions[i]+nextRoll==100 || positions[i]+nextRoll==80) {
                    	if(positions[i]+nextRoll==80) {
                    		System.out.println(order[i] + " moves up to square 80");
                    		System.out.println("Its a ladder! " + order[i] + " moves up to square 100");
                    	}
                    	else {
                            System.out.println(order[i] + " moves up to square 100");
                    	}
                    	// Congratulations message is displayed
                        positions[i] = 100;
                        System.out.println("********     " + order[i] + " has won!" + "     ********	");
                        playing = false;
                        i = 4;
                        break;
                    }
                    // Executes if player has a regular turn (i.e. Player has not reached square 100 or 80,
                    // player has not landed on ladder or snake, player has not gone over 100)
                    else
                    {
                        positions[i] = positions[i]+nextRoll;
                        System.out.println(order[i] + " moves up to square " + positions[i]);
                        reg = false;
                    }
                }
            }
        }
    }
}
