import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
class Path{
	private Node start;
	private int pathLength;

	private class Node{
		private int roomNum;
		private int parent;
		
		private Node break1 = null;
		private Node break2 = null;
		private Node break3 = null;
		private Node break4 = null;
		public Node(int roomNum){
			this.roomNum = roomNum;
		}
		public void addNode(int breakNum){
			if(this.break1 == null){
				//System.out.println(this.roomNum + " to " + breakNum);
				this.break1 = new Node(breakNum);
			}
			else if(this.break2 == null){
				//System.out.println(this.roomNum + " to " + breakNum);
				this.break2 = new Node(breakNum);
			}
			else if(this.break3 == null){
				//System.out.println(this.roomNum + " to " + breakNum);
				this.break3 = new Node(breakNum);
			}
			else if(this.break4 == null){
				//System.out.println(this.roomNum + " to " + breakNum);
				this.break4 = new Node(breakNum);
			}
			else{
				 System.out.println("No more nodes for some reason");
			}
		}
	}

	public Path(){
		this.start = new Node(0);
		pathLength = 0;
	}

	

	public void breakWall(int origin, int destination){
		Node originNode = search(this.start,origin);
		if(originNode!=null){
			originNode.addNode(destination);
			pathLength++;
		}
		else{
			System.out.println("SAVE_US_Y2J");
		}
	}

	public void printPath() throws IOException{
		File file = new File("output.txt");
		FileWriter write = new FileWriter(file);
		PrintWriter print = new PrintWriter(write);
		
		for(int i =1;i<=pathLength;i++){
			Node currentNode = search(this.start,i);
			if(currentNode!=null){
				double parentX = currentNode.parent/(Math.sqrt(pathLength));
				double parentY = currentNode.parent%(Math.sqrt(pathLength));
				double currentX = currentNode.roomNum/(Math.sqrt(pathLength));
				double currentY = currentNode.roomNum%(Math.sqrt(pathLength));
				print.println((int)parentX + " " + (int)parentY + " " +(int) currentX + " " + (int)currentY);
			}
		}
		write.close();
	}

	public Node search(Node start, int roomNum){
		if(start.roomNum == roomNum){
			return start;
		}
		if(start.break1 != null){
			if(start.break1.roomNum==roomNum){
				start.break1.parent = start.roomNum;
				return start.break1;
			}
			else{
				Node temp = search(start.break1, roomNum);
				if(temp != null){
					if(temp.roomNum==roomNum){
						return temp;
					}
				}
			}
			
		}
		if(start.break2 != null){
			if(start.break2.roomNum==roomNum){
				start.break2.parent = start.roomNum;
				return start.break2;
			}
			else{
				Node temp = search(start.break2, roomNum);
				if(temp!= null){
					if(temp.roomNum==roomNum){
						return temp;
					}
				}
			}
			
		}
		if(start.break3 != null){
			if(start.break3.roomNum==roomNum){
				start.break3.parent = start.roomNum;
				return start.break3;
			}
			else{
				Node temp = search(start.break3, roomNum);
				if(temp!=null){
					if(temp.roomNum==roomNum){
						return temp;
					}
				}
			}
			
		}
		if(start.break4 != null){
			if(start.break4.roomNum==roomNum){
				start.break4.parent = start.roomNum;
				return start.break4;
			}
			else{
				Node temp = search(start.break4, roomNum);
				if(temp!=null){
					if(temp.roomNum==roomNum){
						return temp;
					}
				}
			}
		}
		return null;
	}
}