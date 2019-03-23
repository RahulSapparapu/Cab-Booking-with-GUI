package iiitb.ess201a7.r16062;

import iiitb.ess201a7.a7base.*;

class Car16062 extends Car {
	private Location setter;
	static int count_car = 160620;
	private int status=1;
	private Trip tri;
	private double x1,y1;

	public Car16062(int speed) {
		super(++count_car,speed);

	}

	@Override
	public void setLocation(Location l) {
		setter =  l;
		y1 =  setter.getY();
		x1 =  setter.getX();
	}

	@Override
	public Location getLocation() {
		return setter;

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
	public int distSqrd(Location loc) {
		return (int)(Math.pow(setter.getX() - loc.getX(), 2) + Math.pow(setter.getY() - loc.getY(), 2));
	}
	@Override
	public void assignTrip(Trip trip) {
		if(status==1){
			tri = trip;
			status = 2;
			y1 =  setter.getY();
			x1 =  setter.getX();
		}
		else
				return;

	}

	@Override
	public Location getStart() {
		if(status==Idle)
			return null;
		return tri.getStart();
	}
	@Override
	public Trip getTrip() {
		    return tri;
	}

	@Override
	public Location getDest() {
		if(status==Idle)
			return null;
		return tri.getDest();

	}

	@Override
	public void updateLocation(double deltaT) {
		/*if(status==1){
			return;
		}
		else if(status==2){
				car_to_start = Math.sqrt(Math.pow(getLocation().getX()-getStart().getX(),2)+Math.pow(getLocation().getY()-getStart().getY(),2));
				if(deltaT >= car_to_start/getSpeed()){
						setLocation(new Location(getStart().getX(),getStart().getY()));
						x1 = (double) tri.getStart().getX();
						y1 = (double) tri.getStart().getY();
						status = 3;
				}
				else{
					start_to_dest =  (deltaT*getSpeed());
					travel = Math.sqrt(Math.pow(x1 - getStart().getX(), 2) + Math.pow(y1 - getStart().getY(), 2));
					x_travel = (double) (getStart().getX() - x1);
					y_travel = (double) (getStart().getY() - y1);
					//slope1 = Math.atan(getLocation().getY()-getStart().getY()/getLocation().getX()-getStart().getX());
					x1 += start_to_dest*Math.cos(x_travel/travel);
					xx1 = x1.intValue();
					y1 += start_to_dest*Math.sin(y_travel/travel);
					yy1 = y1.intValue();
					setLocation(new Location(xx1,yy1));
				}
		}
		if(status==3){
			car_to_start = Math.sqrt(Math.pow(getStart().getX()-getDest().getX(),2) + Math.pow(getStart().getY() - getDest().getY(),2));
			if(deltaT>=car_to_start/getSpeed()){
				setLocation(new Location(getDest().getX(),getDest().getY()));
				status = 1;
				return;
			}
			start_to_dest = deltaT*getSpeed();
			travel = Math.sqrt(Math.pow(x1 - getDest().getX(), 2) + Math.pow(y1 - getDest().getY(), 2));
			x_travel = (double) (getDest().getX() - x1);
			y_travel = (double) (getDest().getY() - y1);
			//slope2 = Math.atan(getStart().getY() - getDest().getY()/getStart().getX() - getDest().getX());
			x1 += start_to_dest*Math.cos(x_travel/travel);
			xx2 = x1.intValue();
			y1 += start_to_dest*Math.sin(y_travel/travel);
			yy2 = y1.intValue();
			setLocation(new Location(xx2,yy2));
		}
	}*/
	if(this.getStatus()==1)
			return ;
	else if(this.getStatus()==2) {
			if(Math.pow(deltaT*getSpeed(),2)>=(Math.pow(x1-tri.getStart().getX(), 2))+Math.pow(y1-tri.getStart().getY(), 2)) {
				x1 = tri.getStart().getX();
				y1 = tri.getStart().getY();
				setter = this.getStart();
				this.setStatus(3);
				return;
			}
			double dist = Math.sqrt(Math.pow(x1-this.getStart().getX(), 2)+Math.pow(y1-this.getStart().getY(), 2));
			double cos = (this.getStart().getX()-x1)/dist;
			double sin = (this.getStart().getY()-y1)/dist;
			x1 +=this.getSpeed()*deltaT*cos;
			y1 += this.getSpeed()*deltaT*sin ;
			this.setLocation(new Location((int) x1, (int) y1));
		}
		else if(this.getStatus()== 3) {
			if(Math.pow(deltaT*getSpeed(),2)>=(Math.pow(x1-tri.getDest().getX(), 2))+Math.pow(y1-tri.getDest().getY(), 2)) {
				x1 = tri.getDest().getX();
				y1 = tri.getDest().getY();
				setter = getDest();
				this.setStatus(1);
				return;
			}
			double dist = Math.sqrt(Math.pow(x1-this.getDest().getX(), 2)+Math.pow(y1-this.getDest().getY(), 2));
			double cos = (this.getDest().getX()-x1)/dist;
			double sin = (this.getDest().getY()-y1)/dist;
			x1 += this.getSpeed()*deltaT*cos ;
			y1 += this.getSpeed()*deltaT*sin ;
			this.setLocation(new Location((int) x1, (int) y1));
			}
		}
	}
