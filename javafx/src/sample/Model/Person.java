package sample.Model;

/**
 * Created by denis on 17/04/16.
 */
import java.time.LocalDate;

import Messenger.ClientContract;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Conf;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Person {

    private ClientContract clientContract;
    private final StringProperty firstName;
    private ObservableList<Message> messagesReceive = FXCollections.observableArrayList();
    private ObservableList<Message> messagesSend = FXCollections.observableArrayList();

    public Person() {
        this.firstName = new SimpleStringProperty("Joder, joder");
    }

    /**
     * Constructor with some initial data.
     *
     * @param firstName
     */
    public Person(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);

        messagesReceive.add(new Message( new SimpleStringProperty("Denis"), new SimpleStringProperty("Buenos días princesa"), new SimpleIntegerProperty(1)));
        messagesReceive.add(new Message( new SimpleStringProperty("Denis"), new SimpleStringProperty("Volveran las oscuras golondrinas"),new SimpleIntegerProperty(1)));
    }

    public Person(ClientContract clientContract){
        this.clientContract = clientContract;
        this.firstName = new SimpleStringProperty(clientContract.getName());
    }

    public Person setClientContract(ClientContract clientContract) {
        this.clientContract = clientContract;
        return this;
    }

    public ClientContract getClientContract() {
        return clientContract;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }



    public void addMessageReceive(String owner, String content, int type){
        messagesReceive.add(new Message( new SimpleStringProperty(owner), new SimpleStringProperty(content), new SimpleIntegerProperty(type)));
        System.out.println("-- Mensaje enviado");
        clientContract.sendMsg(owner ,content);
    }


    public ObservableList<Message> getMessagesSend() {
        return messagesSend;
    }

    public void setMessagesSend(ObservableList<Message> messagesSend) {
        this.messagesSend = messagesSend;
    }

    public ObservableList<Message> getMessagesReceive() {
        return messagesReceive;
    }

    public void setMessagesReceive(ObservableList<Message> messagesReceive) {
        this.messagesReceive = messagesReceive;
    }

    public void addMessageSend(String owner, String content, int type){
        messagesSend.add(new Message( new SimpleStringProperty(owner), new SimpleStringProperty(content), new SimpleIntegerProperty(type)));
    }
}
