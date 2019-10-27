package ClientServer;

import java.util.List;

public class Forum {
    List<Client> lst;

    public Forum(Client newClient) {
        this.lst.add(newClient);
    }

    public List<Client> getLst() {
        return lst;
    }
}
