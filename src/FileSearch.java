import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSearch {
    private List<String> result = new ArrayList<>();

    public List<String> getResult() {
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

    public void search(String FileName, File directory, boolean searchinfolder) throws FileNotFoundException {
        if (!directory.isDirectory()) throw new FileNotFoundException();
        if (directory.isDirectory()) {
            if (directory.canRead())
                for (File temp : directory.listFiles()) {
                    if (searchinfolder && temp.isDirectory())
                        search(FileName, temp, searchinfolder);
                    else {
                        if (temp.getName().equals(FileName)) {
                            result.add(temp.getAbsoluteFile().toString());
                        }
                    }
                }
            else System.out.println(directory.getAbsoluteFile() + "File can not be read");
        } else System.out.println(directory.getAbsoluteFile() + "Denied");
    }

    /**
     * Поиск файла с заданным в командной строке именем в указанной ключом -d директории, по умолчанию в текущей директории.
     * Ключ -r указывает на необходимость поиска также во всех поддиректориях.
     *
     * @param args
     * @throws FileNotFoundException
     */
    public void main(String[] args) throws FileNotFoundException {
        FileSearch fileSearch = new FileSearch();
        List<String> CmdLine = Arrays.asList(args);
        if (CmdLine.contains("-h")) {
            System.out.println("Enter the commands you need and the file name, \n" +
                    "[-d] - search file in directory \n" +
                    "[-r] - search file in subdirectories \n");
            return;
        }
        File directory = new File(new File("").getAbsolutePath());

        if (CmdLine.contains("-d")) {
            String directoryName = CmdLine.get(CmdLine.indexOf("-d") + 1).toString();
            directory = new File(directoryName);
            System.out.println("Directory where you want to check the presence of a file: " + directory);
        }
        String fileName = args[args.length - 1];
        System.out.println("The name of the found file: " + fileName);
        List<String> result = fileSearch.getResult();
        if (result.size() == 0) System.out.println("There is no such file in this directory!");
        else {
            System.out.println("/nFound " + result.size() + " result:\n");
            for (String str : result) {
                System.out.println(str);
            }
        }
    }


}

