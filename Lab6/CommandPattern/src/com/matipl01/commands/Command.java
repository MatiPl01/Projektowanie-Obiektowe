package com.matipl01.commands;

import com.matipl01.Application;
import com.matipl01.editor.Editor;

public abstract class Command implements ICommand {
    protected final Application app;
    protected final Editor editor;

    public Command(Application app, Editor editor) {
        this.app = app;
        this.editor = editor;
    }

    @Override
    public void execute() {
        saveBackup();
    }
}
