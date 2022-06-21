package com.matipl01.commands;

import java.util.Stack;

public class CommandHistory {
    private final Stack<ICommand> history = new Stack<>();

    public void push(ICommand command) {
        this.history.add(command);
    }

    public ICommand pop() {
        if (history.isEmpty()) return null;
        return history.pop();
    }

    public ICommand getLast() {
        if (history.isEmpty()) return null;
        return history.peek();
    }
}
