package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.Contract;

public class ShowContractsController {

    @FXML
    private TextArea showTA;

    ObservableList<Contract> contracts = FXCollections.<Contract>observableArrayList();

    Contract contract;

    public ShowContractsController() {
    }

    @FXML
    private void showContracts() {

        contract = new Contract();
        contracts.clear();
        showTA.clear();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/assignment05", "root", "");) {

            Statement st = conn.createStatement();
            st.executeQuery("SELECT * FROM contract");

            ResultSet rs = st.getResultSet();

            while (rs.next()) {

                contract = new Contract(rs.getInt("contract_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"), rs.getString("speed"), rs.getString("bandwidth"), rs.getString("duration"));
                contracts.add(contract);

            }

            for (Contract c : contracts) {
                showTA.setEditable(true);
                showTA.appendText(c.toString() + "\n");
            }

        } catch (SQLException exception) {
            System.out.println("Greska tokom konektovanja sa bazom:\n" + exception.getMessage());
        }

    }

}
