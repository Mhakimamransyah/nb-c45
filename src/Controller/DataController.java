/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data;
import Model.Fitur;
import View.PanelData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author M.Hakim Amransyah
 */
public class DataController {
    
    private Data data;
    private String jenis_data;
    
    public DataController(String jenis){
        this.jenis_data = jenis;
    }
    
    public Data muatData(JPanel panel) throws FileNotFoundException, IOException{
       JFileChooser file_chooser = new JFileChooser();
       String header[],data_value, temp[];
       file_chooser.setCurrentDirectory(new File("E:\\"));
       file_chooser.setFileFilter(new FileNameExtensionFilter("csv file", "csv"));
       if(file_chooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION){
            this.data = new Data();
            File f = file_chooser.getSelectedFile();
            this.data.setNama_file(f.getName());
            BufferedReader bf = new BufferedReader(new FileReader(f.getPath()));  
            header = bf.readLine().split(",");
            Fitur fitur;
            for(int i=0;i<header.length;i++){
              fitur = new Fitur();   
              if(i == header.length-1){
                  fitur.setTipe("Label"); //untuk label
              }
              fitur.setNama_fitur(header[i]);
              while((data_value = bf.readLine())!=null){
                temp = data_value.split(",");
                fitur.getKolom_nilai().add(temp[i]);
              }
              this.data.getFitur().add(fitur);
              bf = new BufferedReader(new FileReader(f.getPath())); 
              bf.readLine();
            }
            this.acakData();
       }
       
       return this.data;
    }
    
    private void acakData(){
       long seed = System.nanoTime();
       for(int i=0;i<this.data.getJumlahFitur();i++){
           Collections.shuffle(this.data.getFitur().get(i).getKolom_nilai(), new Random(seed));
       }
    }
    
    public Data getData() {
        return data;
    }

    public String getJenis_data() {
        return jenis_data;
    }
    
    public void panelData(){
        PanelData panel = new PanelData(this,this.data);
        panel.setVisible(true);
    }
    
    public void ubahTipeAtribut(int index,String tipe){
        this.data.getFitur().get(index).setTipe(tipe);
    }
   
    
}
