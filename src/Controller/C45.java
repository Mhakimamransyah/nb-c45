/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data;
import Model.Fitur;
import java.util.ArrayList;

/**
 *
 * @author Azhary Arliansyah
 */
public class C45 {
    
    private MatrixConfussion matriks;
    private ArrayList<String> label;
    
    public C45(MatrixConfussion matriks, ArrayList<String> label){
        this.matriks = matriks;
        this.label = label;
    }
    
     public MatrixConfussion doC45(Data latih, Data uji){
               
        // Akses untuk setiap kolom/fitur data (termaksud label)
        for(Fitur fitur : latih.getFitur()){
            // Kolom/Fitur ke - n
            for(String nilai : fitur.getKolom_nilai()){
                // Nilainya
            }    
        }
        // Akses untuk setiap baris data (termaksud label)
        for(int i=0;i<latih.getJumlahData();i++){
            for(int j=0;j<latih.getFitur().size();j++){
                latih.getFitur().get(j).getKolom_nilai().get(i);
            }
        }
        
        // Untuk Mengisi Matriks Confussion
        // this.matriks.setMatriksConfussion(Output,Aktual);
      
        return matriks;
    }
    
}
