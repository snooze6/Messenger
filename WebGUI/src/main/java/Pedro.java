import Messenger.ClientContract;
import Messenger.credentials;

import java.util.Arrays;

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

        System.out.println(c.getServer().register(new credentials("user", "user", "ior")));
        System.out.println(c.getServer().makeFriends("pedro", "user"));
        String[] requests = c.getServer().getFriendRequest("user");
        for (int i=0; i<requests.length; i++){
            System.out.println(requests[i]);
        }
    }
}
