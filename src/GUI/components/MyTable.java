package GUI.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MyTable extends JTable {
    public MyTable(DefaultTableModel tableModel) {
        super(tableModel);
        Font tableFont = super.getFont().deriveFont(18f);
        super.setFont(tableFont);
        Font headerFont = super.getTableHeader().getFont().deriveFont(Font.BOLD, 18f);
        super.getTableHeader().setFont(headerFont);
        int rowHeight = 30;
        super.setRowHeight(rowHeight);
        int horizontalPadding = 10;
        super.setIntercellSpacing(new Dimension(horizontalPadding, 0));
    }
}
