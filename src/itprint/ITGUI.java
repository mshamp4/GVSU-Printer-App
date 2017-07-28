package itprint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
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

	private ImageIcon settingsIcon = new ImageIcon("src/settingsIcon.png");

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

	private void printerInformation(String prntName) {
		int count = 0;
		while (!prntName.equals(pReport.getPrinters().get(count).getPrntName())) {
			count++;
		}

		Printer currentPrnt = pReport.getPrinters().get(count);
		ImageIcon icon = getStatusIcon(currentPrnt.status());

		JTabbedPane prntPane = new JTabbedPane();
		JComponent info = makeInfoPanel(currentPrnt.toString());
		JComponent edit = makeSettingsPanel(currentPrnt);
		prntPane.addTab("Information", icon, info);
		prntPane.addTab("Update Printer", settingsIcon, edit);
		JOptionPane.showMessageDialog(null, prntPane, "Printer - " + prntName, JOptionPane.PLAIN_MESSAGE);

	}

	// difference between private and protected
	private JComponent makeInfoPanel(String prntStatus) {
		JPanel panel = new JPanel(false);
		JLabel info = getPrinterStatus(prntStatus);
		info.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridBagLayout());
		panel.add(info);
		return panel;
	}

	private JComponent makeSettingsPanel(Printer currentPrnt) {
		JLabel dPrnt = new JLabel("Direct Print");
		JLabel gvPrnt = new JLabel("GV Print");
		JRadioButton enabledD = new JRadioButton("Enabled");
		JRadioButton enabledG = new JRadioButton("Enabled");
		JRadioButton disabledD = new JRadioButton("Disabled");
		JRadioButton disabledG = new JRadioButton("Disabled");
		ButtonGroup dPrntGroup = new ButtonGroup();
		ButtonGroup gPrntGroup = new ButtonGroup();
		dPrntGroup.add(enabledD);
		dPrntGroup.add(disabledD);
		gPrntGroup.add(enabledG);
		gPrntGroup.add(disabledG);
		
		if (currentPrnt.isDirectPrint()) {
			enabledD.setSelected(true);
		} else {
			disabledD.setSelected(true);
		}
		if (currentPrnt.isGvPrint()) {
			enabledG.setSelected(true);
		} else {
			disabledG.setSelected(true);
		}
		ArrayList<JRadioButton> rdoBttn = new ArrayList<JRadioButton>();
		rdoBttn.add(enabledD);
		rdoBttn.add(enabledG);
		rdoBttn.add(disabledD);
		rdoBttn.add(disabledG);
		dPrnt.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		gvPrnt.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		Box prntBox = Box.createVerticalBox();
		prntBox.add(Box.createRigidArea(new Dimension(0, 12)));
		prntBox.add(dPrnt);
		prntBox.add(Box.createVerticalStrut(10));
		prntBox.add(gvPrnt);
		JPanel rdoBttns = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		int count = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				c.gridx = i;
				c.gridy = j;
				rdoBttns.add(rdoBttn.get(count), c);
				count ++;
			}
		}
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(prntBox, BorderLayout.LINE_START);
		panel.add(rdoBttns, BorderLayout.CENTER);
		return panel;
	}

	private JLabel getPrinterStatus(String status) {
		String temp = "<html>" + status;
		temp = temp.concat("<html>");
		temp = temp.replaceAll("[\\t\\n\\r]", "<br>");
		JLabel info = new JLabel(temp);
		return info;
	}

	private ImageIcon getStatusIcon(boolean status) {
		ImageIcon temp = checkmark;
		if (!status) {
			temp = disabledMark;
		}
		return temp;
	}

	// private void printerInfo(String prntName) {
	// int count = 0;
	// // ImageIcon temp = checkmark;
	// while (!prntName.equals(pReport.getPrinters().get(count).getPrntName()))
	// {
	// count++;
	// }
	// Printer currentPrnt = pReport.getPrinters().get(count);
	//
	// // if (!pReport.getPrinters().get(count).status()) {
	// // temp = disabledMark;
	// // }
	//
	// JOptionPane.showMessageDialog(null, currentPrnt.toString(), "Printer - "
	// + prntName,
	// JOptionPane.INFORMATION_MESSAGE, getStatusIcon(currentPrnt.status()));
	// }

	@Override
	public void actionPerformed(final ActionEvent e) {
		// printerInfo(e.getActionCommand());
		printerInformation(e.getActionCommand());
	}
}
