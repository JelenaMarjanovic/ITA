package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Contract;

public class DeleteContractController {

    @FXML
    private TextField id;
    @FXML
    private Label deleteLbl;

    Contract contract;

    @FXML
    public void deleteContract() {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/assignment05", "root", "");) {

            PreparedStatement st = conn.prepareStatement("DELETE FROM contract WHERE contract_id=?");
            st.setString(1, id.getText());
            st.execute();

            deleteLbl.setText("Obrisan je ugovor sa ID-jem: " + id.getText());

        } catch (SQLException exception) {
            System.out.println("Greska tokom konektovanja sa bazom:\n" + exception.getMessage());
        }

    }

}
