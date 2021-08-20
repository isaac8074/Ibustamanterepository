package fixtures;

public abstract class Fixture {
	protected String name;
	protected String shortDescription;
	protected String longDescription;
	
	
	public Fixture(String name, String  shortDescription, String longDescription)	{
		super();
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
	}
	//getters and setters
	public String getFullDesc()	{
		return longDescription;
	}
	public void setFullDesc(String longDescription)	{
		this.longDescription = longDescription;
	}
	
	public String getShortDesc()	{
		return shortDescription;
	}
	public void setShortDesc(String shortDescription)	{
		this.shortDescription = shortDescription;
	}
	public String getName()	{
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
