package com.ericsson.training.parking;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParkingLotTest {

	@Test
	public void shouldParkACarOnAnEmptyParkingLot()
			throws ParkingLotFullException {
		ParkingLot parkingLot = new ParkingLot(3);
		Object car = new Object();
		assertEquals(car.hashCode(), parkingLot.park(car));

	}

	@Test(expected = ParkingLotFullException.class)
	public void shouldNotParkACarOnFullParkingLot()
			throws ParkingLotFullException {
		ParkingLot parkingLot = new ParkingLot(1);
		Object car1 = new Object();
		Object car2 = new Object();
		parkingLot.park(car1);
		parkingLot.park(car2);
	}

	@Test
	public void shouldBeAbleToRetrieveFromNonEmptyParkingLot()
			throws ParkingLotFullException {
		ParkingLot parkingLot1 = new ParkingLot(2);
		Object car1 = new Object();
		Object car2 = new Object();
		int ticketNo1;
		int ticketNo2;
		ticketNo1 = parkingLot1.park(car1);
		ticketNo2 = parkingLot1.park(car2);

		assertEquals(car2, parkingLot1.unpark(ticketNo2));
	}

	@Test(expected = InvalidTicketException.class)
	public void shouldNotBeAbleToRetrieveFromEmptyParkingLot() {
		ParkingLot parkingLot = new ParkingLot(2);
		parkingLot.unpark(0);
	}

	@Test(expected = InvalidTicketException.class)
	public void shouldNotBeAbleToRetriveANonExistingCar()
			throws ParkingLotFullException {
		ParkingLot parkingLot = new ParkingLot(2);
		Object car1 = new Object();
		Object car2 = new Object();
		parkingLot.park(car1);
		parkingLot.unpark(car2.hashCode());

	}

	@Test(expected = InvalidTicketException.class)
	public void shouldNotBeAbleToRetriveACarMoreThanOnce()
			throws ParkingLotFullException {
		ParkingLot parkingLot = new ParkingLot(2);
		Object car1 = new Object();
		parkingLot.park(car1);
		parkingLot.unpark(car1.hashCode());
		parkingLot.unpark(car1.hashCode());

	}

	@Test
	public void parkingLotIsNotAvailable() throws ParkingLotFullException {
		ParkingLot parkingLot = new ParkingLot(2);
		Object car1 = new Object();
		Object car2 = new Object();
		parkingLot.park(car1);
		parkingLot.park(car2);
		assertFalse(parkingLot.isLotAvailable());
	}

	@Test
	public void parkingLotIsAvailable() throws ParkingLotFullException {
		ParkingLot parkingLot = new ParkingLot(3);
		Object car1 = new Object();
		Object car2 = new Object();
		parkingLot.park(car1);
		parkingLot.park(car2);
		assertTrue(parkingLot.isLotAvailable());
	}

	@Test
	public void shouldNotifyWhenFull() throws ParkingLotFullException {
		ParkingLot parkingLot = new ParkingLot(1);
		AttendantMock attendant = new AttendantMock();
		parkingLot.observedBy(attendant);
		parkingLot.park(new Object());
		assertEquals(1, attendant.getParkingLotFullNotifications());
	}

	@Test
	public void shouldNotNotifyWhenNotFull() throws ParkingLotFullException {
		ParkingLot parkingLot = new ParkingLot(2);
		AttendantMock attendant = new AttendantMock();
		parkingLot.observedBy(attendant);
		parkingLot.park(new Object());
		assertEquals(0,attendant.getParkingLotFullNotifications());
	}
	
	@Test
	public void shouldNotifyWhenSpaceAvailable() throws ParkingLotFullException {
		ParkingLot parkingLot = new ParkingLot(1);
		AttendantMock attendant = new AttendantMock();
		parkingLot.observedBy(attendant);
		Object car = new Object();
		parkingLot.park(car);
		parkingLot.unpark(car.hashCode());
		assertEquals(1,attendant.getSpaceAvailableNotifications());
	}
	
	@Test
	public void shouldNotNotifySpaceAvailableIfParkingLotWasNotFullBeforeUnpark() throws ParkingLotFullException {
		ParkingLot parkingLot = new ParkingLot(2);
		AttendantMock attendant = new AttendantMock();
		parkingLot.observedBy(attendant);
		Object car = new Object();
		parkingLot.park(car);
		parkingLot.unpark(car.hashCode());
		assertEquals(0,attendant.getSpaceAvailableNotifications());
	}

}

class AttendantMock implements ParkingLotObserver {
	private int parkingLotFullNotifications = 0;
	private int spaceAvailableNotifications = 0;
	

	public int getParkingLotFullNotifications() {
		return parkingLotFullNotifications;
	}

	public int getSpaceAvailableNotifications() {
		return spaceAvailableNotifications;
	}

	@Override
	public void notifyFull(ParkingLot parkingLot) {
		parkingLotFullNotifications++;
	}

	@Override
	public void notifyAvailable(ParkingLot parkingLot) {
		spaceAvailableNotifications++;
	}

	
}