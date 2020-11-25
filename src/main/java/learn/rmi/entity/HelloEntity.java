package learn.rmi.entity;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloEntity extends Remote {

    String sayHello(String name) throws RemoteException;
}
