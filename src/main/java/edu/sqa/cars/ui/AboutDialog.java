package edu.sqa.cars.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.JLabel.CENTER;

/**
 * A simple about dialog.
 * @
 *
 * PUBLIC FEATURES:
 * // Constructors
 *    public AboutDialog(JFrame parent, String title, boolean modal)
 *
 * // Methods
 *    public void actionPerformed(ActionEvent ev)
 *    public void closing()
 *    public void showAbout()
 *    public int addNewCar(Car c)
 *    public void closing()
 *
 * COLLABORATORS:
 *
 * @version 1.0, 16 Oct 2004
 * @author Adam Black
 */
public class AboutDialog extends JDialog implements ActionListener
{
	private JButton okButton = new JButton("OK");
	private transient WindowCloser closer = new WindowCloser();

	/**
	 * @param parent a parent JFrame to place the dialog on top of
	 * @param title title of the about dialog
	 * @param modal modal means focus cannot be taken away from the dialog, non-modal means it can.
	 */
	public AboutDialog(JFrame parent, String title, boolean modal)
	{
		super(parent, title, modal);
		Container c = getContentPane();

		setSize(480, 130);
		setLocationRelativeTo(parent);
		addWindowListener(closer);
		c.setLayout(new GridLayout(3, 1));
		setTitle(title);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		c.add(new JLabel("Cars Sales System by Adam Black", CENTER));
		c.add(new JLabel("SD2, Assignment 2", CENTER));
		c.add(buttonPanel);
		okButton.addActionListener(this);
	}

	/**
	 * ok button clicked so call the closing method
	 *
	 * @param ev ActionEvent object
	 */
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == okButton)
			closing();
	}

	/**
	 * Hides for from focus, so it can be made visible without needing a new instance
	 */
	public void closing()
	{
		setVisible(false);
	}

	/**
	 * default method to show about dialog. This ensures it is centred on the parent form. Please
	 * ensure you do not use the setVisible() method directly, use this one instead.
	 */
	public void showAbout()
	{
		// center the dialog relative to the parent frame.
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

	class WindowCloser extends WindowAdapter
	{
		/**
		 * calls the car sales system's main closing event
		 *
		 * @param ev WindowEvent object
		 */
		@Override
		public void windowClosing(WindowEvent ev)
		{
			closing();
		}
	}
}