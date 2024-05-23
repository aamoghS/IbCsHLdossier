import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

public class FileOperations {

    public void createFile(String fileName) {
        File file = new File(fileName);

        try {
            if (file.createNewFile()) {
                JOptionPane.showMessageDialog(null, "File created: " + file.getName() );
                System.out.println(file.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(null, "File already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeData(String fileName, String data) {
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            raf.seek(raf.length());

            String[] dataArray = data.split(",");
            for (int i = 0; i < dataArray.length; i++) {
                raf.writeBytes(dataArray[i]);
                if (i < dataArray.length - 1) {
                    raf.writeBytes(",");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData(String fileName) {
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
            byte[] buffer = new byte[(int) raf.length()];
            raf.readFully(buffer);
            String readData = new String(buffer);

            String[] dataArray = readData.split(",");
            for (String part : dataArray) {
                JOptionPane.showMessageDialog(null, "Data Read from : " + fileName + ": " + part );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFiles() {
        String directoryPath = ".";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                    try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
                        raf.seek(0);
                        raf.setLength(0);
                    } catch (IOException e) {
                        System.err.println("Failed to clear file: " + file.getName());
                        e.printStackTrace();
                    }
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Cleared" + fileName);
    }
    
    
}
