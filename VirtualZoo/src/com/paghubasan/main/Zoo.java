package com.paghubasan.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import com.paghubasan.animals.Crocodile;
import com.paghubasan.animals.Eagle;
import com.paghubasan.animals.Lion;
import com.paghubasan.cages.*;

public class Zoo {
	
	private static Scanner input = new Scanner(System.in);
	private static List<LionCage> lions = new ArrayList<>();
	private static List<CrocodileCage> crocodiles = new ArrayList<>();
	private static List<EagleCage> eagles = new ArrayList<>();
	
	public static void main(String[] args) {
		displayMenu();
	}
	
	public static void displayMenu() {
		boolean isExit = false;
		int choice;
		do {
			System.out.println("*******WELCOME TO VIRTUAL ZOO*******");
			System.out.println("Total number of cages = " + totalCages());
			System.out.println();
			System.out.println("Please select a menu: ");
			System.out.println("[1] Add Animal to a Cage");
			System.out.println("[2] Create New Cage");
			System.out.println("[3] Tease Animal");
			System.out.println("[8] Show Zoo");
			System.out.println("[9] Exit Zoo");
			try {
				choice = Integer.parseInt(input.nextLine());
				
				switch(choice) {
					case 1: 
						addAnimalToCage();
						break;
					case 2:
						createNewCage();
						break;
					case 3:
						teaseAnimal();
						break;
					case 8:
						showZoo();
						break;
					case 9:
						System.exit(0);
						input.close();
						break;
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
				input.nextLine();
			}
		} while(!isExit);
		
	}
	
	private static void createNewCage() {
		printCageTypes();
		int cageType = Integer.parseInt(input.nextLine());
		int lionCageNo, crocoCageNo, eagleCageNo;
		
		switch(cageType) {
			case 1:
				LionCage lionCage = new LionCage();
				System.out.println("Enter Cage No. ");
				lionCageNo = Integer.parseInt(input.nextLine());
				lionCage.setCageNumber(lionCageNo);
				lions.add(lionCage);
				System.out.println(String.format("%s created successfully!\n", lionCage.getCageName()));
				break;
			case 2:
				CrocodileCage crocodileCage = new CrocodileCage();
				System.out.println("Enter Cage No. ");
				crocoCageNo = Integer.parseInt(input.nextLine());
				crocodileCage.setCageNumber(crocoCageNo);
				crocodiles.add(crocodileCage);
				System.out.println(String.format("%s created successfully!\n", crocodileCage.getCageName()));
				break;
				
			case 3: 
				EagleCage eagleCage = new EagleCage();
				System.out.println("Enter Cage No. ");
				eagleCageNo = Integer.parseInt(input.nextLine());
				eagleCage.setCageNumber(eagleCageNo);
				eagles.add(eagleCage);
				System.out.println(String.format("%s created successfully!\n", eagleCage.getCageName()));
				break;
			default:
				System.out.println("Please select a valid cage type. Please try again.\n");
		}
	}

	private static void addAnimalToCage() {
		printAnimalType();
		int animalType = Integer.parseInt(input.nextLine());
		int lionCageNo, crocoCageNo, eagleCageNo;
		
		switch(animalType) {
			case 1:
				if (lions.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					System.out.println("Please select a cage: ");
					lions.forEach(lion -> System.out.println(String.format("[%d] Cage No. %d", lion.getCageNoSelection(),lion.getCageNumber())));
					lionCageNo = Integer.parseInt(input.nextLine());
					
					LionCage lionCage = lions.stream()
										.filter(c -> c.getCageNumber() == lionCageNo)
										.findFirst()
										.orElse(null);
					
					if (lionCage != null) {
						System.out.println("Please enter animal's name: ");
						String name = input.nextLine();
						
						if (lionCage.cageSize() < 2) {
							
							Lion lion = new Lion();
							lion.setName(name);
							
							lionCage.addAnimal(lion);
							
							System.out.println(String.format("Added %s to %s.\n", lion.getName(), lionCage.getCageName()));
							
							lionCage.totalAnimals();
						} else {
							System.out.println("You cannot add "+name+ " to " + lionCage.getCageName() + ". The cage is full.\n");
						}
					} else {
						System.out.println("Please enter a valid cage number. Please try again.\n");
					}
				}
				break;
			case 2:
				if (crocodiles.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					System.out.println("Please select a cage: ");
					crocodiles.forEach(crocodile -> System.out.println(String.format("[%d] Cage No. %d", crocodile.getCageNoSelection(),crocodile.getCageNumber())));
					crocoCageNo = Integer.parseInt(input.nextLine());
					
					CrocodileCage crocodileCage = crocodiles.stream()
							.filter(c -> c.getCageNumber() == crocoCageNo)
							.findFirst()
							.orElse(null);
					
					if (crocodileCage != null) {
						System.out.println("Please enter animal's name: ");
						String name = input.nextLine();
						
						if (crocodileCage.cageSize() < 4) {
							Crocodile crocodile = new Crocodile();
							crocodile.setName(name);
							
							crocodileCage.addAnimal(crocodile);
							
							System.out.println(String.format("Added %s to %s.\n", crocodile.getName(), crocodileCage.getCageName()));
							
							crocodileCage.totalAnimals();
						} else {
							System.out.println("You cannot add "+name+ " to " + crocodileCage.getCageName() + ". The cage is full.\n");
						}
					} else {
						System.out.println("Please enter a valid cage number. Please try again.\n");
					}
				}
				break;
			case 3:
				if (eagles.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					System.out.println("Please select a cage: ");
					eagles.forEach(eagle -> System.out.println(String.format("[%d] Cage No. %d", eagle.getCageNoSelection(),eagle.getCageNumber())));
					eagleCageNo = Integer.parseInt(input.nextLine());
					
					EagleCage eagleCage = eagles.stream()
							.filter(e -> e.getCageNumber() == eagleCageNo)
							.findFirst()
							.orElse(null);
					
					if (eagleCage != null) {
						System.out.println("Please enter animal's name: ");
						String name = input.nextLine();
						
						if (eagleCage.cageSize() < 5) {
							Eagle eagle = new Eagle();
							eagle.setName(name);
							
							eagleCage.addAnimal(eagle);
							
							System.out.println(String.format("Added %s to %s.\n", eagle.getName(), eagleCage.getCageName()));
							
							eagleCage.totalAnimals();
						} else {
							System.out.println("You cannot add "+name+ " to " + eagleCage.getCageName() + ". The cage is full.\n");
						}
					} else {
						System.out.println("Please enter a valid cage number. Please try again.\n");
					}
				
				}
				break;
			default:
				System.out.println("Please select a valid animal type. Please try again.\n");
		}
	}
	
	private static void teaseAnimal() {
		printAnimalToTease();
		int animalToTease = Integer.parseInt(input.nextLine());
		int lionCageNo, crocoCageNo, eagleCageNo;
		
		switch(animalToTease) {
			case 1:
				if (lions.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					System.out.println("Please select a cage: ");
					lions.forEach(lion -> System.out.println(String.format("[%d] Cage No. %d", lion.getCageNoSelection(),lion.getCageNumber())));
					lionCageNo = Integer.parseInt(input.nextLine());
					
					LionCage lionCage = lions.stream()
											.filter(l -> l.getCageNumber() == lionCageNo)
											.findFirst()
											.orElse(null);
					
					if(lionCage.cageSize() > 0) {
						System.out.println("Which one to tease: ");
						lionCage.getAnimals().entrySet().forEach((lion) -> System.out.println(String.format("[%d] %s",lion.getValue().getAnimalNo(),lion.getValue().getName())));
						String name = input.nextLine();
						
						Lion lion = (Lion)lionCage.getAnimals().entrySet().stream()
														.filter(lionTease -> lionTease.getKey().equalsIgnoreCase(name))
														.findFirst()
														.orElse(null);
						
						if(lion != null) {
							lion.tease();
						} else {
							System.out.println("Please enter a valid animal name. Please try again.\n");
						}
						
					} else {
						System.out.println("Please enter a valid cage number. Please try again.\n");
					}
				}
				break;
			case 2:
				if (crocodiles.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					System.out.println("Please select a cage: ");
					crocodiles.forEach(crocodile -> System.out.println(String.format("[%d] Cage No. %d", crocodile.getCageNoSelection(),crocodile.getCageNumber())));
					crocoCageNo = Integer.parseInt(input.nextLine());
					
					CrocodileCage crocodileCage = crocodiles.stream()
															.filter(c -> c.getCageNumber() == crocoCageNo)
															.findFirst()
															.orElse(null);
					
					if (crocodileCage.cageSize() > 0) {
						System.out.println("Which one to tease: ");
						crocodileCage.getAnimals().entrySet().forEach(croco -> System.out.println(String.format("[%d] %s",croco.getValue().getAnimalNo(),croco.getValue().getName())));
						String name = input.nextLine();
						
						Crocodile crocodile = (Crocodile)crocodileCage.getAnimals().entrySet().stream()
																	.filter(crocoTease -> crocoTease.getKey().equalsIgnoreCase(name))
																	.findFirst()
																	.orElse(null);
						
						if(crocodile != null) {
							crocodile.tease();
						} else {
							System.out.println("Please enter a valid animal name. Please try again.\n");
						}
					} else {
						System.out.println("Please enter a valid cage number. Please try again.\n");
					}
				}
				break;
			case 3:
				if (eagles.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					System.out.println("Please select a cage: ");
					eagles.forEach(eagle -> System.out.println(String.format("[%d] Cage No. %d", eagle.getCageNoSelection(),eagle.getCageNumber())));
					eagleCageNo = Integer.parseInt(input.nextLine());
					
					EagleCage eagleCage = eagles.stream()
												.filter(e -> e.getCageNumber() == eagleCageNo)
												.findFirst()
												.orElse(null);
					if (eagleCage.cageSize() > 0) {
						System.out.println("Which one to tease: ");
						eagleCage.getAnimals().entrySet().forEach(eagle -> System.out.println(String.format("[%d] %s",eagle.getValue().getAnimalNo(),eagle.getValue().getName())));
						String name = input.nextLine();
						
						Eagle eagle = (Eagle)eagleCage.getAnimals().entrySet().stream()
															.filter(eagleTease -> eagleTease.getKey().equalsIgnoreCase(name))
															.findFirst()
															.orElse(null);
						if(eagle != null) {
							eagle.tease();
						} else {
							System.out.println("Please enter a valid animal name. Please try again.\n");
						}
					} else {
						System.out.println("Please enter a valid cage number. Please try again.\n");
					}
				}
				break;
			default:
					
		}
	}
	
	private static void showZoo() {
		if (totalCages() == 0) {
			System.out.println("Nothing to show. The zoo has no cage and no animals.\n");
		} else {
			lions.forEach(lion -> lion.totalAnimals());
			crocodiles.forEach(croco -> croco.totalAnimals());
			eagles.forEach(eagle -> eagle.totalAnimals());
		}
	}
	
	private static void printCageTypes() {
		System.out.println("Please select cage type: ");
		System.out.println("[1] Lion's Cage");
		System.out.println("[2] Crocodile Cage");
		System.out.println("[3] Eagle Cage");
	}
	
	private static void printAnimalType() {
		System.out.println("Please select an animal to add: ");
		System.out.println("[1] Lion");
		System.out.println("[2] Crocodile");
		System.out.println("[3] Eagle");
	}
	
	private static void printAnimalToTease() {
		System.out.println("Please select an animal to tease: ");
		System.out.println("[1] Lion");
		System.out.println("[2] Crocodile");
		System.out.println("[3] Eagle");
	}
	
	private static int totalCages() {
		return lions.size() + crocodiles.size() + eagles.size();
	}
}
