package assignment04;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "sallary", nullable = false)
    private double sallary;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSallary() {
        return sallary;
    }

    public void setSallary(double sallary) {
        this.sallary = sallary;
    }

    public Employee() {
    }

    public Employee(String name, int age, String address, double sallary) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.sallary = sallary;
    }

    public Employee(int id, String name, int age, String address, double sallary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.sallary = sallary;
    }

    @Override
    public String toString() {
        return "Id: " + id + "\nIme: " + name + "\nGodine: " + age + "\nAdresa: " + address + "\nVisina dohotka: " + sallary + "\n";
    }

}
