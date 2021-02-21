package fst.tp1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class RegisterController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField prixIdField;

    @FXML
    private Button submitButton;

    @FXML
    private Button countId;

    @FXML
    private Button sumPrix;

    @FXML
    public void register() {

        Window owner = submitButton.getScene().getWindow();

        if (fullNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Nom de produit manque");
            return;
        }

        if (prixIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Prix de produit manque");
            return;
        }

        String fullName = fullNameField.getText();
        double prix = Double.parseDouble(prixIdField.getText());

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertRecord(fullName, prix);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Done " + fullNameField.getText());
    }

    @FXML
    public void calc() {

        Window owner = countId.getScene().getWindow();

        int count = new JdbcDao().calcRecord();

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Result ",
                " Result count =" + count);
    }

    @FXML
    public void calcPrix() {

        Window owner = sumPrix.getScene().getWindow();

        int count = JdbcDao.sumAge();

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Result ",
                " Result prix =" + count);
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}