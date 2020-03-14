package edu.sqa.cars.ui;

import edu.sqa.cars.main.CarSalesSystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import static javax.swing.JLabel.CENTER;

/**
 * A welcome dialog, which displays basic statistics about the cars in the data file
 * @
 *
 * PUBLIC FEATURES:
 * // Constructors
 *    public WelcomePanel(CarSalesSystem carSys, JPanel dest, String data)
 *
 * // Methods
 *    public void carsUpdated(CarUpdateEvent ev)
 *    public void stateChanged(ChangeEvent ev)
 *
 * COLLABORATORS:
 *
 *
 * @version 1.0, 16 Oct 2004
 * @author Adam Black
 */
public class WelcomePanel extends JPanel implements ChangeListener
{
	private CarSalesSystem carSystem;
	private JLabel carsLabel = new JLabel();
	private JLabel manufacturersLabel = new JLabel();
	private JLabel avgPriceLabel = new JLabel();
	private JLabel avgKmLabel = new JLabel();
	private JLabel avgAgeLabel = new JLabel();
	private JLabel versionLabel = new JLabel();
	private JLabel dataSizeLabel = new JLabel();
	private boolean carsUpdated = false;
	private String file;

	/**
	 * @param carSys link to CarSalesSystem object
	 * @param data filename of data file
	 */
	public WelcomePanel(CarSalesSystem carSys, String data)
	{
		carSystem = carSys;
		file = data;
		setLayout(new BorderLayout(0, 10));
		carSys.addCarUpdateListener(this);

		JPanel statsPanel = new JPanel();
		statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		centerPanel.add(statsPanel);
		JLabel headingLabel = new JLabel("Welcome to the Car Sales System", CENTER);
		headingLabel.setBorder(new EmptyBorder(new Insets(10, 0, 0, 0)));

		updateStats();

		statsPanel.add(carsLabel);
		statsPanel.add(manufacturersLabel);
		statsPanel.add(avgPriceLabel);
		statsPanel.add(avgKmLabel);
		statsPanel.add(avgAgeLabel);
		statsPanel.add(Box.createVerticalStrut(20));
		statsPanel.add(versionLabel);
		statsPanel.add(dataSizeLabel);

		add(headingLabel, "North");
		add(centerPanel, "Center");
	}

	/**
	 * when the tab on the main frame gets changed over to this one, we may have to update the
	 * car list with the latest version
	 *
	 * @param ev ChangeEvent object
	 */
	public void stateChanged(ChangeEvent ev)
	{
		// the source came from a JTabbedPane
		if (ev.getSource() instanceof JTabbedPane)
		{
			JTabbedPane tab = (JTabbedPane)ev.getSource();
			// the Welcome tab has just been chosen
			if (tab.getSelectedIndex() == 0 && carsUpdated)
			{
				// update them
				updateStats();
				// next time don't update the statistics, unless a car is added to the system
				carsUpdated = false;
			}
		}
	}

	/**
	 * update all the statistics
	 */
	private void updateStats()
	{
		// receive new statistics
		int cars = (int)carSystem.getStatistics(CarSalesSystem.CARS_COUNT);
		int manufacturers = (int)carSystem.getStatistics(CarSalesSystem.MANUFACTURERS_COUNT);
		double avgPrice = Math.floor(carSystem.getStatistics(CarSalesSystem.AVERAGE_PRICE) * 10 + 0.5) / 10;
		double avgKm = Math.floor(carSystem.getStatistics(CarSalesSystem.AVERAGE_DISTANCE) * 10 + 0.5) / 10;
		double avgAge = Math.floor(carSystem.getStatistics(CarSalesSystem.AVERAGE_AGE) * 10 + 0.5) / 10;
		java.io.File f = new java.io.File(file);
		long size = f.length(); // get length of binary data file

		carsLabel.setText("Total number of cars: " + cars);
		manufacturersLabel.setText("Total number of manufacturers: " + manufacturers);
		avgPriceLabel.setText("Average car price: " + avgPrice);
		avgKmLabel.setText("Average car kilometers: " + avgKm);
		avgAgeLabel.setText("Average car age: " + avgAge);
		versionLabel.setText("Car Sales System, Version " + CarSalesSystem.APP_VERSION);
		dataSizeLabel.setText("Size of data file: " + size + " bytes");
	}
}