

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Memory extends JFrame {

	public Memory() {
		var iconNames = new ArrayList<String>();
		addIcons(iconNames);
		addIcons(iconNames);

		var random = new Random();
		var menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		var datei = new JMenu("Datei");
		menuBar.add(datei);
		var renewIcons = new JMenuItem("Neu...");
		datei.add(renewIcons);

		var panel = new JPanel(new GridBagLayout());
		add(panel);

		addIconCards(random, panel, iconNames);

		renewIcons.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addIcons(iconNames);
				addIcons(iconNames);
				Component[] allComponents = panel.getComponents();
				for (Component component : allComponents) {
					JButton button = ((JButton) component);
					String iconName = iconNames.remove(random.nextInt(iconNames.size()));
					button.setIcon(new ImageIcon("img/" + iconName + ".png"));
				}
			}
		});
		setSize(700, 700);
		setVisible(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}

	private static void addIcons(ArrayList<String> iconNames) {
		iconNames.add("cloud");
		iconNames.add("hexagon");
		iconNames.add("smiley");
		iconNames.add("heart");
		iconNames.add("circle");
		iconNames.add("triangle");
		iconNames.add("star6");
		iconNames.add("flower");
	}

	private void addIconCards(Random random, JPanel panel, ArrayList<String> iconNames) {
		var c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		var backIcon = new ImageIcon("img/back.png");
		for (int i = 0; i <= 3; i++) {
			c.gridy = i;
			for (int k = 0; k <= 3; k++) {
				String iconName = iconNames.remove(random.nextInt(iconNames.size()));
				var icon = new ImageIcon("img/" + iconName + ".png");
				var button = new JButton(icon);
				button.addActionListener(new AbstractAction() {
					ImageIcon frontIcon;

					@Override
					public void actionPerformed(ActionEvent e) {
						if (button.getIcon().equals(backIcon)) {
							button.setIcon(frontIcon);
						} else {
							frontIcon = (ImageIcon) button.getIcon();
							button.setIcon(backIcon);
						}
					}
				});
				panel.add(button, c);
			}
		}
	}

	public static void main(String[] args) {
		new Memory();
	}
}
