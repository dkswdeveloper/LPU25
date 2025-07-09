package com.lpu.algo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
//@Scope(value = "singleton") //default
//@Scope("prototype") // create new object every time asked for 
//@Scope("request") // will create new object for every request
//@Scope("session") // for user session create new object
@Scope("application")
//@Scope()
public class MyService {

	//	@Autowired
	public MyService(PasswordHasher hasher1, PasswordHasher hasher2)
	{
		myNextPasswordHasher = hasher1;
		passwordHasher = hasher2;

	}
	public MyService()
	{

	}

	public PasswordHasher getMyNextPasswordHasher() {
		return myNextPasswordHasher;
	}
	@Autowired
	public void setMyNextPasswordHasher(PasswordHasher myNextPasswordHasher) {
		this.myNextPasswordHasher = myNextPasswordHasher;
	}
	public PasswordHasher getPasswordHasher() {
		return passwordHasher;
	}
	@Autowired
	public void setPasswordHasher(PasswordHasher passwordHasher) {
		this.passwordHasher = passwordHasher;
	}



	//	@Autowired
	PasswordHasher myNextPasswordHasher;

	//	@Autowired
	PasswordHasher passwordHasher;

	public void show()
	{
		System.out.println("hashers = " + myNextPasswordHasher + " and " +passwordHasher );
	}
}
