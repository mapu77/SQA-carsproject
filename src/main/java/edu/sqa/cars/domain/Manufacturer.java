package edu.sqa.cars.domain;

/**
 * Stores and retrieves Car objects
 * @
 *
 * PUBLIC FEATURES:
 * // Constructors
 *    public Manufacturer(String nam, Car c)
 *
 * // Methods
 *    public void addCar(Car c)
 *    public int carCount()
 *    public Car[] getAllCars()
 *    public Car[] getAllCars()
 *    public String getManufacturerName()
 *    private Car[] resizeArray(Car[] c, int extendBy)
 *    public void setManufacturersName(String nam)
 *
 * COLLABORATORS:
 *    Car
 *
 * @version 1.0, 16 Oct 2004
 * @author Adam Black
 */
public class Manufacturer implements java.io.Serializable
{
	private String name;		//name of manufacturer
	private Car[] car = new Car[0];		//stores information about all the cars by a manufacturer

	/**
	 * @param nam name of manufacturer
	 * @param c Car object to add to manufacturer
	 */
	public Manufacturer(String nam, Car c)
	{
		name = nam.toUpperCase();
		addCar(c);
	}

	/**
	 * add a new car to manufacturer
	 *
	 * @param c Car to add to manufacturer
	 */
	public void addCar(Car c)
	{
		car = resizeArray(car, 1);
		car[car.length - 1] = c;
	}

	/**
	 * count cars by manufacturer
	 * @return number of cars in manufacturer
	 */
	public int carCount()
	{
		return car.length;
	}

	/**
	 * get all cars by manufacturer
	 * @return array of Car objects by manufacturer
	 */
	public Car[] getAllCars()
	{
		return car;
	}

	public String getManufacturerName()
	{
		return name;
	}

	/**
	 * resize the array by a number of element
	 *
	 * @param c array object to extend
	 * @param extendBy number to extend by
	 * @return resultant, extended array
	 */
	private Car[] resizeArray(Car[] c, int extendBy)
	{
		Car[] result = new Car[c.length + extendBy];

		System.arraycopy(c, 0, result, 0, c.length);

		return result;
	}
}