package com.hayavadana.rating.webservice.rest.json;

public class Student {

	private int id;
	private String firstName;
	private String lastName;
	private int age;
	
	public Student(){
	}
	
	public Student(String fname,String lname,int age,int id){
		this.firstName = fname;
		this.lastName  = lname;
		this.age  = age;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString(){
		return new StringBuffer("  First Name : ").append(this.firstName).append("   Last Name : ").append(this.lastName).append("   Age : ").append(this.age).toString();
	}
}
