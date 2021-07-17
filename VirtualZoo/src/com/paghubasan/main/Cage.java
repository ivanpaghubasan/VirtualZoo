package com.paghubasan.main;

import java.util.List;
import java.util.Map;

import com.paghubasan.animals.Lion;

public interface Cage {
	public void setCageNumber(int number);
	public int getCageNumber();
	public String getCageName();
	public <T> T addAnimal(T t);
	public void totalAnimals();
	public int getCageNoSelection();
	public int cageSize();
	public Map<String, Animal> getAnimals();
	public <T> T removeAnimal(T t);
	public <T> T tranferAnimal(T t);
	public <T> T getAnimal(String name);
}
