package timeLogTry1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        this.getContentPane().setBackground(new Color(123,50,250));
//        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        
        //title
        JLabel headerLabel = new JLabel("Time Log Application", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 55));  
        headerLabel.setBounds(585, 20, 600,70);  
        
 
        //textPane style
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("center", null);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, 0, style, false);
        textPane.setBackground(new Color(193,173,228));
        Font txtPaneFont = new Font("Arial", Font.PLAIN, 20);
        textPane.setEditable(false);
        textPane.setFont(txtPaneFont);
        

        //apply scroll to textPane
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(550, 340, 680, 350);  
        
        
        //style historyArea
        Font txtaFont = new Font("Arial", Font.PLAIN, 20);
        historyArea.setBackground(new Color(193,173,228));
        historyArea.setFont(txtaFont);
        historyArea.setEditable(false);  
        historyArea.setLineWrap(true);   
        historyArea.setWrapStyleWord(true);  
        JScrollPane historyScroll = new JScrollPane(historyArea);//apply scroll to historyArea
        historyScroll.setBounds(2, 2, 430, 742);
        
        
        //Clear Button
        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(850,700 , 100, 40);
        
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
    
}
