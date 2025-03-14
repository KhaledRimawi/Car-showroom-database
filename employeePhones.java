package application;


public class employeePhones {
    private int eid;
    private String phoneNumber;

    public employeePhones(int eid, String phoneNumber) {
        this.eid = eid;
        this.phoneNumber = phoneNumber;
    }

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

    
}
