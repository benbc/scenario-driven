package com.twstudios.qcontalk;

import com.thoughtworks.teach.bank.application.BankRegistry;
import com.thoughtworks.teach.view.BankTestFixture;
import com.thoughtworks.teach.web.WebServer;

public class ServerRunning {
	private WebServer server;

	public void setUp() throws Exception {
        BankTestFixture testFixture = new BankTestFixture();
        server = new WebServer(new BankRegistry(), testFixture, 8088);
        server.run();
	}

	public void tearDown() throws Exception {
		server.stop();
	}
}
