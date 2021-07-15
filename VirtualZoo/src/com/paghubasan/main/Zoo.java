package com.paghubasan.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zoo {
	
	private static Scanner input = new Scanner(System.in);
	private static int choice;
	private static List<Cage> cages = new ArrayList<>();
	
	public static void main(String[] args) {
		displayMenu();
	}
	
	public static void displayMenu() {
		boolean isExit = false;
		
		do {
			System.out.println("*******WELCOME TO VIRTUAL ZOO*******");
			System.out.println("Total number of cages = " + cages.size());
			System.out.println();
			System.out.println("Please select a menu: ");
			System.out.println("[1] Add Animal to a Cage");
			System.out.println("[2] Create New Cage");
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
		if (cages.size() < 2) {
			Cage cage = new Cage();
			System.out.println("Enter Cage No. ");
			int cageNum = Integer.parseInt(input.nextLine());
			cage.setCageNumber(cageNum);
			cages.add(cage);
			System.out.println("Cage created successfully!\n");
		} else {
			System.out.println("The zoo already has a maximum of 2 cages. No new cage can be added.\n");
		}
	}

	private static void addAnimalToCage() {
		if (cages.size() == 0) {
			System.out.println("No cage created yet. Please create a new cage.\n");
		} else {
			cages.forEach(c -> System.out.println(c.getCageNumber()));
			System.out.println("Enter Cage No. ");
			int cageNo = Integer.parseInt(input.nextLine());
			
			Cage cage = cages.stream()
					.filter(c -> c.getCageNumber() == cageNo)
					.findFirst()
					.orElse(null);
			
			if (cage != null) {
				System.out.println("Please enter animal's name: ");
				String name = input.nextLine();
				
				if (!name.isEmpty()) {
					Animal animal = new Animal();
					animal.setName(name);
					if (cage.animalCount() < 2) {
						if (cage.addAnimal(animal))
							System.out.println(String.format("%s is added to cage %d.\n", animal.getName(), cage.getCageNumber()));
					} else {
						System.out.println("The cage is is full cannot add new animal.\n");
					}
				} else {
					System.out.println("You have entered invalid name. Please try again.\n");
				}
			} else {
				System.out.println("You have entered an invalid cage no. Please ensure the cage is created.\n ");
			}
			
		}
	}
	
	private static void showZoo() {
		cages.forEach(cage -> System.out.printf("Cage %d has %d animal(s).\n", cage.getCageNumber(), cage.animalCount()));
		System.out.println(" ");
	}
}
