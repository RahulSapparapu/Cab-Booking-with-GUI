package iiitb.ess201a7.r16036;

import iiitb.ess201a7.a7base.*;
import java.lang.*;
class Car16036 extends Car {

	public Car16036(int id,int speed) {
		super(id,speed);
	}
	public Location lo;
	public int status=1;
	public Trip t;
	@Override
	public int distSqrd(Location loc) {
		int x1,x2,y1,y2,dist;
		x1 = loc.getX();
		y1 = loc.getY();
		x2 = lo.getX();
		y2 = lo.getY();
		dist = (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
		return dist;
	}
	@Override
	public void setLocation(Location l) {
		lo = l;
	}

	@Override
	public Location getLocation() {
		return lo;
	}

	@Override
	public void setStatus(int s) {
		status = s;
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public void assignTrip(Trip trip) {
		t = trip;
		status = 2;
	}
	public Trip getTrip() {
		return t;
	}
	@Override
	public Location getStart() {
		if(status!= 1){
			t.getStart();
		}
		return null;
	}

	@Override
	public Location getDest() {
		if(status!= 1){
			t.getDest();
		}
		return null;
	}

	@Override
	public void updateLocation(double deltaT) {
		// TODO Auto-generated method stub

if(status==2)
{

	Location pick=t.getStart();
Location dest=t.getDest();
double d=(deltaT*maxSpeed);
int prev_x=lo.getX();
int prev_y=lo.getY();
double sin=(pick.getY()-lo.getY())/Math.sqrt((pick.getX()-lo.getX())*(pick.getX()-lo.getX())+(lo.getY()-pick.getY())*(lo.getY()-pick.getY())),cos=(pick.getX()-lo.getX())/Math.sqrt((pick.getX()-lo.getX())*(pick.getX()-lo.getX())+(lo.getY()-pick.getY())*(lo.getY()-pick.getY()));

setLocation(new Location((int)(lo.getX()+Math.ceil(d*cos)),(int)(lo.getY()+Math.ceil(d*sin))));
	if(Math.sqrt((pick.getX()-lo.getX())*(pick.getX()-lo.getX())+(lo.getY()-pick.getY())*(lo.getY()-pick.getY()))<=maxSpeed*deltaT)
	{ setLocation(pick);
		status=3;
	}
	/*else if((lo.getX()>=pick.getX() && prev_x<lo.getX())||( lo.getY()>=pick.getY() && prev_y<lo.getY()))	 {
		setLocation(pick);
			status=3;
	}
	else if((lo.getX()>=pick.getX() && prev_x<lo.getX())||( lo.getY()<=pick.getY() && prev_y>lo.getY()))	 {
		setLocation(pick);
			status=3;
	}
	else if((lo.getX()<=pick.getX() &&  prev_x>lo.getX())||(lo.getY()>=pick.getY() && prev_y<lo.getY()))	 {
		setLocation(pick);
			status=3;
	}*/
}
else if(status==3)
{
	Location pick=t.getStart();
	Location dest=t.getDest();
	double d=(deltaT*maxSpeed);
	int prev_x=lo.getX();
	int prev_y=lo.getY();
	double sin=(dest.getY()-lo.getY())/Math.sqrt((dest.getX()-lo.getX())*(dest.getX()-lo.getX())+(lo.getY()-dest.getY())*(lo.getY()-dest.getY())),cos=(dest.getX()-lo.getX())/Math.sqrt((dest.getX()-lo.getX())*(dest.getX()-lo.getX())+(lo.getY()-dest.getY())*(lo.getY()-dest.getY()));

	setLocation(new Location((int)(lo.getX()+Math.ceil(d*cos)),(int)(lo.getY()+Math.ceil(d*sin))));
	if(Math.sqrt((dest.getX()-lo.getX())*(dest.getX()-lo.getX())+(lo.getY()-dest.getY())*(lo.getY()-dest.getY()))<=maxSpeed*deltaT)
	{ setLocation(dest);
		status=1;
	}
	/*else if(lo.getX()>=dest.getX() && lo.getY()>=dest.getY()&& prev_x<lo.getX() && prev_y<lo.getY())	 {
		setLocation(dest);
			status=1;
	}
	else if(lo.getX()>=dest.getX() && lo.getY()<=dest.getY()&& prev_x<lo.getX() && prev_y>lo.getY())	 {
		setLocation(dest);
			status=1;
	}
	else if(lo.getX()<=dest.getX() && lo.getY()>=dest.getY()&& prev_x>lo.getX() && prev_y<lo.getY())	 {
		setLocation(dest);
			status=1;
	}*/
}
	}



}
