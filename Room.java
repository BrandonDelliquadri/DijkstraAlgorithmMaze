import java.util.ArrayList;
import java.util.List;

class Room{
	private int num;
	private List<Wall> adjacentWalls = new ArrayList<>();

	public int getRoomNum(){
		return num;
	}

	public Room(int num){
		this.num = num;
	}

	public void addWall(Wall w){
		adjacentWalls.add(w);
	}

	public List<Wall> getWalls(){
		return adjacentWalls;
	}
}