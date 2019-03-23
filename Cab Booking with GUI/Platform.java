import java.util.ArrayList;
import java.util.*;
import iiitb.ess201a7.a7base.*;

public class Platform {
	public Platform() {}
	private ArrayList<Fleet> fl=new ArrayList<Fleet>();
	public void addFleet(Fleet f) {
		fl.add(f);
	}
	public Car assignCar(Trip trip) {
		int index;
		double dis,time;
		index = 0;
		time=fl.get(0).findNearestCar(trip.getStart()).distSqrd(trip.getStart());
		for(int i=0;i<fl.size();i++)
		{
			Car c = fl.get(i).findNearestCar(trip.getStart());
			if(c!=null)
			{
			Location l= c.getLocation();
			float x1=l.getX();
			float y1=l.getY();
			float x2=trip.getStart().getX();
			float y2=trip.getStart().getY();
			dis=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));

			if(dis<=time)
			{

				time = dis;
				index=i;
			}
		}
		}
		if(fl.get(index).findNearestCar(trip.getStart()).getStatus()==1)
		{
fl.get(index).findNearestCar(trip.getStart()).assignTrip(trip);
return  fl.get(index).findNearestCar(trip.getStart());
}
return null;
}
public ArrayList<Car> findCars() {
 ArrayList<Car> al=new ArrayList<Car>();
 for(Fleet i:fl){
	al.addAll(i.getCars());
 }
 return al;
}
}
