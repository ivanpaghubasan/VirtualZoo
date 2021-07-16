package com.paghubasan.animals;

import com.paghubasan.main.Animal;

public class Crocodile extends Animal {
	private String name;
	private int animalNo;
	private static int animalCounter = 1;
	
	@Override
	public void setName(String name) {
		this.name = name;
		this.animalNo = animalCounter++;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void eat() {
		System.out.println(String.format("%s is eating.\n", getName()));
	}

	@Override
	public void tease() {
		System.out.println(String.format("%s the crocodile is swimming.\n", getName()));
	}

	@Override
	public int getAnimalNo() {
		// TODO Auto-generated method stub
		return animalNo;
	}

	
}
