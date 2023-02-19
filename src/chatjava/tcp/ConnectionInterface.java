package chatjava.tcp;

public interface ConnectionInterface {
    public void connect(String ipAddress, int port);

    public void stopConnection();
}
