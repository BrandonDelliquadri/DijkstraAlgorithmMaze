class Wall{
	private int origin;
	private int destination;
	private double value;

	public Wall(int origin, int destination, double value){
		this.origin = origin;
		this.destination = destination;
		this.value = value;
	}

	public double getValue(){
		return this.value;
	}
	public int getDestination(){
		return this.destination;
	}
	public int getOrigin(){
		return this.origin;
	}
}