package iiitb.ess201a7.r16123;
import java.util.Random;
import iiitb.ess201a7.a7base.*;

class Car16123 extends Car {
	private Location l;
	private int status=1;
	private Trip trip;
	// private carid=0
	private Random r=new Random();

	public Car16123(int fid,int speed) {
		super(fid, speed);
		l= new Location(r.nextInt(1001),r.nextInt(1001));
		//l= new Location(0,0);
	}

	@Override
	public void setLocation(Location l) {
		// TODO Auto-generated method stub
		this.l=l;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return l;
	}

	@Override
	public void setStatus(int s) {
		// TODO Auto-generated method stub
		status=s;
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public void assignTrip(Trip trip) {
		// TODO Auto-generated method stub
		this.trip=trip;
		status=2;
	}

	@Override
	public Location getStart() {
		// TODO Auto-generated method stub
		// 111111111

		return trip.getStart();
	}
	public Trip getTrip(){
		return trip;
	}

	@Override
	public Location getDest() {
		// TODO Auto-generated method stub
		return trip.getDest();
	}

	@Override
	public void updateLocation(double deltaT) {
		// TODO Auto-generated method stub
		int x=l.getX(), y=l.getY();
		if(getStatus()==1)
			return;
		else if(getStatus()==2){
			if(super.getSpeed()*deltaT<=Math.sqrt(distSqrd(trip.getStart()))){

				// deltay=

				x+=(trip.getStart().getX()-l.getX())*super.getSpeed()*deltaT/Math.sqrt(distSqrd(trip.getStart()));
				y+=(trip.getStart().getY()-l.getY())*super.getSpeed()*deltaT/Math.sqrt(distSqrd(trip.getStart()));
				l.set(x,y);
			}
			else{
				l.set(trip.getStart().getX(),trip.getStart().getY());
				status=3;
			}

		}
		else{
			if(super.getSpeed()*deltaT<=Math.sqrt(distSqrd(trip.getDest()))){
				// deltax=(trip.getDest().getX()-l.getX())*super.getSpeed()*deltaT/Math.sqrt(distSqrd(trip.getDest()));
				// deltay=(trip.getDest().getY()-l.getY())*super.getSpeed()*deltaT/Math.sqrt(distSqrd(trip.getDest()));
				// l.set(l.getX()+deltax,l.getY()+deltay);
				x+=(trip.getDest().getX()-l.getX())*super.getSpeed()*deltaT/Math.sqrt(distSqrd(trip.getDest()));
				y+=(trip.getDest().getY()-l.getY())*super.getSpeed()*deltaT/Math.sqrt(distSqrd(trip.getDest()));
				l.set(x,y);

				}
			else {
				l.set(trip.getDest().getX(),trip.getDest().getY());
				status=1;
			}
			}
	}
	@Override
	public int distSqrd(Location loc) {
		int xy;
		xy= (l.getX()-loc.getX())*(l.getX()-loc.getX())+(l.getY()-loc.getY())*(l.getY()-loc.getY());
		return xy;
	}



}
