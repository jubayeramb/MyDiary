package GUI.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
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
        noteTableModel = new DefaultTableModel(new Object[] { "Title", "Content", "Created At" }, 0);
        noteTable = new MyTable(noteTableModel);
        JScrollPane scrollPane = new JScrollPane(noteTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));

        // Add components to the NoteTab panel
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add dummy notes to the table
        noteList = createDummyNotes();
        for (Note note : noteList) {
            noteTableModel.addRow(new Object[] { note.getTitle(), note.getContent(), note.getCreatedAt() });
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
            noteTableModel.addRow(new Object[] { newNote.getTitle(), newNote.getContent(), newNote.getCreatedAt() });
            System.out.println(
                    "Added note: " + newNote.getTitle() + " " + newNote.getContent());
        }
    }

    private List<Note> createDummyNotes() {
        List<Note> notes = new ArrayList<Note>();
        notes.add(new Note("Note 1", "Content 1"));
        notes.add(new Note("Note 2", "Content 2"));
        notes.add(new Note("Note 3", "Content 3"));
        return notes;
    }
}
