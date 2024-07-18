package socket;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

/**
 * @author goodtime
 * @create 2024-01-24 14:09
 */
public interface RMIInterface extends Remote {

    LocalDateTime getLocalDateTime(String zoneId) throws RemoteException;;

}
