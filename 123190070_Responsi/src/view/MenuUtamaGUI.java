/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class MenuUtamaGUI {
   //DEKLARASI KOMPONEN
  JFrame window = new JFrame("Menu Utama");
  JButton btnInput = new JButton("Tambah Barang");
  JButton btnShow = new JButton("Lihat dan Update Barang");
  
  public MenuUtamaGUI(){
    window.setLayout(null);
    window.setSize(380, 190);
    window.setVisible(true);
    window.setLocationRelativeTo(null); // center
    window.setResizable(false);
    window.setDefaultCloseOperation(EXIT_ON_CLOSE); // running program berhenti jika tombol close ditekan
    
//ADD COMPONENT
    window.add(btnInput);
    window.add(btnShow);

// SETT BOUNDS
// sett bounds(m,n,o,p) >>> (sumbu-x,sumbu-y,panjang komponen, tinggi komponen)
    btnInput.setBounds(100, 35, 180, 30);
    btnShow.setBounds(100, 75, 180, 30);
    
// CUSTOMIZE
    window.getContentPane().setBackground( new Color(50, 50, 50) );
  
    btnInput.setFocusPainted(false);
    btnShow.setFocusPainted(false);
    btnShow.setBackground(new Color(238, 238, 238));
    btnInput.setBackground(new Color(13, 115, 119));
    btnInput.setForeground(Color.WHITE);
    btnInput.setFont(new Font("Tahoma", Font.BOLD, 12));
    
// ACTION LISTENER
    btnInput.addActionListener((ActionEvent arg0) -> {
      window.dispose();
      new TambahBarangGUI();
    });
    btnShow.addActionListener((ActionEvent arg0) -> {
      window.dispose();
      new LihatBarangGUI();
    });
  }
}
