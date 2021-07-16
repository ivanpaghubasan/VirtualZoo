package com.paghubasan.animals;

import com.paghubasan.main.Animal;

public class Lion extends Animal {

	private String name;
	private static int animalCounter = 1;
	private int animalNo;
	
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
		this.animalNo = animalCounter++;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void eat() {
		System.out.println(String.format("%s is eating.\n",getName()));
	}

	@Override
	public void tease() {
		System.out.println(String.format("%s the lion is roaring.\n", getName()));
	}

	@Override
	public int getAnimalNo() {
		// TODO Auto-generated method stub
		return animalNo;
	}

}
