package statsVisualiser.gui;

import model.database.DatabaseService;
import model.database.EquipmentTable;
import model.database.ReservationTable;
import model.database.UserTable;
import model.enums.UserType;
import model.exceptions.EmailNotUniqueException;
import model.exceptions.WeakPasswordException;
import model.systemFacades.LoginAndRegistrationFacade;
import model.userhierarchy.User;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
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
		super("LabU Reserve");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(580, 640);
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
					DatabaseService.getInstance().updateAllTables();
					dispose();
				}
			}
		});
	}

	//  Icon loader helper (kept for callers in other windows)
	static ImageIcon loadIcon(String path, int w, int h) {
		return UITheme.loadIcon(path, w, h);
	}

	//  Login / Register panel
	private JPanel createLoginRegisterPanel() {

		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setBackground(UITheme.BG_DARK);
		wrapper.setBorder(BorderFactory.createEmptyBorder(16, 28, 16, 28));

		// header
		JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 4));
		header.setBackground(UITheme.BG_DARK);

		ImageIcon logo = MainUI.loadIcon("/model/assets/Logo.png", 40, 40);

		JLabel titleLbl = new JLabel("LabU Reserve", logo, JLabel.LEFT);
		titleLbl.setFont(UITheme.FONT_TITLE);
		titleLbl.setForeground(UITheme.ACCENT);

		JLabel subtitleLbl = new JLabel("Equipment Reservation System");
		subtitleLbl.setFont(UITheme.FONT_SMALL);
		subtitleLbl.setForeground(UITheme.TEXT_MUTED);

		header.add(titleLbl);
		header.add(subtitleLbl);

		// tab bar – pill-shaped Login / Register
		JButton tabLogin = tabButton("Login", true);
		JButton tabRegister = tabButton("Register", false);
		JPanel tabBar = new JPanel(new GridLayout(1, 2, 12, 0));
		tabBar.setBackground(UITheme.BG_DARK);
		tabBar.setBorder(BorderFactory.createEmptyBorder(8, 0, 10, 0));
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
		ImageIcon loginIco = loadIcon("/model/assets/user2.png", 120, 120);

		// ── LOGIN form ────────────────────────────────────────────────
		JPanel loginCard = darkCard();
		loginCard.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(8, 8, 8, 8);
		gc.fill = GridBagConstraints.HORIZONTAL;

		JTextField lfEmail = darkField();
		JPasswordField lfPass = darkPassword();
		JButton btnLogin = accentButton("Sign In", UITheme.ACCENT);

		gc.gridx = 0; gc.gridy = 0; gc.gridwidth = 3;
		gc.anchor = GridBagConstraints.CENTER; gc.weightx = 1.0;
		gc.insets = new Insets(4, 8, 12, 8);
		loginCard.add(new JLabel(loginIco), gc);

		gc.gridy = 1; gc.gridx = 0;
		gc.gridwidth = 1; gc.weightx = 0;
		gc.insets = new Insets(8, 8, 8, 8);
		gc.anchor = GridBagConstraints.CENTER;
		loginCard.add(iconLabel(mailIco), gc);

		gc.gridx = 1; gc.gridwidth = 2;
		gc.weightx = 1.0; gc.fill = GridBagConstraints.HORIZONTAL;
		loginCard.add(fieldRow("Email", lfEmail), gc);

		gc.gridy = 2; gc.gridx = 0;
		gc.gridwidth = 1; gc.weightx = 0;
		loginCard.add(iconLabel(passIco), gc);

		gc.gridx = 1; gc.gridwidth = 2; gc.weightx = 1.0;
		loginCard.add(fieldRow("Password", lfPass), gc);

		gc.gridy = 3; gc.gridx = 0;
		gc.gridwidth = 3; gc.weightx = 1.0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(18, 8, 8, 8);
		loginCard.add(btnLogin, gc);

		// ── REGISTER form ─────────────────────────────────────────────
		JPanel regCard = darkCard();
		regCard.setLayout(new GridBagLayout());
		GridBagConstraints gr = new GridBagConstraints();
		gr.insets = new Insets(8, 8, 8, 8);
		gr.fill = GridBagConstraints.HORIZONTAL;
		gr.anchor = GridBagConstraints.CENTER;

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
		JButton btnReg = accentButton("Create Account", UITheme.ACCENT);

		// Row 0 – Account Type
		gr.gridx = 0; gr.gridy = 0; gr.gridwidth = 1; gr.weightx = 0;
		regCard.add(iconLabel(userIco), gr);
		gr.gridx = 1; gr.weightx = 1.0; gr.gridwidth = 1;
		regCard.add(fieldRow("Account Type", typeBox), gr);

		// Row 1 – Email
		gr.gridx = 0; gr.gridy = 1; gr.weightx = 0; gr.gridwidth = 1;
		regCard.add(iconLabel(mailIco), gr);
		gr.gridx = 1; gr.weightx = 1.0;
		regCard.add(fieldRow("Email", rfEmail), gr);

		// Row 2 – Username
		gr.gridx = 0; gr.gridy = 2; gr.weightx = 0;
		regCard.add(iconLabel(userIco), gr);
		gr.gridx = 1; gr.weightx = 1.0;
		regCard.add(fieldRow("Username", rfUsername), gr);

		// Row 3 – Password
		gr.gridx = 0; gr.gridy = 3; gr.weightx = 0;
		regCard.add(iconLabel(passIco), gr);
		gr.gridx = 1; gr.weightx = 1.0;
		regCard.add(fieldRow("Password", rfPass), gr);

		// Row 4 – ID / Cert
		gr.gridx = 0; gr.gridy = 4; gr.weightx = 0;
		regCard.add(iconLabel(userIco), gr);
		gr.gridx = 1; gr.weightx = 1.0;
		regCard.add(fieldRow("Student / Staff ID or Cert #", rfId), gr);

		// Row 5 – Create Account (full width, extra top spacing)
		gr.gridx = 0; gr.gridy = 5; gr.gridwidth = 2; gr.weightx = 1.0;
		gr.fill = GridBagConstraints.HORIZONTAL;
		gr.insets = new Insets(16, 8, 10, 8);
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
					if (u.getUserType().equals(UserType.LABMANAGER) && u.getEmail().equals(email) && u.getPassword().equals(pass)) {
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
				} catch (NumberFormatException ex) {
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
				} catch (EmailNotUniqueException e1) {
					UITheme.showPopup("Email already exists.", "Register Failed", JOptionPane.ERROR_MESSAGE);
				} catch (WeakPasswordException e2) {
					UITheme.showPopup("Password too weak.\nUse uppercase, numbers, and symbols.", "Register Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		wrapper.add(topSection, BorderLayout.NORTH);
		wrapper.add(cards, BorderLayout.CENTER);
		return wrapper;
	}

	// ── Style helpers – all delegate to UITheme for the new black/red pill look ──

	static JPanel darkCard() {
		return UITheme.darkCard();
	}

	static JTextField darkField() {
		return UITheme.darkField();
	}

	static JPasswordField darkPassword() {
		return UITheme.darkPassword();
	}

	static JComboBox<String> darkCombo(Vector<String> items) {
		return UITheme.darkCombo(items);
	}

	static JButton accentButton(String text, Color color) {
		return UITheme.accentButton(text, color);
	}

	static JButton tabButton(String text, boolean active) {
		return UITheme.tabButton(text, active);
	}

	static void setTabActive(JButton on, JButton off) {
		UITheme.setTabActive(on, off);
	}

	static JLabel iconLabel(ImageIcon ico) {
		return UITheme.iconLabel(ico);
	}

	static JPanel fieldRow(String labelText, JComponent field) {
		return UITheme.fieldRow(labelText, field);
	}

	static JPanel topBar(String roleText, ActionListener onLogout) {
		return UITheme.topBar(roleText, onLogout);
	}

	//  Main
	public static void main(String[] args) {
		// Optional: smoother fonts on some platforms
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) {}
		new MainUI();
	}
}
