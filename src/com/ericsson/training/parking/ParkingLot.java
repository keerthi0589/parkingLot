package com.ericsson.training.parking;

import java.util.HashMap;

//understands 
public class ParkingLot {

	
	
	private final int maxCapacity;
	private HashMap<Integer,Object> hashmap;
	private ParkingLotObserver attendant;
	
	public ParkingLot(int capacity) {
		this.maxCapacity = capacity;
		hashmap = new HashMap<Integer, Object>(capacity);
	}

	public int park(Object car) throws ParkingLotFullException  {
		if(hashmap.size() ==maxCapacity) throw new ParkingLotFullException();
		hashmap.put(car.hashCode(), car);
		if(hashmap.size() ==maxCapacity && attendant!=null) attendant.notifyFull(this);
		return car.hashCode();
	}
	
	public boolean isLotAvailable(){
		return hashmap.size() != maxCapacity;
	}

	public Object unpark(int ticketNo) {
		if(!hashmap.containsKey(ticketNo)) throw new InvalidTicketException();
		if(hashmap.size()==maxCapacity && attendant!=null) attendant.notifyAvailable(this);
		return hashmap.remove(ticketNo);
		
	
	}

	public void observedBy(ParkingLotObserver observer) {
		this.attendant=observer;
	}

}
