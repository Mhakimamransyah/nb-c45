/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Fitur implements Cloneable {
    
    private String nilai;
    private String tipe = "belum diatur";
    private String nama_fitur;
    private ArrayList<String> kolom_nilai;
    
    public Object clone() {
        Fitur f = null;
        try {
            f = (Fitur)super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Fitur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
    
    public Fitur(){
        this.kolom_nilai = new ArrayList<String>();
    }
    
    public void tambah_nilai(String nilai){
       this.kolom_nilai.add(nilai);
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public ArrayList<String> getKolom_nilai() {
        return kolom_nilai;
    }

    public void setKolom_nilai(ArrayList<String> kolom_nilai) {
        this.kolom_nilai = kolom_nilai;
    }  
       
    public String getNama_fitur() {
        return nama_fitur;
    }

    public void setNama_fitur(String nama_fitur) {
        this.nama_fitur = nama_fitur;
    }
}
