/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import source.service.ImageFile;

/**
 *
 * @author KELECHI
 */
public class ScorePage extends JFrame implements ActionListener {
    private JLabel background, scoreLabel, scoreSheet, smiley;
    private JButton okButton;
    
    public ScorePage(float finalScore){
        initializeComponents(finalScore);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        LoginPage lp = new LoginPage();
        dispose();
    }
    
    private void initializeComponents(float finalScore){
        ImageIcon icon = new ImageFile().getIcon("Background.png", 500, 400);
        background = new JLabel();
        background.setIcon(icon);
        scoreLabel = new JLabel("Your score");
        scoreLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        scoreLabel.setBounds(50,50,200,50);
        
        scoreSheet = new JLabel(finalScore+"%");
        scoreSheet.setFont(new Font("Courier New", Font.BOLD, 40));
        scoreSheet.setBounds(50,100,400,50);
        
        smiley = new JLabel();
        smiley.setBounds(40,160,150,150);
        ImageIcon faceIcon;
        if(finalScore > 55){
            faceIcon = new ImageFile().getIcon("Smile.png", 150, 150);
        }else{
            faceIcon = new ImageFile().getIcon("Frown.png", 150, 150);
        }
        smiley.setIcon(faceIcon);
        
        okButton = new JButton("HOME");
        okButton.setFont(new Font("Courier New", Font.BOLD, 30));
        okButton.setBounds(330,310,150,50);
        okButton.addActionListener(this);
        okButton.setOpaque(true);
        okButton.setForeground(Color.WHITE);
        okButton.setBackground(new Color(91,155,213));
        
        background.add(scoreLabel);
        background.add(scoreSheet);
        background.add(smiley);
        background.add(okButton);
        
        setLayout(new BorderLayout());
        add(background);
        
        Image image = new ImageFile().getImage("Title.png");
        this.setIconImage(image);
        setSize(500,400);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
