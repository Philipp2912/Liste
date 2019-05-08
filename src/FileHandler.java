import javax.swing.*;
import java.io.*;
import java.util.Formatter;

public class FileHandler {
    JFileChooser fileChooser = new JFileChooser();
    File file;
    boolean load = true;
    BufferedReader reader;

    void SaveFile(String[] size, String[] colour, String[] price, boolean[] sold) {
        boolean save = true;
        try {
            fileChooser.setSelectedFile(file);
            int res = fileChooser.showSaveDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            }
            String finalPath = file.getAbsolutePath();
            file = new File(finalPath);
            if (!file.exists()) {
                new Formatter(finalPath);
            } else {
                if (JOptionPane.showConfirmDialog(null, "Datei Existiert Bereits, soll sie überschrieben werden?",null,0) == 1)
                    save = false;
            }
            if (save) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
                writer.write(String.valueOf(colour.length));
                writer.newLine();
                for (int i = 0; i < size.length; i++)
                    writer.write(size[i] + "°");
                writer.newLine();
                for (int i = 0; i < size.length; i++)
                    writer.write(colour[i] + "°");
                writer.newLine();
                for (int i = 0; i < size.length; i++)
                    writer.write(price[i] + "°");
                writer.newLine();
                for (int i = 0; i < size.length; i++)
                    writer.write(String.valueOf(sold[i]) + "°");
                writer.close();
            }

        } catch (Exception ignored) {
        }
    }

    void getFile() {
        int res = fileChooser.showOpenDialog(null);
        load = (res == 0 ? true : false);
        System.out.println(load);
        file = fileChooser.getSelectedFile();
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (Exception ignored) {
        }
    }

    String selectInstance(String line, int position) {
        int i = 0;
        String selectedInstance = "";

        for (int c = 0; c < position; c = c) {
            c = (line.charAt(i) == '°' ? ++c : c);
            i++;
        }
        while (line.charAt(i) != '°') {
            selectedInstance = selectedInstance + line.charAt(i);
            i++;
        }
        return selectedInstance;
    }

    int size() {
        int size = 0;
        try {
            size = Integer.parseInt(reader.readLine());
        } catch (Exception ignored) {
        }
        return size;
    }

    String[] getArray(int arraySize) {
        String[] array = new String[arraySize];
        String line = ";";
        try {
            line = reader.readLine();
        } catch (Exception ignored) {

        }
        for (int i = 0; i < arraySize; i++) {
            array[i] = selectInstance(line, i);
        }
        return array;
    }

    void exitLoading() {
        try {
            reader.close();
        } catch (Exception ignored) {

        }
    }
}