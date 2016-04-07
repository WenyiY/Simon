
public class Button {
	String[] dispArray;
	String[] wipeArray = {"|     |","|     |", "|     |", "|_____|"};
	String[] coverArray = {"|*****|","|*****|", "|*****|", "|_____|"};
	int columnStart;
	int columnEnd;
	int rowNum;
	ASCIIGame game;
	
	public Button(ASCIIGame asciiGame, int rowNum, int columnStart, int columnEnd, String[] dispArray){
		this.game = asciiGame;
		this.rowNum = rowNum; 
		this.dispArray = dispArray;
		this.columnStart = columnStart;
		this.columnEnd = columnEnd;
	}
	
	public void display() { // display number 1 by line
		for(int i = 0; i < 5; i++){
			game.screen[rowNum+i].replace(columnStart, columnEnd, dispArray[i]);
			game.printScreen();
		}
	}
	
	public void wipe(){ // display empty button by line
		for(int i = 1; i < 5; i++){
			game.screen[rowNum+i].replace(columnStart, columnEnd, wipeArray[i-1]);
			game.printScreen();
		}
	}
	
	public void cover(){ // display pressed button by line
		for(int i = 1; i < 5; i++){
			game.screen[rowNum+i].replace(columnStart, columnEnd, coverArray[i-1]);
			game.printScreen();
		}
	}
}
