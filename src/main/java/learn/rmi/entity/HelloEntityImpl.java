package learn.rmi.entity;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class HelloEntityImpl extends UnicastRemoteObject implements HelloEntity  {

    public HelloEntityImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return name + "hello!";
    }
}
