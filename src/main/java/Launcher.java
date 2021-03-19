import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.IOException;

public class Launcher {
    @Option(name = "-out")
    private String outputFileName;
    @Option(name = "-z")
    private String inputFileNameToPack;
    @Option(name = "-u")
    private String inputFileNameToUnpack;


    public static void main(String[] args) {
        new Launcher().launch(args);
    }

    private void launch(String[] args){
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar pack-rle.jar [-z|-u] InputName [-out OutputName]");
            parser.printUsage(System.err);
        }
        System.out.println(outputFileName);
        System.out.println(inputFileNameToPack);
        System.out.println(inputFileNameToUnpack);
        try {
            if(inputFileNameToPack != null)
                new Packer(inputFileNameToPack, outputFileName).pack();
            else if(inputFileNameToUnpack != null)
                new Packer(inputFileNameToUnpack, outputFileName).unPack();
            else {
                System.err.println("java -jar pack-rle.jar [-z|-u] InputName [-out OutputName]");
                parser.printUsage(System.err);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
