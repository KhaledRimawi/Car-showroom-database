package application;
public class employees {
    private int eid;
    private int salary;
    private String name;
    private String address;

    public employees(int eid, int salary ,String name,String address) {
        this.eid = eid;
        this.salary = salary;
        this.name = name;
        this.address = address;
    }

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
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

	


}
