package itprint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ITGUI extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7814810129525846626L;

	private JLabel logoLabel;

	private JPanel bottom;

	private PrinterReport pReport;

	private JTextField room;

	private ImageIcon printerIcon = new ImageIcon("src/printerIcon.png");

	private ImageIcon disabledIcon = new ImageIcon("src/disabledIcon.png");

	private ImageIcon checkmark = new ImageIcon("src/checkmark.png");

	private ImageIcon disabledMark = new ImageIcon("src/disabledMark.png");

	private Box box;

	public ITGUI(String prntName) {
		pReport = new PrinterReport(prntName);
		box = Box.createVerticalBox();
		setBackground(Color.lightGray);
		add(box);

		try {
			BufferedImage gvLogo = ImageIO.read(new File("src/gvsuLogo.png"));
			logoLabel = new JLabel(new ImageIcon(gvLogo));
			logoLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			box.add(logoLabel);
			box.add(Box.createVerticalStrut(12));
		} catch (IOException e) {
			box.add(Box.createVerticalStrut(12));
			// e.printStackTrace();
			// work on this catch statement
		}

		Font textFont = new Font("Verdana", Font.BOLD, 22);

		room = new JTextField(prntName);
		room.setEditable(false);
		room.setForeground(Color.BLACK);
		room.setBackground(Color.lightGray);
		room.setBorder(null);
		room.setFont(textFont);
		room.setMaximumSize(room.getPreferredSize());
		room.setAlignmentX(JTextField.CENTER_ALIGNMENT);
		room.setHorizontalAlignment(JTextField.CENTER);
		box.add(room);
		bottom = new JPanel(new GridBagLayout());
		bottom.setBackground(Color.lightGray);
		addPrinters(pReport.getPrinters().size());
	}

	private void addPrinters(int numPrinters) {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(15, 20, 10, 20);

		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (numPrinters == count) {
					break;
				}
				JButton printer = new JButton(pReport.getPrinters().get(count).getPrntName());
				printer.addActionListener(this);
				if (pReport.getPrinters().get(count).status()) {
					printer.setIcon(printerIcon);
				} else {
					printer.setIcon(disabledIcon);
				}
				printer.setBackground(Color.lightGray);
				printer.setBorder(null);
				c.gridx = j;
				c.gridy = i;
				bottom.add(printer, c);
				count++;
			}
		}
		box.add(bottom, BorderLayout.SOUTH);
	}

	public int changeRoom(String room) {
		if (room == null || room.length() < 2) {
			return -1;
		} else {
			pReport = new PrinterReport(room);
			addPrinters(pReport.getPrinters().size());
			this.room.setText(room);
		}
		return 1;
	}

	public void removePrinters() {
		bottom.removeAll();
		bottom.revalidate();
		bottom.repaint();
	}

	private void printerInfo(String prntName) {
		int count = 0;
		while (!prntName.equals(pReport.getPrinters().get(count).getPrntName())) {
			count++;
		}
		if (pReport.getPrinters().get(count).status()) {
			JOptionPane.showMessageDialog(null, pReport.getPrinters().get(count).toString(), "Printer - " + prntName,
					JOptionPane.INFORMATION_MESSAGE, checkmark);
		} else {
			JOptionPane.showMessageDialog(null, pReport.getPrinters().get(count).toString(), "Printer - " + prntName,
					JOptionPane.INFORMATION_MESSAGE, disabledMark);
		}
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		printerInfo(e.getActionCommand());
	}
}
