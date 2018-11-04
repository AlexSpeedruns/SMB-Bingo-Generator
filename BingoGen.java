import java.io.*;
import java.util.*;

public class BingoGen {
	
	public static void main(String[] args) {
		ArrayList<String> goals = readGoals();
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
				goals = addGoal(goals, goal);
			}
			else {
				if(goals.size() < 25) {
					System.out.println("\nNot enough goals to make a bingo board");
				}
				else {
					int size = 0;
					print = "[";
					ArrayList<Integer> picked = new ArrayList<Integer>(0);
					while(size < 25) {
						int r = (int) (Math.random() * goals.size());
						while(picked.contains(r)) {
							r = (int) (Math.random() * goals.size());
						}
						picked.add(r);
						String curGoal = goals.get(r);
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
	
	public static ArrayList<String> readGoals() {
		File file = new File("goals.txt");
		ArrayList<String> goals = new ArrayList<String>(0);
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
					goals.add(line);
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
	
	public static ArrayList<String> addGoal(ArrayList<String> goals, String goal) {
		goals.add(goal);
		File file = new File("goals.txt");
		try {	
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			if(file.length() == 0) {
				bw.write(goal);
			}
			else {
				bw.newLine();
				bw.write(goal);
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
}