/**
 * Entity Class
 * Author:Vaibhav Karn
 */

package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Candidate {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String title;
	private String unit;
	private String place;
	private String supid;
	private String skill;
	private double salary;

	public Candidate() {

	}

	public Candidate(String name, String title, String unit, String place, String supid, String skill, double salary) {
		super();
		this.name = name;
		this.title = title;
		this.unit = unit;
		this.place = place;
		this.supid = supid;
		this.skill = skill;
		this.salary = salary;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getSupid() {
		return supid;
	}

	public void setSupid(String supid) {
		this.supid = supid;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", name=" + name + ", title=" + title + ", unit=" + unit + ", place=" + place
				+ ", supid=" + supid + ", skill=" + skill + ", salary=" + salary + "]";
	}

}
