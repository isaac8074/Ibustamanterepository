package main;

import java.util.Scanner;


public class Converter {
	
	static Scanner scan = new Scanner(System.in);
	static double type; 
	static double output;
	public static void main(String[] args) {
		int menuSelection = 0;
		int menuSelection2 = 0;
		while(menuSelection != 4)	{
			System.out.println("Please select an option:");
			System.out.println("1. Volume conversion");
			System.out.println("2. Distance conversion");
			System.out.println("3. Mass conversion");
			System.out.println("4. Quit");
			menuSelection = scan.nextInt();
			switch(menuSelection)	{
			case 1:
				while(menuSelection2 != 9)	{
					System.out.println("please select an option");
					System.out.println("1. quarts to gallons");
					System.out.println("2. gallons to quarts");
					System.out.println("3. ounces to gallons");
					System.out.println("4. gallons to ounces");
					System.out.println("5. pints to quarts");
					System.out.println("6. quarts to pints");
					System.out.println("7. ounces to cups");
					System.out.println("8. cups to ounces");
					System.out.println("9. Return to main menu please");
					
					menuSelection2 = scan.nextInt();
					scan.nextLine();
					switch(menuSelection2)	{
					case 1:
						System.out.println(typein() + " quarts to " + convertQuartsToGallons(type) + " gallons.");
						break;
					case 2:
						System.out.println(typein() + " gallons to " + convertGallonsToQuarts(type) + " Quarts.");
						break;
					case 3:
						System.out.println(typein() + " ounces to " + convertOuncesToGallons(type) + " gallons.");
						break;
					case 4:
						System.out.println(typein() + " gallons to " + convertGallonsToOunces(type) + " Ounces.");
						break;
					case 5:
						System.out.println(typein() + " pints to " + convertPintsToQuarts(type) + " quarts.");
						break;
					case 6:
						System.out.println(typein() + " quarts to " + convertQuartsToPints(type) + " pints.");
						break;
					case 7:
						System.out.println(typein() + " Ounces to " + convertOuncesToCups(type) + " Cups.");
						break;
					case 8:
						System.out.println(typein() + " Cups to " + convertCupsToOunces(type) + " Ounces.");
						break;
					case 9:
						System.out.println("Returning to main menu.");
						break;
					default:
						System.out.println("Invalid! please choose a number from 1 to 9");
					}
					System.out.println();
				}
				break;
			case 2:
				while(menuSelection2 != 7)	{
					System.out.println("Please select an option");
					System.out.println("1. Milimeters to Inches");
					System.out.println("2. Inches to Feet");
					System.out.println("3. Inches to Kilometers");
					System.out.println("4. Feet to Meters");
					System.out.println("5. Feet to Yards");
					System.out.println("6. Meters to Kilometers");
					System.out.println("7. Return to main menu please");
					menuSelection2 = scan.nextInt();
					scan.nextLine();
					switch(menuSelection2)	{
					case 1:
						System.out.println(typein() + " Milimeters to " + convertMilimetersToInches(type) + " Inches.");
						break;
					case 2:
						System.out.println(typein() + " Inches to " + convertInchesToFeet(type) + " Feet.");
						break;
					case 3:
						System.out.println(typein() + " Inches to " + convertInchesToKilometers(type) + " Kilometers.");
						break;
					case 4:
						System.out.println(typein() + " Feet to " + convertFeetToMeters(type) + " Meters.");
						break;
					case 5:
						System.out.println(typein() + " Feet to " + convertFeetToYards(type) + " Yards.");
						break;
					case 6:
						System.out.println(typein() + " Meters to " + convertMetersToKilometers(type) + " Kilometers.");
						break;
					case 7:
						System.out.println("Returning to main menu.");
						break;
					default:
						System.out.println("Invalid! please choose a number from 1 to 7");
					}
					System.out.println();
				}
				break;
			case 3:
				while(menuSelection2 != 5)	{
					System.out.println("Please Select an option");
					System.out.println("1. Pounds to kiograms");
					System.out.println("2. grams to ounces");
					System.out.println("3. ounces to grams");
					System.out.println("4. kilograms to pounds");
					System.out.println("5. return to main menu please");
					menuSelection2 = scan.nextInt();
					scan.nextLine();
					switch(menuSelection2)	{
					case 1:
						System.out.println(typein() + " pounds to " + convertPoundsToKilograms(type) + " kilograms.");
						break;
					case 2:
						System.out.println(typein() + " inches to " + convertInchesToFeet(type) + " feet.");
						break;
					case 3:
						System.out.println(typein() + " ounces to " + convertOuncesToGrams(type) + " grams.");
						break;
					case 4:
						System.out.println(typein() + " kilograms to " + convertKilogramsToPounds(type) + " pounds.");
						break;
					case 5:
						System.out.println("Returning to main menu.");
						break;
					default:
						System.out.println("Invalid! please choose a number from 1 to 5");
					}
					System.out.println();
				}
				break;
			case 4:
				System.out.println("Thank you for using the Bustamante Unit Converter Application");
				System.out.println("Created by Jose Isaac Bustamante");
				break;
			default:
				System.out.println("Invalid! please choose a number from 1 to 4");
			}
		}
		scan.close();
	}
	public static double typein()	{
		System.out.println("Enter a number");
		type = scan.nextDouble();
		scan.nextLine();
		return type;
	}
	public static double convertQuartsToGallons(double num)	{
		output = num/4;
		return output;
	}
	public static double convertGallonsToQuarts(double num)	{
		output = num/0.25;
		return output;
	}
	public static double convertOuncesToGallons(double num)	{
		output = num/128;
		return output;
	}
	public static double convertGallonsToOunces(double num)	{
		output = num/0.00781;
		return output;
	}
	public static double convertPintsToQuarts(double num)	{
		output = num/2;
		return output;
	}
	public static double convertQuartsToPints(double num)	{
		output = num/0.5;
		return output;
	}
	public static double convertOuncesToCups(double num)	{
		output = num/8;
		return output;
	}
	public static double convertCupsToOunces(double num)	{
		output = num/0.125;
		return output;
	}
	public static double convertMilimetersToInches(double num)	{
		output = num/25.4;
		return output;
	}
	public static double convertInchesToFeet(double num)	{
		output = num/12;
		return output;
	}
	public static double convertInchesToKilometers(double num)	{
		output = num/39_370.08;
		return output;
	}
	public static double convertFeetToMeters(double num)	{
		output = num/3.0313;
		return output;
	}
	public static double convertFeetToYards(double num)	{
		output = num/3;
		return output;
	}
	public static double convertMetersToKilometers(double num)	{
		output = num/1_000;
		return output;
	}
	public static double convertPoundsToKilograms(double num)	{
		output = num/2.2;
		return output;
	}
	public static double convertGramsToOunces(double num)	{
		output = num/28.35;
		return output;
	}
	public static double convertOuncesToGrams(double num)	{
		output = num/0.035;
		return output;
	}
	public static double convertKilogramsToPounds(double num)	{
		output = num/0.454;
		return output;
	}
}
