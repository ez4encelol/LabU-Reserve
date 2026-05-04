package statsVisualiser.gui;

import model.Equipment;
import model.IdGeneration;
import model.Reservation;
import model.database.DatabaseService;
import model.database.EquipmentTable;
import model.database.ReservationTable;
import model.database.UserTable;
import model.enums.EquipmentStatus;
import model.enums.ReservationStatus;
import model.enums.UserType;
import model.exceptions.EmailNotUniqueException;
import model.exceptions.WeakPasswordException;
import model.paymentclasses.CreditPayment;
import model.paymentclasses.DebitPayment;
import model.paymentclasses.PaymentStrategy;
import model.paymentclasses.ResearchGrantPayment;
import model.systemFacades.EquipmentManagementFacade;
import model.systemFacades.EquipmentReservationFacade;
import model.systemFacades.LoginAndRegistrationFacade;
import model.userhierarchy.User;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

public class MainUI extends JFrame {

	private LoginAndRegistrationFacade loginFacade = new LoginAndRegistrationFacade();

	private static MainUI instance;
	public static MainUI getInstance() {
		if (instance == null) {
			instance = new MainUI();
		}
		return instance;
	}

	MainUI() {
		super("Lab Equipment Reservation System");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(580, 550);
		setLocationRelativeTo(null);
		setResizable(false);

		// window icon
		ImageIcon icon = UITheme.loadIcon("/model/assets/reservationIcon.png", 32, 32);
		if (icon != null) {
			setIconImage(icon.getImage());
		}

		UserTable.getInstance();
		EquipmentTable.getInstance();
		ReservationTable.getInstance();
		setContentPane(createLoginRegisterPanel());
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(
						MainUI.this,
						"Are you sure you want to exit?",
						"Confirm Exit",
						JOptionPane.YES_NO_OPTION
						);
				if (result == JOptionPane.YES_OPTION) {
					DatabaseService.getInstance().updateAllTables(); //updates when close manager window
					dispose();
				}
			}
		});
	}

	//  Icon loader helper
	static ImageIcon loadIcon(String path, int w, int h) {
		try {
			java.io.InputStream is = MainUI.class.getResourceAsStream(path);
			if (is == null) {
				return null;
			}
			ImageIcon raw = new ImageIcon(is.readAllBytes());
			return new ImageIcon(raw.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
		} catch (Exception e) {
			return null;
		}
	}

	//  Login / Register panel
	private JPanel createLoginRegisterPanel() {

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setBackground(UITheme.BG_DARK);
		wrapper.setBorder(BorderFactory.createEmptyBorder(16, 24, 16, 24));

		// header
		JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 4));
		header.setBackground(UITheme.BG_DARK);

		ImageIcon logo = MainUI.loadIcon("/model/assets/Logo.png", 40, 40);

		JLabel titleLbl = new JLabel("LabU Reserve", logo, JLabel.LEFT);
		titleLbl.setFont(UITheme.FONT_TITLE);
		titleLbl.setForeground(UITheme.ACCENT);

		JLabel subtitleLbl = new JLabel("Equipment System");
		subtitleLbl.setFont(UITheme.FONT_SMALL);
		subtitleLbl.setForeground(UITheme.TEXT_MUTED);

		header.add(titleLbl);
		header.add(subtitleLbl);

		// tab bar
		JButton tabLogin = tabButton("Login", true);
		JButton tabRegister = tabButton("Register", false);
		JPanel tabBar = new JPanel(new GridLayout(1, 2, 4, 0));
		tabBar.setBackground(UITheme.BG_DARK);
		tabBar.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
		tabBar.add(tabLogin);
		tabBar.add(tabRegister);

		JPanel topSection = new JPanel(new BorderLayout());
		topSection.setBackground(UITheme.BG_DARK);
		topSection.add(header, BorderLayout.NORTH);
		topSection.add(tabBar, BorderLayout.SOUTH);

		// card panel
		CardLayout cl = new CardLayout();
		JPanel cards = new JPanel(cl);
		cards.setBackground(UITheme.BG_DARK);

		// icons
		ImageIcon mailIco = loadIcon("/model/assets/mail.png", 18, 18);
		ImageIcon passIco = loadIcon("/model/assets/pass.png", 18, 18);
		ImageIcon userIco = loadIcon("/model/assets/user.png", 18, 18);
		ImageIcon loginIco = loadIcon("/model/assets/user2.png", 150, 150);

		// LOGIN form
		JPanel loginCard = darkCard();
		loginCard.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(6, 8, 6, 8);
		gc.fill = GridBagConstraints.HORIZONTAL;

		JTextField lfEmail = darkField();
		JPasswordField lfPass = darkPassword();
		JButton btnLogin = accentButton("Login", UITheme.ACCENT2);

		gc.gridx = 0; gc.gridy = 1; gc.gridwidth = 3;
		gc.anchor = GridBagConstraints.CENTER; gc.weightx = 1.0;
		loginCard.add(new JLabel(loginIco), gc);

		gc.gridy = 2; gc.gridx = 0;
		gc.gridwidth = 1; gc.weightx = 0;
		loginCard.add(iconLabel(mailIco), gc);


		gc.gridx = 1; gc.gridwidth = 2; 
		gc.weightx = 1.0; gc.fill = GridBagConstraints.HORIZONTAL;
		loginCard.add(lfEmail, gc);
		loginCard.add(fieldRow("Email", lfEmail), gc);

		gc.gridy = 3; gc.gridx = 0; 
		gc.gridwidth = 1; gc.weightx = 0;
		loginCard.add(iconLabel(passIco), gc);

		gc.gridx = 1; gc.gridwidth = 2; gc.weightx = 1.0;
		loginCard.add(lfPass, gc);
		loginCard.add(fieldRow("Password", lfPass), gc);

		gc.gridy = 4; gc.gridx = 0;
		gc.gridwidth = 3; gc.anchor = GridBagConstraints.CENTER;
		loginCard.add(btnLogin, gc);

		// register panel
		JPanel regCard = darkCard();
		regCard.setLayout(new GridBagLayout());
		GridBagConstraints gr = new GridBagConstraints();
		gr.insets = new Insets(5, 8, 5, 8);
		gr.fill = GridBagConstraints.HORIZONTAL;

		Vector<String> types = new Vector<>();
		types.add("Student");
		types.add("Faculty");
		types.add("Researcher");
		types.add("Guest");
		types.add("LabManager");

		JComboBox<String> typeBox = darkCombo(types);
		JTextField rfEmail = darkField();
		JTextField rfUsername = darkField();
		JPasswordField rfPass = darkPassword();
		JTextField rfId = darkField();
		JButton btnReg = accentButton("Create Account", UITheme.BTN_GREEN);

		gr.gridx=0; gr.gridy=0; gr.weightx=0;
		regCard.add(iconLabel(userIco), gr);
		gr.gridx=1; gr.weightx=1;
		regCard.add(fieldRow("Account Type", typeBox), gr);

		gr.gridx=0; gr.gridy=1; gr.weightx=0;
		regCard.add(iconLabel(mailIco), gr);
		gr.gridx=1; gr.weightx=1;
		regCard.add(fieldRow("Email", rfEmail), gr);

		gr.gridx=0; gr.gridy=2; gr.weightx=0;
		regCard.add(iconLabel(userIco), gr);
		gr.gridx=1; gr.weightx=1;
		regCard.add(fieldRow("Username", rfUsername), gr);

		gr.gridx=0; gr.gridy=3; gr.weightx=0;
		regCard.add(iconLabel(passIco), gr);
		gr.gridx=1; gr.weightx=1;
		regCard.add(fieldRow("Password", rfPass), gr);

		gr.gridx=0; gr.gridy=4; gr.weightx=0;
		regCard.add(iconLabel(userIco), gr);
		gr.gridx=1; gr.weightx=1;
		regCard.add(fieldRow("Student / Staff ID or Cert #", rfId), gr);

		gr.gridx=0; gr.gridy=5; gr.gridwidth=2; gr.weightx=1;
		regCard.add(btnReg, gr);

		cards.add(loginCard, "LOGIN");
		cards.add(regCard, "REGISTER");
		cl.show(cards, "LOGIN");

		// tab switching
		tabLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(cards, "LOGIN");
				setTabActive(tabLogin, tabRegister);
			}
		});
		tabRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(cards, "REGISTER");
				setTabActive(tabRegister, tabLogin);
			}
		});

		// Login action
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = lfEmail.getText().trim();
				String pass = new String(lfPass.getPassword()).trim();

				List<User> users = UserTable.getInstance().getUsersAsList();
				for (int i = 0; i < users.size(); i++) {
					User u = users.get(i);
					if(u.getUserType().equals(UserType.LABMANAGER) && u.getEmail().equals(email) && u.getPassword().equals(pass)) {
						dispose();
						new ManagerWindow().setVisible(true);
						return;
					}
					if (!(u.getUserType().equals(UserType.LABMANAGER)) && u.getEmail().equals(email) && u.getPassword().equals(pass)) {
						dispose();
						new UserWindow(u).setVisible(true);
						return;
					}
				}
				UITheme.showPopup("Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
			}
		});

		// Register action
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = rfEmail.getText().trim();
				String username = rfUsername.getText().trim();
				String pass = new String(rfPass.getPassword()).trim();
				String idStr = rfId.getText().trim();
				String type = typeBox.getSelectedItem().toString().toUpperCase();
				if (email.isBlank() || pass.isBlank() || idStr.isBlank()) {
					UITheme.showPopup("Please fill in all fields.", "Register", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (!email.contains("@")) {
					UITheme.showPopup("Invalid Email.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int verNum;
				try { 
					verNum = Integer.parseInt(idStr);
				}
				catch (NumberFormatException ex) {
					UITheme.showPopup("Student/Staff ID must be a number.", "Register", JOptionPane.ERROR_MESSAGE);
					return;
				}
				UserType ut = UserType.valueOf(type);
				try {
					loginFacade.registerUser(email, username, pass, verNum, ut);
					List<User> users = UserTable.getInstance().getUsersAsList();
					for (int i = 0; i < users.size(); i++) {
						User u = users.get(i);
						if (u.getUsername().equals(email)) {
							UserTable.getInstance().update();
							break;
						}
					}
					UITheme.showPopup("Account created! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
					cl.show(cards, "LOGIN");
					setTabActive(tabLogin, tabRegister);
				} 
				catch (EmailNotUniqueException e1) {
					UITheme.showPopup("Email already exists.", "Register Failed", JOptionPane.ERROR_MESSAGE);
				}
				catch (WeakPasswordException e2) {
					UITheme.showPopup("Password too weak.\nUse uppercase, numbers, and symbols.", "Register Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		wrapper.add(topSection, BorderLayout.NORTH);
		wrapper.add(cards, BorderLayout.CENTER);
		return wrapper;
	}

	// style helpers
	static JPanel darkCard() {
		JPanel p = new JPanel();
		p.setBackground(UITheme.BG_CARD);
		p.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(UITheme.BORDER_CLR, 1, true),
				BorderFactory.createEmptyBorder(14, 14, 14, 14)));
		return p;
	}

	static JTextField darkField() {
		JTextField tf = new JTextField();
		tf.setFont(UITheme.FONT_BODY);
		tf.setBackground(UITheme.BG_INPUT);
		tf.setForeground(UITheme.TEXT_WHITE);
		tf.setCaretColor(UITheme.TEXT_WHITE);
		tf.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(UITheme.BORDER_CLR, 1, true),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)));
		return tf;
	}

	static JPasswordField darkPassword() {
		JPasswordField pf = new JPasswordField();
		pf.setFont(UITheme.FONT_BODY);
		pf.setBackground(UITheme.BG_INPUT);
		pf.setForeground(UITheme.TEXT_WHITE);
		pf.setCaretColor(UITheme.TEXT_WHITE);
		pf.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(UITheme.BORDER_CLR, 1, true),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)));
		return pf;
	}

	static JComboBox<String> darkCombo(Vector<String> items) {
		JComboBox<String> cb = new JComboBox<>(items);
		cb.setFont(UITheme.FONT_BODY);
		cb.setBackground(UITheme.BG_INPUT);
		cb.setForeground(UITheme.TEXT_WHITE);
		return cb;
	}

	static JButton accentButton(String text, Color color) {
		JButton b = new JButton(text);
		b.setFont(UITheme.FONT_BTN);
		b.setBackground(color);
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setBorderPainted(false);
		b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				b.setBackground(color.brighter());
			}
			public void mouseExited (MouseEvent e) {
				b.setBackground(color);
			}
		});
		return b;
	}

	static JButton tabButton(String text, boolean active) {
		JButton b = new JButton(text);
		b.setFont(UITheme.FONT_BTN);
		b.setFocusPainted(false);
		b.setBorderPainted(false);
		b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		if (active) {
			b.setBackground(UITheme.ACCENT);
			b.setForeground(Color.WHITE);
		}
		else {
			b.setBackground(UITheme.BG_CARD);
			b.setForeground(UITheme.TEXT_MUTED);
		}
		return b;
	}

	static void setTabActive(JButton on, JButton off) {
		on.setBackground(UITheme.ACCENT);
		on.setForeground(Color.WHITE);
		off.setBackground(UITheme.BG_CARD);
		off.setForeground(UITheme.TEXT_MUTED);
	}

	static JLabel iconLabel(ImageIcon ico) {
		JLabel label;
		if (ico != null) {
			// If an icon exists, show it
			label = new JLabel(ico);
		} else {
			// If no icon use a small blank placeholder to keep layout spacing
			label = new JLabel("  ");
		}
		return label;
	}

	// wraps a label + component in a small vertical stack
	static JPanel fieldRow(String labelText, JComponent field) {
		JPanel p = new JPanel(new BorderLayout(0, 2));
		p.setBackground(UITheme.BG_CARD);
		JLabel l = new JLabel(labelText);
		l.setFont(UITheme.FONT_SMALL);
		l.setForeground(UITheme.TEXT_MUTED);
		p.add(l, BorderLayout.NORTH);
		p.add(field, BorderLayout.CENTER);
		return p;
	}

	// top bar for logout for manager and user (logout button for both so no repeat code)
	static JPanel topBar(String roleText, ActionListener onLogout) {
		JPanel bar = new JPanel(new BorderLayout());
		bar.setBackground(new Color(20, 22, 35));
		bar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, UITheme.BORDER_CLR),
				BorderFactory.createEmptyBorder(8, 16, 8, 16)));

		JLabel role = new JLabel("LabReserve  —  " + roleText);
		role.setFont(UITheme.FONT_HEADER);
		role.setForeground(UITheme.ACCENT);
		bar.add(role, BorderLayout.WEST);

		JButton btnLogout = accentButton("Logout", UITheme.BTN_RED);
		btnLogout.setPreferredSize(new Dimension(90, 30));
		btnLogout.addActionListener(onLogout);
		JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		right.setBackground(new Color(20, 22, 35));
		right.add(btnLogout);
		bar.add(right, BorderLayout.EAST);
		return bar;
	}

	//  Main
	public static void main(String[] args) {
		new MainUI();
	}
}
