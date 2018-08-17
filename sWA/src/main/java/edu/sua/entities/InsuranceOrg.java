package edu.sua.entities;

public class InsuranceOrg {
	private long inn;
	private long ogrn;
	private String name;
	private String address;

	public InsuranceOrg() {
	}

	public InsuranceOrg(long inn, long ogrn, String name, String address) {
		this.inn = inn;
		this.name = name;
		this.address = address;
		this.ogrn = ogrn;
	}

	public long getInn() {
		return inn;
	}

	public void setInn(long inn) {
		this.inn = inn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getOgrn() {
		return ogrn;
	}

	public void setOgrn(long ogrn) {
		this.ogrn = ogrn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



}
