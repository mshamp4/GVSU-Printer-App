package itprint;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Description goes here
 * 
 * @author Matthew Shampine
 * @version 1.0
 */
public class IT {

	/** Creates a menu at the top of the GUI. */
	private static JMenuBar menuBar;

	/** For a tab 'menu' in menuBar. */
	private static JMenu menu;

	private static JMenu menuRoom;

	private static JMenuItem menuDEV;

	private static JMenuItem menuCHS;

	private static JMenuItem menuEC;

	private static JMenuItem menuSCB;

	/** An item in the menu to exit the app. */
	private static JMenuItem menuExit;

	private static ITGUI board;

	/** A JFrame to add elements to. */
	private static JFrame frame;

	// change this code later
	private static ImageIcon mobileIcon = new ImageIcon("src/mobileIcon.png");

	/**
	 * Description goes here.
	 * 
	 */
	public static void main(final String[] args) {
		board = new ITGUI("DEVOS");
		frame = new JFrame("GVSU IT Print");
		// frame.setSize(600, 600);
		frame.setResizable(false);
		frame.add(board);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(mobileIcon.getImage());
		// frame.setLocationRelativeTo(null);

		/**
		 * This class is used for the different menu options.
		 * 
		 * @author Matthew Shampine
		 */
		class MenuActionListener implements ActionListener {

			@Override
			/**
			 * Performs an action based on a menu selection.
			 * 
			 */
			public void actionPerformed(final ActionEvent e) {
				if (e.getSource() == menuDEV) {
					board.removePrinters();
					board.changeRoom("DEVOS");
				}
				if (e.getSource() == menuCHS) {
					board.removePrinters();
					board.changeRoom("CHS");
				}
				if (e.getSource() == menuEC) {
					board.removePrinters();
					board.changeRoom("EC");
				}
				if (e.getSource() == menuSCB) {
					board.removePrinters();
					board.changeRoom("SCB");
				}
				if (e.getSource() == menuExit) {
					System.exit(0);
				}
			}
		}

		menuBar = new JMenuBar();
		menu = new JMenu("Settings");
		menu.setPreferredSize(new Dimension(102, 20));
		menuBar.add(menu);

		menuRoom = new JMenu("Change Room");
		menuRoom.addActionListener(new MenuActionListener());
		menu.add(menuRoom);

		menuDEV = new JMenuItem("DEVOS");
		menuDEV.addActionListener(new MenuActionListener());
		menuRoom.add(menuDEV);

		menuRoom.addSeparator();
		menuCHS = new JMenuItem("CHS");
		menuCHS.addActionListener(new MenuActionListener());
		menuRoom.add(menuCHS);

		menuRoom.addSeparator();
		menuEC = new JMenuItem("EC");
		menuEC.addActionListener(new MenuActionListener());
		menuRoom.add(menuEC);

		menuRoom.addSeparator();
		menuSCB = new JMenuItem("SCB");
		menuSCB.addActionListener(new MenuActionListener());
		menuRoom.add(menuSCB);

		menu.addSeparator();
		menuExit = new JMenuItem("Exit");
		menuExit.addActionListener(new MenuActionListener());
		menu.add(menuExit);

		frame.setJMenuBar(menuBar);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
