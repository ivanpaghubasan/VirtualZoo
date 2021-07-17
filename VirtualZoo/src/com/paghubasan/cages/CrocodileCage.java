package com.paghubasan.cages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paghubasan.animals.Crocodile;
import com.paghubasan.main.Animal;
import com.paghubasan.main.Cage;

public class CrocodileCage implements Cage {

	private String name;
	private int number;
	private int cageSelectionNo;
	private static int cageCounter = 1;
	private Map<String, Crocodile> crocodiles = new HashMap<>();
	
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
	public <T> T addAnimal(T animal) {
		crocodiles.put(((Crocodile)animal).getName(), (Crocodile)animal);
		return animal;
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
	public Map<String, Animal> getAnimals() {
		// TODO Auto-generated method stub
		Map<String, Animal> animals = new HashMap<>(crocodiles);
		return animals;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T removeAnimal(T t) {
		// TODO Auto-generated method stub
		return (T) crocodiles.remove(((Crocodile)t).getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T tranferAnimal(T t) {
		// TODO Auto-generated method stub
		return (T) crocodiles.put(((Crocodile)t).getName(), (Crocodile)t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAnimal(String name) {
		// TODO Auto-generated method stub
		return (T)crocodiles.get(name);
	}

}
