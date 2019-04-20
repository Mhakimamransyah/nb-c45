/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Data {
    
    private String nama_file;
    private ArrayList<Fitur> fitur;
    private ArrayList<String> label;
    
    public Data(){
      this.fitur = new ArrayList<>();    
      this.label = new ArrayList<>();
    }
    
    public void tambah_fitur(Fitur fitur){
        this.fitur.add(fitur);
    }
    
    public ArrayList<Fitur> getFitur() {
        return fitur;
    }

    public void setFitur(ArrayList<Fitur> fitur) {
        this.fitur = fitur;
    }
    
    public int getJumlahFitur(){
       return this.fitur.size();
    }
    
    public int getJumlahData(){
       return this.fitur.get(0).getKolom_nilai().size();
    }
     
    public String getNama_file() {
        return nama_file;
    }
    
    public String getNamaLabel(){
        String nama =  this.fitur.get(this.fitur.size()-1).getNama_fitur();
        return nama;
    }

    public void setNama_file(String nama_file) {
        this.nama_file = nama_file;
    }
    
    public ArrayList<String> getLabel(){
        Fitur label_fitur = this.fitur.get(this.fitur.size()-1);
        for(String value : label_fitur.getKolom_nilai()){
           if(this.checkValue(value)){
               this.label.add(value);
           }
        }
        return this.label;
    }
    
    public int[] getJumlahSebaranLabel(){
        int jumlah_label[] = new int[this.label.size()];
        int index_label = this.fitur.size()-1;
        for(String value : this.fitur.get(index_label).getKolom_nilai()){
            for(int i=0;i<this.label.size();i++){
                if(value.equalsIgnoreCase(this.label.get(i))){
                    jumlah_label[i]++;
                    break;
                }
            }
        }
        return jumlah_label;
    }
    
    private boolean checkValue(String label){
       boolean not_fill_yet = true;
       for(String value : this.label){
         if(label.equalsIgnoreCase(value)){
              not_fill_yet = false;
              break;
         }     
       }     
       return not_fill_yet;
    }
   
    public void cetakData(){
       
       int kolom = this.fitur.size();
       int baris = this.fitur.get(0).getKolom_nilai().size();
       for(int i=0;i<baris;i++){
           for(int j=0;j<kolom;j++){
               System.out.print(this.fitur.get(j).getKolom_nilai().get(i)+" ");
           }
           System.out.println("");
       }
        
    }
    
    
}
