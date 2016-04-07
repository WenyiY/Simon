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
import jline.*;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/************************************************************
 * This code wraps an ASCII game so that we can process individual
 * char inputs (otherwise, Java only likes to process whole lines
 * followed by "enter" key).
 ************************************************************/

public class ASCIIMain {

    static ASCIIGame game;

    public static void main(String[] args) throws IOException {
        Character mask = null;
        String trigger = null;
        game = new ASCIIGame();
	
        ConsoleReader reader = new ConsoleReader(System.in, new PrintWriter(System.out));

        char[] allowed = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 's'};
        char[] menu = {'q', 's'};
	
        while(game.i != (int)'q'){
        	game = new ASCIIGame();
        	game.init();
        	game.printScreen();
	
        	try {
        		while(game.i != (int)'q') {
        			//allowed = {}
        			if(game.hasStart){
        				game.playSequence();
        			}
        			//allowed = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 's'};
        			game.roundNotOver = true;
        			while(game.roundNotOver){
        				long t_start = System.currentTimeMillis();
        				game.i = reader.readCharacter(allowed);
        				game.processChar(System.currentTimeMillis(), t_start);
        				TimeUnit.MILLISECONDS.sleep(100);
        			}
        		}
        	} 
        	catch (InterruptedException e) {
        		e.printStackTrace();
        	}
		
        	game.i = reader.readCharacter(menu);
        }
    }
}
