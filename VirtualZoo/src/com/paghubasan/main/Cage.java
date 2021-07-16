package com.paghubasan.main;

import java.util.List;

public interface Cage {
	public void setCageNumber(int number);
	public int getCageNumber();
	public String getCageName();
	public Animal addAnimal(Animal animal);
	public void totalAnimals();
	public int getCageNoSelection();
	public int cageSize();
	public List<Animal> getAnimals();
}
