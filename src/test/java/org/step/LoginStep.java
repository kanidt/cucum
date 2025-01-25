package org.step;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStep {
	

@Given("testing1")
public void testing1() {
    System.out.println("test1");
}
@When("testing2")
public void testing2() {
    System.out.println("test2");
}

@Then("Testing4 {int}")
public void testing4(Integer value) {
	try {
	Assert.assertEquals(new Integer(1), value);
	}catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
    // Write code here that turns the phrase above into concrete actions
   // throw new io.cucumber.java.PendingException();
}




}
