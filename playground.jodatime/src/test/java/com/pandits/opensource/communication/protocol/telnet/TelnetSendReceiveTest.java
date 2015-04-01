package com.pandits.opensource.communication.protocol.telnet;

import java.io.IOException;
import java.net.SocketException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TelnetSendReceiveTest {

    private TelnetSendReceive telnet;

    @Before
    public void before() throws SocketException, IOException {
        telnet = new TelnetSendReceive("127.0.0.1", 50000);
    }

    @After
    public void after() throws IOException {
        telnet.disconnect();
    }

    @Test
    public void test() throws IOException {
        ResultWrapper wrapper = telnet.sendReceive("1");
        System.out.println(wrapper.getResult());
        assertNotNull(wrapper);
    }

}
