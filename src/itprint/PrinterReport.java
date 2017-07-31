package itprint;

import java.util.ArrayList;

public class PrinterReport {

	private ArrayList<Printer> printers;

	public PrinterReport(String name) {
		initalizePrinters(name);
	}

	private void initalizePrinters(String name) {
		if (name.equalsIgnoreCase("DEVOS")) {
			devosPrnt();
		}
		if (name.equalsIgnoreCase("CHS")) {
			chsPrnt();
		}
		if (name.equalsIgnoreCase("EC")) {
			ecPrnt();
		}
		if (name.equalsIgnoreCase("SCB")) {
			scbPrnt();
		}
	}

	private void devosPrnt() {
		ArrayList<Printer> temp = new ArrayList<Printer>();
		Printer d1 = new Printer("203A", "Devos");
		Printer d2 = new Printer("204A", "Devos");
		Printer d3 = new Printer("205A", "Devos");
		Printer d4 = new Printer("210A", "Devos");
		Printer d5 = new Printer("211A", "Devos");
		Printer d6 = new Printer("127A", "Devos");
		Printer d7 = new Printer("115A", "Devos");
		Printer d8 = new Printer("106A", "Devos");
		Printer d9 = new Printer("125C", "Devos");
		temp.add(d1);
		temp.add(d2);
		temp.add(d3);
		temp.add(d4);
		temp.add(d5);
		temp.add(d6);
		temp.add(d7);
		temp.add(d8);
		temp.add(d9);
		setPrinters(temp);
	}

	private void chsPrnt() {
		ArrayList<Printer> temp = new ArrayList<Printer>();
		Printer c1 = new Printer("100", "CHS");
		Printer c2 = new Printer("189A", "CHS");
		Printer c3 = new Printer("189B", "CHS");
		Printer c4 = new Printer("190", "CHS");
		Printer c5 = new Printer("191A", "CHS");
		Printer c6 = new Printer("191B", "CHS");
		Printer c7 = new Printer("290", "CHS");
		temp.add(c1);
		temp.add(c2);
		temp.add(c3);
		temp.add(c4);
		temp.add(c5);
		temp.add(c6);
		temp.add(c7);
		setPrinters(temp);
	}

	private void ecPrnt() {
		ArrayList<Printer> temp = new ArrayList<Printer>();
		Printer e1 = new Printer("513", "EC");
		temp.add(e1);
		setPrinters(temp);
	}

	private void scbPrnt() {
		ArrayList<Printer> temp = new ArrayList<Printer>();
		Printer s1 = new Printer("2009", "SCB");
		Printer s2 = new Printer("2011", "SCB");
		Printer s3 = new Printer("2015", "SCB");
		Printer s4 = new Printer("2046", "SCB");
		Printer s5 = new Printer("1060", "SCB");
		temp.add(s1);
		temp.add(s2);
		temp.add(s3);
		temp.add(s4);
		temp.add(s5);
		setPrinters(temp);
	}
	
	public boolean updated() {
		for (Printer temp: printers) {
			if (temp.isUpdate()) {
				return true;
			}
		}
		return false;
	}
	
	public void setUpdated() {
		for (Printer temp: printers) {
			if (temp.isUpdate()) {
				temp.setUpdate(false);
			}
		}
	}

	public ArrayList<Printer> getPrinters() {
		return printers;
	}

	private void setPrinters(ArrayList<Printer> printers) {
		this.printers = printers;
	}
}
