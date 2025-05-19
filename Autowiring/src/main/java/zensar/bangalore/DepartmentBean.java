package zensar.bangalore;

public class DepartmentBean {
	
	private String depName;
	private String address;
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "DepartmentBean [depName=" + depName + ", address=" + address + "]";
	}
	public DepartmentBean(String depName, String address) {
		super();
		this.depName = depName;
		this.address = address;
	}
	public DepartmentBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
