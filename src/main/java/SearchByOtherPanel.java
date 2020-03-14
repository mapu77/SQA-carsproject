import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * A panel used for obtaining search parameters using car price and distance travelled
 * @
 *
 * PUBLIC FEATURES:
 * // Constructors
 *    public SearchByOtherPanel(CarSalesSystem carSys, JPanel dest)
 *
 * // Methods
 *    public void actionPerformed(ActionEvent ev)
 *
 * COLLABORATORS:
 *    CarDetailComponents
 *
 * @version 1.0, 16 Oct 2004
 * @author Adam Black
 */
public class SearchByOtherPanel extends JPanel implements ActionListener
{
	private final String[] price = {"5001-10000", "10001-15000", "15001-20000", "20001-50000",
		"50001-100000", "100001-200000", "200001-300000", "300001+"};
	private final String[] distance = {"0", "1-10000", "10001-20000", "20001-30000", "30001-40000",
		"40001-50000", "50001-80000", "80001-100000", "100001-200000", "200001-300000", "300001+"};
	private Car[] carList;
	private CarSalesSystem carSystem;
	private int currentIndex = 0;
	private JButton searchButton = new JButton("Search");
	private JButton resetButton = new JButton("Reset");
	private JButton previousButton = new JButton("Previous");
	private JButton nextButton = new JButton("Next");
	private JComboBox priceCombo = new JComboBox(price);
	private JComboBox distanceCombo = new JComboBox(distance);
	private CarDetailsComponents carComponents = new CarDetailsComponents();

	/**
	 * @param carSys links to a CarSalesSystem object
     */
	public SearchByOtherPanel(CarSalesSystem carSys)
	{
		carSystem = carSys;
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

		previousButton.addActionListener(this);
		nextButton.addActionListener(this);
		resetButton.addActionListener(this);
		searchButton.addActionListener(this);

		JLabel priceLabel = new JLabel("Price");
		JPanel pricePanel = new JPanel();
		pricePanel.add(priceLabel);
		pricePanel.add(priceCombo);
		JLabel distanceLabel = new JLabel("Distance traveled");
		JPanel distancePanel = new JPanel();
		distancePanel.add(distanceLabel);
		distancePanel.add(distanceCombo);
		JPanel priceDistancePanel = new JPanel();
		priceDistancePanel.add(pricePanel);
		priceDistancePanel.add(distancePanel);

		JPanel searchButtonsPanel = new JPanel();
		searchButtonsPanel.add(searchButton);
		searchButtonsPanel.add(resetButton);
		JPanel navigateButtonsPanel = new JPanel();
		navigateButtonsPanel.add(previousButton);
		navigateButtonsPanel.add(nextButton);

		JLabel headingLabel = new JLabel("Search on Price and Distance Traveled");
		headingLabel.setAlignmentX(0.5f);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(headingLabel);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(priceDistancePanel);
		topPanel.add(searchButtonsPanel);
		topPanel.add(Box.createVerticalStrut(15));
		carComponents.add(navigateButtonsPanel, "Center");
		carComponents.setVisible(false);

		add(topPanel, "North");
		add(carComponents, "Center");
	}

	/**
	 * check for button clicks
	 *
	 * @param ev ActionEvent object
	 */
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == searchButton)
			searchButtonClicked();
		else if (ev.getSource() == resetButton)
			resetButtonClicked();
		else if (ev.getSource() == previousButton)
			previousButtonClicked();
		else if (ev.getSource() == nextButton)
			nextButtonClicked();
	}

	/**
	 * get next index if it exists, and display it visually using CarDetailsComponents
	 */
	private void nextButtonClicked()
	{
		if (currentIndex < carList.length - 1)
		{
			currentIndex++;
			carComponents.displayDetails(carList[currentIndex]);
		}
		else
			JOptionPane.showMessageDialog(carSystem, "You can't navigate any further", "Alert", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * get previous index if it exists, and display it visually using CarDetailsComponents
	 */
	private void previousButtonClicked()
	{
		if (currentIndex > 0)
		{
			currentIndex--;
			carComponents.displayDetails(carList[currentIndex]);
		}
		else
			JOptionPane.showMessageDialog(carSystem, "You can't navigate any further", "Alert", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * clear search results, begin next search from scratch
	 */
	private void resetButtonClicked()
	{
		currentIndex = 0;
		carList = null;
		carComponents.setVisible(false);
		priceCombo.setSelectedIndex(0);
		distanceCombo.setSelectedIndex(0);
	}

	/**
	 * search cars based on price and distance travelled
	 */
	private void searchButtonClicked()
	{
		// convert distance and price combo box text into a range
		double[] distanceRange = CarSalesSystem.convertToRange((String)distanceCombo.getSelectedItem());
		double[] priceRange = CarSalesSystem.convertToRange((String)priceCombo.getSelectedItem());

		if (priceRange[0] >= 0 && distanceRange[0] >= 0)
		{
			carList = carSystem.search((int)priceRange[0], (int)priceRange[1], distanceRange[0], distanceRange[1]);
		}

		if (carList.length > 0)
		{
			currentIndex = 0;
			carComponents.setVisible(true);
			carComponents.displayDetails(carList[0]);

			if (carList.length == 1)
			{
				nextButton.setEnabled(false);
				previousButton.setEnabled(false);
			}
			else
			{
				nextButton.setEnabled(true);
				previousButton.setEnabled(true);
			}

			carSystem.repaint();
		}
		else
			JOptionPane.showMessageDialog(carSystem, "Sorry, no search results were returned", "Search failed", JOptionPane.WARNING_MESSAGE);
	}
}