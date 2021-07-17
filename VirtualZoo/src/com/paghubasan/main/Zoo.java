package com.paghubasan.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.*;

import com.paghubasan.animals.Crocodile;
import com.paghubasan.animals.Eagle;
import com.paghubasan.animals.Lion;
import com.paghubasan.cages.*;

public class Zoo {
	
	private static Scanner input = new Scanner(System.in);
	private static List<LionCage> lions = new ArrayList<>();
	private static List<CrocodileCage> crocodiles = new ArrayList<>();
	private static List<EagleCage> eagles = new ArrayList<>();
	private static Set<Visitor> visitors = new HashSet<>();
	static AtomicInteger index;
	
	public static void main(String[] args) throws VirtualZooException, SecurityException, IOException {
		displayMenu();
	}
	
	public static void displayMenu() throws SecurityException, IOException {
		boolean isExit = false;
		int choice;
		Logger logger = null;
		
		do {
			
			System.out.println("*******WELCOME TO VIRTUAL ZOO*******");
			System.out.println("Total number of cages = " + totalCages());
			System.out.println();
			System.out.println("Please select a menu: ");
			System.out.println("[1] Add Animal to a Cage");
			System.out.println("[2] Create New Cage");
			System.out.println("[3] Tease Animal");
			System.out.println("[4] Transfer Animal");
			System.out.println("[5] Register Visitor");
			System.out.println("[8] Show Zoo");
			System.out.println("[9] Exit Zoo");
			try {
				logger = virtualZooLog("logs.txt");
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
					case 4:
						transferAnimal();
						break;
					case 5:
						registerVisitor();
						break;
					case 8:
						showZoo();
						break;
					case 9:
						System.exit(0);
						input.close();
						break;
				}
				
			} catch (VirtualZooException ex) {
				//System.out.println(ex.getMessage());
				logger.log(Level.WARNING, ex.getMessage());
				
			} catch (NumberFormatException ex) {
				//System.out.println("You must enter a numeric number. Please try again.\n");
				logger.log(Level.WARNING, "You must enter a numeric number. Please try again.");
			} catch (Exception ex) {
				ex.printStackTrace();
				input.nextLine();
			}
		} while(!isExit);
		
	}
	
	private static void createNewCage() throws NumberFormatException, VirtualZooException {
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
				throw new VirtualZooException("Please select a valid cage type. Please try again.\n");
		}
	}

	private static void addAnimalToCage() throws VirtualZooException {
		printAnimalType();
		int animalType = Integer.parseInt(input.nextLine());
		int lionCageNo, crocoCageNo, eagleCageNo;
		
		switch(animalType) {
			case 1:
				if (lions.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					index = new AtomicInteger(1);
					System.out.println("Please select a cage: ");
					lions.forEach(lion -> System.out.println(String.format("[%d] Cage No. %d", index.getAndIncrement(),lion.getCageNumber())));
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
							//System.out.println("You cannot add "+name+ " to " + lionCage.getCageName() + ". The cage is full.\n");
							throw new VirtualZooException(String.format("You cannot add %s to %s. The cage is full.\n", name, lionCage.getCageName()));
						}
					} else {
						throw new VirtualZooException("Please enter a valid cage number. Please try again.\n");
					}
				}
				break;
			case 2:
				if (crocodiles.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					index = new AtomicInteger(1);
					System.out.println("Please select a cage: ");
					crocodiles.forEach(crocodile -> System.out.println(String.format("[%d] Cage No. %d", index.getAndIncrement(),crocodile.getCageNumber())));
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
							//System.out.println("You cannot add "+name+ " to " + crocodileCage.getCageName() + ". The cage is full.\n");
							throw new VirtualZooException(String.format("You cannot add %s to %s. The cage is full.\n", name, crocodileCage.getCageName()));
						}
					} else {
						throw new VirtualZooException("Please enter a valid cage number. Please try again.\n");
					}
				}
				break;
			case 3:
				if (eagles.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					index = new AtomicInteger(1);
					System.out.println("Please select a cage: ");
					eagles.forEach(eagle -> System.out.println(String.format("[%d] Cage No. %d", index.getAndIncrement(),eagle.getCageNumber())));
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
							//System.out.println("You cannot add "+name+ " to " + eagleCage.getCageName() + ". The cage is full.\n");
							throw new VirtualZooException(String.format("You cannot add %s to %s. The cage is full.\n", name, eagleCage.getCageName()));
						}
					} else {
						throw new VirtualZooException("Please enter a valid cage number. Please try again.\n");
					}
				
				}
				break;
			default:
				throw new VirtualZooException("Please select a valid animal type. Please try again.\n");
		}
	}
	
	private static void teaseAnimal() throws VirtualZooException {
		printAnimalToTease();
		int animalToTease = Integer.parseInt(input.nextLine());
		int lionCageNo, crocoCageNo, eagleCageNo;
		
		switch(animalToTease) {
			case 1:
				if (lions.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					index = new AtomicInteger(1);
					System.out.println("Please select a cage: ");
					lions.forEach(lion -> System.out.println(String.format("[%d] Cage No. %d", index.getAndIncrement(),lion.getCageNumber())));
					lionCageNo = Integer.parseInt(input.nextLine());
					
					LionCage lionCage = lions.stream()
											.filter(l -> l.getCageNumber() == lionCageNo)
											.findFirst()
											.orElse(null);
					
					if(lionCage != null && lionCage.cageSize() > 0) {
						index = new AtomicInteger(1);
						System.out.println("Which one to tease: ");
						lionCage.getAnimals().entrySet().forEach((lion) -> System.out.println(String.format("[%d] %s",index.getAndIncrement(),lion.getValue().getName())));
						String name = input.nextLine();
						
						Lion lion = lionCage.getAnimal(name);
						
						if(lion != null) {
							lion.tease();
						} else {
							throw new VirtualZooException("Please enter a valid animal name. Please try again.\n");
						}
						
					} else {
						throw new VirtualZooException("Please enter a valid cage number. Please try again.\n");
					}
				}
				break;
			case 2:
				if (crocodiles.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					index = new AtomicInteger(1);
					System.out.println("Please select a cage: ");
					crocodiles.forEach(crocodile -> System.out.println(String.format("[%d] Cage No. %d", index.getAndIncrement(),crocodile.getCageNumber())));
					crocoCageNo = Integer.parseInt(input.nextLine());
					
					CrocodileCage crocodileCage = crocodiles.stream()
															.filter(c -> c.getCageNumber() == crocoCageNo)
															.findFirst()
															.orElse(null);
					
					if (crocodileCage != null && crocodileCage.cageSize() > 0) {
						index = new AtomicInteger(1);
						System.out.println("Which one to tease: ");
						crocodileCage.getAnimals().entrySet().forEach(croco -> System.out.println(String.format("[%d] %s",index.getAndIncrement(),croco.getValue().getName())));
						String name = input.nextLine();
						
						Crocodile crocodile = crocodileCage.getAnimal(name);
						
						if(crocodile != null) {
							crocodile.tease();
						} else {
							throw new VirtualZooException("Please enter a valid animal name. Please try again.\n");
						}
					} else {
						throw new VirtualZooException("Please enter a valid cage number. Please try again.\n");
					}
				}
				break;
			case 3:
				if (eagles.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					index = new AtomicInteger(1);
					System.out.println("Please select a cage: ");
					eagles.forEach(eagle -> System.out.println(String.format("[%d] Cage No. %d", index.getAndIncrement(),eagle.getCageNumber())));
					eagleCageNo = Integer.parseInt(input.nextLine());
					
					EagleCage eagleCage = eagles.stream()
												.filter(e -> e.getCageNumber() == eagleCageNo)
												.findFirst()
												.orElse(null);
					
					if (eagleCage != null && eagleCage.cageSize() > 0) {
						index = new AtomicInteger(1);
						System.out.println("Which one to tease: ");
						eagleCage.getAnimals().entrySet().forEach(eagle -> System.out.println(String.format("[%d] %s",index.getAndIncrement(),eagle.getValue().getName())));
						String name = input.nextLine();
						
						Eagle eagle = eagleCage.getAnimal(name);
						
						if(eagle != null) {
							eagle.tease();
						} else {
							throw new VirtualZooException("Please enter a valid animal name. Please try again.\n");
						}
					} else {
						throw new VirtualZooException("Please enter a valid cage number. Please try again.\n");
					}
				}
				break;
			default:
					
		}
	}
	
	private static void transferAnimal() throws VirtualZooException {
		printAnimalToTransfer();
		int animalType = Integer.parseInt(input.nextLine());
		int lionCageNo, crocoCageNo, eagleCageNo;
		
		
		switch(animalType) {
			case 1:
				if (lions.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					index = new AtomicInteger(1);
					System.out.println("Please select a cage: ");
					lions.forEach(lion -> System.out.println(String.format("[%d] Cage No. %d", index.getAndIncrement(),lion.getCageNumber())));
					lionCageNo = Integer.parseInt(input.nextLine());
					
					LionCage lionCage = lions.stream()
							.filter(c -> c.getCageNumber() == lionCageNo)
							.findFirst()
							.orElse(null);
					
					if (lionCage != null && lionCage.cageSize() > 0) {
						index = new AtomicInteger(1);
						System.out.println("Which one to transfer: ");
						lionCage.getAnimals().entrySet().forEach((lion) -> System.out.println(String.format("[%d] %s",index.getAndIncrement(),lion.getValue().getName())));
						String name = input.nextLine();
						Lion lion = lionCage.getAnimal(name);
						
						if (lion != null) {
							index = new AtomicInteger(1);
							System.out.println("Please select a cage to transfer the animal to: ");
							lions.forEach(l -> System.out.println(String.format("[%d] Cage No. %d", index.getAndIncrement(),l.getCageNumber())));
							int cageNo = Integer.parseInt(input.nextLine());
							
							LionCage cageToTransfer = lions.stream()
									.filter(c -> c.getCageNumber() == cageNo)
									.findFirst()
									.orElse(null);
							
							if (cageToTransfer.cageSize() < 2) {
								// remove animal to the cage
								lionCage.removeAnimal(lion);
								
								// transfer animal to another cage
								cageToTransfer.tranferAnimal(lion);
								
								System.out.println(String.format("Transferred %s to %s No. %d\n", lion.getName(), cageToTransfer.getCageName(), cageToTransfer.getCageNumber()));
								
							} else {
								// cage is full
								throw new VirtualZooException(String.format("You cannot transfer %s to %s No. %d. The cage is full.\n", name, cageToTransfer.getCageName(), cageToTransfer.getCageNumber()));
							}
						} else {
							throw new VirtualZooException("Please enter a valid animal name. Please try again.\n");
						}
						
					} else {
						throw new VirtualZooException("Please enter a valid cage number. Please try again.\n");
					}
				}
				break;
			case 2:
				if (crocodiles.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					index = new AtomicInteger(1);
					System.out.println("Please select a cage: ");
					crocodiles.forEach(eagle -> System.out.println(String.format("[%d] Cage No. %d", index.getAndIncrement(),eagle.getCageNumber())));
					crocoCageNo = Integer.parseInt(input.nextLine());
					
					CrocodileCage crocoCage = crocodiles.stream()
							.filter(c -> c.getCageNumber() == crocoCageNo)
							.findFirst()
							.orElse(null);
					
					if (crocoCage != null && crocoCage.cageSize() > 0) {
						index = new AtomicInteger(1);
						System.out.println("Which one to transfer: ");
						crocoCage.getAnimals().entrySet().forEach((croco) -> System.out.println(String.format("[%d] %s",index.getAndIncrement(),croco.getValue().getName())));
						String name = input.nextLine();
						Crocodile croco = crocoCage.getAnimal(name);
						
						if (croco != null) {
							index = new AtomicInteger(1);
							System.out.println("Please select a cage to transfer the animal to: ");
							crocodiles.forEach(l -> System.out.println(String.format("[%d] Cage No. %d", index.getAndIncrement(),l.getCageNumber())));
							int cageNo = Integer.parseInt(input.nextLine());
							
							CrocodileCage cageToTransfer = crocodiles.stream()
									.filter(c -> c.getCageNumber() == cageNo)
									.findFirst()
									.orElse(null);
							
							if (cageToTransfer.cageSize() < 2) {
								// remove animal to the cage
								crocoCage.removeAnimal(croco);
								
								// transfer animal to another cage
								cageToTransfer.tranferAnimal(croco);
								
								System.out.println(String.format("Transferred %s to %s No. %d\n", croco.getName(), cageToTransfer.getCageName(), cageToTransfer.getCageNumber()));
								
							} else {
								// cage is full
								throw new VirtualZooException(String.format("You cannot transfer %s to %s No. %d. The cage is full.\n", name, cageToTransfer.getCageName(), cageToTransfer.getCageNumber()));
							}
						} else {
							throw new VirtualZooException("Please enter a valid animal name. Please try again.\n");
						}
						
					} else {
						throw new VirtualZooException("Please enter a valid cage number. Please try again.\n");
					}
					
				}
				break;
			case 3:
				if (eagles.size() == 0) {
					System.out.println("No cage created yet. Please create a new cage.\n");
				} else {
					index = new AtomicInteger(1);
					System.out.println("Please select a cage: ");
					eagles.forEach(eagle -> System.out.println(String.format("[%d] Cage No. %d", index.getAndIncrement(),eagle.getCageNumber())));
					eagleCageNo = Integer.parseInt(input.nextLine());
					
					EagleCage eagleCage = eagles.stream()
							.filter(e -> e.getCageNumber() == eagleCageNo)
							.findFirst()
							.orElse(null);
					
					if (eagleCage != null && eagleCage.cageSize() > 0) {
						index = new AtomicInteger(1);
						System.out.println("Which one to transfer: ");
						eagleCage.getAnimals().entrySet().forEach((eagle) -> System.out.println(String.format("[%d] %s",index.getAndIncrement(),eagle.getValue().getName())));
						String name = input.nextLine();
						Eagle eagle = eagleCage.getAnimal(name);
						
						if (eagle != null) {
							index = new AtomicInteger(1);
							System.out.println("Please select a cage to transfer the animal to: ");
							crocodiles.forEach(l -> System.out.println(String.format("[%d] Cage No. %d", index.getAndIncrement(),l.getCageNumber())));
							int cageNo = Integer.parseInt(input.nextLine());
							
							EagleCage cageToTransfer = eagles.stream()
									.filter(e -> e.getCageNumber() == cageNo)
									.findFirst()
									.orElse(null);
							
							if (cageToTransfer.cageSize() < 2) {
								// remove animal to the cage
								eagleCage.removeAnimal(eagle);
								
								// transfer animal to another cage
								cageToTransfer.tranferAnimal(eagle);
								
								System.out.println(String.format("Transferred %s to %s No. %d\n", eagle.getName(), cageToTransfer.getCageName(), cageToTransfer.getCageNumber()));
								
							} else {
								// cage is full
								throw new VirtualZooException(String.format("You cannot transfer %s to %s No. %d. The cage is full.\n", name, cageToTransfer.getCageName(), cageToTransfer.getCageNumber()));
							}
						} else {
							throw new VirtualZooException("Please enter a valid animal name. Please try again.\n");
						}
						
					} else {
						throw new VirtualZooException("Please enter a valid cage number. Please try again.\n");
					}
				}
				break;
			default:
				throw new VirtualZooException("Please select a valid animal type. Please try again.\n");
		}
		
	}
	
	private static void registerVisitor() throws VirtualZooException {
		System.out.println("Please enter the visitor's name: ");
		String name = input.nextLine();
		
		if (visitors.size() < 2) {
			Visitor visitor = new Visitor();
			visitor.setName(name);
			
			boolean isExist = visitors.stream()
					.anyMatch(v -> v.getName().equalsIgnoreCase(name));
					
			
			if (!isExist) {
				visitors.add(visitor);
				System.out.println(String.format("The visitor with name '%s' has been registered with the zoo.\n", visitor.getName()));
			} else {
				throw new VirtualZooException("The visitor has already registered. Please enter another name.\n");
			}
		} else {
			throw new VirtualZooException("The visitor is full.\n");
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
	
	private static void printAnimalToTransfer() {
		System.out.println("Please select an animal to transfer: ");
		System.out.println("[1] Lion");
		System.out.println("[2] Crocodile");
		System.out.println("[3] Eagle");
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

	private static Logger virtualZooLog(String fileName) throws SecurityException, IOException {
		File file = new File(fileName);
		
		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileHandler fileHandler = new FileHandler(fileName, true);
		Logger logger = Logger.getLogger("Virtual Zoo Logs");
		logger.addHandler(fileHandler);
		SimpleFormatter formatter = new SimpleFormatter();
		fileHandler.setFormatter(formatter);
		fileHandler.close();
		return logger;
	}
}
