
//Pravallika Santhil
//Joshua Culver
//Wenyi Yin
/*
 * Copyright (c) 2002-2006, Marc Prud'hommeaux. All rights reserved.
 *
 * This software is distributable under the BSD license. See the terms of the
 * BSD license in the documentation provided with this software.
 */
//package workspace;

import java.util.concurrent.*;
import static java.lang.Math.*;

/**
 * Code for this class currently runs a local main() for testing (shows
 * a ball moving across the screen) or operates with ASCIIGameTemplate
 * main() to allow keyboard input (see allowed chars below) to move the ball
 * right.
 **/

class ASCIIGame {

    static StringBuilder[] screen;
    static StringBuilder line;
    static StringBuilder blank;
    int score;
    int[] nums;
    int index;
    final static int WIDTH = 76;
    final static int HEIGHT = 20;
    final static int BOXWIDTH = 13;
    boolean roundNotOver = true;
    boolean hasStart = false;
    int round;
    int i;
    String insLine1 = "In this game, you will be given observe a pattern of numbers being";
    String insLine2 = "pressed. Your job is to re-enter the pattern you see.";
    String insLine3 = "Be sure to do it in time!";
    String insLine4 = "Press s to begin!";
    String[][] dispArrays = {{" _____ ","|     |","|  |  |", "|  |  |", "|_____|"}, {" _____ ","|  _  |","|  _| |", "| |_  |", "|_____|"},
    		{" _____ ","|  _  |","|  _| |", "|  _| |", "|_____|"}, {"|_____|","|     |","| |_| |", "|   | |", "|_____|"},
    		{"|_____|","|  _  |","| |_  |", "|  _| |", "|_____|"}, {"|_____|","|  _  |","| |_  |", "| |_| |", "|_____|"},
    		{"|_____|","|  _  |","|   | |", "|   | |", "|_____|"}, {"|_____|","|  _  |","| |_| |", "| |_| |", "|_____|"},
    		{"|_____|","|  _  |","| |_| |", "|   | |", "|_____|"}};
    Button[] buttons = {new Button(this, 7, 26, 33, dispArrays[0]), new Button(this, 7, 32, 39, dispArrays[1]), 
    		new Button(this, 7, 38, 45, dispArrays[2]), new Button(this, 11, 26, 33, dispArrays[3]), new Button(this, 11, 32, 39, dispArrays[4]),
    		new Button(this, 11, 38, 45, dispArrays[5]), new Button(this, 15, 26, 33, dispArrays[6]), new Button(this, 15, 32, 39, dispArrays[7]),
    		new Button(this, 15, 38, 45, dispArrays[8])};
    

    /*******************************************************************
     * Constructor - initializes screen and line
     *******************************************************************/
    ASCIIGame(){
	//Initialize variables
	this.score = 0;
	this.nums = new int[100];
	this.index = 0;
	this.round = 1;
	this.i = 0;

	screen = new StringBuilder[HEIGHT];

	blank = new StringBuilder("||");
	for (int j = 0; j < WIDTH-2; j++) 
		blank.insert(1, ' ');

	for(int j = 0; j < HEIGHT; j++)
	    screen[j] = new StringBuilder(blank); //WHY?

	//Prints the home screen
	screen[0].replace(28, 44, "WELCOME TO SIMON");
	screen[2].replace(1, insLine1.length() + 1, insLine1);
	screen[3].replace(1, insLine2.length() + 1, insLine2);
	screen[4].replace(1, insLine3.length() + 1, insLine3);
	screen[5].replace(1, insLine4.length() + 1, insLine4);
	for(int i = 0; i<9; i++){
		buttons[i].display();
	}

	line = new StringBuilder("");
	for (int j = 0; j < WIDTH; j++) 
		line.append('_');
    }

    //Function generates a random integer between 1 and 9
    public int randNum(){
    	return (int) Math.ceil(Math.random() * 9);
    }

    //Function fills the nums array with random numbers
    public void fillRand(){
    	for (int j = 0; j < 100; j++){
    		nums[j] = randNum();
    		}
    } 
    /*******************************************************************
     * Print the current state.
     *******************************************************************/
    void printScreen(){
		System.out.println(line);
		for (int j = 0; j < HEIGHT; j++)
		    System.out.println(screen[j]);
		System.out.println(line);
    }

     /********************************************************************
     * Initialize game pieces.
     ********************************************************************/
    void init(){
    	this.fillRand();
    }
     /********************************************************************
      * Prints Game Over screen
      ********************************************************************/
     void gameOver(){
    	 screen[1].replace(20, 54, " _   _        _      _       _  _ ");
       	 screen[2].replace(20, 54, "|_  |_| |\\/| |_     | | | / |_ |_|");
       	 screen[3].replace(20, 54, "|_| | | |  | |_     |_| |/  |_ |\\ ");
       	 screen[6].replace(1, 36, "Press s to restart! Press q to quit");
       	 printScreen();	
     }
    /********************************************************************
     * shows the sequence of keys being pressed
     ********************************************************************/
    public void playSequence() {
    	for(int j = 0; j < round; j++) {

    		buttons[nums[j]-1].cover();
	        long t_begin = System.currentTimeMillis();
	        long t_end = System.currentTimeMillis();
            while(t_end - t_begin<500 - (10*round)){
            	t_end = System.currentTimeMillis();
	        }

            buttons[nums[j]-1].display();
		    t_begin = System.currentTimeMillis();
	        t_end = System.currentTimeMillis();
            while(t_end - t_begin<500 - (10*round)){
            	t_end = System.currentTimeMillis();
           	}	
    	}
    }
    /********************************************************************
     * Have game respond to a single character input.
     ********************************************************************/
    void processChar(long endTime, long t_start){
    	//add sound when user press the numbers
    	//System.out.println("\007"); 

	    //The user entered number flashes on screen to let the user know what they’ve inputted
    	if (i != 'q' && i != 's'){
    		int k = Character.getNumericValue(i);
    		buttons[k-1].wipe();

	          long t_begin = System.currentTimeMillis();
	          long t_end = System.currentTimeMillis();
              while(t_end - t_begin < 50){
            	  t_end = System.currentTimeMillis();
	          }

              buttons[k-1].display();
		      t_begin = System.currentTimeMillis();
	          t_end = System.currentTimeMillis();
              while(t_end - t_begin < 50){
            	  t_end = System.currentTimeMillis();
	          }
    	}
	
    	if(hasStart){
    		if(endTime - t_start > 4000){
    			i = (int) 'q';
    			roundNotOver = false;
    			gameOver();
            }
    		else if(Character.getNumericValue(i) == nums[index]){
    			score++;
    			index++;
    			screen[5].replace(12-(Integer.toString(score).length()), 12, Integer.toString(score));
    			screen[4].replace(12-(Integer.toString(round).length()), 12, Integer.toString(round));
    			printScreen();
    			if(index==round){
    				index = 0;
    				round++;
    				roundNotOver = false;
    			}
    		}
    		else{
    			gameOver();
    			roundNotOver = false;
    			i = (int) 'q';
            } 
    	}
    	else {
    		switch(i){
	        	case 's':
	        		hasStart = true;
		       
	        		//Clears the instructions
	        		String insBlank1 = "";
	        		String insBlank2 = "";
	        		String insBlank3 = "";
	        		String insBlank4 = "";
	        		for (int i = 0; i < insLine1.length();i++){
	        			insBlank1 += " ";
	        		}	 
	        		for (int i = 0; i < insLine2.length();i++){
	        			insBlank2 += " ";
	        		}
	        		for (int i = 0; i < insLine3.length();i++){
	        			insBlank3 += " ";
	        		} 
	        		for (int i = 0; i < insLine4.length();i++){
	        			insBlank4 += " ";
	        		}  

	        		screen[2].replace(1, insLine1.length() + 1, insBlank1);
	        		screen[3].replace(1, insLine2.length() + 1, insBlank2);
	        		screen[4].replace(1, insLine3.length() + 1, insBlank3);
	        		screen[5].replace(1, insLine4.length() + 1, insBlank4);

	        		//Prints player’s credentials
	        		screen[4].replace(1, 12, "Round:    1");
	        		screen[5].replace(1, 12, "Score:    0");
	        		screen[6].replace(1, 17, "Press q to quit.");
		       
	        		playSequence();
    		}       
    	}
    }
    
    /********************************************************************
     * For testing purposes only.
     ********************************************************************/
    public static void main(String[] a){
    	ASCIIGame game = new ASCIIGame();
    	try {
    		game.printScreen();
    		TimeUnit.MILLISECONDS.sleep(100);
	    
    		for(int j = 0; j < 25; j++){
    			game.printScreen();
    		}
    	} 
    	catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
}

