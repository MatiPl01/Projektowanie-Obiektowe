package com.matipl01;

import com.matipl01.commands.CopyCommand;
import com.matipl01.commands.CutCommand;
import com.matipl01.commands.PasteCommand;
import com.matipl01.editor.Editor;

public class Main {
    public static void main(String[] args) throws Exception {
	    // Create the app
        Application app = new Application();
        // Create editors
        Editor editor1 = new Editor();
        Editor editor2 = new Editor();
        // Add editors to the app
        app.addEditors(editor1, editor2);
        app.createUI();  // This does nothing


        /*
         * Test the editor1 (will be active by default)
         */
        System.out.println("Test the editor1 (will be active by default)");
        // Paste the text into the editor
        app.setClipboard("Test1");
        app.executeCommand(new PasteCommand(app, app.getActiveEditor()));
        printState(app);  // Clipboard: Test1    Selection: Test1

        // Check if cut works
        app.getActiveEditor().replaceSelection("Test2");
        app.executeCommand(new CutCommand(app, app.getActiveEditor()));
        printState(app);  // Clipboard: Test2    Selection: null

        // Replace the selection in the editor and check if copy works
        app.getActiveEditor().replaceSelection("Test3");
        app.executeCommand(new CopyCommand(app, app.getActiveEditor()));
        printState(app);  // Clipboard: Test3    Selection: Test3


        /*
         * Test the editor2 (after switching)
         */
        System.out.println("\n\nTest the editor2 (after switching)");
        app.setActiveEditor(1);

        // Paste the currently copied text
        app.executeCommand(new PasteCommand(app, app.getActiveEditor()));
        printState(app);  // Clipboard: Test3    Selection: Test3

        // Replace the selection in the editor and check if copy works
        app.getActiveEditor().replaceSelection("Test4");
        app.executeCommand(new CopyCommand(app, app.getActiveEditor()));
        printState(app);  // Clipboard: Test4    Selection: Test4

        // Check if cut works
        app.getActiveEditor().replaceSelection("Test5");
        app.executeCommand(new CutCommand(app, app.getActiveEditor()));
        printState(app);  // Clipboard: Test5    Selection: null


        /*
         * Check if the first editor's selection is unchanged
         */
        System.out.println("\n\nCheck if the first editor's selection is unchanged");
        app.setActiveEditor(0);
        printState(app);  // Clipboard: Test5    Selection: Test3


        /*
         * Undo commands in editors
         */
        System.out.println("\n\nUndo commands in editors");
        for (int i = 0; i < 6; i++) {
            app.undo();
            printEditorsState(app);
            System.out.println();
        }

        // Because there were only 6 commands executed, the state
        // should remain unchanged after calling undo more times
        for (int i = 0; i < 100; i++) app.undo();
        printEditorsState(app);
    }

    private static void printState(Application app) {
        System.out.println("Clipboard: " + app.getClipboard() + "   Selection: " + app.getActiveEditor().getSelection());
    }

    private static void printEditorsState(Application app) throws Exception {
        app.setActiveEditor(0);
        System.out.println("Editor 1:");
        printState(app);
        app.setActiveEditor(1);
        System.out.println("Editor 2:");
        printState(app);
    }
}
