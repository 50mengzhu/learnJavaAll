package learn.rmi.client;

import learn.rmi.entity.HelloEntity;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(8888);
            HelloEntity entity = (HelloEntity) registry.lookup("Hello");
            String diaomao = entity.sayHello("diaomao");
            System.out.println(diaomao);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
