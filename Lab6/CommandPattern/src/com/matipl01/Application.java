package com.matipl01;

import com.matipl01.commands.CommandHistory;
import com.matipl01.commands.ICommand;
import com.matipl01.commands.UndoCommand;
import com.matipl01.editor.Editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    private final CommandHistory history = new CommandHistory();
    private final List<Editor> editors = new ArrayList<>();
    private Editor activeEditor;
    private String clipboard;

    public String getClipboard() {
        return clipboard;
    }

    public ICommand getLastCommand() {
        return history.getLast();
    }

    public void setClipboard(String text) {
        clipboard = text;
    }

    public Editor getActiveEditor() {
        return activeEditor;
    }

    public void setActiveEditor(int editorId) throws Exception {
        if (editorId >= editors.size()) {
            throw new Exception("There is no editor with id " + editorId);
        }
        activeEditor = editors.get(editorId);
    }

    public void addEditor(Editor editor) {
        editors.add(editor);
        if (editors.size() == 1) {
            try {
                setActiveEditor(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addEditors(Editor ...editors) {
        Arrays.stream(editors).forEach(this::addEditor);
    }

    public void createUI() {}

    public void executeCommand(ICommand command) {
        command.execute();
        if (command instanceof UndoCommand) history.pop();
        else history.push(command);
    }

    public void undo() {
        executeCommand(new UndoCommand(this, null));
    }
}
