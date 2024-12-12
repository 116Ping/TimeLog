package nalang;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class MainFrame extends JFrame {
    TopPanel tp;
    JTextPane textPane = new JTextPane();
    JTextArea historyArea = new JTextArea();
    
    public MainFrame() {
        
        this.setTitle("Time Log");
        this.setSize(600, 650);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(123, 50, 250));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        //title
        JLabel headerLabel = new JLabel("Time Log Application", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 55));  
        headerLabel.setBounds(585, 20, 600, 70); 
        
        //download button
        JButton downloadButton = new JButton("Download");
        downloadButton.setFont(new Font("Arial", Font.BOLD, 14));
        downloadButton.setBounds(750, 700, 120, 40);
        this.add(downloadButton);
        downloadButton.addActionListener(e -> saveTextFile());
        
        //textPane style
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("center", null);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, 0, style, false);
        textPane.setBackground(new Color(193, 173, 228));
        Font txtPaneFont = new Font("Arial", Font.PLAIN, 20);
        textPane.setEditable(false);
        textPane.setFont(txtPaneFont);
        
        //apply scroll to textPane
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(550, 340, 680, 350);  
        
        //style historyArea
        Font txtaFont = new Font("Arial", Font.PLAIN, 20);
        historyArea.setBackground(new Color(193, 173, 228));
        historyArea.setFont(txtaFont);
        historyArea.setEditable(false);  
        historyArea.setLineWrap(true);   
        historyArea.setWrapStyleWord(true);  
        JScrollPane historyScroll = new JScrollPane(historyArea);
        historyScroll.setBounds(2, 2, 430, 742);
        
        //Clear Button
        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(900, 700 , 120, 40);
        
        clearBtn.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                clear(); 
            }  
        });  

        tp = new TopPanel(this);
        
        this.add(clearBtn);
        this.add(headerLabel);
        this.add(tp);
        this.add(scrollPane);
        this.add(historyScroll);
        
        this.setVisible(true);
    }

    private void clear() {
        textPane.setText("");
        historyArea.setText("");
    }
    
    private void saveTextFile() {
        // Open file chooser to select save location
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save History");
        fileChooser.setSelectedFile(new File("history.txt"));
        
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (FileWriter writer = new FileWriter(fileToSave)) {
                writer.write(historyArea.getText());  // Save the content of historyArea
                JOptionPane.showMessageDialog(this, "History saved successfully!", "Save Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving history: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
