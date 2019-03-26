/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Home;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 *
 * @author M.Hakim Amransyah
 */
public class Main {

    private DataController data;
    private DataController data_supply;
    
    public Main(){
        this.data         = new DataController("Data Latih");
        this.data_supply  = new DataController("Data Uji");
    }
    
    public static void main(String[] args) {
        Main main = new Main();
        main.frameUtama();
    }
    
    public void frameUtama(){
        Home home = new Home(this);
        home.setVisible(true);
    }
    
    public void frameData(String jenis){
       if(jenis.equalsIgnoreCase("Data")){
          this.data.panelData();
       }else if(jenis.equalsIgnoreCase("Supply")){
          this.data_supply.panelData();
       }
    }
    
    public void do_validation(HashMap<String, String> konfig,JButton btn, JPanel log, JTable matrix_table){
       this.data.acakData();
       ValidationController validasi = new ValidationController(this.data.getData(),konfig, btn, log, matrix_table);
       
       if(konfig.get("Tipe").equalsIgnoreCase("Supply")){
          if(this.data_supply.getData() != null){
            validasi.setData_supply(this.data_supply.getData());
            validasi.execute();   
          }else{
            JOptionPane.showMessageDialog(null,"Data testing belom ada", "OoOps !!",JOptionPane.ERROR_MESSAGE);
          } 
       }else{
         validasi.execute();      
       }
    }
}
