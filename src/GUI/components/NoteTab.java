package GUI.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import data.entities.Note;

public class NoteTab extends JPanel {
    private MyTable noteTable;
    private DefaultTableModel noteTableModel;
    private List<Note> noteList;

    public NoteTab() {
        setLayout(new BorderLayout());

        // Create the "Add Note" button and add it to the top right corner
        MyButton addNoteButton = new MyButton("Add Note");
        addNoteButton.addActionListener(this::addNoteButtonActionPerformed);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(addNoteButton);

        // Create the table model and table for displaying notes
        noteTableModel = new DefaultTableModel(new Object[] { "Title", "Content", "Updated At", "Created At" }, 0);
        noteTable = new MyTable(noteTableModel);
        JScrollPane scrollPane = new JScrollPane(noteTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));

        // Set custom row renderer
        noteTable.setDefaultRenderer(Object.class, new NoteTableRowRenderer());

        // Add action listener for table row click
        noteTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = noteTable.getSelectedRow();
                if (selectedRow != -1) {
                    // editNoteDialog(selectedRow);
                    viewNoteDialog(selectedRow);
                }
            }
        });

        // Add components to the NoteTab panel
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add dummy notes to the table
        noteList = createDummyNotes();
        for (Note note : noteList) {
            noteTableModel.addRow(
                    new Object[] { note.getTitle(), note.getContent(), note.getUpdatedAt(), note.getCreatedAt() });
        }
    }

    private void addNoteButtonActionPerformed(ActionEvent e) {
        NoteInputDialog dialog = new NoteInputDialog((Frame) SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);

        if (dialog.isClickedOk()) {
            String title = dialog.getTitle();
            String content = dialog.getContent();
            if (title == null || title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (content == null || content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Note content cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Create a new note object
            Note newNote = new Note(title, content);
            noteList.add(newNote);

            // Add the note to the table
            noteTableModel.addRow(new Object[] { newNote.getTitle(), newNote.getContent(), newNote.getUpdatedAt(),
                    newNote.getCreatedAt() });
            System.out.println(
                    "Added note: " + newNote.getTitle() + " " + newNote.getContent());
        }
    }

    // show a dialog to view the note

    private void viewNoteDialog(int row) {
        Note note = noteList.get(row);
        JDialog viewDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), note.getTitle(), true);
        viewDialog.setLayout(new BorderLayout());

        // Create the title label
        JLabel titleLabel = new JLabel(note.getTitle());
        titleLabel.setFont(titleLabel.getFont().deriveFont(20f));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            viewDialog.dispose();
            editNoteDialog(row);
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(editButton);

        // Create the content label
        JLabel contentLabel = new JLabel("<html>" + note.getContent().replaceAll("\n", "<br/>") + "</html>");
        contentLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a scroll pane for the content label
        JScrollPane scrollPane = new JScrollPane(contentLabel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Set scrollable viewport size
        scrollPane.setPreferredSize(new Dimension(550, 300));

        // Add components to the dialog
        viewDialog.add(topPanel, BorderLayout.NORTH);
        viewDialog.add(scrollPane, BorderLayout.CENTER);
        viewDialog.setPreferredSize(new Dimension(600, 400));
        viewDialog.pack();
        viewDialog.setLocationRelativeTo(this);
        viewDialog.setVisible(true);
    }

    private void editNoteDialog(int row) {
        Note note = noteList.get(row);
        NoteInputDialog dialog = new NoteInputDialog((Frame) SwingUtilities.getWindowAncestor(this), note.getTitle(),
                note.getContent());
        dialog.setVisible(true);

        if (dialog.isClickedOk()) {
            String title = dialog.getTitle();
            String content = dialog.getContent();
            if (title == null || title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (content == null || content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Note content cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Update the note object
            note.setTitle(title);
            note.setContent(content);
            note.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

            // Update the table
            noteTableModel.setValueAt(title, row, 0);
            noteTableModel.setValueAt(content, row, 1);
            noteTableModel.setValueAt(note.getUpdatedAt(), row, 2);
            System.out.println(
                    "Updated note: " + note.getTitle() + " " + note.getContent());
        }
    }

    private List<Note> createDummyNotes() {
        List<Note> notes = new ArrayList<Note>();
        notes.add(new Note("Note 1", "Content 1"));
        notes.add(new Note("Note 2", "Content 2"));
        notes.add(new Note("Note 3", "Content 3"));
        return notes;
    }

    private class NoteTableRowRenderer extends DefaultTableCellRenderer {
        private static final Color SECONDARY_COLOR = new Color(0, 0, 0, 60);

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            JLabel label = (JLabel) c;
            label.setHorizontalAlignment(JLabel.CENTER);
            if (row % 2 == 0) {
                c.setBackground(Color.WHITE);
            } else {
                c.setBackground(SECONDARY_COLOR);
            }

            if (column == 2 || column == 3) { // check if the Created At column
                Timestamp date = (Timestamp) table.getValueAt(row, 2);
                label.setText(date.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")));
            }

            return c;
        }
    }

}
