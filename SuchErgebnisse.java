import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
        resultArea.setTabSize(5);
        resultArea.setEditable(false); // Nur Lesemodus
        JScrollPane scrollPane = new JScrollPane(resultArea); // Scrollbar hinzufügen
        
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(200,300));

        // ActionListener für den Button
        btnNewButton.addActionListener(e -> {
            String name = textField.getText(); // Nimm den Text aus dem Textfeld
            Pokemon result = Main.getCardData(name); // Suche das Pokémon
            
            // Zeige das Ergebnis an
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
                    imageLabel.setIcon(new ImageIcon(image));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                resultArea.setText("Karte nicht gefunden.");
            }
        });
        
        JScrollBar scrollBar = new JScrollBar();

//Layout for Frame -> made with GUIwindowBuilder so no idea wtf this means lol 
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblNewJgoodiesTitle)
        						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(btnNewButton))
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addGap(20)
        							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        							.addPreferredGap(ComponentPlacement.RELATED))))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(20)
        					.addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(6)
        			.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(lblNewJgoodiesTitle)
        			.addGap(18)
        			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(btnNewButton))
        					.addGap(18)
        					.addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        
        JPopupMenu popupMenu = new JPopupMenu();
        addPopup(imageLabel, popupMenu);
        
        JButton btnNewButton_1 = new JButton("Favoriten");
        popupMenu.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Home");
        popupMenu.add(btnNewButton_2);
        
        Button button = new Button("New button");
        popupMenu.add(button);

        contentPane.setLayout(gl_contentPane);
    }
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}