package sample.Controller;

import Messenger.ClientContract;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Client;
import sample.Conf;
import sample.MainApp;
import sample.Model.Person;
import sample.RefreshListTask;

import java.net.ConnectException;

import static sample.Conf.client;

/**
 * Created by denis on 18/04/16.
 */
public class LoginController {

    // Reference to the main application
    private MainApp mainApp;

    @FXML
    private TextField loginNameField;
    @FXML
    private TextField loginPasswordField;
    @FXML
    private TextField registerNameField;
    @FXML
    private TextField registerPasswordField;
    @FXML
    private TextField confirmRegister;


    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    private boolean isInputLoginValid() {
        String errorMessage = "";

        if (loginNameField.getText() == null || loginNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (loginPasswordField.getText() == null || loginPasswordField.getText().length() == 0) {
            errorMessage += "No valid pass!\n";
        }
        Conf.client = new Client(loginNameField.getText(), loginPasswordField.getText());
        if (!Conf.client.login()) {
            errorMessage += "Invalid credentials\n";
        }


        if (errorMessage.length() == 0) {
            return populateList();
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid");
            alert.setHeaderText("Invalid");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }


    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleLogin() {
        if (isInputLoginValid()) {
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleRegister() {
        if (isInputRegisterValid()) {
            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputRegisterValid() {
        String errorMessage = "";

        if (registerNameField.getText() == null || registerNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (registerPasswordField.getText() == null || registerPasswordField.getText().length() == 0) {
            errorMessage += "No valid pass\n";
        }
        if (confirmRegister.getText() == null || confirmRegister.getText().length() == 0) {
            errorMessage += "No valid pass!\n";
        }
        if (confirmRegister.getText().equals(registerPasswordField.getText()) == false) {
            errorMessage += "No match passwords!\n";
        }
        Conf.client = new Client(registerNameField.getText(), registerPasswordField.getText());
        if (!Conf.client.register()) {
            errorMessage += "Register unsucesfull, try again later\n";
        }


        if (errorMessage.length() == 0) {
            return populateList();
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid");
            alert.setHeaderText("Invalid");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }

    private boolean populateList() {
        ClientContract[] friends = client.getServer().getFriends(client.username);
        for (ClientContract c : friends) {
            try {
                System.out.println("-- " + c.getName());
                mainApp.getPersonData().add(new Person(c));
//                personData.add(new Person(c));
            } catch (Exception e) {
                System.out.println("##################################################################");
//                mainApp.getPersonData().add(new Person("Joder, ostia"));
            }
        }
        RefreshListTask.doit(mainApp.getPersonData());
        return true;
    }


}
