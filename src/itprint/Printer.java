package itprint;

import java.util.Random;

public class Printer {

	private String prntName;

	private String roomLoc;

	private boolean directPrint;

	private boolean gvPrint;

	public Printer(String prntName, String roomLoc) {
		Random r = new Random();
		int n = r.nextInt(2) + 1;
		boolean random = false;
		if (n == 1) {
			random = true;
		}
		setPrntName(prntName);
		setRoomLocation(roomLoc);
		setDirectPrint(random);
		setGvPrint(true);
	}

	public String getPrntName() {
		return prntName;
	}

	public void setPrntName(String prntName) {
		this.prntName = prntName;
	}

	public String getRoomLocation() {
		return roomLoc;
	}

	public void setRoomLocation(String roomLoc) {
		this.roomLoc = roomLoc;
	}

	public boolean isDirectPrint() {
		return directPrint;
	}

	public void setDirectPrint(boolean directPrint) {
		this.directPrint = directPrint;
	}

	public boolean isGvPrint() {
		return gvPrint;
	}

	public void setGvPrint(boolean gvPrint) {
		this.gvPrint = gvPrint;
	}
	
	public boolean status() {
		return (isDirectPrint() && isGvPrint());
	}
		
	public String toString() {
		String dPrnt = "Disabled";
		String gvPrnt = "Disabled";
		String status = "Not working";
		if (isDirectPrint()) {
			dPrnt = "Enabled";
		}
		if (isGvPrint()) {
			gvPrnt = "Enabled";
		}
		if (status()) {
			status = "Working";
		}
		return "Status: " + status + "\n" + "Room location: " + roomLoc + "\n" +
				"Direct Print - " + dPrnt + "\n" + "GV Print - " + gvPrnt;
	}
}
