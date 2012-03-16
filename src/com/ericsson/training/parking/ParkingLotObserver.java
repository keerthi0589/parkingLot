package com.ericsson.training.parking;

public interface ParkingLotObserver {
	
	public void notifyFull(ParkingLot parkingLot);
	public void notifyAvailable(ParkingLot parkingLot);

}
