package editortext;
import java.util.ArrayList;
import java.util.Scanner;

public class TextEditor {
    private StringBuilder text;
    private ArrayList<String> history;
    private int currentIndex;

    public TextEditor() {
        text = new StringBuilder();
        history = new ArrayList<>();
        currentIndex = -1;
    }

    public void show() {
        System.out.println("Current text: " + text.toString());
    }

    public void write(String newText) {
        if (currentIndex < history.size() - 1) {
            history.subList(currentIndex + 1, history.size()).clear();
        }
        history.add(text.toString());
        currentIndex++;
        text.append(newText);
    }

    public void undo() {
        if (currentIndex > 0) {
            currentIndex--;
            text = new StringBuilder(history.get(currentIndex));
        } else {
            System.out.println("Tidak ada yang bisa di-undo.");
        }
    }

    public void redo() {
        if (currentIndex < history.size() - 1) {
            currentIndex++;
            text = new StringBuilder(history.get(currentIndex));
        } else {
            System.out.println("Tidak ada yang bisa di-redo.");
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Selamat datang di Text Editor! Gunakan perintah berikut:");
        System.out.println("1. 'write [teks]' untuk menulis teks");
        System.out.println("2. 'undo' untuk mengembalikan teks sebelumnya");
        System.out.println("3. 'redo' untuk memulihkan teks ke versi lebih baru");
        System.out.println("4. 'show' untuk menampilkan teks saat ini");
        System.out.println("5. 'exit' untuk keluar dari editor");

        while (true) {
            System.out.print("Masukkan perintah: ");
            command = scanner.nextLine();

            if (command.startsWith("write ")) {
                String newText = command.substring(6);
                editor.write(newText);
            } else if (command.equals("undo")) {
                editor.undo();
            } else if (command.equals("redo")) {
                editor.redo();
            } else if (command.equals("show")) {
                editor.show();
            } else if (command.equals("exit")) {
                System.out.println("Keluar dari Text Editor.");
                break;
            } else {
                System.out.println("Perintah tidak dikenal, coba lagi.");
            }
        }

        scanner.close();
    }
}
