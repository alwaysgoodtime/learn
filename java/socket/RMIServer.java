package socket;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 服务端实现虚拟方法，并发布到网络供客户端调用
 * @author goodtime
 * @create 2024-01-24 14:11
 */
public class RMIServer implements RMIInterface {
    @Override
    public LocalDateTime getLocalDateTime(String zoneId) throws RemoteException {
        return LocalDateTime.now(ZoneId.of(zoneId)).withNano(0);
    }

    public static void main(String[] args) throws RemoteException {
        System.out.println("create World clock remote service...");
        // 实例化一个RMIServer:
        RMIInterface server = new RMIServer();
        // 将此服务转换为远程服务接口:
        RMIInterface skeleton = (RMIInterface) UnicastRemoteObject.exportObject(server, 0);
        // 将RMI服务注册到1099端口:
        Registry registry = LocateRegistry.createRegistry(1099);
        // 注册此服务，服务名为"WorldClock":
        registry.rebind("WorldClock", skeleton);
    }
}
