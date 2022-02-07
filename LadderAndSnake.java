public class LadderAndSnake {
    private int players;
    private final static int [][] board = {{1,38},{4,14},{9,31},{16,6},{21,42}
    ,{28,84},{36,44},{48,30},{51,67},{62,19},{64,60},{71,91},{93,68},{95,24}, {97,76},{98,78}}; 
   //Removed {80, 100} and hardcoding it into loop in play()
    
    public LadderAndSnake() {}
    public LadderAndSnake(LadderAndSnake las) {
    	this.players = las.players;
    }
    public LadderAndSnake(int numPlayers) {
    	players = numPlayers;
    }
    public int getPlayers() {
    	return players;
    }
    public void setPlayers(int players) {
    	this.players = players;
    }
    public String[] playerOrder(String p1, String p2, String p3, String p4) {
    	String[] playerString = {p1, p2, p3, p4};
    	return playerString;
    }
    public int flipDice(){
    	int roll = (int)(Math.random()*6+1);
    	return roll;
    }
    public void play(String[] order) {
        boolean playing = true;
        boolean checkLS;
        boolean reg;
        int[] positions = {0, 0, 0, 0};
        while(playing) {
            for(int i = 0; i < getPlayers(); i++) {
                checkLS = true;
                reg = true;
                int nextRoll = flipDice();
                System.out.println("\nNEW PLAYER POSITIONS:");
                for(int x = 0; x < getPlayers(); x++)
                {
                	System.out.println(order[x] + " is at sqaure " + positions[x] + "	");
                }
                System.out.println("\nRolling.......... ");
                System.out.println(order[i] + " rolls " + nextRoll);
                while(checkLS) {
                    for(int j = 0; j <15; j++) {
                        if(positions[i]+nextRoll == board[j][0]) {
                            if(positions[i] < board[j][1]) {
                                System.out.println(order[i] + " moves up to square " + (positions[i] + nextRoll));
                                System.out.println("Its a ladder! " + order[i] + " moves up to square " + board[j][1]);
                            }
                            else {
                                System.out.println(order[i] + " moves up to square " + (positions[i] + nextRoll));
                                System.out.println("Oh no a snake! " + order[i] + " moves down to square " + board[j][1]);
                            }
                            positions[i] = board[j][1];
                            reg = false;
                            break;
                        }
                    }
                    checkLS = false;
                }
                while(reg)
                {
                    if(positions[i]+nextRoll > 100) {
                        int excess = 100-(positions[i] + nextRoll);
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
                        else {
                            positions[i] = 100 + excess;
                            System.out.println(order[i] + " rolled over 100... moves back to square " + positions[i]);
                        }
                        break;
                    }
                    if(positions[i]+nextRoll==100 || positions[i]+nextRoll==80) {
                    	if(positions[i]+nextRoll==80) {
                    		System.out.println(order[i] + " moves up to square 80");
                    		System.out.println("Its a ladder! " + order[i] + " moves up to square 100");
                    	}
                    	else {
                            System.out.println(order[i] + " moves up to square 100");
                    	}
                        positions[i] = 100;
                        System.out.println(order[i] + " has won!");
                        playing = false;
                        i = 4;
                        break;
                    }  
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
