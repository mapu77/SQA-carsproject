package edu.sqa.cars.ui;

import edu.sqa.cars.domain.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import static java.awt.GridBagConstraints.RELATIVE;
import static java.awt.GridBagConstraints.WEST;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

/**
 * This class contais the group of text fields representing the cars information visually
 * inside a panel.
 * @
 *
 * PUBLIC FEATURES:
 * // Constructors
 *    public CarDetailsComponents()
 *
 * // Methods
 *    public void clearTextFields()
 *    public void componentHidden(ComponentEvent ev)
 *    public void componentMoved(ComponentEvent ev)
 *    public void componentResized(ComponentEvent ev)
 *    public void componentShown(ComponentEvent ev)
 *    public void displayDetails(Car c)
 *    public JPanel getDetailsPanel()
 *    public String getInfoText()
 *    public String getKmText()
 *    public String getManufacturerText()
 *    public String getModelText()
 *    public String getPriceText()
 *    public String getYearText()
 *    public void setFocusManufacturerTextField()
 *
 * COLLABORATORS:
 *
 * @version 1.0, 16 Oct 2004
 * @author Adam Black
 */
public class CarDetailsComponents extends JPanel implements ComponentListener
{
	private JTextField manufacturerTextField = new JTextField();
	private JTextField yearTextField = new JTextField();
	private JTextField modelTextField = new JTextField();
	private JTextField priceTextField = new JTextField();
	private JTextField kmTextField = new JTextField();
	private JTextArea infoTextArea = new JTextArea(4, 0);

	private static final int DIV_FACTOR = 27;

	/**
	 * set up a new CarDetailComponents object and return a reference to the object which is a kind
	 * of panel
	 */
	public CarDetailsComponents()
	{
		Insets currentInsets;
		GridBagConstraints gridBagConstraints;
		setLayout(new BorderLayout(0, 20));
		JPanel compPanel = new JPanel(new GridBagLayout());
		JLabel manufacturerLabel = new JLabel("Manufacturer");
		String currentFont = manufacturerLabel.getFont().getName();
		currentInsets =  new Insets(0, 10, 0, 30);

		manufacturerLabel.setFont(new Font(currentFont, Font.BOLD, 12));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = WEST;
        gridBagConstraints.insets = currentInsets;
        compPanel.add(manufacturerLabel, gridBagConstraints);

		JLabel yearLabel = new JLabel("Year");
		yearLabel.setFont(new Font(currentFont, Font.BOLD, 12));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = WEST;
        gridBagConstraints.insets = currentInsets;
        compPanel.add(yearLabel, gridBagConstraints);

		JLabel modelLabel = new JLabel("Model");
		modelLabel.setFont(new Font(currentFont, Font.BOLD, 12));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = WEST;
        gridBagConstraints.insets = currentInsets;
        compPanel.add(modelLabel, gridBagConstraints);

		JLabel priceLabel = new JLabel("Price");
		priceLabel.setFont(new Font(currentFont, Font.BOLD, 12));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = WEST;
        gridBagConstraints.insets = currentInsets;
        compPanel.add(priceLabel, gridBagConstraints);

		JLabel kmLabel = new JLabel("Km Traveled");
		kmLabel.setFont(new Font(currentFont, Font.BOLD, 12));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = WEST;
        gridBagConstraints.insets = currentInsets;
        compPanel.add(kmLabel, gridBagConstraints);

		JLabel infoLabel = new JLabel("Extra Information");
		infoLabel.setFont(new Font(currentFont, Font.BOLD, 12));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = WEST;
        gridBagConstraints.insets = currentInsets;
        compPanel.add(infoLabel, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = RELATIVE;
		gridBagConstraints.anchor = WEST;
        gridBagConstraints.weightx = 1.0;
        compPanel.add(manufacturerTextField, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = RELATIVE;
		gridBagConstraints.anchor = WEST;
        gridBagConstraints.weightx = 1.0;
        compPanel.add(yearTextField, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = RELATIVE;
		gridBagConstraints.anchor = WEST;
        gridBagConstraints.weightx = 1.0;
        compPanel.add(modelTextField, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = RELATIVE;
		gridBagConstraints.anchor = WEST;
        gridBagConstraints.weightx = 1.0;
        compPanel.add(priceTextField, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = RELATIVE;
		gridBagConstraints.anchor = WEST;
        gridBagConstraints.weightx = 1.0;
        compPanel.add(kmTextField, gridBagConstraints);

		infoTextArea.setLineWrap(true);
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = WEST;
		gridBagConstraints.weightx = 1.0;
        compPanel.add(new JScrollPane(infoTextArea, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER), gridBagConstraints);

		// this listens for resize events
		addComponentListener(this);
        add(compPanel, "North");
	}

	/**
	 * clear all the text fields
	 */
	public void clearTextFields()
	{
		manufacturerTextField.setText("");
		yearTextField.setText("");
		modelTextField.setText("");
		priceTextField.setText("");
		kmTextField.setText("");
		infoTextArea.setText("");
	}

	@Override
	public void componentHidden(ComponentEvent ev) {
		// No behaviour needed
	}

	@Override
	public void componentMoved(ComponentEvent ev) {
		// No behaviour needed
	}

	/**
	 * when the details panel is resized change the length of the text boxes
	 *
	 * @return ev ComponentEvent object
	 */
	public void componentResized(ComponentEvent ev)
	{
		if (ev.getSource() == this)
		{
			int width = getWidth();

			if (width >= 0)
			{
				/* these text fields had to be resized manually. Using insets didn't work for
				smaller areas of the panel. */
				manufacturerTextField.setColumns(width / DIV_FACTOR);
				yearTextField.setColumns(width / DIV_FACTOR);
				modelTextField.setColumns(width / DIV_FACTOR);
				priceTextField.setColumns(width / DIV_FACTOR);
				kmTextField.setColumns(width / DIV_FACTOR);
				infoTextArea.setColumns((width / DIV_FACTOR) + 3); // this text box is larger
			}
		}
	}

	@Override
	public void componentShown(ComponentEvent ev){
		// No behaviour needed
	}

	/**
	 * display details about a car through the text box components
	 *
	 * @param c the car to display details about
	 */
	public void displayDetails(Car c)
	{
		manufacturerTextField.setText(c.getManufacturer());
		yearTextField.setText(Integer.toString(c.getYear()));
		modelTextField.setText(c.getModel());
		priceTextField.setText(Integer.toString(c.getPrice()));
		kmTextField.setText(Double.toString(c.getKilometers()));
		infoTextArea.setText(c.getInformation());
	}

	public String getInfoText()
	{
		return infoTextArea.getText();
	}

	public String getKmText()
	{
		return kmTextField.getText();
	}

	public String getManufacturerText()
	{
		return manufacturerTextField.getText();
	}

	public String getModelText()
	{
		return modelTextField.getText();
	}

	public String getPriceText()
	{
		return priceTextField.getText();
	}

	public String getYearText()
	{
		return yearTextField.getText();
	}

	/**
	 * set focus to the manufacturer text field. ie, put the cursor inside it
	 */
	public void setFocusManufacturerTextField()
	{
		manufacturerTextField.grabFocus();
	}
}
