package LLD.ParkingLot.entities;

import LLD.ParkingLot.constants.VehicleSize;

public abstract class Vehicle {
	protected String vehicleLicenseNo;
	protected VehicleSize size;
	protected ParkingSpot parkedAt;

	public VehicleSize getSize() {
		return size;
	}

	public void clearSpot() {
		this.parkedAt = null;
	}

	public void park(ParkingSpot p) {
		this.parkedAt = p;
	}
}
