import java.io.*;
import java.util.Objects;

public class Packer {
    String inputFileName;
    String outputFileName;

    public Packer(String inputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = inputFileName + ".rle";
    }

    public Packer(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = Objects.requireNonNullElseGet(outputFileName, () -> inputFileName + ".rle");
    }

    public void unPack() throws IOException {
        FileReader fr = new FileReader(inputFileName);
        FileWriter fw = new FileWriter(outputFileName);

        int c = fr.read();
        int charToPrint = c;
        StringBuilder number = new StringBuilder();
        while (c != -1) {
            c = fr.read();
            if (Character.isDigit((char) c))
                number.append((char) c);
            else {
                if (!number.toString().equals("")) {
                    fw.write(Character.toString(charToPrint).repeat(Integer.parseInt(number.toString().toString())));
                    number = new StringBuilder();
                } else fw.write(charToPrint);
                charToPrint = c;
            }
        }
        fr.close();
        fw.close();
    }

    public void pack() throws IOException {

        FileReader fr = new FileReader(inputFileName);
        FileWriter fw = new FileWriter(outputFileName);

        int c = fr.read();
        int k = 1;

        while (c != -1) {
            int next = fr.read();
            if (c == next)
                k++;
            else if (k == 1) {
                fw.write(c);
                k = 1;
            } else {
                fw.write(c);
                fw.write(Integer.toString(k));
                k = 1;
            }
            c = next;
        }
        fr.close();
        fw.close();
    }
}
