package com.itwill.mymedi.model;

public class Pharms {
	
	public static final class Entity {
		public static final String TBL_PHARMS = "PHARMS";
		
		public static final String COL_ID = "id";
		public static final String COL_NAME = "name";
		public static final String COL_ADDRESS = "address";
	}
	
	private int id;
	private String name;
	private String address;
	
	public Pharms() {}
	
	public Pharms(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Pharms [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
	
}
