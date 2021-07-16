package com.paghubasan.cages;

import java.util.ArrayList;
import java.util.List;

import com.paghubasan.animals.Lion;
import com.paghubasan.main.Animal;
import com.paghubasan.main.Cage;

public class LionCage implements Cage {
	private String name;
	private int number;
	private int cageSelectionNo;
	private static int cageCounter = 1;
	private List<Lion> lions = new ArrayList<>();
	
	public LionCage() {
		this.name = "Lion's Cage";
	}
	
	@Override
	public void setCageNumber(int number) {
		this.number = number;
		this.cageSelectionNo = cageCounter++;
	}

	@Override
	public Animal addAnimal(Animal animal) {
		if (animal instanceof Lion) {
			lions.add((Lion)animal);
		}
		
		return animal;
	}
	
	@Override
	public List<Animal> getAnimals(){
		List<Animal> animals = new ArrayList<>(lions);
		return animals;
	}
	
	@Override
	public void totalAnimals() {
		System.out.println(String.format("%s contains %d lions(s).\n", getCageName(), cageSize()));
	}

	@Override
	public String getCageName() {
		return name;
	}

	@Override
	public int getCageNumber() {
		return number;
	}

	@Override
	public int getCageNoSelection() {
		return cageSelectionNo;
	}

	@Override
	public int cageSize() {
		return lions.size();
	}


}
