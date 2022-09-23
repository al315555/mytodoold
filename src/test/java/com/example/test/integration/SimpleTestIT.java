package com.example.test.integration;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.testbench.TestBenchTestCase;

public class SimpleTestIT extends TestBenchTestCase {

	private static final String URL = "http://localhost";
	private static final String PORT = "8080";

	@BeforeEach
	public void setUp() throws Exception {
		setDriver(new FirefoxDriver());
		getDriver().get(URL + ":" + PORT);
	}

	@AfterEach
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test
	public void clickGrettingsButton() {
		ButtonElement element = $(ButtonElement.class).first();

		element.click();

		Assert.assertTrue($(NotificationElement.class).exists());
		Assert.assertEquals("Hi!, you clicked the button Greetings", $(NotificationElement.class).first().getText());
	}
}
