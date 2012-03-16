package com.ericsson.training.parking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AttendantTest {

	@Test
	public void shouldBeAbleToParkInOneParkingLot() throws ParkingLotFullException {
		ParkingLot parkingLot = new ParkingLot(2);
		Attendant attendant = new Attendant();
		Object car = new Object();
		attendant.assignParkingLot(parkingLot);
		assertEquals(car.hashCode(),attendant.park(car));
	}
	
	
	@Test
	public void shouldBeAbleToUnparkfromOneParkingLot() throws ParkingLotFullException {
		ParkingLot parkingLot = new ParkingLot(2);
		Attendant attendant = new Attendant();
		attendant.assignParkingLot(parkingLot);
		Object car = new Object();
		attendant.park(car);
		assertEquals(car,attendant.unpark(car.hashCode()));
	}
	
	
	
	@Test 
	public void shouldBeAbleToAssign2ParkingLotsAndParkAcarEachInBoth() throws ParkingLotFullException
	
	{
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		Attendant attendant = new Attendant();
		Object car1 = new Object();
		Object car2 = new Object();
		attendant.assignParkingLot(parkingLot1);
		attendant.assignParkingLot(parkingLot2);
		attendant.park(car1);
		attendant.park(car2);
		
	}
	

	@Test (expected = ParkingLotFullException.class)
	public void shouldThrowExceptionifParkingMoreThanAllAvailableSlots() throws ParkingLotFullException
	
	{
		ParkingLot parkingLot1 = new ParkingLot(2);
		ParkingLot parkingLot2 = new ParkingLot(1);
		Attendant attendant = new Attendant();
		Object car1 = new Object();
		Object car2 = new Object();
		Object car3 = new Object();
		Object car4 = new Object();
		attendant.assignParkingLot(parkingLot1);
		attendant.assignParkingLot(parkingLot2);
		attendant.park(car1);
		attendant.park(car2);
		attendant.park(car3);
		attendant.park(car4);
	}
	
	@Test 
	public void shouldRetrieveACarAcross2AvailableParkingLots() throws ParkingLotFullException{
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		Attendant attendant = new Attendant();
		Object car1 = new Object();
		Object car2 = new Object();
		attendant.assignParkingLot(parkingLot1);
		attendant.assignParkingLot(parkingLot2);
		attendant.park(car1);
		int token2=attendant.park(car2);
		assertEquals(car2,attendant.unpark(token2));
	}
	
	@Test (expected = InvalidTicketException.class)
	public void shouldThrowExceptionWhenRetrievingACarWhichIsNotAvailable() throws ParkingLotFullException{
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		Attendant attendant = new Attendant();
		Object car1 = new Object();
		Object car2 = new Object();
		attendant.assignParkingLot(parkingLot1);
		attendant.assignParkingLot(parkingLot2);
		attendant.park(car1);
		attendant.unpark(car2.hashCode());
	}
	
	@Test (expected = InvalidTicketException.class)
	public void shouldThrowExceptionWhenRetrievingACarFromEmptyParkingLots(){
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		Object car = new Object();
		Attendant attendant = new Attendant();
		attendant.assignParkingLot(parkingLot1);
		attendant.assignParkingLot(parkingLot2);
		attendant.unpark(car.hashCode());
		
	}
	
	@Test(expected = ParkingLotFullException.class) 
	public void shouldThrowExceptionWhenParkingInFullParkingLot() throws ParkingLotFullException{
		ParkingLot parkingLot1 = new ParkingLot(1);
		Attendant attendant = new Attendant();
		attendant.assignParkingLot(parkingLot1);
		attendant.notifyFull(parkingLot1);
		attendant.park(new Object());
	}
	
	@Test 
	public void shouldAllowToParkCarInAParkingLotWhichIsJustGotFreed() throws ParkingLotFullException{
		ParkingLot parkingLot1 = new ParkingLot(2);
		Attendant attendant = new Attendant();
		attendant.assignParkingLot(parkingLot1);
		attendant.park(new Object());
		int token=attendant.park(new Object());
		attendant.unpark(token);
		attendant.park(new Object());
	}
}
