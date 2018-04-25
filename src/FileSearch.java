import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSearch {
    private ArrayList<String> result = new ArrayList<>();

    public ArrayList<String> getResult() {
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof FileSearch) {
            FileSearch other = (FileSearch) obj;
            for (int i = 0; i < this.result.size(); i++) {
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
        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(result.toArray());
    }

    /**
     * Проверить и найти путь к файлy.
     * Поиск файла с заданным в командной строке именем в указанной ключом директории, по умолчанию в текущей директории.
     *
     * @param fileName
     * @param directory
     * @param searchInSubFolder
     * @throws FileNotFoundException
     */
    public void search(String fileName, File directory, boolean searchInSubFolder) throws FileNotFoundException {
        if (!directory.isDirectory()) throw new FileNotFoundException();
        if (directory.isDirectory()) {
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
            } else System.out.println(directory.getAbsoluteFile() + "Permission denied");
        }
    }

    /**
     * find проложить маршрут.
     * Поиск файла с заданным в командной строке именем в указанной ключом -d директории, по умолчанию в текущей директории.
     * Ключ -r указывает на необходимость поиска также во всех поддиректориях.
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        FileSearch fileSearch = new FileSearch();
        List<String> cmdLine = Arrays.asList(args);
        if (cmdLine.contains("find")) {
            System.out.println("Enter the commands you need and the file name, \n" +
                    "[-d] - search file in directory \n" +
                    "[-r] - search file in subdirectories \n");
            return;
        }
        if (args.length < 1 || args.length > 4) throw new IndexOutOfBoundsException();

        File directory = new File(new File("").getAbsolutePath());

        if (cmdLine.contains("-d")) {
            String directoryName = cmdLine.get(cmdLine.indexOf("-d") + 1).toString();
            directory = new File(directoryName);
            System.out.println("Directory where you want to check the presence of a file: " + directory);
        }
        String fileName = args[args.length - 1];
        try {
            fileSearch.search(fileName, directory, cmdLine.contains("-r"));
        } catch (Exception e) {
            System.err.println(e.toString());
            return;
        }

        System.out.println("The name of the found file: " + fileName);
        List<String> result = fileSearch.getResult();
        if (result.size() == 0) System.out.println("There is no files in this directory!");
        else {
            System.out.println("\n Found " + result.size() + " result:\n");
            for (String str : result) {
                System.out.println(str);
            }
        }
    }


}

