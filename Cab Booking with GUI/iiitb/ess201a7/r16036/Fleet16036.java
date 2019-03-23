package iiitb.ess201a7.r16036;

import iiitb.ess201a7.a7base.*;
import java.util.*;


public class Fleet16036 extends Fleet {
int i=1;
private ArrayList<Car> cl = new ArrayList<Car>();

	public Fleet16036 (String colour) {
		super(16036,colour);
	}


	@Override
	public void addCar(int speed) {

		Car cat=new Car16036(160360+i,speed);
		cl.add(cat);
		i+=1;
	}

	@Override
	public Car findNearestCar(Location loc) {
		int index=0, i;
		double dis,time;
		dis=cl.get(0).distSqrd(loc);
		time=dis;
		i=-1;
		for(Car item: cl)
		{
			Location l1 = item.getLocation();
			float x1=l1.getX();
			float y1=l1.getY();
			float x2=loc.getX();
			float y2=loc.getY();
			dis=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
			i+=1;
			if(dis<=time && cl.get(i).getStatus()==1)
			{
				time=dis;index=i;
			}
		}
if(cl.get(index).getStatus()==1)
{
		return cl.get(index);
}
return null;
	}

	public ArrayList<Car> getCars()
	{
		return cl;
	}

}
