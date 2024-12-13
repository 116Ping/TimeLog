package timeLogTry1;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javax.swing.*;

public class TopPanel extends JPanel {
    private final HashMap<String, Person> studentDatabase = new HashMap<>();
    private final HashMap<String, String[]> timeLogs = new HashMap<>();
    private final MainFrame mainFrame;
    private final JLabel studentImageLabel = new JLabel();

    public TopPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // Set panel properties
        this.setBackground(new Color(123, 50, 250));
        this.setLayout(null);
        this.setBounds(570, 170, 550, 150);

        // Student image label
        studentImageLabel.setBounds(490, 400, 200, 200);
        mainFrame.add(studentImageLabel);

        // Populate student database
        studentDatabase.put("24117061", new Person("Nalang, Aenon C.", "CCS", "IT", "2nd Year", "nalang.jpg"));
        studentDatabase.put("24115966", new Person("Rago, Yrll John D.", "CCS", "IT", "2nd Year", "rago.jpg"));
        studentDatabase.put("2022100206", new Person("Garay, Kishelle A.", "CCS", "IT", "2nd Year", "garay.jpg"));
        studentDatabase.put("23068851", new Person("Cabatana, Laica U.", "CCS", "IT", "2nd Year", "cabatana.jpg"));

        // Entry panel
        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new GridLayout(2, 0));
        entryPanel.setBackground(new Color(123, 50, 250));
        entryPanel.setBounds(25, 25, 500, 95);

        JLabel entryLabel = new JLabel("Enter ID Number: ");
        entryLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JTextField txtField = new JTextField(10);
        txtField.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton btnIn = new JButton("Time In");
        JButton btnOut = new JButton("Time Out");

        entryPanel.add(entryLabel);
        entryPanel.add(txtField);
        entryPanel.add(btnIn);
        entryPanel.add(btnOut);
        this.add(entryPanel);

        // Button actions
        btnIn.addActionListener(e -> handleButtonClick(txtField.getText().trim(), true));
        btnOut.addActionListener(e -> handleButtonClick(txtField.getText().trim(), false));
    }

    private void handleButtonClick(String id, boolean isTimeIn) {
        if (studentDatabase.containsKey(id)) {
            Person student = studentDatabase.get(id);
            String currentTime = getCurrentTime();

            if (isTimeIn) {
                timeLogs.put(id, new String[]{currentTime, null});
                updateStudentDetails(student, "Time In", currentTime);
            } else {
                if (timeLogs.containsKey(id) && timeLogs.get(id)[0] != null) {
                    timeLogs.get(id)[1] = currentTime;
                    updateStudentDetails(student, "Time Out", currentTime);
                } else {
                    mainFrame.textPane.setText(mainFrame.textPane.getText() + "\nTime In not recorded yet.");
                }
            }

            updateStudentImage(student.getImagePath());
        } else {
            mainFrame.textPane.setText(mainFrame.textPane.getText() + "\nInvalid Student ID.");
            updateStudentImage(null);
        }
    }

    private void updateStudentDetails(Person student, String action, String time) {
        String details = action + " recorded.\n\n" + getStudentDetails(student) + "\n" + action + ": " + time;
        mainFrame.textPane.setText(mainFrame.textPane.getText() + "\n" + details);
        mainFrame.updateHistory("Name: " +student.getName() + " - " + action + ": " + time);
    }

    private void updateStudentImage(String imagePath) {
        if (imagePath != null) {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image scaledImage = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            studentImageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            studentImageLabel.setIcon(null);
        }
        studentImageLabel.repaint();
    }

    private String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private String getStudentDetails(Person student) {
        return "Name: " + student.getName() +
                "\nDepartment: " + student.getDepartment() +
                "\nCourse: " + student.getCourse() +
                "\nYear Level: " + student.getYrlvl();
    }

}
