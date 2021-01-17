package model.Dto;

public class MapDto {
	private int hospital_No;
	private double latitude;
	private double longitude;
	private String hospital_Name;
	private String hospital_Addr;
	private String hospital_Tel;

	public MapDto() {
		super();
	}

	public MapDto(int hospital_No, double latitude, double longitude, String hospital_Name, String hospital_Addr,
			String hospital_Tel) {
		super();
		this.hospital_No = hospital_No;
		this.latitude = latitude;
		this.longitude = longitude;
		this.hospital_Name = hospital_Name;
		this.hospital_Addr = hospital_Addr;
		this.hospital_Tel = hospital_Tel;
	}

	public int getHospital_No() {
		return hospital_No;
	}

	public void setHospital_No(int hospital_No) {
		this.hospital_No = hospital_No;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getHospital_Name() {
		return hospital_Name;
	}

	public void setHospital_Name(String hospital_Name) {
		this.hospital_Name = hospital_Name;
	}

	public String getHospital_Addr() {
		return hospital_Addr;
	}

	public void setHospital_Addr(String hospital_Addr) {
		this.hospital_Addr = hospital_Addr;
	}

	public String getHospital_Tel() {
		return hospital_Tel;
	}

	public void setHospital_Tel(String hospital_Tel) {
		this.hospital_Tel = hospital_Tel;
	}

}
