package com.matipl01.editor;

public class Editor {
    private String text;

    public String getSelection() {
       return text;
    }

    public void deleteSelection() {
       this.text = null;
    }

    public void replaceSelection(String text) {
       this.text = text;
    }
}
