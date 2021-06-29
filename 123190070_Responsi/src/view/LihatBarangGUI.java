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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class LihatBarangGUI {
  //DEKLARASI KOMPONEN
  JFrame window = new JFrame("Lihat Data");
  JTable tabel;
  DefaultTableModel tableModel;
  JScrollPane scrollPane;
  Object namaKolom[] = {"ID", "Nama Barang", "Massa (gr)", "Harga/satuan"};
  JButton btnBack = new JButton("Kembali");
  
  JLabel lHint = new JLabel("*Klik Baris Tabel Sesuai Barang yang Akan Dilihat");
  
  ControlBarang rd = new ControlBarang();
  Barang b = new Barang();
  public LihatBarangGUI() {
    window.setLayout(null);
    window.setSize(550, 470);
    window.setVisible(true);
    window.setLocationRelativeTo(null);
    window.setResizable(false);
    window.setDefaultCloseOperation(EXIT_ON_CLOSE); // running program berhenti jika tombol close ditekan
    
// SET BOUNDS
    // sett bounds(m,n,o,p) >>> (sumbu-x,sumbu-y,panjang komponen, tinggi komponen)
    btnBack.setBounds(230, 375, 80, 30);
    lHint.setBounds(20, 330, 300, 30);
    
    if(rd.readDataBarang()== null){
      JOptionPane.showMessageDialog(null, "Tidak Ada Data");
      tabel = new JTable(null, namaKolom); //tabel merupakan variabel untuk tabelnya dengan isi tablemodel
    }else{
      tabel = new JTable(rd.readDataBarang(), namaKolom); //tabel merupakan variabel untuk tabelnya dengan isi tablemodel
    }
    
    scrollPane = new JScrollPane(tabel);
    window.add(scrollPane);
    window.add(btnBack);
    window.add(lHint);
    
    scrollPane.setBounds(20, 35, 500, 300);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    
    tabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
// CUSTOMIZE
    btnBack.setBackground(new Color(249, 237, 105) );
    lHint.setForeground(Color.white);
    window.getContentPane().setBackground( new Color(50, 50, 50) );
    
// LISTENER
    btnBack.addActionListener((ActionEvent arg0) -> {
      window.dispose();
      new MenuUtamaGUI();
    });
    
    tabel.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        int row = tabel.rowAtPoint(evt.getPoint());
        int col = tabel.columnAtPoint(evt.getPoint());
        System.out.println("row " + row);
        if(rd.getData(b,row)){
          System.out.println("id Lihat " + b.getId());
          window.dispose();
          new DetailBarangGUI(b);
        }
      }
    });
    window.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.out.println("Closed");
      }
    });
  }
}
