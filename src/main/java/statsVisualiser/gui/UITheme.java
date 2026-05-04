package statsVisualiser.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.util.Vector;

/**
 * Shared design tokens and reusable Swing component factory methods.
 * All other GUI classes reference this instead of duplicating style code.
 */
public class UITheme {

	// colours
	public static final Color BG_DARK = new Color(30, 33, 48);
	public static final Color BG_CARD = new Color(40, 44, 60);
	public static final Color BG_INPUT = new Color(50, 55, 75);
	public static final Color ACCENT = new Color(227, 28, 56);
	public static final Color ACCENT2 = new Color(70, 130, 180);
	public static final Color BTN_GREEN = new Color(46, 160, 67);
	public static final Color BTN_ORANGE = new Color(210, 105, 30);
	public static final Color BTN_RED = new Color(185, 43, 39);
	public static final Color LIGHT_BLUE = new Color(0, 153, 204);
	public static final Color TEXT_WHITE = new Color(240, 240, 250);
	public static final Color TEXT_MUTED = new Color(150, 155, 175);
	public static final Color BORDER_CLR = new Color(60, 65, 90);

	// fonts
	public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 22);
	public static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 15);
	public static final Font FONT_BODY = new Font("Segoe UI", Font.PLAIN, 13);
	public static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 11);
	public static final Font FONT_BTN = new Font("Segoe UI", Font.BOLD, 13);

	// icon loader
	public static ImageIcon loadIcon(String path, int w, int h) {
		try {
			InputStream is = UITheme.class.getResourceAsStream(path);
			if (is == null) {
				return null;
			}
			ImageIcon raw = new ImageIcon(is.readAllBytes());
			return new ImageIcon(raw.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
		} catch (Exception e) { 
			return null; }
	}

	// component factories
	public static JPanel darkCard() {
		JPanel p = new JPanel();
		p.setBackground(BG_CARD);
		p.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(BORDER_CLR, 1, true),
				BorderFactory.createEmptyBorder(14, 14, 14, 14)));
		return p;
	}

	public static JTextField darkField() {
		JTextField tf = new JTextField();
		tf.setFont(FONT_BODY);
		tf.setBackground(BG_INPUT);
		tf.setForeground(TEXT_WHITE);
		tf.setCaretColor(TEXT_WHITE);
		tf.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(BORDER_CLR, 1, true),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)));
		return tf;
	}

	public static JPasswordField darkPassword() {
		JPasswordField pf = new JPasswordField();
		pf.setFont(FONT_BODY);
		pf.setBackground(BG_INPUT);
		pf.setForeground(TEXT_WHITE);
		pf.setCaretColor(TEXT_WHITE);
		pf.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(BORDER_CLR, 1, true),
				BorderFactory.createEmptyBorder(5, 8, 5, 8)));
		return pf;
	}

	public static JComboBox<String> darkCombo(Vector<String> items) {
		JComboBox<String> cb = new JComboBox<>(items);
		cb.setFont(FONT_BODY);
		cb.setBackground(BG_INPUT);
		cb.setForeground(TEXT_WHITE);
		return cb;
	}

	public static JButton accentButton(String text, Color color) {
		JButton b = new JButton(text);
		b.setFont(FONT_BTN);
		b.setBackground(color);
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setBorderPainted(false);
		b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) { 
				b.setBackground(color.brighter()); 
			}
			public void mouseExited (MouseEvent e){
				b.setBackground(color);
			}
		});
		return b;
	}

	public static JButton tabButton(String text, boolean active) {
		JButton b = new JButton(text);
		b.setFont(FONT_BTN);
		b.setFocusPainted(false);
		b.setBorderPainted(false);
		b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		if (active) {
			b.setBackground(ACCENT);
			b.setForeground(Color.WHITE);
		}
		else {
			b.setBackground(BG_CARD);
			b.setForeground(TEXT_MUTED);
		}
		return b;
	}

	public static void setTabActive(JButton on, JButton off) {
		on.setBackground(ACCENT);
		on.setForeground(Color.WHITE);
		off.setBackground(BG_CARD);
		off.setForeground(TEXT_MUTED);
	}

	// Icon label – blank spacer if icon is null
	public static JLabel iconLabel(ImageIcon ico) {
		JLabel l = ico != null ? new JLabel(ico) : new JLabel("  ");
		l.setPreferredSize(new Dimension(26, 26));
		return l;
	}

	// Small muted label above a field, both in a vertical stack
	public static JPanel fieldRow(String labelText, JComponent field) {
		JPanel p = new JPanel(new BorderLayout(0, 2));
		p.setBackground(BG_CARD);
		JLabel l = new JLabel(labelText);
		l.setFont(FONT_SMALL);
		l.setForeground(TEXT_MUTED);
		p.add(l, BorderLayout.NORTH);
		p.add(field, BorderLayout.CENTER);
		return p;
	}

	// Branded top bar with role label and a Logout button
	public static JPanel topBar(String roleText, ActionListener onLogout) {
		JPanel bar = new JPanel(new BorderLayout());
		bar.setBackground(new Color(20, 22, 35));
		bar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_CLR),
				BorderFactory.createEmptyBorder(8, 16, 8, 16)));

		JLabel role = new JLabel("LabReserve  —  " + roleText);
		role.setFont(FONT_HEADER);
		role.setForeground(ACCENT);
		bar.add(role, BorderLayout.WEST);

		JButton btnLogout = accentButton("Logout", BTN_RED);
		btnLogout.setPreferredSize(new Dimension(90, 30));
		btnLogout.addActionListener(onLogout);

		JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		right.setBackground(new Color(20, 22, 35));
		right.add(btnLogout);
		bar.add(right, BorderLayout.EAST);
		return bar;
	}

	// Shared popup helper
	public static void showPopup(String msg, String title, int type) {
		JOptionPane.showMessageDialog(null, msg, title, type);
	}
}
