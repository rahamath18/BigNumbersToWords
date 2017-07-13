package com.math.bignumbertowords;

class InvalidNumber extends Exception {
	
	@Override
	public String getMessage() {
		return "Number is not natural or positive!";
	}
}
