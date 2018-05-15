package filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSearch {
    private ArrayList<String> result = new ArrayList<>();

    public ArrayList<String> getResult() {
        if (result.isEmpty()) result.add("There is no files in this directory " +
                "or file you want to check does not exist!");
        return result;
    }

    /**
     * Проверить и найти путь к файлy.
     * Поиск файла с заданным в командной строке именем в указанной ключом директории, по умолчанию в текущей директории.
     *
     * @param fileName
     * @param directory
     * @param searchInSubFolder
     */
    public void search(String fileName, File directory, boolean searchInSubFolder) {
        if (!directory.isDirectory()) result.add("Directory not found!");
        if (directory.isDirectory()) {
            if (directory.canRead()) {
                for (File temp : directory.listFiles()) {
                    if (searchInSubFolder && temp.isDirectory())
                        search(fileName, temp, searchInSubFolder);
                    if (temp.getName().equalsIgnoreCase(fileName)) {
                        result.add(temp.getAbsoluteFile().toString());
                    }
                }
            } else result.add("Permission denied");
        }
    }
}

