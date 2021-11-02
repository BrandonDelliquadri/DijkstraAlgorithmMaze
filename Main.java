/*
Created by: Brandon Delliquadri
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class Main {

	/*
	*Reads the input file. puts all of the data in an arraylist
	*/
	public static void readFile(ArrayList<Double> input){
		Scanner scan;
		File file = new File("input.txt");
		try{
			scan = new Scanner(file);

			while(scan.hasNextDouble()){
				input.add(scan.nextDouble());
			}
		}
		catch(FileNotFoundException e){
			System.out.println("Expected input.txt");
			e.printStackTrace();
		}
	}

  public static void main(String[] args)throws IOException {
		ArrayList<Double> inputList = new ArrayList<Double>();
		readFile(inputList);
		double size = inputList.remove(0);
		size = size * size;
		Maze inputMaze = new Maze(size);
		inputMaze.populateMaze(inputList);
		inputMaze.solveMaze();
		
  }
}