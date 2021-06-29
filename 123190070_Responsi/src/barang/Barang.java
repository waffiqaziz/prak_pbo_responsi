/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package barang;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class Barang {
  private String nama;
  private int harga;
  private int massa;
  private int id;
  
  public void setBarang(String nama, int massa, int harga){
    this.harga = harga;
    this.massa = massa;
    this.nama = nama;
  }
  public void setId(int id){
   this.id = id;
  }
  
  public String getNama(){
    return nama;
  }
  public int getMassa(){
    return massa;
  }
  public int getHarga(){
    return harga;
  }
  public int getId(){
    return id;
  }
}
