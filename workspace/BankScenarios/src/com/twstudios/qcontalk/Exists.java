package com.twstudios.qcontalk;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class Exists {

	private TwistSelenium selenium;

	public Exists(TwistSelenium selenium) {
		this.selenium = selenium;
	}

	public void setUp(String string1) throws Exception {
		//This method is executed before the scenario execution starts.
	}

	public void tearDown(String string1) throws Exception {
		//This method is executed after the scenario execution finishes.
	}

}
