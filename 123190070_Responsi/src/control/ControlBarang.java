/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import barang.Barang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class ControlBarang {
  public boolean input(Barang b) {
    String query = "INSERT INTO `barang`(`nama`, `massa`, `harga`) VALUES (?,?,?)";
     PreparedStatement ps;  
    
     try {
      MyConnection myConnection = new MyConnection();
      ps = myConnection.getConnection().prepareStatement(query);
      ps.setString(1, b.getNama());
      ps.setInt(2, b.getMassa());
      ps.setInt(3, b.getHarga());

      // jika berhasil
      if (ps.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException ex) {
      Logger.getLogger(ControlBarang.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
    return false;
  }
  
  private int countRow() {
    String query = "SELECT * FROM `barang`";
    PreparedStatement ps;
    ResultSet rs;

    int n = 0;
    try {
      MyConnection myConnection = new MyConnection();
      ps = myConnection.getConnection().prepareStatement(query);
      rs = ps.executeQuery();

      while (rs.next()) {
        n++;
      }
      return n ;

    } catch (SQLException ex) {
      Logger.getLogger(ControlBarang.class.getName()).log(Level.SEVERE, null, ex);
    }
    return -4;
  }
  
  public String[][] readDataBarang() {
    String query = "SELECT * FROM `barang`";
    PreparedStatement ps;
    ResultSet rs;
    
    // untuk menghitung jumlah baris
    int cr = countRow();
    
    // untuk menyimpan data
    String data[][] = new String[cr][4];

    try {
      MyConnection myConnection = new MyConnection();
      ps = myConnection.getConnection().prepareStatement(query);
      rs = ps.executeQuery();

      int n = 0; // untuk menjumahkan baris(row)
      while (rs.next()) { //konversi tabel ke string
        data[n][0] = rs.getString(1);
        data[n][1] = rs.getString(2);
        data[n][2] = rs.getString(3);
        data[n][3] = rs.getString(4);
        n++;
      }
      return data;
    } catch (SQLException ex) {
      System.out.println("Read Data Gagal");
      Logger.getLogger(ControlBarang.class.getName()).log(Level.SEVERE, null, ex);
      return null;
    } 
  }
  
  public boolean getData(Barang b, int n){
    // query untuk baris tertentu
    String query = "SELECT * FROM `barang` LIMIT " + String.valueOf(n) + ",1";
    PreparedStatement ps;
    ResultSet rs;
    
    try {
      MyConnection myConnection = new MyConnection();
      ps = myConnection.getConnection().prepareStatement(query);
      
      rs = ps.executeQuery();
      if (rs.next()) {
        int id = rs.getInt(1);
        String nama = rs.getString(2);
        int massa = rs.getInt(3);
        int harga = rs.getInt(4);
        
        // SET DATA
        b.setBarang(nama, massa, harga);
        b.setId(id);
        System.out.println("id getDAta " + b.getId());
        System.out.println(b.getNama());
        System.out.println(b.getMassa());
        System.out.println(b.getHarga());
        System.out.println("---------------------");
        return true;
      }
    } catch (SQLException ex) {
      Logger.getLogger(ControlBarang.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
    return false;
  }
  
  public String calculateDiscount(int jml,int harga){
    if(jml < 12){
      harga = harga * jml;
    } else if (jml < 20) {
      harga = jml * harga - (jml * harga * 5 / 100);
    } else if (jml < 144) {
      harga = jml * harga - (jml * harga * 10 / 100);
    } else {
      harga = jml * harga - (jml * harga * 25 / 100);
    }
    return String.valueOf(harga);
  }
  
  public boolean edit(Barang b){
    String query = "UPDATE `barang` SET `nama`=?,`massa`=?,`harga`=? WHERE `id`=?";
    PreparedStatement ps;
     System.out.println("idEdit " + b.getId());
     try {
      MyConnection myConnection = new MyConnection();
      ps = myConnection.getConnection().prepareStatement(query);
      ps.setString(1, b.getNama());
      ps.setInt(2, b.getMassa());
      ps.setInt(3, b.getHarga());
      ps.setInt(4, b.getId());
      int i = ps.executeUpdate();

      return i == 1; // jika change pin success
      
    } catch (SQLException ex) {
      Logger.getLogger(ControlBarang.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
  }
  
  public boolean deleteData(Barang b){
    String query = "DELETE FROM `barang` WHERE `id`=?";
    PreparedStatement ps;

     try {
      MyConnection myConnection = new MyConnection();
      ps = myConnection.getConnection().prepareStatement(query);
      ps.setInt(1, b.getId());

      int i = ps.executeUpdate();

      return i == 1; // jika change pin success
      
    } catch (SQLException ex) {
      Logger.getLogger(ControlBarang.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
  }
}
