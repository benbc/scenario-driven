package com.twstudios.qcontalk;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class TheSystemIsRunning {

	private TwistSelenium selenium;

	public TheSystemIsRunning(TwistSelenium selenium) {
		this.selenium = selenium;
	}

	public void setUp() throws Exception {
		//This method is executed before the scenario execution starts.
	}

	public void tearDown() throws Exception {
		//This method is executed after the scenario execution finishes.
	}

}
