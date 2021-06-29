/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barang;

import control.MyConnection;
import view.MenuUtamaGUI;

/**
 *
 * @author Waffiq Aziz
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    MyConnection myConnection = new MyConnection();
    if (myConnection.getConnection() == null);
    else new MenuUtamaGUI();
  }
  
}
