package com.matipl01.commands;

import com.matipl01.Application;
import com.matipl01.editor.Editor;

public class PasteCommand extends Command {
    private String selectionBackup;

    public PasteCommand(Application app, Editor editor) {
        super(app, editor);
    }

    @Override
    public void execute() {
        super.execute();
        editor.replaceSelection(app.getClipboard());
    }

    @Override
    public void undo() {
        editor.replaceSelection(selectionBackup);
    }

    @Override
    public void saveBackup() {
        selectionBackup = editor.getSelection();
    }
}
