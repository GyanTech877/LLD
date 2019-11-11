package LLD.SnakeAndLadder.entities;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
	private int userId;
	private String userName;
	private Address address;
	private static final AtomicInteger count = new AtomicInteger(0); 
	
	public User(String userName, Address address) {
		super();
		this.userId = count.getAndIncrement();
		this.userName = userName;
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", address=" + address + "]";
	}

}
