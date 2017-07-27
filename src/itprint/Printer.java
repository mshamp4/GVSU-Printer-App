package itprint;

public class Printer {

	private String prntName;

	private String roomLoc;

	private boolean directPrint;

	private boolean gvPrint;

	public Printer(String prntName, String roomLoc) {
		setPrntName(prntName);
		setRoomLocation(roomLoc);
		setDirectPrint(false);
		setGvPrint(false);
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
}
