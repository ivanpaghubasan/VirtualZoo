package com.paghubasan.main;

import java.util.ArrayList;
import java.util.List;

public class Cage {
	private int cageNumber;
	private List<Animal> animals = new ArrayList<>();
	
	public boolean addAnimal(Animal animal) {
		return animals.add(animal);
	}
	
	public int animalCount() {
		return animals.size();
	}
	
	public void setCageNumber(int cageNum) {
		cageNumber = cageNum;
	}
	
	public int getCageNumber() {
		return cageNumber;
	}
}
