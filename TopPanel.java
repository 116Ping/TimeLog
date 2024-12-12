package timeLogTry1;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javax.swing.*;

public class TopPanel extends JPanel {
    private final HashMap<String, Person> studentDatabase = new HashMap<>();
    private final HashMap<String, String[]> timeLogs = new HashMap<>();
    private final MainFrame mainFrame;

    public TopPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        
        this.setBackground(new Color(123,50,250));
        this.setLayout(null);
        this.setBounds(620, 170, 550, 150);
       
        studentDatabase.put("24117061", new Person("Nalang, Aenon C.", "CCS", "IT", "2th Year"));
        studentDatabase.put("24117061", new Person("Nalang, Aenon C.", "CCS", "IT", "2th Year"));
        studentDatabase.put("24117061", new Person("Nalang, Aenon C.", "CCS", "IT", "2th Year"));
        studentDatabase.put("24117061", new Person("Nalang, Aenon C.", "CCS", "IT", "2th Year"));
        studentDatabase.put("24117061", new Person("Nalang, Aenon C.", "CCS", "IT", "2th Year"));
        studentDatabase.put("24117061", new Person("Nalang, Aenon C.", "CCS", "IT", "2th Year"));
        studentDatabase.put("24117061", new Person("Nalang, Aenon C.", "CCS", "IT", "2th Year"));
        
        // Date-Time Label
        JLabel dateTimeLabel = new JLabel("", SwingConstants.CENTER);
        dateTimeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        dateTimeLabel.setBounds(705, 80, 400, 150);
        mainFrame.add(dateTimeLabel);

        // Update date-time every second
        Timer timer = new Timer(1000, e -> {
            LocalDateTime now = LocalDateTime.now();
            dateTimeLabel.setText(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        });
        timer.start();

        // Entry Panel
        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new GridLayout(2,0));
        entryPanel.setBackground(new Color(123,50,250));
        entryPanel.setBounds(25, 25, 500, 95);
        JLabel entryLabel = new JLabel("Enter ID Number: ");
        entryLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JTextField txtField = new JTextField(10);
        Font textFieldFont = new Font( "Arial", Font.PLAIN, 20);
        txtField.setFont(textFieldFont);
        JButton btnIn = new JButton("Time In");
        JButton btnOut = new JButton("Time Out");
        

        entryPanel.add(entryLabel);
        entryPanel.add(txtField);
        entryPanel.add(btnIn);
        entryPanel.add(btnOut);
        this.add(entryPanel);

        // Button Actions
        btnIn.addActionListener((ActionEvent e) -> {
            String id = txtField.getText().trim();
            if (studentDatabase.containsKey(id)) {
                String currentTime = getCurrentTime();
                timeLogs.put(id, new String[]{currentTime, null});
                // Update textPane and append to history
                String newMessage = "Time In recorded.\n\n" + getStudentDetails(id) + "\nTime In: " + currentTime + "\n\n";
                mainFrame.textPane.setText(mainFrame.textPane.getText() + "\n" + newMessage);  // Update main text
                updateHistory("ID: " + id + " - Time In: " + currentTime);  // Update history
            } else {
                // Display error message and update history
                mainFrame.textPane.setText(mainFrame.textPane.getText() + "\nInvalid Student ID.");
                updateHistory("Invalid Student ID attempt: " + id);  // Log invalid ID attempt
            }
        });

        btnOut.addActionListener((ActionEvent e) -> {
            String id = txtField.getText().trim();
            if (studentDatabase.containsKey(id)) {
                if (timeLogs.containsKey(id) && timeLogs.get(id)[0] != null) {
                    String currentTime = getCurrentTime();
                    timeLogs.get(id)[1] = currentTime;
                    // Update textPane and append to history
                    String newMessage = "Time Out recorded.\n\n" + getStudentDetails(id)
                            + "\nTime In: " + timeLogs.get(id)[0]
                            + "\nTime Out: " + currentTime;
                    mainFrame.textPane.setText(mainFrame.textPane.getText() + "\n" + newMessage);  // Update main text
                    updateHistory("ID: " + id + " - Time Out: " + currentTime);  // Update history
                } else {
                    // Display error message and update history
                    mainFrame.textPane.setText(mainFrame.textPane.getText() + "\nTime In not recorded yet.");
                    updateHistory("ID: " + id + " - Error: Time In not recorded.");
                }
            } else {
                // Display error message and update history
                mainFrame.textPane.setText(mainFrame.textPane.getText() + "\nInvalid Student ID.");
                updateHistory("Invalid Student ID attempt: " + id);  // Log invalid ID attempt
            }
        });
    }

    private String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private String getStudentDetails(String id) {
        Person student = studentDatabase.get(id);
        return "Name: " + student.getName() + "\nDepartment: " + student.getDepartment() +
               "\nCourse: " + student.getCourse() + "\nYear Level: " + student.getYrlvl();
    }
    
    
    
    
    public void updateHistory(String message) {
        mainFrame.historyArea.append(message + "\n"); 
    }
}
