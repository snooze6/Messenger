package sample.Controller;

/**
 * Created by denis on 18/04/16.
 */
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import sample.Conf;
import sample.Model.Message;
import sample.Model.Person;
import sample.MainApp;
import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;


    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;



    @FXML
    private TableView<Message> chatTable;

    @FXML
    private TableColumn<Message,String> contentMessage;

    @FXML
    private TableColumn<Message,String> ownerMessage;


    @FXML
    private TextField sendTextField;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initializeRightTable() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());


            // Clear person details.
            showPersonDetails(null);

            // Listen for selection changes and show the person details when changed.
            personTable.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showPersonDetails(newValue));
        initializeLeftTable();

    }

    private void initializeLeftTable(){
        chatTable.setStyle("-fx-border-color: white");
        contentMessage.setStyle("-fx-border-color: white");
        contentMessage.setStyle("-fx-background-color: white");
        ownerMessage.setStyle("-fx-border-color: white");
        ownerMessage.setStyle("-fx-background-color: white");

        ownerMessage.setCellValueFactory(cellData -> cellData.getValue().ownerProperty());
        contentMessage.setCellValueFactory(cellData -> cellData.getValue().contentProperty());
        sendTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ENTER:  sendMessage();
                }
            }
        });

        TableColumn descCol = contentMessage;
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call( TableColumn param) {
                final TableCell cell = new TableCell() {
                    private Text text;
                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            text= new Text(item.toString());
                            text.setWrappingWidth(descCol.getWidth()-20);
                            setGraphic(text);
                        }
                    }
                };
                return cell;
            }
        };
        descCol.setCellFactory(cellFactory);


    }


    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());




    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            contentMessage.setText(person.getFirstName());
            chatTable.setItems(person.getMessagesReceive());

          /*  firstNameLabel.setText(person.getFirstName());

            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());

            // TODO: We need a way to convert the birthday into a String!
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));*/

        } else {
            // Person is null, remove all the text.
 /*            firstNameLabel.setText("");
           lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");*/
        }
    }



    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table");

            alert.showAndWait();
        }
    }





    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Invalid Fields");
            alert.setContentText("Please correct invalid fields");

            alert.showAndWait();
        }
    }


    //Denis interfaz
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void sendMessage() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null){
            System.out.println(sendTextField.getText());
            String content = sendTextField.getText();
            selectedPerson.addMessageReceive(Conf.client.username ,content,1);
            chatTable.setItems(selectedPerson.getMessagesReceive());
            sendTextField.clear();
        }


/*        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }*/
    }


    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

//        // Set extension filter
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
//                "XML files (*.xml)", "*.xml");
//        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            System.out.println("Error opening file");
        }

        try {
            Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
            String fichero = readFile(file.toString());


            String ruta = file.toString();
            String[] parts  = ruta.split("/");
            int longitud =parts.length;
            String name = parts[longitud-1];
            System.out.println(name);
            String enviar = name + "@@@" +fichero;
            selectedPerson.getClientContract().transfer(enviar);

            selectedPerson.getClientContract().sendMsg(Conf.client.username, "El archivo <"+name+"> se ha enviado con éxito");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile( String file ) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while( ( line = reader.readLine() ) != null ) {
                stringBuilder.append( line );
                stringBuilder.append( ls );
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
}