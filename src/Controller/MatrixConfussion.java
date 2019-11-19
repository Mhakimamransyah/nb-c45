/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 */
public class MatrixConfussion {

    private String[][] matriks;
    private Data data;
    private ArrayList<String> label;
    
    public MatrixConfussion(Data data){
       this.data = data;
       this.bentukMatriksConfussion();
    }
    
    public void setMatriksConfussion(String output, String aktual){
        DecimalFormat df = new DecimalFormat("#.##");
        int index_aktual = this.getIndex(aktual);
        int index_output = this.getIndex(output);
        this.matriks[index_output][index_aktual] = (Integer.parseInt(this.matriks[index_output][index_aktual])+1)+"";
        
        int index = 1;
        for(String value : this.label){
            // this.matriks[this.matriks.length-1][index] = df.format(this.getRecall(value)).replaceAll(",",".")+" %";
            // this.matriks[index][this.matriks.length-1] = df.format(this.getPresisi(value)).replaceAll(",",".")+" %";
            this.matriks[this.matriks.length-1][index] = "-";
            this.matriks[index][this.matriks.length-1] = "-";
            index++;
        }
        this.matriks[this.matriks.length-1][this.matriks.length-1] = df.format(this.getAkurasi()).replaceAll(",",".")+" %";
    }
    
    public String[][] getMatrixConfussion(){
        int index = 1;
//        this.cetakMatriksConfussion();
        for(String value : this.label){
            this.matriks[this.matriks.length-1][index] = this.getRecall(value)+"";
            this.matriks[index][this.matriks.length-1] = this.getPresisi(value)+"";
            index++;
        }
        this.matriks[this.matriks.length-1][this.matriks.length-1] = this.getAkurasi()+"";
        return this.matriks;        
    }
    
    
    private int getIndex(String label){
        int index = 0;
        for(int i=0;i<this.label.size();i++){
            if(this.label.get(i).equalsIgnoreCase(label)){
                index = i;
                break;
            }
        }
        return index+1;
    }
    
    public double getPresisi(String label){
        int index         = this.getIndex(label);
        double sum        = 0;
        double true_value = Double.parseDouble(this.matriks[index][index]); 
        for(int i=1;i<this.matriks[index].length-1;i++){
            sum = sum + Double.parseDouble(this.matriks[index][i]);
        }
        if(sum != 0){
            sum = (true_value/sum)*100;
        }
        return sum;
    }
    
    public String[][] getMatriks() {
        return matriks;
    }
    
    
    public double getRecall(String label){
        int index         = this.getIndex(label);
        double sum        = 0;
        double true_value = Double.parseDouble(this.matriks[index][index]); 
        for(int i=1;i<this.matriks.length-1;i++){
            sum  = sum + Double.parseDouble(this.matriks[i][index]);
        }
        if(sum != 0){
            sum = (true_value/sum)*100;
        }
        return sum;
    }
    
    public double getAkurasi(){
        double sum = 0;
        for(int i=1;i<this.matriks.length-1;i++){
            for(int j=1;j<this.matriks[i].length;j++){
                if(i == j){
                    sum = sum + Double.parseDouble(this.matriks[i][j]);
                }
            }
        }
        sum = (sum/this.getJumlahSeluruhData())*100;
        return sum;
    }
    
    public ArrayList<String> getLabel() {
        return label;
    }
    
    private double getJumlahSeluruhData(){
        double sum = 0;
        for(int i=1;i<this.matriks.length-1;i++){
            for(int j=1;j<this.matriks[i].length-1;j++ ){
                sum = sum +  Double.parseDouble(this.matriks[i][j]);
            }
        }
        return sum;
    }
    
    private void bentukMatriksConfussion(){
        this.label              = this.data.getLabel();
        int jumlah_label        = label.size();
        this.matriks = new String[jumlah_label+2][jumlah_label+2];
        this.matriks[0][0]              = "Aktual/Klasifikasi";
        // this.matriks[0][this.matriks[0].length-1] = "Presisi";
        // this.matriks[this.matriks.length-1][0] = "Recall";
        this.matriks[0][this.matriks[0].length-1] = "-";
        this.matriks[this.matriks.length-1][0] = "-";
        
        for(int i=0;i<this.matriks[0].length;i++){
            if( i != 0 && i != (this.matriks[0].length-1)){
                this.matriks[0][i] = this.label.get(i-1);
            }
        }
       
        for(int j=0;j<this.matriks.length;j++){
            if( j != 0 &&  j != this.matriks.length-1 ){
                this.matriks[j][0] = this.label.get(j-1);
            }
        }
        
         for(int i=1;i<this.matriks.length;i++){
            for(int j=1;j<this.matriks[i].length;j++){
                this.matriks[i][j] = "0";
            }
        }
    }
    
    public void cetakMatriksConfussion(){
        for(int i=0;i<this.matriks.length;i++){
            for(int j=0;j<this.matriks[i].length;j++){
                System.out.print(this.matriks[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    
    
}
