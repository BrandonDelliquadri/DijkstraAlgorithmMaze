import java.util.List;
import java.util.ArrayList;
import java.lang.Math;
import java.io.IOException;

class Maze{
	private int rooms;
	private Room[] maze;
	private Path mazePath;
	

	public Maze(double rooms){
		this.rooms = (int)rooms;
		maze = new Room[this.rooms];
		for(int i = 0; i<this.rooms;i++){
			maze[i] = new Room(i);
		}
	}

	public void generateWall(int origin, int destination, double value){
		Wall tempWall = new Wall(origin,destination,value);
		maze[origin].addWall(tempWall);
		tempWall = new Wall(destination,origin,value);
		maze[destination].addWall(tempWall);
	}

	public List<Wall> getAdjacent(int roomNum){
		return maze[roomNum].getWalls();
	}

	public void displayAdjacentRooms(int roomNum){
		List<Wall> neighborList = getAdjacent(roomNum);
		int neighborAmmount = neighborList.size();
		System.out.println("-------------------------------------------");
		System.out.println("Room #" + roomNum + " is connected to:");
		System.out.println("-------------------------------------------");
		for(int i = 0;i<neighborAmmount;i++){
			Wall neighbor = neighborList.get(i);
			System.out.println("Room #" + neighbor.getDestination() +" with value " + neighbor.getValue());
		}
		System.out.println("-------------------------------------------");
	}

	public void populateMaze(ArrayList<Double> inputList){
		int wallsPerLine = (int)Math.sqrt(rooms);
		int vertIterator = inputList.size()/2;
		int horiIterator = 0;
		for(int i = 0;i<rooms;i++){
			if((i+1)%wallsPerLine!=0){
				generateWall(i,i+1,inputList.get(horiIterator));
				horiIterator+=1;
			}
			if(i<wallsPerLine){
				for(int j = 0; j<rooms-wallsPerLine;j+=wallsPerLine){
					generateWall(j + i,i+j+wallsPerLine,inputList.get(vertIterator));
					vertIterator++;
				}
			}
		}
	}

	public void printMaze(){
		for(int i = 0; i<rooms;i++){
			displayAdjacentRooms(i);
		}
	}

	public void solveMaze()throws IOException{
		List<Room> visitedRooms = new ArrayList<>();
		List<Room> visitableRooms = new ArrayList<>();
		mazePath = new Path();
		visitableRooms.add(maze[0]);
		visitedRooms.add(maze[0]);
		double highestValue = 0.0;
		Wall highestValueWall = null;
		while(visitableRooms.size()!=rooms){
			highestValue = 0.0;
			for(int i = 0; i<visitableRooms.size(); i++){
				Room currentRoom = visitableRooms.get(i);
				List<Wall> currentWalls = currentRoom.getWalls();
				for(int j = 0; j<currentWalls.size();j++){
					if(currentWalls.get(j).getValue() > highestValue){
						if(!visitedRooms.contains(maze[currentWalls.get(j).getDestination()])){		
							highestValueWall = currentWalls.get(j);
							highestValue = highestValueWall.getValue();
						}
					}
				}
			}
			int origin = highestValueWall.getOrigin();
			int destination = highestValueWall.getDestination();
			visitableRooms.add(maze[destination]);
			visitedRooms.add(maze[destination]);
			mazePath.breakWall(origin,destination);
			outputPath();
		}
	}

	public void outputPath()throws IOException{
		mazePath.printPath();
	}
}