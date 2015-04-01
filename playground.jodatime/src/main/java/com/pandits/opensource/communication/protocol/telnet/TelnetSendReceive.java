package com.pandits.opensource.communication.protocol.telnet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.telnet.TelnetClient;

import com.pandits.opensource.communication.protocol.SyncSendReceive;

public class TelnetSendReceive implements SyncSendReceive<ResultWrapper> {

    private TelnetClient client;

    public TelnetSendReceive(String host, int port) throws SocketException, IOException {
        super();
        initializeTelnetClient(host, port);
    }

    @Override
    public ResultWrapper sendReceive(String command) throws IOException {
        sendData(command);
        return new ResultWrapper(IOUtils.toString(client.getInputStream()));
    }

    @Override
    public void disconnect() throws IOException {
        client.disconnect();
    }

    private void sendData(String command) throws IOException, UnsupportedEncodingException {
        OutputStream outputStream = client.getOutputStream();

        outputStream.write(command.getBytes("UTF-8"));
        outputStream.flush();
    }

    private void initializeTelnetClient(String host, int port) throws SocketException, IOException {
        this.client = new TelnetClient();
        this.client.connect(host, port);
    }

}
