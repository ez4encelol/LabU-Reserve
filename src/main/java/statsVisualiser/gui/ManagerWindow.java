package statsVisualiser.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Equipment;
import model.database.DatabaseService;
import model.database.EquipmentTable;
import model.systemFacades.EquipmentManagementFacade;

class ManagerWindow extends JFrame {

	private EquipmentManagementFacade mgmtFacade = new EquipmentManagementFacade();

	ManagerWindow() {
		super("Manager Dashboard");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setSize(780, 420);
		setLocationRelativeTo(null);
		ImageIcon icon = UITheme.loadIcon("/model/assets/reservationIcon.png", 32, 32);
		if (icon != null) {
			setIconImage(icon.getImage());
		}

		getContentPane().setBackground(UITheme.BG_DARK);

		JPanel root = new JPanel(new BorderLayout(0, 0));
		root.setBackground(UITheme.BG_DARK);
		root.add(MainUI.topBar("Lab Manager", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseService.getInstance().updateAllTables(); // updates when logout
				dispose();
				new MainUI(); 
			}
		}), BorderLayout.NORTH);
		root.add(createEquipmentPanel(), BorderLayout.CENTER);
		setContentPane(root);
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        int result = JOptionPane.showConfirmDialog(
		            ManagerWindow.this,
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
	
	private JPanel createEquipmentPanel() {

		JPanel outer = new JPanel(new BorderLayout(0, 12));
		outer.setBackground(UITheme.BG_DARK);
		outer.setBorder(BorderFactory.createEmptyBorder(16, 20, 16, 20));

		JLabel title = new JLabel("Equipment Management");
		title.setFont(UITheme.FONT_HEADER);
		title.setForeground(UITheme.TEXT_WHITE);
		outer.add(title, BorderLayout.NORTH);

		JPanel card = MainUI.darkCard();
		card.setLayout(new GridBagLayout());
		GridBagConstraints gridBox = new GridBagConstraints();
		gridBox.insets = new Insets(6, 8, 6, 8);
		gridBox.fill = GridBagConstraints.HORIZONTAL;

		ImageIcon eqIcon = MainUI.loadIcon("/model/assets/user.png", 18, 18);

		JTextField tfName = MainUI.darkField();
		JTextField tfDesc = MainUI.darkField();
		JTextField tfLoc  = MainUI.darkField();
		JButton btnAdd = MainUI.accentButton("+ Add Equipment", UITheme.ACCENT2);
		JComboBox<String> eqBox = MainUI.darkCombo(getEquipmentNames());
		JButton btnEnable = MainUI.accentButton("Enable", UITheme.BTN_GREEN);
		JButton btnDisable = MainUI.accentButton("Disable", UITheme.BTN_ORANGE);
		JButton btnMaint = MainUI.accentButton("Maintenance", UITheme.BTN_RED);

		gridBox.gridx=0; gridBox.gridy=0; gridBox.weightx=0.05;
		card.add(MainUI.iconLabel(eqIcon), gridBox);
		gridBox.gridx=1; gridBox.weightx=0.3;
		card.add(MainUI.fieldRow("Name", tfName), gridBox);
		gridBox.gridx=2; gridBox.weightx=0.3;
		card.add(MainUI.fieldRow("Description", tfDesc), gridBox);
		gridBox.gridx=3; gridBox.weightx=0.3;
		card.add(MainUI.fieldRow("Location", tfLoc), gridBox);
		gridBox.gridx=4; gridBox.weightx=0.05;
		card.add(btnAdd, gridBox);

		gridBox.gridx=0;
		gridBox.gridy=1;
		gridBox.weightx=0.05;
		card.add(MainUI.iconLabel(eqIcon), gridBox);
		
		gridBox.gridx=1;
		gridBox.weightx=0.6;
		gridBox.gridwidth=2;
		card.add(MainUI.fieldRow("Select Equipment", eqBox), gridBox);
		
		JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
		btnRow.setBackground(UITheme.BG_CARD);
		btnRow.add(btnEnable);
		btnRow.add(btnDisable);
		btnRow.add(btnMaint);
		gridBox.gridx=1; gridBox.gridy=2;
		gridBox.gridwidth=3;
		card.add(btnRow, gridBox);
		outer.add(card, BorderLayout.CENTER);

		// ── actions ──
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = tfName.getText().trim();
                String desc = tfDesc.getText().trim();
                String loc  = tfLoc.getText().trim();
                
				if (name.isBlank() || desc.isBlank() || loc.isBlank()) {
					UITheme.showPopup("Enter name, description and/or location.", "Add Equipment", JOptionPane.WARNING_MESSAGE);
					return;
				}
				mgmtFacade.addEquipment(name, desc, loc);
				refreshBox(eqBox);
				tfDesc.setText(""); tfLoc.setText("");
				UITheme.showPopup("Added: " + name + " - " + desc + " at " + loc, "Equipment Added", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnEnable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipment eq = findEquipment((String)
						eqBox.getSelectedItem());
				if (eq == null) {
					return;
				}
				mgmtFacade.enableEquipment(eq); 
				UITheme.showPopup("Enabled: " + eq.getName(), "Equipment", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnDisable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipment eq = findEquipment((String) eqBox.getSelectedItem()); 
				if (eq == null) {
					return;
				}
				mgmtFacade.disableEquipment(eq); 
				UITheme.showPopup("Disabled: " + eq.getName(), "Equipment", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnMaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipment eq = findEquipment((String) eqBox.getSelectedItem()); 
				if (eq == null) {
					return;
				}
				mgmtFacade.markAsUnavailable(eq);
				UITheme.showPopup("Maintenance: " + eq.getName(), "Equipment", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		return outer;
	}

	private Vector<String> getEquipmentNames() {
		Vector<String> v = new Vector<>();
		List<Equipment> equipmentList = EquipmentTable.getInstance().getEquipmentAsList();
		for (int i = 0; i< equipmentList.size(); i++) {
			v.add(equipmentList.get(i).getName());
		}
		return v;
	}
	private void refreshBox(JComboBox<String> box) {
		List<String> equipmentNames = getEquipmentNames();
		box.removeAllItems(); 
		for (int i = 0; i < equipmentNames.size(); i++) {
		    box.addItem(equipmentNames.get(i));
		}
	}
	private Equipment findEquipment(String name) {
		if (name == null) {
			return null;
		}
		List<Equipment> equipments = EquipmentTable.getInstance().getEquipmentAsList();
		for (int i = 0; i < equipments.size(); i++) {
		    Equipment eq = equipments.get(i);
		    if (eq.getName().equals(name)) {
		        return eq;
		    }
		}
		return null;
	}
}

