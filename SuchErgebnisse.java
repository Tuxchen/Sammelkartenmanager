import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Button;

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

        JLabel suchleiste = new JLabel("Geb das Pokemon ein das du suchen möchtest ");

        JLabel lableTitel = new JLabel("Pokemon Sucher");
        lableTitel.setBackground(Color.PINK);
        lableTitel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        textField = new JTextField();
        textField.setBackground(new Color(255, 213, 234));
        textField.setColumns(10);

        JButton suchButton = new JButton("Suchen");
        suchButton.setBackground(new Color(255, 213, 234));

        // JtextArea shows result 
        resultArea = new JTextArea();
        resultArea.setTabSize(5);
        resultArea.setEditable(false); // Only Readingmode so you cant chance it 
        JScrollPane scrollPane = new JScrollPane(resultArea);
        JButton favoritenButton = new JButton("Zu Favoriten hinzufügen");
        // ActionListener für den Button
        suchButton.addActionListener(e -> {
            String name = textField.getText(); // Takes Text from textField
            Pokemon result = Main.getCardData(name); // looking for the Pokemon 
            
            // shows result 
            if (result != null) {
                resultArea.setText("Name: " + result.getName() + "\n" +
                        "Set: " + result.getSet() + "\n"+
                        "Seltenheit: " + result.getRarity() + "\n"+
                        "Preis: " + result.getPrice());
                
                try {
                    // Extracts Bild-URL
                    URL imageUrl = new URL(result.getImageUrl()); 
                    ImageIcon imageIcon = new ImageIcon(imageUrl);
                    
                    //  Picture Size  
                    Image image = imageIcon.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                favoritenButton.setEnabled(true);
                favoritenButton.addActionListener(e2->
                { Favoriten.addToFavorite(result);
                JOptionPane.showMessageDialog(null, "Pokemon hinzugefügt");});
            } else {
                resultArea.setText("Karte nicht gefunden.");
            }
        });
        
        JScrollBar scrollBar = new JScrollBar();
        
       
        
//Add Everything to Frame made by GUI Builder
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(lableTitel)
        				.addComponent(suchleiste, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(suchButton)
        					.addPreferredGap(ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
        					.addComponent(favoritenButton)
        					.addGap(57))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(20)
        					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(lableTitel)
        			.addGap(18)
        			.addComponent(suchleiste, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(suchButton)
        				.addComponent(favoritenButton))
        			.addGap(336)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        			.addContainerGap())
        		.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
        			.addGap(95)
        			.addComponent(scrollBar, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE))
        );

        contentPane.setLayout(gl_contentPane);
    }
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}