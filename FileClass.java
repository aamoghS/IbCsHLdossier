import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class FileClass {

     JFrame fileFrame;
    private JTextField fileNameTextField;
    private JTextField dataTextField;
    private FileOperations fileOperations;
    private final Action action = new SwingAction();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FileClass window = new FileClass();
                    window.fileFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FileClass() {
        initialize();
        fileOperations = new FileOperations();
    }

    private void initialize() {
        fileFrame = new JFrame();
        fileFrame.setBounds(100, 100, 450, 300);
        fileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        fileFrame.getContentPane().add(panel, "Center");
        panel.setLayout(null);

        JLabel lblFileName = new JLabel("File Name:");
        lblFileName.setBounds(26, 34, 80, 14);
        panel.add(lblFileName);

        fileNameTextField = new JTextField();
        fileNameTextField.setBounds(120, 31, 200, 20);
        panel.add(fileNameTextField);
        fileNameTextField.setColumns(10);

        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(26, 69, 46, 14);
        panel.add(lblData);

        dataTextField = new JTextField();
        dataTextField.setBounds(120, 66, 200, 20);
        panel.add(dataTextField);
        dataTextField.setColumns(10);

        JButton btnCreateFile = new JButton("Create File");
        btnCreateFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createFile();
            }
        });
        btnCreateFile.setBounds(26, 101, 120, 23);
        panel.add(btnCreateFile);

        JButton btnWriteData = new JButton("Write Data");
        btnWriteData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeData();
            }
        });
        btnWriteData.setBounds(160, 101, 120, 23);
        panel.add(btnWriteData);

        JButton btnReadData = new JButton("Read Data");
        btnReadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                readData();
            }
        });
        btnReadData.setBounds(296, 101, 120, 23);
        panel.add(btnReadData);

        JButton btnClearFiles = new JButton("Clear Files");
        btnClearFiles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFiles();
            }
        });
        btnClearFiles.setBounds(160, 134, 120, 23);
        panel.add(btnClearFiles);
        
        JButton btnNewButton = new JButton("Return to Menu");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MainClass Mc = new MainClass(); // Fc - allows for the user to input data
                Mc.mainFrame.setVisible(true);
                fileFrame.dispose();
        		
        	}
        });
        btnNewButton.setBounds(109, 211, 236, 21);
        panel.add(btnNewButton);
    }

    private void createFile() {
        String fileName = fileNameTextField.getText();
        fileOperations.createFile(fileName);
    }

    private void writeData() {
        String fileName = fileNameTextField.getText();
        String data = dataTextField.getText();
        fileOperations.writeData(fileName, data);
    }

    private void readData() {
        String fileName = fileNameTextField.getText();
        fileOperations.readData(fileName);
    }

    private void clearFiles() {
        fileOperations.clearFiles();
    }
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}