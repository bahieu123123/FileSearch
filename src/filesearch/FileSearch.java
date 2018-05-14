package filesearch;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSearch {
    @Option(name = "-r", metaVar = "SubDirect", usage = "Find Subdirectory")
    private boolean subDirectory;

    @Option(name = "-d", metaVar = "Directory", usage = "Path to the File")
    private String directory;

    @Argument(metaVar = "FileName", required = true, usage = "File name")
    private String fileName;

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



    /**
     * find проложить маршрут.
     * Поиск файла с заданным в командной строке именем в указанной ключом -d директории, по умолчанию в текущей директории.
     * Ключ -r указывает на необходимость поиска также во всех поддиректориях.
     *
     * @param args
     */
    public static void main(String[] args) {
        FileSearch fileSearch = new FileSearch();
        List<String> cmdLine = Arrays.asList(args);
        if (cmdLine.contains("find")) {
            System.out.println("Enter the commands you need and the file name, \n" +
                    "[-d] - search file in directory \n" +
                    "[-r] - search file in subdirectories \n");
            return;
        }

        File directory = new File(new File("").getAbsolutePath());

        if (cmdLine.contains("-d")) {
            if (args[args.length - 1].equals("-d"))
                System.out.println("Directory where you want to check does not exist!");
            else {
                String directoryName = cmdLine.get(cmdLine.indexOf("-d") + 1).toString();
                directory = new File(directoryName);
            }
            if (!directory.isDirectory()) System.out.println("Directory where you want to check does not exist!");
            else System.out.println("Directory where you want to check the presence of a file: " + directory);
        }
        if (args[0].equals("-r")) {
            String fileName = args[args.length - 1];
            fileSearch.search(fileName, directory, cmdLine.contains("-r"));

            System.out.println("The name of the found file: " + fileName);
            List<String> result = fileSearch.getResult();
            System.out.println("\nFound " + " result:\n");
            for (String str : result) {
                System.out.println(str);
            }
        } else {
            if (args.length <= 2 || args.length > 4) System.out.println("File name or directory name not yet written!");
            else {
                String fileName = args[args.length - 1];
                fileSearch.search(fileName, directory, cmdLine.contains("-r"));

                System.out.println("The name of the found file: " + fileName);
                List<String> result = fileSearch.getResult();
                System.out.println("\nFound " + " result:\n");
                for (String str : result) {
                    System.out.println(str);
                }
            }
        }
    }


}

