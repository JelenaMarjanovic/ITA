package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contract {

    private final IntegerProperty contractId = new SimpleIntegerProperty();
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
    private final StringProperty address = new SimpleStringProperty(this, "address", "");
    private final StringProperty speed = new SimpleStringProperty(this, "speed", "");
    private final StringProperty bandwidth = new SimpleStringProperty(this, "bandwidth", "");
    private final StringProperty duration = new SimpleStringProperty(this, "duration", "");

    public Contract() {
    }

    public Contract(String firstName, String lastName, String address, String speed, String bandwidth, String duration) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.address.set(address);
        this.speed.set(speed);
        this.bandwidth.set(bandwidth);
        this.duration.set(duration);
    }

    public Contract(Integer contractId, String firstName, String lastName, String address, String speed, String bandwidth, String duration) {
        this.contractId.set(contractId);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.address.set(address);
        this.speed.set(speed);
        this.bandwidth.set(bandwidth);
        this.duration.set(duration);
    }

    public Integer getId() {
        return contractId.get();
    }

    public void setId(Integer contractId) {
        this.contractId.set(contractId);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty speedProperty() {
        return speed;
    }

    public String getSpeed() {
        return speed.get();
    }

    public void setSpeed(String speed) {
        this.speed.set(speed);
    }

    public StringProperty bandwidthProperty() {
        return bandwidth;
    }

    public String getBandwidth() {
        return bandwidth.get();
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth.set(bandwidth);
    }

    public StringProperty durationProperty() {
        return duration;
    }

    public String getDuration() {
        return duration.get();
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    public boolean isValid() {

        boolean isValid = true;

        if (firstName.get() != null && firstName.get().equals("")) {
            isValid = false;
        }
        if (lastName.get() != null && lastName.get().equals("")) {
            isValid = false;
        }
        if (address.get() != null && address.get().equals("")) {
            isValid = false;
        }
        if (speed.get() != null && speed.get().equals("")) {
            isValid = false;
        }
        if (bandwidth.get() != null && bandwidth.get().equals("")) {
            isValid = false;
        }
        if (duration.get() != null && duration.get().equals("")) {
            isValid = false;
        }

        return isValid;

    }

    @Override
    public String toString() {
        return "ID: " + contractId.get() + "\nIme i prezime: " + firstName.get() + " " + lastName.get() + "\nAdresa: " + address.get() + "\nBrzina: " + speed.get() + " Mbit\nProtok: " + bandwidth.get() + "\nTrajanje ugovora: " + duration.get() + " god.\n";
    }

}
