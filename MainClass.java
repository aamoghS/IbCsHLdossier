import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.Font;

public class MainClass {

    JFrame mainFrame;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    // Main method to launch the application
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainClass window = new MainClass();
                    window.mainFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructor for the main class
    public MainClass() {
        initialize();
    }

    // Method to initialize the contents of the frame
    private void initialize() {
        mainFrame = new JFrame();
        mainFrame.setBounds(100, 100, 450, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(null);

        // Button to perform actions based on the selected radio button
        JButton btnNewButton = new JButton("Enter");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check which radio button is selected
                if (buttonGroup.getSelection() != null) {
                    String selectedRadioButton = buttonGroup.getSelection().getActionCommand();

                    // Use a switch statement to perform actions based on the selected radio button
                    switch (selectedRadioButton) {
                        case "Option1":
                            // Perform actions for Option1
                            FileClass Fc = new FileClass(); // Fc - allows for the user to input data
                            Fc.fileFrame.setVisible(true);
                            mainFrame.dispose();
                            break;
                        case "Option2":
                            // Perform actions for Option2
                            DerivateClass Df = new DerivateClass(""); // df is derivate frame
                            Df.derivateFrame.setVisible(true);
                            mainFrame.dispose();
                            break;
                        case "Option3":
                            // Perform actions for Option3
                            IntegralClass If = new IntegralClass(""); // If is integral frame
                            If.integralFrame.setVisible(true);
                            mainFrame.dispose();
                            break;
                        case "Option4":  // Update to handle the new radio button
                            // Perform actions for Option4
                            SeriesClass Sf = new SeriesClass(""); // Sf is series frame
                            Sf.seriesFrame.setVisible(true);
                            mainFrame.dispose();
                            break;
                        // Add more cases if needed
                        case "Option5":
                            System.exit(0); // Exit the program
                    }
                }
            }
        });
        btnNewButton.setBounds(133, 216, 85, 21);
        mainFrame.getContentPane().add(btnNewButton);

        // Radio buttons for different options
        JRadioButton rdbtnNewRadioButton = new JRadioButton("Enter Values");
        buttonGroup.add(rdbtnNewRadioButton);
        rdbtnNewRadioButton.setBounds(133, 70, 147, 34);
        mainFrame.getContentPane().add(rdbtnNewRadioButton);

        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Derivative ");
        buttonGroup.add(rdbtnNewRadioButton_1);
        rdbtnNewRadioButton_1.setBounds(133, 106, 103, 21);
        mainFrame.getContentPane().add(rdbtnNewRadioButton_1);

        JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Integral");
        buttonGroup.add(rdbtnNewRadioButton_2);
        rdbtnNewRadioButton_2.setBounds(133, 129, 103, 21);
        mainFrame.getContentPane().add(rdbtnNewRadioButton_2);

        // Set action commands for radio buttons to identify them uniquely
        rdbtnNewRadioButton.setActionCommand("Option1");
        rdbtnNewRadioButton_1.setActionCommand("Option2");
        rdbtnNewRadioButton_2.setActionCommand("Option3");

        JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Series");
        buttonGroup.add(rdbtnNewRadioButton_3);
        rdbtnNewRadioButton_3.setBounds(133, 152, 103, 21);
        mainFrame.getContentPane().add(rdbtnNewRadioButton_3);
        rdbtnNewRadioButton_3.setActionCommand("Option4");  // Set action command for the new radio button

        // Label for the welcome message
        JLabel lblNewLabel = new JLabel("Welcome to the Online Calculator");
        lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
        lblNewLabel.setBounds(22, 18, 455, 57);
        mainFrame.getContentPane().add(lblNewLabel);

        // Radio button for exiting the program
        JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("Exit");
        buttonGroup.add(rdbtnNewRadioButton_4);
        rdbtnNewRadioButton_4.setBounds(133, 175, 103, 21);
        mainFrame.getContentPane().add(rdbtnNewRadioButton_4);
        rdbtnNewRadioButton_4.setActionCommand("Option5");
    }
}
