package learn.rmi.server;

import learn.rmi.entity.HelloEntityImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(8888);
            HelloEntityImpl helloEntity = new HelloEntityImpl();

            registry.rebind("Hello", helloEntity);
            System.out.println("----------start----------");
        } catch (RemoteException  e) {
            e.printStackTrace();
        }

    }
}
