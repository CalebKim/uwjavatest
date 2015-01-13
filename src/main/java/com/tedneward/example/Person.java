package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn ="";
  }

  public int getAge() {
    return age;
  }
  
  public void setAge(int newAge){
	if (newAge < 0) {
		throw new IllegalArgumentException("Age must be greater than 0");
	} else {
		age = newAge;
	}
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String newName) {
	if (newName == null) {
		throw new IllegalArgumentException("name cannot be null");
	} else {
		name = newName;
	}
  }
  
  public double getSalary() {
    return salary;
  }
  
  public void setSalary(double newSalary){
	salary = newSalary;
  }
  
  public String getSSN() {
    return ssn;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }
  
  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public boolean equals(Object check) {
	  if (check instanceof Person){
        Person other = (Person) check;
		  return ((getName().equals(other.getName())) && (getAge() == other.getAge()));
	  }
    return false;
  }

  public String toString() {
     return "[Person name:" + getName() +" age:" + getAge() + " salary:" + getSalary() +"]";
     
  }

@Override
  public int compareTo(Person other) {
	 if (getSalary() > other.getSalary()) {
		 return -1;
	 } else if (getSalary() < other.getSalary()){
		 return 1;
	 } else {
		 return 0;
	 } 
  }
  
  public static List<Person> getNewardFamily() {
	  List<Person> list = new ArrayList<Person>();
	  list.add(new Person("Ted", 41, 250000));
	  list.add(new Person("Charlotte", 43, 150000));
	  list.add(new Person("Michael", 22, 10000));
	  list.add(new Person("Matthew", 15, 0));
	  return list;
  }
  
  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
  public static class AgeComparator implements Comparator<Person>{
      @Override
      public int compare(Person one, Person two) {
         return one.getAge() - two.getAge();
      }
  }
}
