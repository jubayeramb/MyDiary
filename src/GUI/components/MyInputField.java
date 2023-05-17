package GUI.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyInputField extends JTextField {
    private static final Color BACKGROUND_COLOR = new Color(240, 240, 240);
    private static final Color BORDER_COLOR = new Color(200, 200, 200);
    private static final int BORDER_THICKNESS = 2;
    private static final int PADDING = 4;
    private static final int DEFAULT_WIDTH = 250;
    private static final int DEFAULT_HEIGHT = 30;

    public MyInputField() {
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS),
                new EmptyBorder(PADDING, PADDING, PADDING, PADDING)));
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setFont(new Font("Arial", Font.PLAIN, 14));
    }

    public MyInputField(int width) {
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS),
                new EmptyBorder(PADDING, PADDING, PADDING, PADDING)));
        setPreferredSize(new Dimension(width, DEFAULT_HEIGHT));
        setFont(new Font("Arial", Font.PLAIN, 14));
    }

    public MyInputField(int width, int height) {
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS),
                new EmptyBorder(PADDING, PADDING, PADDING, PADDING)));
        setPreferredSize(new Dimension(width, height));
        setFont(new Font("Arial", Font.PLAIN, 14));
    }
}
