import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.io.IOException;

public class SeriesClass {

    JFrame seriesFrame;

    /**
     * @wbp.parser.entryPoint
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SeriesClass window = new SeriesClass("");
                window.seriesFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @wbp.parser.entryPoint
     */
    public SeriesClass(String fileName) {
        initialize(fileName);
    }

    /**
     * @wbp.parser.entryPoint
     */
    private void initialize(String fileName) {
    	seriesFrame = new JFrame();
    	seriesFrame.setTitle("Integral Application");
    	seriesFrame.setBounds(100, 100, 450, 300);
    	seriesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        seriesFrame.getContentPane().add(panel);

        JButton returnButton = new JButton("Return to menu");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Handle button click event
            	MainClass Mc = new MainClass(); // Mc - allows for the user to input data
                Mc.mainFrame.setVisible(true);
                seriesFrame.dispose();
            }
        });

        JLabel titleLabel = new JLabel("Series Classes");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SeriesCalculator seriesCalculator = new SeriesCalculator();
				double[] coefficients = integralCalculator.readCoefficientsFromFile(fileName);
				double[] integralCoefficients = integralCalculator.calculateIntegral(coefficients);
				String result = integralCalculator.getPolynomialString(integralCoefficients);
				JOptionPane.showMessageDialog(seriesFrame, "Series: " + result);
            }
        });

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGap(126)
                            .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGap(151)
                            .addComponent(calculateButton))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGap(140)
                            .addComponent(returnButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(158, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, gl_panel.createSequentialGroup()
                    .addGap(27)
                    .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                    .addComponent(calculateButton)
                    .addGap(18)
                    .addComponent(returnButton)
                    .addGap(29))
        );
        panel.setLayout(gl_panel);
    }
}
