package iiitb.ess201a7.r16006;

import iiitb.ess201a7.a7base.*;
import java.lang.*;
import java.util.*;

public class Car16006 extends Car {

	private Location l1,start,end;
	private int status,dist;
	private Trip trp;

	public Car16006(int fid,int speed) {
		super(fid,speed);
		status=Idle;
		l1=new Location(10,40);
	}

	@Override
	public void setLocation(Location l) {
		// TODO Auto-generated method stub
		l1=l;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return l1;
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
		start=trip.getStart();
		end=trip.getDest();
		trp=new Trip(start,end);
		this.setStatus(Booked);
	}

	@Override
	public Location getStart() {
		// TODO Auto-generated method stub
		if(status==Idle)
			return null;
		return start;
	}

	@Override
	public Location getDest() {
		// TODO Auto-generated method stub
		if(status==Idle)
			return null;
		return end;
	}

	@Override
	public void updateLocation(double deltaT) {
		// TODO Auto-generated method stub
		double newX,newY;
		int speed=this.getSpeed();
		if(status==OnTrip){
			// double theta=Math.atan((end.getY()-start.getY())/(end.getX()-start.getX()));
			double height=end.getY()-l1.getY();
			double base=end.getX()- l1.getX();
			double dist=deltaT*this.getSpeed();
			double d=Math.sqrt(this.distSqrd(end));
			if(d<=dist){
				newX=end.getX();
				newY=end.getY();
				l1.set((int)newX,(int)newY);
				this.setStatus(Idle);
			}
			else{
				newX=l1.getX()+((dist*base)/d);
				newY=l1.getY()+((dist*height)/d);
				l1.set((int)newX,(int)newY);
			}
			// if(newX==end.getX() && newY==end.getY())
			// 	this.setStatus(Idle);
		}
		else if(status==Booked){
			// double theta=Math.atan((start.getY()-l1.getY())/(start.getX()-l1.getX()));
			double height=start.getY()-l1.getY();
			double base=start.getX()-l1.getX();
			double dist=deltaT*this.getSpeed();
			double d=Math.sqrt(this.distSqrd(start));
			if(d<=dist){
				newX=start.getX();
				newY=start.getY();
				l1.set((int)newX,(int)newY);
				this.setStatus(OnTrip);
			}
			else{
				newX=l1.getX()+((dist*base)/d);
				newY=l1.getY()+((dist*height)/d);
				l1.set((int)newX,(int)newY);
			// if(newX==start.getX() && newY=start.getY())
			// 	this.setStatus(OnTrip);
			}
		}
	}

	@Override
	public int distSqrd(Location loc){
		int x1=loc.getX();
		int x2=l1.getX();
		int y1=loc.getY();
		int y2=l1.getY();
		return ((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}

	@Override
	public Trip getTrip(){
		return trp;
	}
}
