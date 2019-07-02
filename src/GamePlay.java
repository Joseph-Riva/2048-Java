import java.util.Arrays;
import java.util.Scanner;
public class GamePlay {
	public static int[][] board;
	public static void initBoard() {
		board = new int[4][4];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j<board[i].length; j++) {
				board[i][j] = 0;
			}
		}
		//board[0][0] = 2;
		//board[0][1] = 2;
		//board[0][2] = 2;
		for(int i = 0; i < 2; i++) {
			int num = (int)(Math.random()*2);
			if(num == 0) {
				num = 2;
			}else{
				num = 4;
			}
			int[] starter = getBlankPosition();
			board[starter[0]][starter[1]] = num;
		}
	}
	public static void printBoard() {
		String top = " ~~~~~~";
		String bottom = "|      ";
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j<3; j++) {
				if(j == 0) {
					for(int a = 0; a < 4; a++) {
						System.out.print(top);
					}
					System.out.println();
				}else if(j == 1){
					for(int a = 0; a < 5; a++) {
						System.out.print(bottom);
					}
					System.out.println();
				}
				else if(j == 2) {
					for(int a = 0; a < 4; a++) {
						int cur = board[i][a];
						if(cur == 0) {
						System.out.print(bottom);
						}
						if(cur != 0) {
							System.out.print("| ");
							System.out.format("%4d", cur);
							System.out.print(" ");
						}
					}
					System.out.print(bottom);
					System.out.println();
				}
			}
		}
		for(int a = 0; a < 4; a++) {
			System.out.print(top);
		}
	}
	public static boolean isFull() {
		int count = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j<board[i].length; j++) {
				int cur = board[i][j];
				if(cur == 0) {
					count++;
				}
			}
		}
		if(count == 0) {
			return true;
		}else {
		return false;
		}
	}
	public static int[] getBlankPosition() {
		int[] position = new int[2];
		if(isFull()) {
			return null;
		}else { 
			while(true) {
				int randomRow = (int) (Math.random()*4);
				int randomCol = (int) (Math.random()*4);
				if(board[randomRow][randomCol] == 0) {
					position[0] = randomRow;
					position[1] = randomCol;
					break;
				}
			}
		}
		return position;	
	}
	public static char getInputDirection() {
		Scanner s = new Scanner(System.in);
		char direction = 'a';
		while(true) {
			System.out.println("");
			System.out.println("Input a direction(w, a, s, or d): ");
			String input = s.nextLine();
			if(input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d")){
				direction = input.charAt(0);
				break;
			}
		}
		return direction;
	}
	public static int[] getRow(int row, char direction) {
		int[] curRow = new int[board[row].length];
		int count = 0;
		if(direction == 'd') {
			for(int j = 0; j < board[row].length;j++) {
				curRow[count] = board[row][j];
				count++;
			}
		}else {
			for(int j = board[row].length-1; j >=0 ;j--) {
				curRow[count] = board[row][j];
				count++;
			}
		}
		return curRow;
			
	}
	public static int[] getCol(int col, char direction) {
		int[] curCol = new int[board.length];
		int count = 0;
		if(direction == 's') {
			for(int j = 0; j < board.length; j++) {
				curCol[count] = board[j][col];
				count++;
			}
		}else {
			for(int j = board.length-1; j >= 0; j--) {
				curCol[count] = board[j][col];
				count++;
			}
		}
		return curCol;
	}
	public static void copyRow(int row, int[] curRow, char direction) {
		if(direction == 'd') {
			for(int i = 0; i < board[row].length; i++) {
				board[row][i] = curRow[i];
			}
		}else {
			int counter = 0;
			for(int i = board[row].length-1; i>=0; i--) {
				board[row][i] = curRow[counter];
				counter++;
			}
		}
	}
	public static void copyColumn(int col, int[] curCol, char direction) {
		if(direction == 's') {
			for(int i = 0; i < board.length; i++) {
				board[i][col] = curCol[i];
			}
		}else {
			int count = 0;
			for(int i = board.length-1; i>=0; i--) {
				board[i][col] = curCol[count];
				count++;
			}
		}
	}
	public static void changeElements(int[] array) {
		int index = array.length - 1;
		for(int i = array.length-1; i >= 0; i--) {
			if(array[i] != 0)
			{
				array[index] = array[i];
				index--;
			}
		}
		for (int i = index; i >= 0; i--)
		{
			array[i] = 0;
		}
	}
	public static void sumArray(int[] array) {
		for(int i = array.length-1; i >0; i--){
			if(array[i] == array[i-1]) {
				array[i] = array[i]*2;
				array[i-1] = 0;
			}
		}
		/*
		if(direction == 'd' || direction == 's') {
			//right and down case
			for(int i = 0; i < array.length-1; i++) {
				if(array[i] == array[i+1]){
					if(i <= array.length-3 && array[i+1] == array[i+2]) {
						array[i+2] += array[i+1];
						array[i+1] = 0;
					}else{
						array[i+1] += array[i];
						array[i] = 0;
					}
				}
			}
		}else {
			//left and up cases
			for(int i = 0; i < array.length-1; i++) {
				if(array[i] == array[i+1]){
					array[i+1] += array[i];
					array[i] = 0;
				}
			}
		}
		*/
	}
	public static void moveBoard(char direction) {
		if(direction == 'a' || direction == 'd') {
			for(int i = 0; i < board.length; i++) {
				int[] curRow = getRow(i, direction);
				//System.out.print("change in one: " + Arrays.toString(curRow));
				changeElements(curRow);
				//System.out.print("change in before sum: " + Arrays.toString(curRow));
				sumArray(curRow);
				changeElements(curRow);
				copyRow(i, curRow, direction);
				//System.out.println(" change out: " + Arrays.toString(curRow));

			}
		}else {
			for(int i = 0; i < board[0].length; i++) {
				int[] curCol = getCol(i, direction);
				//System.out.print("Change in one: " + Arrays.toString(curCol));
				changeElements(curCol);
				//System.out.print("Change in before sum: " + Arrays.toString(curCol));
				sumArray(curCol);
				changeElements(curCol);
				copyColumn(i, curCol, direction);
				//System.out.println("Change out: " + Arrays.toString(curCol));
			}
		}
	}
	public static boolean check2048() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				int cur = board[i][j];
				if(cur == 2048) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean checkAdjacent() {
		//returns false if there are no adjacent tiles which are the same num
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				int cur = board[i][j];
				int next = board[i+1][j];
				int bottom = board[i][j+1];
				if(cur == next || cur == bottom) {
					return true;
				}
			}
		}
		return false;
	}
	public static void addNewNumber() {
		int[] position = getBlankPosition();
		board[position[0]][position[1]] = 2;
	}
	public static int[][] getBoard(){
		if(board == null) {
			initBoard();
		}
		return board;
	}
	public static void main(String[] args) {
		initBoard();
		boolean isEnd = false;
		while(true) {
			printBoard();
			char direction = getInputDirection();
			moveBoard(direction);
			isEnd = check2048();
			if(isEnd) {
				System.out.println("You win!!!");
				break;
			}
			isEnd = isFull();
			if(isEnd && !checkAdjacent()){
				System.out.println("You lose!!!");
				break;
			}
			addNewNumber();
		}
	}

}
