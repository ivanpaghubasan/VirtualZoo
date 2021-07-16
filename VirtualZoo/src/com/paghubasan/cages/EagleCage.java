package com.paghubasan.cages;

import java.util.ArrayList;
import java.util.List;

import com.paghubasan.animals.Eagle;
import com.paghubasan.main.Animal;
import com.paghubasan.main.Cage;

public class EagleCage implements Cage {
	private String name;
	private int number;
	private int cageSelectionNo;
	private static int cageCounter = 1;
	private List<Eagle> eagles = new ArrayList<>();
	
	public EagleCage() {
		this.name = "Eagle's Cage";
	}
	
	@Override
	public void setCageNumber(int number) {
		this.number = number;
		this.cageSelectionNo = cageCounter++;
	}

	@Override
	public int getCageNumber() {
		// TODO Auto-generated method stub
		return number;
	}

	@Override
	public String getCageName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Animal addAnimal(Animal animal) {
		if (animal instanceof Eagle) {
			eagles.add((Eagle)animal);
		}
		return animal;
	}

	@Override
	public void totalAnimals() {
		System.out.println(String.format("%s contains %d eagle(s).\n", getCageName(), eagles.size()));
	}

	@Override
	public int getCageNoSelection() {
		// TODO Auto-generated method stub
		return cageSelectionNo;
	}

	@Override
	public int cageSize() {
		// TODO Auto-generated method stub
		return eagles.size();
	}

	@Override
	public List<Animal> getAnimals() {
		List<Animal> animals = new ArrayList<>(eagles);
		return animals;
	}

}
