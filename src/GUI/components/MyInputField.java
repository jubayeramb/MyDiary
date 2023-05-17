package GUI.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyInputField extends JTextField {
    private static final Color BORDER_COLOR = new Color(200, 200, 200);
    private static final int BORDER_THICKNESS = 1;
    private static final int PADDING = 10;

    public MyInputField() {
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS),
                new EmptyBorder(PADDING, PADDING, PADDING, PADDING)));
    }

    public MyInputField(int width, int height) {
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS),
                new EmptyBorder(PADDING, PADDING, PADDING, PADDING)));

        setPreferredSize(new Dimension(width, height));
    }
}
