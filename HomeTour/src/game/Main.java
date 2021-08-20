package game;
/*
 * This HomeTour application is a description of my 
 * current home I live in. Currently the tour is going 
 * in first level and around my property. Unfortunately wasn't able
 * to add functions of using objects, but was able to implement
 * them into movement method in a basic way. 
 */
import java.util.Scanner;
//main inherits roommanager
public class Main extends RoomManager	{
	public Main(int roomCount)	{
		super(roomCount);
	}
	/*
	 * Creating rooms, initializing scanner, and running program. 
	 * There is an option to quit program any time once done.
	 */
	private static RoomManager manager = new RoomManager(7);
	static int gameRunning = 1;
	public static void main(String[] args) {
		manager.init();
		Player player = new Player();
		player.setCurrentRoom(manager.startingRoom);
		while (gameRunning ==1)	{
			Main.printRoom(player);
			String[] input = Main.collectInput();
			Main.parse(input, player);
		}
		if (gameRunning ==0)	{
			System.out.println("Game Over!");
		}
	}
	private static void printRoom(Player player) {
		//this class prints room and full description of room
		player.getCurrentRoom();
		System.out.println(player.currentRoom.getName() + "\n");
		System.out.println(player.currentRoom.getFullDesc() + "\n");
	}

	private static String[] collectInput() {
		//this function collects the input of direction
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nTo continue please type go and a direction. \nFor Example:\"Go North\"");
		String input = scanner.nextLine();
		
		String[] strArr = input.split(" ");
		if (gameRunning==0)	{
			scanner.close();
		}
		return strArr;
	}
		
	private static void parse(String[] input, Player player) {
		/*
		 * this parse function collects commands like G,Go, Move, and M
		 *Also you are able to quit program/game
		 *if there is misspelling, you have to type any value (words are 
		 *case sensitive)
		 */
		if (input[0].equals("Quit"))	{
			gameRunning = 0;
		}
		switch (input[0])	{
		case "Go" :
			Main.movement(input, player);
			break;
		case "Move" :
			Main.movement(input, player);
			break;
		case "G" :
			Main.movement(input, player);
			break;
		case "M" :
			Main.movement(input, player);
			break;
		default:
			System.out.println("\nLooks like you misspelled...\nPlease follow the given direction!\n");
			break;
		}
	}
	
	public static void movement(String[] input, Player player ) {
		/*
		 * There is movements being done in each rooms
		 * the possibility that there is to go in each room using index
		 */
		if(player.currentRoom.equals(manager.rooms[0])) {
			switch (input[1]) {
			case "East":
				System.out.println("Now You're walking to the restroom\n");
				player.setCurrentRoom(manager.rooms[5]);
				break;
			case "West":
				System.out.println("There is nothing this way but a wall.\n");
				break;
			case "South":
				System.out.println("Walking towards the frontyard.\n");
				player.setCurrentRoom(manager.rooms[4]);
				break;
			case "North":
				System.out.println("Great! Now, you're walking towards the living room.\n");
				player.setCurrentRoom(manager.rooms[2]);
				break;
			default:
				System.out.println("\nLooks like you misspelled!\nPlease follow the given direction!\n");
				break;
			}
			
		} else if (player.currentRoom.equals(manager.rooms[1])) {
		
			switch (input[1]) {
			case "East":
				System.out.println("Walking into the Garage\n");
				player.setCurrentRoom(manager.rooms[6]);
				break;
			case "West":
				System.out.println("Using the sink.\n");
				break;
			case "South":
				System.out.println("You walk back into the Living room\n");
				player.setCurrentRoom(manager.rooms[2]);
				break;
			case "North":
				System.out.println("Getting food out of the Pantry.\n");
				break;
			default:
				System.out.println("\nLooks like you misspelled!\nPlease follow the given direction!\n");
				break;
			}
		} else if(player.currentRoom.equals(manager.rooms[2])) {
		
			switch (input[1]) {
			case "East":
				System.out.println("You walk out into the kitchen\n");
				player.setCurrentRoom(manager.rooms[1]);
				break;
			case "West":
				System.out.println("Sitting on the couch and watching tv\n");
				break;
			case "South":
				System.out.println("Heading towards the hallway.\n");
				player.setCurrentRoom(manager.rooms[0]);
				break;
			case "North":
				System.out.println("You walk out into the backyard.\n");
				player.setCurrentRoom(manager.rooms[3]);
				break;
			default:
				System.out.println("\nLooks like you misspelled!\nPlease follow the given direction!\n");
				break;
			}
		} else if(player.currentRoom.equals(manager.rooms[3])) {
		
			switch (input[1]) {
			case "East":
				System.out.println("Using the basketball goal.\n");
				break;
			case "West":
				System.out.println("using the lawn mower..\n");
				break;
			case "South":
				System.out.println("Heading back into the house in the living room.\n");
				player.setCurrentRoom(manager.rooms[2]);
				break;
			case "North":
				System.out.println("waving towards the neighbors.\n");
				break;
			default:
				System.out.println("\nLooks like you misspelled!\nPlease follow the given direction!\n");
				break;
			}
		} else if(player.currentRoom.equals(manager.rooms[4])) {
		
			switch (input[1]) {
			case "East":
				System.out.println("The neighbors house is to the right.\n");
				break;
			case "West":
				System.out.println("The neighbors house is to the left.\n");
				break;
			case "South":
				System.out.println("You are now back in the hallway in the house\n");
				player.setCurrentRoom(manager.rooms[0]);
				break;
			case "North":
				System.out.println("There are cars passing by the street.\n");
				break;
			default:
				System.out.println("\nLooks like you misspelled!\nPlease follow the given direction!\n");
				break;
			}
		} else if(player.currentRoom.equals(manager.rooms[5]))	{
			switch (input[1])	{
			case "East":
				System.out.println("using toilet...\n");
				break;
			case "West":
				System.out.println("using sink...\n");
				break;
			case "South":
				System.out.println("You are going back to the hallway now.\n");
				player.setCurrentRoom(manager.rooms[0]);
				break;
			case "North":
				System.out.println("There is nothing this way please select something else.\n");
				break;
			default:
				System.out.println("\nLooks like you misspelled!\nPlease follow the given directions!\n");
				break;
			}
		} else if(player.currentRoom.equals(manager.rooms[6]))	{
			switch (input[1])	{
			case "East":
				System.out.println("getting on mortocycle bike...\n");
				break;
			case "West":
				System.out.println("getting on corvette...\n");
				break;
			case "South":
				System.out.println("You are going back to the kitchen now.\n");
				player.setCurrentRoom(manager.rooms[1]);
				break;
			case "North":
				System.out.println("There is nothing this way please select something else.\n");
				break;
			default:
				System.out.println("\nLooks like you misspelled!\nPlease follow the given directions!\n");
				break;
			}
		}
	}
}