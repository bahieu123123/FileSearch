package filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


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
        if (directory.canRead()) {
            for (File temp : directory.listFiles()) {
                if (searchInSubFolder && temp.isDirectory())
                    search(fileName, temp, searchInSubFolder);
                else {
                    if (temp.getName().equals(fileName)) {
                        result.add(temp.getAbsoluteFile().toString());
                    }
                }
            }
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof FileSearch) {
            FileSearch other = (FileSearch) obj;
            for (int i = 0; i < java.lang.Math.max(this.result.size(), other.result.size()); i++) {
                if (this.result.get(i).equals(other.result.get(i)))
                    return false;
            }
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String str : this.result) {
            sb.append(str).append("; ");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(result.toArray());
    }
}

