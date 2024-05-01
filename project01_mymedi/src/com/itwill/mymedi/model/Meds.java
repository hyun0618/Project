package com.itwill.mymedi.model;

public class Meds {
	
	public static final class Entity {
		public static final String TBL_MEDS = "MEDS";
		
		public static final String COL_MED = "med";
		public static final String COL_STOCK = "stock";
	}
	
	private String med;
	private int stock;
	
	public Meds() {}
	
	public Meds(String med, int stock) {
		this.med = med;
		this.stock = stock;
	}

	public String getMed() {
		return med;
	}

	public void setMed(String med) {
		this.med = med;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Meds [med=" + med + ", stock=" + stock + "]";
	}

}
