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
        FileSearch fileSearch = new FileSearch();
        List<String> cmdLine = Arrays.asList(args);
        File directory = new File(new File("").getAbsolutePath());

        if (cmdLine.contains("-d")) {
            if (args.length >= 3) {
                String directoryName = cmdLine.get(cmdLine.indexOf("-d") + 1).toString();
                directory = new File(directoryName);
                if (!directory.isDirectory()) System.out.println("Directory where you want to check does not exist!");
                else System.out.println("Directory where you want to check the presence of a file: " + directory);
            } else System.out.println("File name not found!");

        }

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

