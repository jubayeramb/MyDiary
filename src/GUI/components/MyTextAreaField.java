package GUI.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class MyTextAreaField extends JTextArea {
    private static final Color BORDER_COLOR = new Color(200, 200, 200);
    private static final int BORDER_THICKNESS = 1;
    private static final int PADDING = 10;

    public MyTextAreaField() {
        setBorder(createBorder());
    }

    private Border createBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS),
                BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
    }
}
