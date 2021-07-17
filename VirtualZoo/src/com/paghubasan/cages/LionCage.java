package com.paghubasan.cages;

import java.util.HashMap;
import java.util.Map;

import com.paghubasan.animals.Lion;
import com.paghubasan.main.Animal;
import com.paghubasan.main.Cage;

public class LionCage implements Cage {
	private String name;
	private int number;
	private int cageSelectionNo;
	private static int cageCounter = 1;
	private Map<String, Lion> lions = new HashMap<>();
	
	public LionCage() {
		this.name = "Lion's Cage";
	}
	
	@Override
	public void setCageNumber(int number) {
		this.number = number;
		this.cageSelectionNo = cageCounter++;
	}

	@Override
	public <T> T addAnimal(T animal) {
		lions.put(((Lion)animal).getName(), (Lion)animal);
		return animal;
	}
	
	@Override
	public Map<String, Animal> getAnimals(){
		Map<String, Animal> animals = new HashMap<>(lions);
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

	@SuppressWarnings("unchecked")
	@Override
	public <T> T removeAnimal(T t) {
		return (T) lions.remove(((Lion)t).getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T tranferAnimal(T t) {
		return (T) lions.put(((Lion)t).getName(), (Lion) t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAnimal(String name) {
		// TODO Auto-generated method stub
		return (T) lions.get(name);
	}


}
