package iiitb.ess201a7.r16006;

import iiitb.ess201a7.a7base.*;
import java.lang.*;
import java.util.*;

public class Fleet16006 extends Fleet {

	private ArrayList<Car> carsList;
	private int count;

	public Fleet16006(String colour) {
		super(16006,colour);
		carsList=new ArrayList<Car>();
		count=0;
	}

	@Override
	public void addCar(int speed) {
		count++;
		String id=String.valueOf(this.getId())+ String.valueOf(count);
		int carId=Integer.parseInt(id);
		Car c=new Car16006(carId,speed);
		carsList.add(c);
	}

	@Override
	public Car findNearestCar(Location loc) {
		double min=1600699.0;
		int a=789455;
		for(int i=0;i<count;i++){
			Car c=carsList.get(i);
			if(c.getStatus()==1){
				Location l1=c.getLocation();
				int x1=loc.getX();
				int x2=l1.getX();
				int y1=loc.getY();
				int y2=l1.getY();
				double dist=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
				if(min>dist){
					min=dist;
					a=i;
				}
			}
		}
		if(min!=1600699.0 && a!=789455)
			return carsList.get(a);
		return null;
	}

	@Override
	public ArrayList<? extends Car> getCars(){
		return carsList;
	}
}
