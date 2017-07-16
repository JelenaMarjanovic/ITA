package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import model.Contract;

public class AddContractController {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;
    @FXML
    private ToggleGroup speed;
    @FXML
    private ToggleGroup bandwidth;
    @FXML
    private ToggleGroup duration;
    @FXML
    private Label addLbl;

    ObservableList<Contract> contracts = FXCollections.<Contract>observableArrayList();

    Contract contract;

    public AddContractController() {
    }

    @FXML
    private void initialize() {

        contract = new Contract();

        firstName.textProperty().bindBidirectional(contract.firstNameProperty());
        lastName.textProperty().bindBidirectional(contract.lastNameProperty());
        address.textProperty().bindBidirectional(contract.addressProperty());

        speed.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    RadioButton selected = (RadioButton) newValue;
                    contract.speedProperty().set(selected.getText());
                }
            }
        });

        bandwidth.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    RadioButton selected = (RadioButton) newValue;
                    contract.bandwidthProperty().set(selected.getText());
                }
            }
        });

        duration.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    RadioButton selected = (RadioButton) newValue;
                    contract.durationProperty().set(selected.getText());
                }
            }
        });

    }

    @FXML
    private void addContract() {

        if (contract.isValid()) {

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/assignment05", "root", "");) {

                PreparedStatement st = conn.prepareStatement("INSERT INTO contract (first_name,last_name,address,speed,bandwidth,duration) VALUES (?,?,?,?,?,?)");
                st.setString(1, contract.getFirstName());
                st.setString(2, contract.getLastName());
                st.setString(3, contract.getAddress());
                st.setString(4, contract.getSpeed());
                st.setString(5, contract.getBandwidth());
                st.setString(6, contract.getDuration());
                st.execute();

                ResultSet rs = st.executeQuery("SELECT last_insert_id() AS contract_id FROM contract");
                rs.next();

                addLbl.setText("Unet je nov ugovor sa ID-jem: " + rs.getString("contract_id"));

            } catch (SQLException exception) {
                System.out.println("Greska tokom konektovanja sa bazom:\n" + exception.getMessage());
            }

        } else {
            addLbl.setText("Nisu uneti svi neophodni podaci!");
        }

    }

    @FXML
    private void clearData() {

        contract.firstNameProperty().set("");
        contract.lastNameProperty().set("");
        contract.addressProperty().set("");
        speed.getSelectedToggle().setSelected(false);
        bandwidth.getSelectedToggle().setSelected(false);
        duration.getSelectedToggle().setSelected(false);
        addLbl.setText("");

    }

}
