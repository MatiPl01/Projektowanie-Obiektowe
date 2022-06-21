package com.matipl01.commands;

public interface ICommand {
    void execute();
    void undo();
    void saveBackup();
}
