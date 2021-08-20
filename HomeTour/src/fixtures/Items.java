package fixtures;

public class Items extends Fixture	{
	String dialogue;
	public Items(String name, String shortDescription, String longDescription, String dialogue)	{
		super(name, shortDescription, longDescription);
		this.dialogue = dialogue;
	}
	public String getDialogue()	{
		return this.dialogue;
	}
	public void setNothing()	{
		this.name = "Nothing";
		this.shortDescription = "Nothing";
		this.longDescription = "Nothing";
		this.dialogue = "Nothing";
	}
}
