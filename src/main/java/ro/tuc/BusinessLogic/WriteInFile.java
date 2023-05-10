package ro.tuc.BusinessLogic;
import java.io.FileWriter;
import java.io.IOException;

public class WriteInFile {
    private String givenFileName;
    private String givenFileContent;

    public WriteInFile(String givenName, String givenContent) {
        this.givenFileName = givenName;
        this.givenFileContent = givenContent;
    }

    public void write(){
        try {
            FileWriter fileWriter = new FileWriter(this.givenFileName);
            fileWriter.write(this.givenFileContent);
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
