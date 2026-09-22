package statsVisualiser.gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.InputStream;
import java.util.Vector;

/**
 * Shared design tokens and reusable Swing component factory methods.
 * Sleek black + red theme inspired by modern dark UIs (pill buttons, deep blacks, bold red accents).
 */
public class UITheme {

	// ── Colours (deep black / red palette) ──────────────────────────────
	public static final Color BG_DARK     = new Color(12, 12, 14);      // near-black
	public static final Color BG_CARD     = new Color(18, 18, 22);      // slightly elevated
	public static final Color BG_INPUT    = new Color(28, 28, 34);      // input wells
	public static final Color ACCENT      = new Color(227, 28, 56);      // primary red
	public static final Color ACCENT_SOFT = new Color(227, 28, 56, 180); // semi-transparent red
	public static final Color ACCENT2     = new Color(200, 30, 50);      // secondary red (was blue)
	public static final Color BTN_GREEN   = new Color(46, 160, 67);
	public static final Color BTN_ORANGE  = new Color(210, 105, 30);
	public static final Color BTN_RED     = new Color(185, 43, 39);
	public static final Color LIGHT_BLUE  = new Color(0, 153, 204);
	public static final Color TEXT_WHITE  = new Color(245, 245, 250);
	public static final Color TEXT_MUTED  = new Color(140, 140, 155);
	public static final Color BORDER_CLR  = new Color(40, 40, 48);
	public static final Color TOPBAR_BG   = new Color(10, 10, 12);

	// ── Fonts ───────────────────────────────────────────────────────────
	public static final Font FONT_TITLE  = new Font("Segoe UI", Font.BOLD, 22);
	public static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 15);
	public static final Font FONT_BODY   = new Font("Segoe UI", Font.PLAIN, 13);
	public static final Font FONT_SMALL  = new Font("Segoe UI", Font.PLAIN, 11);
	public static final Font FONT_BTN    = new Font("Segoe UI", Font.BOLD, 13);

	// ── Icon loader ─────────────────────────────────────────────────────
	public static ImageIcon loadIcon(String path, int w, int h) {
		try (InputStream is = UITheme.class.getResourceAsStream(path)) {
			if (is == null) {
				return null;
			}
			ImageIcon raw = new ImageIcon(is.readAllBytes());
			return new ImageIcon(raw.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
		} catch (Exception e) {
			return null;
		}
	}

	// ── Rounded / pill border ───────────────────────────────────────────
	/** Soft rounded rectangle border used for cards and inputs. */
	public static class RoundedBorder extends AbstractBorder {
		private final Color color;
		private final int thickness;
		private final int radius;

		public RoundedBorder(Color color, int thickness, int radius) {
			this.color = color;
			this.thickness = thickness;
			this.radius = radius;
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(color);
			g2.setStroke(new BasicStroke(thickness));
			g2.drawRoundRect(x + thickness / 2, y + thickness / 2,
					width - thickness, height - thickness, radius, radius);
			g2.dispose();
		}

		@Override
		public Insets getBorderInsets(Component c) {
			return new Insets(radius / 2 + thickness, radius / 2 + thickness,
					radius / 2 + thickness, radius / 2 + thickness);
		}

		@Override
		public Insets getBorderInsets(Component c, Insets insets) {
			insets.left = insets.right = insets.top = insets.bottom = radius / 2 + thickness;
			return insets;
		}
	}

	// ── Pill-shaped button (filled, highly rounded) ─────────────────────
	/**
	 * Creates a modern pill-shaped button. Colour is read from
	 * getBackground() so setBackground() / setTabActive work correctly.
	 */
	public static JButton pillButton(String text, Color baseColor) {
		JButton b = new JButton(text) {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				int w = getWidth();
				int h = getHeight();
				int arc = h; // full pill – perfectly rounded ends

				Color bg = getBackground();
				if (getModel().isPressed()) {
					g2.setColor(bg.darker());
				} else if (getModel().isRollover()) {
					g2.setColor(brighter(bg, 1.18f));
				} else {
					g2.setColor(bg);
				}
				g2.fill(new RoundRectangle2D.Float(0, 0, w, h, arc, arc));

				// text centred
				g2.setColor(getForeground());
				FontMetrics fm = g2.getFontMetrics();
				int tx = (w - fm.stringWidth(getText())) / 2;
				int ty = (h + fm.getAscent() - fm.getDescent()) / 2;
				g2.drawString(getText(), tx, ty);
				g2.dispose();
			}

			@Override
			protected void paintBorder(Graphics g) {
				// shape is painted in paintComponent – no rectangular border
			}
		};

		b.setFont(FONT_BTN);
		b.setBackground(baseColor);
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
		b.setOpaque(false);
		b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b.setBorder(BorderFactory.createEmptyBorder(10, 22, 10, 22));
		b.setPreferredSize(new Dimension(140, 40));
		b.setMinimumSize(new Dimension(100, 36));
		return b;
	}

	/** Slightly brighter version of a colour (handles alpha). */
	private static Color brighter(Color c, float factor) {
		int r = Math.min(255, (int) (c.getRed() * factor));
		int g = Math.min(255, (int) (c.getGreen() * factor));
		int b = Math.min(255, (int) (c.getBlue() * factor));
		return new Color(r, g, b, c.getAlpha());
	}

	// ── Component factories ─────────────────────────────────────────────
	public static JPanel darkCard() {
		JPanel p = new JPanel();
		p.setBackground(BG_CARD);
		// Tighter outer radius insets so content is not squeezed vertically
		p.setBorder(BorderFactory.createCompoundBorder(
				new RoundedBorder(BORDER_CLR, 1, 14),
				BorderFactory.createEmptyBorder(14, 16, 14, 16)));
		return p;
	}

	public static JTextField darkField() {
		JTextField tf = new JTextField();
		tf.setFont(FONT_BODY);
		tf.setBackground(BG_INPUT);
		tf.setForeground(TEXT_WHITE);
		tf.setCaretColor(TEXT_WHITE);
		tf.setBorder(BorderFactory.createCompoundBorder(
				new RoundedBorder(BORDER_CLR, 1, 10),
				BorderFactory.createEmptyBorder(8, 12, 8, 12)));
		return tf;
	}

	public static JPasswordField darkPassword() {
		JPasswordField pf = new JPasswordField();
		pf.setFont(FONT_BODY);
		pf.setBackground(BG_INPUT);
		pf.setForeground(TEXT_WHITE);
		pf.setCaretColor(TEXT_WHITE);
		pf.setBorder(BorderFactory.createCompoundBorder(
				new RoundedBorder(BORDER_CLR, 1, 10),
				BorderFactory.createEmptyBorder(8, 12, 8, 12)));
		return pf;
	}

	public static JComboBox<String> darkCombo(Vector<String> items) {
		JComboBox<String> cb = new JComboBox<>(items) {
			@Override
			public void updateUI() {
				// Keep the dark BasicComboBoxUI even if a native L&F is installed
				setUI(darkComboUI());
			}

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(BG_INPUT);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
				g2.dispose();
				super.paintComponent(g);
			}
		};
		cb.setFont(FONT_BODY);
		cb.setBackground(BG_INPUT);
		cb.setForeground(TEXT_WHITE);
		cb.setOpaque(false);
		cb.setBorder(BorderFactory.createCompoundBorder(
				new RoundedBorder(BORDER_CLR, 1, 10),
				BorderFactory.createEmptyBorder(6, 10, 6, 8)));
		cb.setMaximumRowCount(8);
		cb.setRenderer(darkComboRenderer());
		cb.setUI(darkComboUI());
		return cb;
	}

	private static DefaultListCellRenderer darkComboRenderer() {
		return new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value,
					int index, boolean isSelected, boolean cellHasFocus) {
				JLabel c = (JLabel) super.getListCellRendererComponent(
						list, value, index, isSelected, cellHasFocus);
				c.setOpaque(true);
				c.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
				if (isSelected) {
					c.setBackground(ACCENT);
					c.setForeground(Color.WHITE);
				} else {
					c.setBackground(BG_INPUT);
					c.setForeground(TEXT_WHITE);
				}
				return c;
			}
		};
	}

	private static javax.swing.plaf.basic.BasicComboBoxUI darkComboUI() {
		return new javax.swing.plaf.basic.BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				JButton arrow = new JButton() {
					@Override
					protected void paintComponent(Graphics g) {
						Graphics2D g2 = (Graphics2D) g.create();
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						g2.setColor(BG_INPUT);
						g2.fillRect(0, 0, getWidth(), getHeight());
						g2.setColor(TEXT_MUTED);
						int cx = getWidth() / 2;
						int cy = getHeight() / 2;
						int[] xs = { cx - 4, cx + 4, cx };
						int[] ys = { cy - 2, cy - 2, cy + 3 };
						g2.fillPolygon(xs, ys, 3);
						g2.dispose();
					}
				};
				arrow.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 10));
				arrow.setContentAreaFilled(false);
				arrow.setFocusable(false);
				arrow.setOpaque(true);
				arrow.setBackground(BG_INPUT);
				return arrow;
			}

			@Override
			public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
				g.setColor(BG_INPUT);
				g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
			}

			@Override
			protected ComboPopup createPopup() {
				BasicComboPopup popup = (BasicComboPopup) super.createPopup();
				popup.getList().setBackground(BG_INPUT);
				popup.getList().setForeground(TEXT_WHITE);
				popup.getList().setSelectionBackground(ACCENT);
				popup.getList().setSelectionForeground(Color.WHITE);
				popup.setBorder(new LineBorder(BORDER_CLR, 1));
				popup.setBackground(BG_INPUT);
				return popup;
			}
		};
	}


	/** Primary action button – solid red pill. */
	public static JButton accentButton(String text, Color color) {
		return pillButton(text, color);
	}

	/** Tab-style pill button (Login / Register). */
	public static JButton tabButton(String text, boolean active) {
		JButton b = pillButton(text, active ? ACCENT : new Color(35, 35, 42));
		if (!active) {
			b.setForeground(TEXT_MUTED);
		}
		return b;
	}

	public static void setTabActive(JButton on, JButton off) {
		on.setBackground(ACCENT);
		on.setForeground(Color.WHITE);
		off.setBackground(new Color(35, 35, 42));
		off.setForeground(TEXT_MUTED);
		on.repaint();
		off.repaint();
	}

	// Icon label – blank spacer if icon is null
	public static JLabel iconLabel(ImageIcon ico) {
		JLabel l = ico != null ? new JLabel(ico) : new JLabel("  ");
		l.setPreferredSize(new Dimension(26, 26));
		return l;
	}

	// Small muted label above a field, both in a vertical stack
	public static JPanel fieldRow(String labelText, JComponent field) {
		JPanel p = new JPanel(new BorderLayout(0, 4));
		p.setBackground(BG_CARD);
		p.setOpaque(true);
		JLabel l = new JLabel(labelText);
		l.setFont(FONT_SMALL);
		l.setForeground(TEXT_MUTED);
		p.add(l, BorderLayout.NORTH);
		p.add(field, BorderLayout.CENTER);
		return p;
	}

	// Branded top bar with role label and a Logout pill button
	public static JPanel topBar(String roleText, ActionListener onLogout) {
		JPanel bar = new JPanel(new BorderLayout());
		bar.setBackground(TOPBAR_BG);
		bar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_CLR),
				BorderFactory.createEmptyBorder(10, 18, 10, 18)));

		JLabel role = new JLabel("LabU Reserve  —  " + roleText);
		role.setFont(FONT_HEADER);
		role.setForeground(ACCENT);
		bar.add(role, BorderLayout.WEST);

		JButton btnLogout = pillButton("Logout", BTN_RED);
		btnLogout.setPreferredSize(new Dimension(100, 32));
		btnLogout.addActionListener(onLogout);

		JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		right.setBackground(TOPBAR_BG);
		right.add(btnLogout);
		bar.add(right, BorderLayout.EAST);
		return bar;
	}

	// Shared popup helper
	public static void showPopup(String msg, String title, int type) {
		JOptionPane.showMessageDialog(null, msg, title, type);
	}
}
