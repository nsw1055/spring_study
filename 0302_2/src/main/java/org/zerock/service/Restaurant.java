package org.zerock.service;

import org.springframework.stereotype.Service;

@Service
public class Restaurant {
	
	private final Chef chef;

	public Restaurant(Chef chef) {
		super();
		this.chef = chef;
	}

	@Override
	public String toString() {
		return "Restaurant [chef=" + chef + "]";
	}
	
	
}
