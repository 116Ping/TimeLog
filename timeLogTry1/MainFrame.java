package timeLogTry1;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class MainFrame extends JFrame {
    TopPanel tp;
    JTextPane textPane = new JTextPane();
    JTextArea historyArea = new JTextArea();

    public MainFrame() {
        this.setTitle("Time Log");
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(123, 50, 250));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Title
        JLabel headerLabel = new JLabel("Time Log Application", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 55));
        headerLabel.setBounds(550, 20, 600, 70);

        // textPane style
        textPane.setBackground(new Color(193, 173, 228));
        textPane.setFont(new Font("Arial", Font.PLAIN, 20));
        textPane.setEditable(false);

        // Apply scroll to textPane
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(850, 340, 480, 350);

        // Style historyArea
        historyArea.setBackground(new Color(193, 173, 228));
        historyArea.setFont(new Font("Arial", Font.PLAIN, 20));
        historyArea.setEditable(false);
        historyArea.setLineWrap(true);
        historyArea.setWrapStyleWord(true);
        JScrollPane historyScroll = new JScrollPane(historyArea);
        historyScroll.setBounds(2, 2, 350, 680);

        // Download button
        JButton downloadButton = new JButton("Download");
        downloadButton.setFont(new Font("Arial", Font.BOLD, 14));
        downloadButton.setBounds(80, 700, 180, 40);
        downloadButton.addActionListener(e -> saveTextFile());

        // Exit button
        JButton Exit = new JButton("Exit");
        Exit.setFont(new Font("Arial", Font.BOLD, 14));
        Exit.setBounds(700, 700, 120, 40);
        Exit.addActionListener(e -> System.exit(0));

        // Clear button
        JButton clearBtn = new JButton("Clear");
        clearBtn.setFont(new Font("Arial", Font.BOLD, 14));
        clearBtn.setBounds(870, 700, 120, 40);
        clearBtn.addActionListener(e -> clear());

        tp = new TopPanel(this);

        this.add(headerLabel);
        this.add(tp);
        this.add(scrollPane);
        this.add(historyScroll);
        this.add(downloadButton);
        this.add(Exit);
        this.add(clearBtn);

        this.setVisible(true);
    }

    private void clear() {
        textPane.setText("");
        historyArea.setText("");
        tp = new TopPanel(this);
    }

    private void saveTextFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save History");
        fileChooser.setSelectedFile(new File("history.txt"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (FileWriter writer = new FileWriter(fileToSave)) {
                writer.write(historyArea.getText());
                JOptionPane.showMessageDialog(this, "History saved successfully!", "Save Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving history: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateHistory(String message) {
        historyArea.append(message + "\n");
    }
}