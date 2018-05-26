package filesearch;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FindLauncher {
    @Option(name = "-r", metaVar = "SubDirect", usage = "Find Subdirectory")
    private boolean subDirectory;

    @Option(name = "-d", metaVar = "Directory", usage = "Path to the File")
    private String directory;

    @Argument(metaVar = "FileName", required = true, usage = "File name")
    private String fileName;


    /**
     * find проложить маршрут.
     * Поиск файла с заданным в командной строке именем в указанной ключом -d директории, по умолчанию в текущей директории.
     * Ключ -r указывает на необходимость поиска также во всех поддиректориях.
     *
     * @param args
     */
    public static void main(String[] args) {
        new FindLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("[-r] [-d directory] filename.txt");
            parser.printUsage(System.err);
            return;
        }
        if (args.length <= 1 || args.length > 4) {
            System.out.println("Command Line:[-r] [-d directory] filename.txt");
        } else {
            FileSearch fileSearch = new FileSearch();
            this.fileName = args[args.length - 1];
            if (args[0] == "-r") {
                subDirectory = true;
            }
            File directory = new File(new File("").getAbsolutePath());
            if (args[0].equals("-d") || args[1].equals("-d")) {
                this.directory=args[args.length-2];
                directory = new File(this.directory);
                System.out.println("Directory where you want to check the presence of a file: " + directory);
            } else System.out.println("Find subdirectory");
            fileSearch.search(fileName, directory, subDirectory);

            System.out.println("The name of the found file: " + fileName);

            List<String> result = fileSearch.getResult();
            System.out.println("\nFound " + " result:\n");
            for (String str : result) {
                System.out.println(str);
            }
        }
    }
}

