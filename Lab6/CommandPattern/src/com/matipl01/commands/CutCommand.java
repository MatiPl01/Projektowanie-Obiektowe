package com.matipl01.commands;

import com.matipl01.Application;
import com.matipl01.editor.Editor;

public class CutCommand extends Command {
    private String clipboardBackup;
    private String selectionBackup;

    public CutCommand(Application app, Editor editor) {
        super(app, editor);
    }

    @Override
    public void execute() {
        super.execute();
        String selection = editor.getSelection();
        editor.deleteSelection();
        app.setClipboard(selection);
    }

    @Override
    public void undo() {
        editor.replaceSelection(selectionBackup);
        app.setClipboard(clipboardBackup);
    }

    @Override
    public void saveBackup() {
        clipboardBackup = app.getClipboard();
        selectionBackup = editor.getSelection();
    }
}
