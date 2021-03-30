import java.io.*;

public class Packer {

    public void pack(final Reader reader, final Writer writer) throws IOException {
        int c = reader.read();
        int k = 1;
        int next;
        while (c != -1) {
            next = reader.read();
            if (c == next && k != 9)
                k++;
            else {
                writer.write(Integer.toString(k));
                writer.write(c);
                k = 1;
                c = next;
            }
        }
        reader.close();
        writer.close();
    }

    public void unPack(final Reader reader, final Writer writer) throws IOException {
        int c1 = reader.read();
        int c2 = reader.read();
        while (c1 != -1) {
            int number = Integer.parseInt(Character.toString(c1));
            String charToPrint = Character.toString(c2);
            writer.write(charToPrint.repeat(number));
            c1 = reader.read();
            c2 = reader.read();
        }
        reader.close();
        writer.close();
    }

}

