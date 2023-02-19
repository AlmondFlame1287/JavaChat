package chatjava.tcp;

public interface ConnectionInterface {
    public void connect(final String ipAddress, final int port);

    public void stopConnection();
}
