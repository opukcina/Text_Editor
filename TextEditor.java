package pm;

import java.util.Stack;
import java.util.Scanner;

public class TextEditor {
    private Stack<String> undoStack;
    private Stack<String> redoStack;
    private String currentText;

    public TextEditor() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        currentText = "";
    }

    public void write(String newText) {
        undoStack.push(currentText);
        currentText += newText;
        redoStack.clear();
        System.out.println("Written: " + newText);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText);
            currentText = undoStack.pop();
            System.out.println("Undo performed.");
        } else {
            System.out.println("No more undos available.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText);
            currentText = redoStack.pop();
            System.out.println("Redo performed.");
        } else {
            System.out.println("No more redos available.");
        }
    }

    public void show() {
        System.out.println("Current Text: " + currentText);
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter command: write <text>, undo, redo, show, or exit:");
            String command = scanner.nextLine();

            if (command.startsWith("write")) {
                String text = command.substring(6);
                editor.write(text);
            } else if (command.equals("undo")) {
                editor.undo();
            } else if (command.equals("redo")) {
                editor.redo();
            } else if (command.equals("show")) {
                editor.show();
            } else if (command.equals("exit")) {
                System.out.println("Exiting text editor...");
                break;
            } else {
                System.out.println("Invalid command.");
            }
        }

        scanner.close();
    }
}

