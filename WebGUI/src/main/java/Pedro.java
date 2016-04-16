import Messenger.ClientContract;

/**
 * Created by snooze on 4/16/16.
 */
public class Pedro {
    public static void main(String args[]) {
        Client c = new Client("pedro", "pedro");
        System.out.println(c.login());
//        System.out.println(c.getServer().getUser("juan").getName());
        ClientContract[] pedros = c.getServer().getFriends("pedro");
        for (ClientContract j:
             pedros) {
            System.out.println(j.getName());
        }
    }
}
