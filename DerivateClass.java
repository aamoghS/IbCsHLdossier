import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.io.IOException;
import java.io.RandomAccessFile;
public class DerivateClass {

    JFrame derivateFrame;
DerivativeCalculator derivativeCalculator = new DerivativeCalculator();
double[] coefficients; 
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DerivateClass window = new DerivateClass("")
                    window.derivateFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public DerivateClass(String fileName) {
        initialize(fileName);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(String fileName) {
        derivateFrame = new JFrame();
        derivateFrame.setTitle("Derivate Application");
        derivateFrame.setBounds(100, 100, 450, 300);
        derivateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        derivateFrame.getContentPane().add(panel);

        JButton btnNewButton = new JButton("Return to menu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Handle button click event
            	MainClass Mc = new MainClass(); // Fc - allows for the user to input data
                Mc.mainFrame.setVisible(true);
                derivateFrame.dispose();
            }
        });

        JLabel lblNewLabel = new JLabel("Derivate Application");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JButton btnNewButton_1 = new JButton("Calculate");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            
                try {
                    coefficients = derivativeCalculator.readCoefficientsFromFile(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            double[] derivativeCoefficients = derivativeCalculator.calculateDerivative(coefficients);
            String result = derivativeCalculator.getPolynomialString(derivativeCoefficients);
				JOptionPane.showMessageDialog(derivateFrame, "Derivative:" + result );
            }
        });

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGap(126)
                            .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGap(151)
                            .addComponent(btnNewButton_1))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGap(140)
                            .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(158, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                    .addGap(27)
                    .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                    .addComponent(btnNewButton_1)
                    .addGap(18)
                    .addComponent(btnNewButton)
                    .addGap(29))
        );
        panel.setLayout(gl_panel);
    }
}
