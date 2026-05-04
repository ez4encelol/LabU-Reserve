package statsVisualiser.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import model.Department;
import model.Equipment;
import model.Reservation;
import model.database.DatabaseService;
import model.database.EquipmentTable;
import model.database.ReservationTable;
import model.enums.EquipmentStatus;
import model.enums.ReservationStatus;
import model.paymentclasses.CreditPayment;
import model.paymentclasses.DebitPayment;
import model.paymentclasses.PaymentStrategy;
import model.paymentclasses.ResearchGrantPayment;
import model.systemFacades.EquipmentReservationFacade;
import model.userhierarchy.User;

class UserWindow extends JFrame {

	private User currentUser;
	private EquipmentReservationFacade resvFacade = new EquipmentReservationFacade();
	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	private ImageIcon eqIcon = MainUI.loadIcon("/model/assets/user.png", 30, 30);
	private ImageIcon clockIcon = MainUI.loadIcon("/model/assets/clock.png", 35, 35);
	private ImageIcon labEquipIcon = MainUI.loadIcon("/model/assets/labEquipment.png", 60, 60);
	ImageIcon revIcon = UITheme.loadIcon("/model/assets/reservationIcon.png", 32, 32);

	UserWindow(User user) {
		super(user.getUsername());
		this.currentUser = user;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(1200, 420);
		setLocationRelativeTo(null);
		if (revIcon != null) {
			setIconImage(revIcon.getImage());
		}

		JPanel root = new JPanel(new BorderLayout(0, 0));
		root.setBackground(UITheme.BG_DARK);
		root.add(MainUI.topBar(user.getUsername() + "  |  Reservation Cost: $" + user.getHourlyRate() + "/hr",
				new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseService.getInstance().updateAllTables();
				dispose();
				new MainUI();
			}
		}), BorderLayout.NORTH);
		root.add(createReservationPanel(), BorderLayout.CENTER);
		setContentPane(root);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(UserWindow.this, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					DatabaseService.getInstance().updateAllTables(); //updates when close manager window
					dispose();
				}
			}
		});
	}

	private JPanel createReservationPanel() {

		JPanel outer = new JPanel(new BorderLayout(0, 12));
		outer.setBackground(UITheme.BG_DARK);
		outer.setBorder(BorderFactory.createEmptyBorder(16, 20, 16, 20));

		JLabel title = new JLabel("Equipment Reservations");
		title.setFont(UITheme.FONT_HEADER);
		title.setForeground(UITheme.TEXT_WHITE);
		outer.add(title, BorderLayout.NORTH);

		JPanel card = MainUI.darkCard();
		card.setLayout(new GridBagLayout());
		GridBagConstraints gridBox = new GridBagConstraints();
		gridBox.insets = new Insets(6, 8, 6, 8);
		gridBox.fill = GridBagConstraints.HORIZONTAL;


		JComboBox<String> eqBox = MainUI.darkCombo(getEquipmentNames());

		JTextField tfStart = MainUI.darkField(); tfStart.setText("2026-01-01");
		JComboBox<String> cbStartHour = hourDropdown();
		cbStartHour.setSelectedItem("09:00");

		JTextField tfEnd = MainUI.darkField(); tfEnd.setText("2026-01-01");
		JComboBox<String> cbEndHour = hourDropdown();
		cbEndHour.setSelectedItem("11:00");


		Vector<String> pays = new Vector<>();
		pays.add("Credit"); pays.add("Debit");
		pays.add("Research Grant");
		pays.add("Institutional");
		JComboBox<String> payBox = MainUI.darkCombo(pays);
		JButton btnReserve = MainUI.accentButton("Reserve", UITheme.ACCENT2);

		JTextField tfResvId = MainUI.darkField(); 
		tfResvId.setToolTipText("Reservation ID");
		JTextField tfHours  = MainUI.darkField();
		tfHours.setToolTipText("Hours");
		JButton btnCancel = MainUI.accentButton("Cancel Reservation", UITheme.BTN_RED);
		JButton btnExtend = MainUI.accentButton("Extend", UITheme.BTN_GREEN);
		JButton btnAvailReservation = MainUI.accentButton("Available Reservation", UITheme.BTN_GREEN);
		JButton btnArrive = MainUI.accentButton("Arrive at Reservation", UITheme.BTN_GREEN);
		JButton btnDeptApproval = MainUI.accentButton("Get Department Approval", UITheme.BTN_ORANGE);

		// Row 0 – equipment, start, end, payment, reserve button
		gridBox.gridx=0; gridBox.gridy=0; gridBox.weightx=0.03; card.add(MainUI.iconLabel(labEquipIcon), gridBox);
		gridBox.gridx=1; gridBox.weightx=0.22; card.add(MainUI.fieldRow("Equipment", eqBox), gridBox);
		gridBox.gridx=2; gridBox.weightx=0.03; card.add(MainUI.iconLabel(clockIcon), gridBox);

		gridBox.gridx=3; gridBox.weightx=0.14; card.add(MainUI.fieldRow("Start (yyyy-MM-dd)", tfStart), gridBox);
		gridBox.gridx=4; gridBox.weightx=0.08; card.add(MainUI.fieldRow("HH", cbStartHour), gridBox);
		gridBox.gridx=5; gridBox.weightx=0.03; card.add(MainUI.iconLabel(clockIcon), gridBox);

		gridBox.gridx=6; gridBox.weightx=0.14; card.add(MainUI.fieldRow("End (yyyy-MM-dd)", tfEnd), gridBox);
		gridBox.gridx=7; gridBox.weightx=0.08; card.add(MainUI.fieldRow("HH", cbEndHour), gridBox);
		gridBox.gridx=8; gridBox.weightx=0.22; card.add(MainUI.fieldRow("Payment", payBox), gridBox);

		gridBox.gridx=9; gridBox.weightx=0.03; card.add(btnReserve, gridBox);

		// Row 1 – reservation id, cancel, extend hours, extend button
		gridBox.gridx=0; gridBox.gridy=1; gridBox.weightx=0.03; card.add(MainUI.iconLabel(eqIcon), gridBox);
		gridBox.gridx=1; gridBox.weightx=0.15; card.add(MainUI.fieldRow("Reservation ID", tfResvId), gridBox);
		gridBox.gridx=2; gridBox.gridwidth=2; gridBox.weightx=0.3; card.add(btnCancel, gridBox);
		gridBox.gridwidth=1;
		gridBox.gridx=4; gridBox.weightx=0.03; card.add(MainUI.iconLabel(clockIcon), gridBox);
		gridBox.gridx=5; gridBox.weightx=0.15; card.add(MainUI.fieldRow("Extend by (hrs)", tfHours), gridBox);
		gridBox.gridx=6; gridBox.weightx=0.1;  card.add(btnExtend, gridBox);
		gridBox.gridx=7; gridBox.weightx=0.1;  card.add(btnAvailReservation, gridBox);


		gridBox.gridx=0; gridBox.gridy=2; 
		gridBox.weightx=0.03; card.add(MainUI.iconLabel(eqIcon), gridBox);

		gridBox.gridx=1; gridBox.gridwidth=2; gridBox.weightx=0.25; 
		card.add(btnArrive, gridBox);

		gridBox.gridx=3; gridBox.gridwidth=3; gridBox.weightx=0.35;
		card.add(btnDeptApproval, gridBox);
		gridBox.gridwidth=1;

		outer.add(card, BorderLayout.CENTER);

		// Reserve button action
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipment eq = findEquipment((String) eqBox.getSelectedItem());
				if (eq == null) { 
					UITheme.showPopup("No equipment selected.", "Reserve", JOptionPane.WARNING_MESSAGE); 
					return; 
				}
				if (eq.getStatus() != EquipmentStatus.ENABLED) { 
					UITheme.showPopup("Equipment is not available.", "Reserve", JOptionPane.WARNING_MESSAGE); 
					return; 
				}
				LocalDateTime start, end;
				try { 
					start = LocalDateTime.parse(tfStart.getText().trim() + " " + cbStartHour.getSelectedItem(), fmt);
					end = LocalDateTime.parse(tfEnd.getText().trim() + " " + cbEndHour.getSelectedItem(), fmt);
				}
				catch (Exception ex) { 
					UITheme.showPopup("Invalid date format. Use: yyyy-MM-dd HH:mm", "Reserve", JOptionPane.ERROR_MESSAGE); 
					return; 
				}
				if (!end.isAfter(start)) { 
					UITheme.showPopup("End time must be after start time.", "Reserve", JOptionPane.WARNING_MESSAGE); 
					return; 
				}

				PaymentStrategy pay = getPayment((String) payBox.getSelectedItem());
				boolean ok = resvFacade.reserveEquipment(start, end, currentUser, eq, pay);
				if (ok) {
					List<Reservation> all = ReservationTable.getInstance().getReservationsAsList();
					int newId = all.get(all.size() - 1).getId();
					UITheme.showPopup(
							"Reservation confirmed!\n"
									+ "Equipment: " + eq.getName() + "\n"
									+ "From: " + fmt.format(start) + "\n"
									+ "To: " + fmt.format(end) + "\n"
									+ "Payment: " + payBox.getSelectedItem() + "\n"
									+ "ID: " + newId + "  <- Save this for cancellation",
									"Reserved", JOptionPane.INFORMATION_MESSAGE);
				} else {
					UITheme.showPopup("Conflict – that time slot is already taken.", "Reserve", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Cancel
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id; 
				try { 
					id = Integer.parseInt(tfResvId.getText().trim()); 
				}
				catch (NumberFormatException ex) { 
					UITheme.showPopup("Enter a valid reservation ID.", "Cancel", JOptionPane.WARNING_MESSAGE); 
					return; 
				}

				Reservation r = ReservationTable.getInstance().getReservation(id);

				if (r == null) { 
					UITheme.showPopup("Reservation not found.", "Cancel", JOptionPane.ERROR_MESSAGE); 
					return; 
				}

				if (!r.getUser().equals(currentUser)) { 
					UITheme.showPopup("That is not your reservation.", "Cancel", JOptionPane.ERROR_MESSAGE); 
					return; 
				}
				if (r.getStatus() == ReservationStatus.CANCELLED) {
					UITheme.showPopup("Already cancelled.", "Cancel", JOptionPane.WARNING_MESSAGE); 
					return; 
				}
				if (LocalDateTime.now().isAfter(r.getStartTime())) {
					UITheme.showPopup("Cannot cancel – reservation has started.", "Cancel", JOptionPane.ERROR_MESSAGE); 
					return; 
				}

				resvFacade.cancelReservation(r);
				UITheme.showPopup("Reservation " + id + " cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// Extend
		btnExtend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id; 
				try { 
					id = Integer.parseInt(tfResvId.getText().trim()); 
				}
				catch (NumberFormatException ex) { 
					UITheme.showPopup("Enter a valid reservation ID.", "Extend", JOptionPane.WARNING_MESSAGE); 
					return; 
				}

				int hours;
				try { 
					hours = Integer.parseInt(tfHours.getText().trim()); 
				}
				catch (NumberFormatException ex) {
					UITheme.showPopup("Enter a valid number of hours.", "Extend", JOptionPane.WARNING_MESSAGE);
					return; 
				}

				Reservation r = ReservationTable.getInstance().getReservation(id);

				if (r == null) {
					UITheme.showPopup("Reservation not found.", "Extend", JOptionPane.ERROR_MESSAGE); 
					return; 
				}
				if (!r.getUser().equals(currentUser))
				{ UITheme.showPopup("That is not your reservation.", "Extend", JOptionPane.ERROR_MESSAGE);
				return;
				}
				boolean ok = resvFacade.extendReservation(r, hours);
				if (ok) {
					UITheme.showPopup("Extended by " + hours + " hour(s).", "Extended", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					UITheme.showPopup("Cannot extend – next slot is taken.", "Extend", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Available Reservation pop up
		btnAvailReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Equipment> availableEquipment = resvFacade.getEnabledEquipment();

				StringBuilder availableReservations = new StringBuilder("Available Reservations:\n");

				boolean hasAvailableReservations = false;

				// Loop through all available equipment and check for unavailable time slots
				for (int i = 0; i < availableEquipment.size(); i++) {
					Equipment eq = availableEquipment.get(i);
					String unavailableTimes = resvFacade.getUnavailableTimes(eq);

					if (!unavailableTimes.isEmpty()) {
						String formattedUnavailableTimes = unavailableTimes.replaceAll("(Start:|End:)", "\n$1");

						availableReservations.append("Equipment: " + eq.getName() + "\n" + "Unavailable Times: " + formattedUnavailableTimes + "\n\n");
						hasAvailableReservations = true;
					} else {
						availableReservations.append("Equipment: " + eq.getName() + "\n"
								+ "No reservations currently\n\n");
						hasAvailableReservations = true;
					}
				}

				// If there are available reservations, show them in the popup
				if (hasAvailableReservations) {
					UITheme.showPopup(availableReservations.toString(), "Available Reservations", JOptionPane.INFORMATION_MESSAGE);
				} else {
					UITheme.showPopup("No available reservations at the moment.", "Available Reservations", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		// Arrive at Reservation
		btnArrive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showArriveDialog();
			}
		});

		// Get Department Approval
		btnDeptApproval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Department dept = new Department();
				boolean approved = dept.approveAccount(currentUser);
				if (approved) {
					UITheme.showPopup(
							"Department approval granted for " + currentUser.getUsername() + "!\n"
									+ "Your account is now university-department approved.",
									"Approval Granted", JOptionPane.INFORMATION_MESSAGE);
				} else {
					UITheme.showPopup(
							"Department approval could not be granted.\n"
									+ "Only University-Affiliated accounts (Student, Faculty, Researcher) are eligible.",
									"Approval Denied", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		return outer;
	}

	private Vector<String> getEquipmentNames() {
		Vector<String> v = new Vector<>();
		List<Equipment> equipmentList = EquipmentTable.getInstance().getEquipmentAsList();
		for (int i = 0; i < equipmentList.size(); i++) {
			Equipment eq = equipmentList.get(i);
			v.add(eq.getName());
		}
		return v;
	}
	private Equipment findEquipment(String name) {
		if (name == null) {
			return null;
		}
		List<Equipment> equipmentList = EquipmentTable.getInstance().getEquipmentAsList();
		for (int i = 0; i < equipmentList.size(); i++) {
			Equipment eq = equipmentList.get(i);
			if (eq.getName().equals(name)) {
				return eq;
			}
		}
		return null;
	}
	private PaymentStrategy getPayment(String s) {
		switch (s) {
		case "Debit":
			return new DebitPayment();
		case "Research Grant": 
			return new ResearchGrantPayment();
		default:
			return new CreditPayment();
		}
	}
	private JComboBox<String> hourDropdown() {
		Vector<String> hours = new Vector<>();
		for (int h = 0; h < 24; h++) {
			hours.add(String.format("%02d:00", h));
		}
		return MainUI.darkCombo(hours);
	}

	private void showArriveDialog() {
		List<Reservation> all = ReservationTable.getInstance().getReservationsAsList();
		List<Reservation> userActive = new ArrayList<>();
		for (int i = 0; i < all.size(); i++) {
			Reservation r = all.get(i);
			if (r.getUserId() == currentUser.getId() && r.getStatus() == ReservationStatus.ACTIVE) {
				userActive.add(r);
			}
		}
		
		
		JDialog dialog = new JDialog(this, "Arrive at Reservation", true);
		dialog.setSize(620, 420);
		dialog.setLocationRelativeTo(this);
		dialog.getContentPane().setBackground(UITheme.BG_DARK);
		dialog.setLayout(new BorderLayout());

		JLabel hdr = new JLabel("Your Active Reservations");
		hdr.setFont(UITheme.FONT_HEADER);
		hdr.setForeground(UITheme.TEXT_WHITE);
		hdr.setBorder(BorderFactory.createEmptyBorder(12, 12, 8, 12));
		dialog.add(hdr, BorderLayout.NORTH);

		if (userActive.isEmpty()) {
			JLabel none = new JLabel("No active reservations to arrive at.", JLabel.CENTER);
			none.setFont(UITheme.FONT_BODY);
			none.setForeground(UITheme.TEXT_MUTED);
			dialog.add(none, BorderLayout.CENTER);
		} else {
			JPanel listPanel = new JPanel();
			listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
			listPanel.setBackground(UITheme.BG_DARK);
			listPanel.setBorder(BorderFactory.createEmptyBorder(4, 12, 4, 12));

			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

			for (int i = 0; i < userActive.size(); i++) {
				Reservation r = userActive.get(i);
				Equipment eq = r.getEquipment();
				String eqName;
				if (eq != null) {
					eqName = eq.getName();
				} else {
					eqName = "Unknown";
				}

				JPanel row = new JPanel(new BorderLayout(10, 0));
				row.setBackground(UITheme.BG_CARD);
				row.setBorder(BorderFactory.createCompoundBorder(
						new LineBorder(UITheme.BORDER_CLR, 1, true),
						BorderFactory.createEmptyBorder(8, 12, 8, 12)));
				row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));

				String info = "<html><b>Reservation #" + r.getId() + "</b>  —  " + eqName
						+ "<br/>Start: " + r.getStartTime().format(df)
						+ " &nbsp; End: " + r.getEndTime().format(df)
						+ "<br/>Deposit: $" + r.getDepositAmount()
						+ " &nbsp; Total Owed: $" + r.getTotalOwed()
						+ " &nbsp; <i>(Must arrive within 20 min of start)</i></html>";
				JLabel infoLbl = new JLabel(info);
				infoLbl.setFont(UITheme.FONT_BODY);
				infoLbl.setForeground(UITheme.TEXT_WHITE);
				row.add(infoLbl, BorderLayout.CENTER);

				JButton arriveBtn = MainUI.accentButton("Arrive", UITheme.LIGHT_BLUE);
				arriveBtn.setPreferredSize(new Dimension(90, 36));

				final Reservation res = r;
				arriveBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						LocalDateTime now = LocalDateTime.now();
						LocalDateTime start = res.getStartTime();

						if (now.isBefore(start)) {
							UITheme.showPopup(
									"Your booking hasn't started yet.\nPlease wait until " + start.format(df) + ".", "Too Early", JOptionPane.WARNING_MESSAGE);
							return;
						}

						// triggers sensor which checks the 20min window
						res.getEquipment().getSensor().arrive();

						if (res.getStatus() == ReservationStatus.ARRIVED) {
							UITheme.showPopup(
									"Arrival confirmed for Reservation #" + res.getId() + "!\n"
											+ "Equipment: " + res.getEquipment().getName() + "\n"
											+ "Your deposit of $" + res.getDepositAmount()
											+ " has been deducted from your total.\n"
											+ "Remaining balance: $" + res.getTotalOwed(),
											"Arrived On Time", JOptionPane.INFORMATION_MESSAGE);
						} else {
							UITheme.showPopup(
									"You arrived more than 20 minutes after the booking start time.\n"
											+ "Your deposit of $" + res.getDepositAmount() + " has been forfeited.\n"
											+ "Total owed: $" + res.getTotalOwed(),
											"Late Arrival - Deposit Forfeited", JOptionPane.WARNING_MESSAGE);
						}

						DatabaseService.getInstance().updateAllTables();
						dialog.dispose();
					}
				});

				row.add(arriveBtn, BorderLayout.EAST);
				listPanel.add(row);
				listPanel.add(Box.createRigidArea(new Dimension(0, 8)));
			}

			JScrollPane scroll = new JScrollPane(listPanel);
			scroll.getViewport().setBackground(UITheme.BG_DARK);
			scroll.setBorder(BorderFactory.createEmptyBorder());
			dialog.add(scroll, BorderLayout.CENTER);
		}

		JButton closeBtn = MainUI.accentButton("Close", UITheme.BTN_RED);
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { dialog.dispose(); }
		});
		JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		south.setBackground(UITheme.BG_DARK);
		south.add(closeBtn);
		dialog.add(south, BorderLayout.SOUTH);

		dialog.setVisible(true);
	}
}
