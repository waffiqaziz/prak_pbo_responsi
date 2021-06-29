/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import barang.Barang;
import control.ControlBarang;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class EditBarangGUI {
  //DEKLARASI KOMPONEN
  JFrame window = new JFrame("Edit Data Barang");
  JLabel lNama = new JLabel("Nama");
  JLabel lMassa = new JLabel("Massa (gr)");
  JLabel lHarga = new JLabel("Harga");

  JTextField tfNama = new JTextField(200);
  JTextField tfMassa = new JTextField();
  JTextField tfHarga = new JTextField();

  JButton btnEdit = new JButton("Edit");
  JButton btnReset = new JButton("Reset");
  JButton btnBack = new JButton("Kembali");
  
  ControlBarang controlEdit = new ControlBarang();
  public EditBarangGUI(Barang b) {
    window.setLayout(null);
    window.setSize(380, 270);
    window.setVisible(true);
    window.setLocationRelativeTo(null); // center
    window.setResizable(false);
    window.setDefaultCloseOperation(EXIT_ON_CLOSE); // running program berhenti jika tombol close ditekan

//ADD COMPONENT
    window.add(lNama);
    window.add(lMassa);
    window.add(lHarga);
    window.add(tfMassa);
    window.add(tfNama);
    window.add(tfHarga);
    window.add(btnEdit);
    window.add(btnReset);
    window.add(btnBack);

// SETT BOUNDS
// sett bounds(m,n,o,p) >>> (sumbu-x,sumbu-y,panjang komponen, tinggi komponen)
    lNama.setBounds(80, 35, 70, 30);
    lMassa.setBounds(80, 75, 70, 30);
    lHarga.setBounds(80, 115, 70, 30);

    tfNama.setBounds(170, 35, 120, 30);
    tfMassa.setBounds(170, 75, 120, 30);
    tfHarga.setBounds(170, 115, 120, 30);

    btnEdit.setBounds(240, 175, 80, 30);
    btnReset.setBounds(150, 175, 80, 30);
    btnBack.setBounds(60, 175, 80, 30);
    
// CUSTOMIZE
    lNama.setForeground(Color.WHITE);
    lMassa.setForeground(Color.WHITE);
    lHarga.setForeground(Color.WHITE);
    window.getContentPane().setBackground( new Color(50, 50, 50) );
    btnBack.setBackground(new Color(249, 237, 105) );
    btnEdit.setBackground(new Color(13, 115, 119));
    btnEdit.setForeground(Color.WHITE);
    btnReset.setBackground(new Color(232, 69, 69));
    
    // sett mouse pointer
    lNama.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lMassa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lHarga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    // SETT TEXT
    tfHarga.setText(String.valueOf(b.getHarga()));
    tfNama.setText(b.getNama());
    tfMassa.setText(String.valueOf(b.getMassa()));
    
    System.out.println("id edit " + b.getId());
// ACTION LISTENER
    btnEdit.addActionListener((var arg0) -> {
      String nama = tfNama.getText();
      int massa = 0, harga = 0;;
      try {
        harga = Integer.valueOf(tfHarga.getText());
        massa = Integer.valueOf(tfMassa.getText());

        if (nama.isEmpty()) {
          JOptionPane.showMessageDialog(null, "Kolom Nama Harap Di isi");
          tfNama.requestFocusInWindow();
        }else if(harga < 0 || massa < 0){
           JOptionPane.showMessageDialog(null,"Input Massa dan Harga Harus Bilangan Positif", "Pesan Kesalahan", JOptionPane.INFORMATION_MESSAGE);
           tfMassa.requestFocus();
        }else{
          b.setBarang(nama, massa, harga); // set barang
          if (controlEdit.edit(b)) {
            window.dispose();
            new LihatBarangGUI();
            JOptionPane.showMessageDialog(null, "Edit Data Berhasil");
          }
        }
        
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Pastikan Semua Kolom Sudah Di isi" + "\nInput Massa dan Harga Harus Numeric Positif", "Pesan Kesalahan", JOptionPane.INFORMATION_MESSAGE);
      }
    });

    btnReset.addActionListener((ActionEvent arg0) -> {
      tfNama.setText("");
      tfMassa.setText("");
      tfHarga.setText("");
    });
    btnBack.addActionListener((ActionEvent arg0) -> {
      window.dispose();
      new DetailBarangGUI(b);
    });

// MOUSE LISTENER
    lNama.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        tfNama.requestFocusInWindow();
      }
    });
    lMassa.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        tfMassa.requestFocusInWindow();
      }
    });
    lHarga.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        tfHarga.requestFocusInWindow();
      }
    });

    window.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.out.println("Closed");
      }
    });

    tfNama.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          btnEdit.doClick();
        }
      }
    });
    tfMassa.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          btnEdit.doClick();
        }
      }
    });
    tfHarga.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          btnEdit.doClick();
        }
      }
    });
  }
}
