import java.io.*;
import java.util.*;

public class BingoGen {
	
	public static void main(String[] args) {
		ArrayList<Goal> goals = readGoals();
		ArrayList<Integer> picked = new ArrayList<Integer>(0);
		String again = "yes";
		String print = "[";
		while(again.equals("yes")) {	
			printMenu();
			Scanner scInt = new Scanner(System.in);
			Scanner sc = new Scanner(System.in);
			int c = -1;
			while(c == -1) {
				c = scInt.nextInt();
				if(c != 1 && c != 2) {
					System.out.println("\nNumber entered does to not correspond to a valid choice");
					System.out.print("Enter number: ");
					c = -1;
				}
			}
			if(c == 2) {
				System.out.print("Enter goal: ");
				String goal = sc.next();
				System.out.print("\nEnter length: ");
				String length = sc.next();
				goals = addGoal(goals, goal, length);
			}
			else {
				printMenu2();
				c = -1;
				int cnt = 0;
				while(c == -1) {
					c = scInt.nextInt();
					if(c != 1 && c != 2 && c != 3) {
						System.out.println("\nNumber entered does to not correspond to a valid choice");
						System.out.print("Enter number: ");
						c = -1;
					}
				}
				if(c == 2) {
					for(int i = 0; i < goals.size(); i++) {
						if(goals.get(i).getLength().equals("short")) {
							cnt++;
						}
					}
				}
				if(c == 3) {
					for(int i = 0; i < goals.size(); i++) {
						if(goals.get(i).getLength().equals("long")) {
							cnt++;
						}
					}
				}
				if(goals.size() < 25) {
					System.out.println("Not enough goals to make a bingo board");
				}
				else if((c == 2 || c == 3) && cnt < 25) {
					System.out.println("Not enough goals for this type of bingo board");
				}
				else {
					int size = 0;
					print = "[";
					while(size < 25) {
						int r = (int) (Math.random() * goals.size());
						if(c == 1) {
							while(picked.contains(r)) {
								r = (int) (Math.random() * goals.size());
							}
						}
						else if(c == 2) {
							while(picked.contains(r) || !goals.get(r).getLength().equals("short")) {
								r = (int) (Math.random() * goals.size());
							}
						}
						else {
							while(picked.contains(r) || !goals.get(r).getLength().equals("long")) {
								r = (int) (Math.random() * goals.size());
							}
						}
						picked.add(r);
						String curGoal = goals.get(r).getName();
						print += "{\"name\": \"" + curGoal + "\"},\n";
						size++;
					}
					print = print.substring(0, print.length()-2);
					print += "]";
					System.out.println("\n" + print);
				}
			}
			System.out.print("\nDo you want to do anything else? ");
			again = sc.next();
		}
	}
	
	public static ArrayList<Goal> readGoals() {
		File file = new File("goals.txt");
		ArrayList<Goal> goals = new ArrayList<Goal>(0);
		try {
			file.createNewFile();
		}
		catch(IOException e) {
			
		}
		if(file.length() != 0) {
			String line = null;
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				while((line = br.readLine()) != null) {
					String[] tokens = line.split(",");
					String n = tokens[0];
					String l = tokens[1];
					Goal g = new Goal(n, l);
					goals.add(g);
				}
				br.close();
			}
			catch(FileNotFoundException e) {
				
			}
			catch(IOException e) {
				
			}
		}
		return goals;
	}
	
	public static ArrayList<Goal> addGoal(ArrayList<Goal> goals, String name, String length) {
		Goal goal = new Goal(name, length);
		goals.add(goal);
		File file = new File("goals.txt");
		try {	
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			if(file.length() == 0) {
				bw.write(name + "," + length);
			}
			else {
				bw.newLine();
				bw.write(name + "," + length);
			}
			bw.close();
		}
		catch(FileNotFoundException e) {
				
		}
		catch(IOException e) {
				
		}
		return goals;
	}
	
	public static void printMenu() {
		System.out.println("Welcome to SMB Bingo Generator");
		System.out.println("Press 1 to generate JSON for a board");
		System.out.println("Press 2 to add a goal to the list of goals");
		System.out.print("Enter number: ");
	}
	
	public static void printMenu2() {
		System.out.println("Press 1 for normal bingo");
		System.out.println("Press 2 for short bingo");
		System.out.println("Press 3 for long bingo");
		System.out.print("Enter number: ");
	}
}

class Goal {
	private String name;
	private String length;
	
	public Goal(String name, String length) {
		this.name = name;
		this.length = length;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getLength() {
		return this.length;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLength(String length) {
		this.length = length;
	}
	
}