import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.net.URL;

public class SuchErgebnisse extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea resultArea;
    private JLabel imageLabel; // Bild Label

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

    public SuchErgebnisse() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 500); // Fenstergröße
        contentPane = new JPanel();
        contentPane.setBackground(Color.PINK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel suchleiste = new JLabel("Gib das Pokemon ein, das du suchen möchtest");
        JLabel lableTitel = new JLabel("Pokemon Sucher");
        lableTitel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        textField = new JTextField();
        textField.setColumns(10);

        JButton suchButton = new JButton("Suchen");
        suchButton.setBackground(new Color(255, 213, 234));

        // JTextArea für die Ergebnisse
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        JButton favoritenButton = new JButton("Zu Favoriten hinzufügen");

        // imageLabel für das Bild
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(200, 300)); // Bevorzugte Bildgröße

        // ActionListener für den SuchButton
        suchButton.addActionListener(e -> {
            String name = textField.getText();
            Pokemon result = Main.getCardData(name); // Pokemon suchen

            if (result != null) {
                resultArea.setText("Name: " + result.getName() + "\n" +
                        "Set: " + result.getSet() + "\n" +
                        "Seltenheit: " + result.getRarity() + "\n" +
                        "Preis: " + result.getPrice());

                try {
                    // Bild-URL extrahieren
                    URL imageUrl = new URL(result.getImageUrl());
                    ImageIcon imageIcon = new ImageIcon(imageUrl);

                    // Bild skalieren
                    Image image = imageIcon.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(image)); // Bild im Label setzen
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                // Favoriten-Button aktivieren
                favoritenButton.setEnabled(true);
                favoritenButton.addActionListener(e2 -> {
                    Favoriten.addToFavorite(result);
                    JOptionPane.showMessageDialog(null, "Pokemon hinzugefügt");
                });
            } else {
                resultArea.setText("Karte nicht gefunden.");
            }
        });

        // Layout für das Fenster
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(lableTitel)
                        .addComponent(suchleiste)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(suchButton)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(favoritenButton))
                        .addComponent(imageLabel) // Bild in das Layout einfügen
                        .addComponent(scrollPane))
                    .addContainerGap())
        );

        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(lableTitel)
                    .addGap(18)
                    .addComponent(suchleiste)
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(suchButton)
                        .addComponent(favoritenButton))
                    .addGap(18)
                    .addComponent(imageLabel) // Bild unter den anderen Komponenten anzeigen
                    .addGap(18)
                    .addComponent(scrollPane)
                    .addContainerGap())
        );

        contentPane.setLayout(gl_contentPane);
    }
}
