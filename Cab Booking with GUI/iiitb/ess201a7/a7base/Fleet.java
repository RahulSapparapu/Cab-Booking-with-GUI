package iiitb.ess201a7.a7base;

import java.util.ArrayList;

public abstract class Fleet {

	private int id;
	private String colour;

	protected Fleet(int fid, String col){
		id = fid;
		colour = col;
	}

	public int getId() {
		return id;
	}

	public String getColour() {
		return colour;
	}

	public abstract void addCar(int speed);

	public abstract Car findNearestCar(Location loc);

	public abstract ArrayList<? extends Car> getCars();
}
