package sample;

import Messenger.ClientContractPOA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.CORBA.ORB;
import sample.Model.Person;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by snooze on 4/16/16.
 */



public class ClientImpl extends ClientContractPOA{

    private ORB orb;
    private String name;
    private ObservableList<Person> personData = null;
    private MainApp app = null;


    public ClientImpl(String name) {
        this.name = name;
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public ClientImpl setPersonData(ObservableList<Person> personData) {
        this.personData = personData;
        return this;
    }

    public MainApp getApp() {
        return app;
    }

    public ClientImpl setApp(MainApp app) {
        this.app = app;
        return this;
    }

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    public String getName() {
        System.out.println("-- Pedido el nombre: "+name);
        return name;
    }

    public String sendMsg(String username, String msg) {
        System.out.println("-- Recibido mensaje: "+msg);
        System.out.println("-- De: "+username);

        // TODO: Display en pantalla
        if (personData!=null) {
            System.out.println("-- Actualizando pantalla");
            for (Person p: personData) {
                if (p.getFirstName().equals(username)){
                    p.addMessageReceive(username, msg, 42);
                }
            }
        } else {
            System.out.println("-- No tengo pantalla para imprimirlo");
        }

        return msg;
    }

    public String transfer(String path) {
        System.out.println("-- Recibido archivo: "+path);
        String[] parts = path.split("@@@");
        String name = parts[0];
        String content = parts[1];
        System.out.println("El nombre es "+ name);
        System.out.println("El contenido es " + content);
        try( PrintWriter out = new PrintWriter(name)){
            out.println(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return path;
    }
}
