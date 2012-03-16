package com.ericsson.training.parking;

import java.util.ArrayList;
import java.util.List;


public class Attendant {

	List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
	List<ParkingLot> availableParkingLots = new ArrayList<ParkingLot>();
	
	
	public void assignParkingLot(ParkingLot parkingLot) {
		parkingLots.add(parkingLot);
		availableParkingLots.add(parkingLot);
		parkingLot.managedBy(this);
		
	}

	public int park(Object car) throws ParkingLotFullException {
		for(ParkingLot parkingLot : availableParkingLots) 
		{
			if(parkingLot.isLotAvailable())
					return parkingLot.park(car);
		}
		throw new ParkingLotFullException();
	}

	public Object unpark(int hashCode) {
		
		for(ParkingLot parkingLot : parkingLots) 
		{
			try {
				 return parkingLot.unpark(hashCode);
				 
			} catch (InvalidTicketException e) {
				
			}		
		}
		throw new InvalidTicketException();
	}

	public void notifyFull(ParkingLot parkingLot) {
		availableParkingLots.remove(parkingLot);
	}

	public void notifyAvailable(ParkingLot parkingLot) {
		availableParkingLots.add(parkingLot);
	}
	

}
