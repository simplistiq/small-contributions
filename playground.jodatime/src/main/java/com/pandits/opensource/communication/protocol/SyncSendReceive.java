package com.pandits.opensource.communication.protocol;

import java.io.IOException;

public interface SyncSendReceive<T> {
    public T sendReceive(String command) throws IOException;

    public void disconnect() throws IOException;
}
