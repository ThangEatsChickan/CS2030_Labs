import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;

class Main {
    public static void main (String[] args) {
        try {
            BufferedReader scroll = new BufferedReader(new FileReader(args[0]));
            String line = new String();
            Farm f = new Farm();
            while ((line = scroll.readLine()) != null) {
                try {
                    f.runInstruction(line);
                    if(line == null) {
                       break;
                    }
                } catch (IllegalInstructionException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found. Please check input!");
        } catch (IOException e) {
            System.err.println("Oops! Unable to read the file!");
        }
    }
}

