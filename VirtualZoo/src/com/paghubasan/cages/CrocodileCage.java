package com.paghubasan.cages;

import java.util.ArrayList;
import java.util.List;

import com.paghubasan.animals.Crocodile;
import com.paghubasan.main.Animal;
import com.paghubasan.main.Cage;

public class CrocodileCage implements Cage {

	private String name;
	private int number;
	private int cageSelectionNo;
	private static int cageCounter = 1;
	private List<Crocodile> crocodiles = new ArrayList<>();
	
	public CrocodileCage() {
		this.name = "Crocodile's Cage";
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
		// TODO Auto-generated method stub
		if (animal instanceof Crocodile) {
			crocodiles.add((Crocodile)animal);
		}
		return null;
	}

	@Override
	public void totalAnimals() {
		System.out.println(String.format("%s contains %d crocodile(s).\n", getCageName(), cageSize()));
	}

	@Override
	public int getCageNoSelection() {
		// TODO Auto-generated method stub
		return cageSelectionNo;
	}

	@Override
	public int cageSize() {
		// TODO Auto-generated method stub
		return crocodiles.size();
	}

	@Override
	public List<Animal> getAnimals() {
		// TODO Auto-generated method stub
		List<Animal> animals = new ArrayList<>(crocodiles);
		return animals;
	}

}
