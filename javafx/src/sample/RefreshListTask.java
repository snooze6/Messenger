package sample;

import Messenger.ClientContract;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.CORBA.ORB;
import sample.Model.Person;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static sample.Conf.client;

/**
 * Created by snooze on 4/16/16.
 */
public class RefreshListTask implements Runnable {
//    private Client orb;
    private ObservableList<Person> personData;

    public RefreshListTask(ObservableList<Person> personData) {
//        this.orb = orb;
        this.personData = personData;
        Conf.client.client.setPersonData(personData);
    }

    public void run() {
        System.out.println("-- Refrescando lista de amigos");
        ClientContract[] friends = client.getServer().getFriends(client.username);

        for (ClientContract c : friends) {
            try {
                Boolean update = false;
                for (Person p : personData) {
                    if (p.getFirstName().equals(c.getName())) {
                        System.out.println("-- Actualizando");
                        p.setClientContract(c);
                        update = true;
                    }
                }
                if (!update) {
                    personData.add(new Person(c));
                    System.out.println("-- Añadiendo");
                }
            } catch (Exception e) {
                System.out.println("##################################################################");
//                mainApp.getPersonData().add(new Person("Joder, ostia"));
            }
        }

        ArrayList<Person> deleted = new ArrayList<Person>();
        for (Person p:
             personData) {
            try {
                p.getClientContract().getName();
            } catch (Exception e) {
//                System.out.println("-- Eliminando "+p.getFirstName());
                deleted.add(p);
//                personData.remove(p);
            }
        }

        for (Person p:
             deleted) {
            personData.remove(p);
            System.out.println("-- Eliminando "+p.getFirstName());
        }
    }

    public static void doit(ObservableList<Person> personData){
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new RefreshListTask(personData), 0, 5, TimeUnit.SECONDS);
    }
}
