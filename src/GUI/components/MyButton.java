package GUI.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButton extends JButton {
    private static final Color PRIMARY_COLOR = new Color(59, 89, 152);
    private static final Color HOVER_COLOR = new Color(89, 119, 172);
    private static final Color PRESS_COLOR = new Color(29, 59, 112);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Font FONT = new Font("Arial", Font.BOLD, 14);
    private static final int PADDING = 10;

    public MyButton(String text) {
        super(text);

        setBackground(PRIMARY_COLOR);
        setForeground(TEXT_COLOR);
        setFont(FONT);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setMargin(new Insets(PADDING, PADDING, PADDING, PADDING));

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(HOVER_COLOR);
            }

            public void mouseExited(MouseEvent e) {
                setBackground(PRIMARY_COLOR);
            }

            public void mousePressed(MouseEvent e) {
                setBackground(PRESS_COLOR);
            }

            public void mouseReleased(MouseEvent e) {
                setBackground(HOVER_COLOR);
            }
        });
    }

    public MyButton(String text, Color primaryColor, Color textColor, Color hoverColor, Color pressColor, Font font,
            int padding) {
        super(text);

        setBackground(primaryColor);
        setForeground(textColor);
        setFont(font);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setMargin(new Insets(padding, padding, padding, padding));

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
            }

            public void mouseExited(MouseEvent e) {
                setBackground(primaryColor);
            }

            public void mousePressed(MouseEvent e) {
                setBackground(pressColor);
            }

            public void mouseReleased(MouseEvent e) {
                setBackground(hoverColor);
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded rectangle background
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());

        // Draw button text
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getHeight();
        g2d.setColor(getForeground());
        g2d.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - fm.getDescent());

        g2d.dispose();
    }
}
