package game;

import fixtures.Room;

public class RoomManager {
	public Room startingRoom;
	public Room[] rooms;
	public RoomManager(int roomCount)	{
		rooms = new Room[roomCount];
	}
	public void init()	{
		Room hallWay = new Room(
				"Hallway",
				"A tiny foyer close to stairs",
				"Now Currently you're standing at the hallway connected to the kitchen and living room."
				+ " To your North, you find your living room." + "\n"
						+ "And last but not least in the South you can find your Front yard. The restroom is in the East.");
		this.rooms[0] = hallWay;
		this.startingRoom = hallWay;
		Room kitchen = new Room("Your Kitchen", 
				"This is your Kitchen next to living room", "This is your Kitchen, where food can be "
						+ "prepared and refreshments are located. In order to go to the hallway go south, and the garage is in the East");
		this.rooms[1] = kitchen;
		Room livingRoom = new Room(
				"Living Room",
				"This is a small living room", 
				"this living room has a tv and Couches."
				+ "The Hallway is in the South, towards the North there is your backyard, and towards East there is the kitchen.");
		this.rooms[2] = livingRoom;
		Room backYard = new Room("Your Backyard","medium back-yard","There is a basketball "
				+ "hoop to play basketball. It is a good place for outdoop games and mow lawn. To go back go South.");
		this.rooms[3] = backYard;
		Room frontYard = new Room(
				"front yard",
				"Over here there nice lawn, a driveway where the cars are parked sometimes, and houses",
				"You can go back inside if you like by going South.");
		this.rooms[4]=frontYard;
		Room restRoom = new Room(
				"Restroom",
				"This is a small restroom",
				"This is where hands are washed and using toilet... To go back to hallway go south.");
		this.rooms[5] = restRoom;
		Room garage = new Room(
				"Garage",
				"This is a two car garage",
				"This is where the tools are at and cars are parked. To go back to kitchen go South.");
		this.rooms[6] = garage;
		}
	//getters and setters for rooms
	public Room getStartingRoom()	{
		return this.startingRoom;
	}
	public Room[] getRooms()	{
		return this.rooms;
	}
	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}
	
}
