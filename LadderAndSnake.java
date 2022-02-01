public class LadderAndSnake {
    private int players;
    private final static int [][] board = {{1,38},{4,14},{9,31},{16,6},{21,42}
    ,{28,84},{36,44},{48,30},{51,67},{62,19}
    ,{64,60},{71,91},{93,68},{95,24},
    {97,76},{98,78}}; //Removing {80, 100} and hardcoding it into loop in play()
    
    public LadderAndSnakeRetry() {}
    public LadderAndSnakeRetry(LadderAndSnakeRetry lasr) {
    	this.players = lasr.players;
    }
    public LadderAndSnakeRetry(int numPlayers) {
    	players = numPlayers;
    }
    
    public int getPlayers() {
    	return players;
    }
    public void setPlayers(int players) {
    	this.players = players;
    }
    public int[] playerOrder() {
    	int[] test = {1,2,3,4};
    	return test;
    }
    public int flipDice(){
    	int roll = (int)(Math.random()*6+1);
    	return roll;
    }
    public void play() {
    //STILL NEED TO RESET I and J
    boolean playing = true;
    boolean checkLS = true;
    boolean reg = true;
	int[] positions = {0, 0, 0, 0};
    while(playing) {
    	for(int i = 0; i < getPlayers(); i++) {
    		checkLS = true;
    	    reg = true;
    	    int nextRoll = flipDice();
			System.out.println("POSITIONS: " + positions[0] + ", " + positions[1]+ ", " + positions[2]+ ", " + positions[3]); 
			System.out.println("ROLL for player " + i + " is " + nextRoll);
			while(checkLS) {
				System.out.println("Checkin LS");
        		for(int j = 0; j <15; j++) {
        			if(positions[i]+nextRoll == board[j][0]) {
            			positions[i] = board[j][1];
            			checkLS = false;
                		reg = false;
            			break;
            		}
        		}
        		checkLS = false;
    		}
			while(reg)
			{
				System.out.println("In regular");
				//positions[i] = positions[i] + nextRoll;
				if(positions[i]+nextRoll > 100) {
					int excess = 100-(positions[i] + nextRoll);
					System.out.println("*****OVER 100*****");
					if(100+excess == 98 ) {
						positions[i] = 78;
						reg = false;
					}
					if(100+excess == 98) {
						positions[i] = 76;
						reg = false;
					}
					if(100+excess == 95) {
						positions[i] = 24;
						reg = false;
					}
					else {
						positions[i] = 100+excess;
						reg = false;
					}
				}
				if(positions[i]+nextRoll==100 || positions[i]+nextRoll==80) {
					positions[i] = 100;
					System.out.println("Player " + i + " has won ");
					reg = false;
					checkLS = false;
					playing = false;
					i = 3;
					break;
				}
				else
				{
					positions[i] = positions[i]+nextRoll;
					reg = false;
				}
			}
    	}
    }
}
}



