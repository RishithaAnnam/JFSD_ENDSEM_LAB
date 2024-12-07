package com.klef.jfsd.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "dept_id")
	    private int departmentId;
	    
	    @Column(name = "dept_name", length = 100)
	    private String departmentName;
	    
	    @Column(name = "location", length = 100)
	    private String location;
	    
	    @Column(name = "hod_name", length = 100)
	    private String hodName;

    // Default constructor
    public Department() {
    }

    // Parameterized constructor
    public Department(String departmentName, String location, String hodName) {
        this.departmentName = departmentName;
        this.location = location;
        this.hodName = hodName;
    }

    // Getters and Setters
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHodName() {
        return hodName;
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }

    @Override
    public String toString() {
        return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName 
               + ", location=" + location + ", hodName=" + hodName + "]";
    }
}