package iiitb.ess201a7.r16123;
import java.util.*;
import iiitb.ess201a7.a7base.*;

public class Fleet16123 extends Fleet {
	private int carid=0;
	private ArrayList<Car> listofcars=new ArrayList<Car>();
	public Fleet16123(String colour) {

		super(16123,colour);

	}

	@Override
	public void addCar(int speed) {
		Car carob=new Car16123(Integer.parseInt("16123"+carid) ,speed);
		listofcars.add(carob);
		carid++;
	}

	@Override
	public Car findNearestCar(Location loc) {
		int minindex=0;int i;double min=Double.POSITIVE_INFINITY;
		for(i=0;i<listofcars.size();i++){
			if(listofcars.get(i).getStatus()==1){
				if(Math.sqrt(listofcars.get(i).distSqrd(loc))<min){
					min=Math.sqrt(listofcars.get(i).distSqrd(loc));
					minindex=i;
				}
			}
		}
		return listofcars.get(minindex);
	}
	public ArrayList<? extends Car> getCars(){
		return listofcars;
	}
}
