package LLD.ParkingLot.entities;

import LLD.ParkingLot.constants.VehicleSize;

public class ParkingSpot {
	private Vehicle vehicle;
	private VehicleSize size;
	private Level level;
	private int spotNo;
	private int rowNo;

	public ParkingSpot(Level level, int rowNo, int spotNo, VehicleSize size) {
		this.level = level;
		this.rowNo = rowNo;
		this.spotNo = spotNo;
		this.size = size;
	}

	public int getSpotNo() {
		return spotNo;
	}

	public int getRowNo() {
		return rowNo;
	}

	public Level getLevel() {
		return level;
	}

	public boolean isAvailable() {
		return vehicle == null;
	}

	public boolean canFitVehicle(Vehicle vehicle) {
		return (this.size == vehicle.size);
	}

	public boolean park(Vehicle vehicle) {
		return true;
	}

	public void removeVehicle() {

	}
}
