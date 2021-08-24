
public class Earth {
	public static void main(String[] args)	{
		Human tom = new Human();
		tom.age = 5;
		tom.eyeColor="brown";
		tom.heightInInches = 72;
		tom.name = "Tom Zsabo";
		tom.speak();
		tom.work();
		tom.walk();
		tom.eat();
		Human joe = new Human();
		joe.age = 22;
		joe.eyeColor = "green";
		joe.heightInInches = 100;
		joe.name = "Joe Smith";
		joe.speak();
		joe.work();
		joe.walk();
		joe.eat();
	}
}
