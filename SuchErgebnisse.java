import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;

public class SuchErgebnisse extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea resultArea;

    
     //Launch the application.
     
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SuchErgebnisse frame = new SuchErgebnisse();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
     //Create the frame.
     
    public SuchErgebnisse() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.PINK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Geb das Pokemon ein das du suchen möchtest ");

        JLabel lblNewJgoodiesTitle = new JLabel("Pokemon Sucher");
        lblNewJgoodiesTitle.setBackground(Color.PINK);
        lblNewJgoodiesTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));

        textField = new JTextField();
        textField.setBackground(new Color(255, 213, 234));
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Suchen");
        btnNewButton.setBackground(new Color(255, 213, 234));

        // Erstelle ein JTextArea, um die Ergebnisse anzuzeigen
        resultArea = new JTextArea();
        resultArea.setEditable(false); // Nur Lesemodus
        JScrollPane scrollPane = new JScrollPane(resultArea); // Scrollbar hinzufügen

        // ActionListener für den Button
        btnNewButton.addActionListener(e -> {
            String name = textField.getText(); // Nimm den Text aus dem Textfeld
            Pokemon result = Main.getPokemonData(name); // Suche das Pokémon
            
            // Zeige das Ergebnis an
            if (result != null) {
            
                resultArea.setText("Name: " + result.getName() + "\n" +
                        "Typ: " + result.getType() + "\n");
            } else {
                resultArea.setText("Pokemon nicht gefunden.");
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblNewJgoodiesTitle)
        				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(btnNewButton)
        					.addContainerGap(268, Short.MAX_VALUE))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(20)
        					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
        					.addContainerGap())))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(lblNewJgoodiesTitle)
        			.addGap(18)
        			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnNewButton))
        			.addGap(18)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        			.addContainerGap())
        );

        contentPane.setLayout(gl_contentPane);
    }
}
