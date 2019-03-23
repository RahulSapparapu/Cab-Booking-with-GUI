package iiitb.ess201a7.a7base;

public abstract class Car {
	private int id;

	protected int maxSpeed;  

	
	public static final int Idle = 1;
	public static final int Booked = 2;
	public static final int OnTrip = 3;

	protected Car(int fid, int speed) {
		id = fid;
		maxSpeed = speed;
	}

	public int getId() {
		return id;
	}

	public int getSpeed() {
		return maxSpeed;
	}

	public abstract int distSqrd(Location loc) ;

	public abstract void setLocation(Location l);

	public abstract Location getLocation();

	public abstract void setStatus(int s);

	public abstract int getStatus();

	public abstract void assignTrip(Trip trip);

	public abstract Trip getTrip();

	public abstract Location getStart();

	public abstract Location getDest();

	public abstract void updateLocation(double deltaT);

}
