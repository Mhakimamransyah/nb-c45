/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data;
import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 * 
 */
public class NaiveBayes {
    
    private MatrixConfussion matriks;
    private ArrayList<String> label;
    
    public NaiveBayes(MatrixConfussion matriks, ArrayList<String> label){
        this.matriks = matriks;
        this.label = label;
    }
    
    public MatrixConfussion doNaiveBayes(Data latih, Data uji){
        String aktual = "";
        double prior_probability[];
        ArrayList<double[]> posterior_probability;
        prior_probability = this.hitungPriorProbability(latih);
   
        for(int i=0;i<uji.getJumlahData();i++){
           posterior_probability = new ArrayList<double[]>();
           aktual = uji.getFitur().get(uji.getFitur().size()-1).getKolom_nilai().get(i);
           for(String label_value : this.label){
//              System.out.println("label : "+label_value);
              double[] posterior = new double[uji.getJumlahFitur()-1];
              for(int j=0;j<uji.getJumlahFitur()-1;j++){
                 posterior[j] = this.hitungConditionalProbability(latih, j, uji.getFitur().get(j).getKolom_nilai().get(i), label_value);               
//                 System.out.print(" "+posterior[j]);
              }
//                System.out.println("");
              posterior_probability.add(posterior);
           }
           this.matriks.setMatriksConfussion(this.hitungPosteriorProbability(posterior_probability), aktual);
           // hitung kesimpulan akhir
        }
      
        return matriks;
    }
    
    private String hitungPosteriorProbability(ArrayList<double[]> posterior){
        double[] posterior_value = new double[posterior.size()];
        int i = 0;
        for(double[] value : posterior){
            double temp = 1;
            for(double value_p : value){
//                System.out.print(value_p+" ");
                temp = temp * value_p;
            }
//            System.out.println("");
            posterior_value[i] = temp;
            i++;
        }
//         System.out.println("");
        double max = 0;
        int index_max = 0;
        for(int j=0;j<posterior_value.length;j++){
//         System.out.println(" posterior "+(j+1)+" = "+posterior_value[j]);
           if(posterior_value[j] > max){
               max = posterior_value[j];
               index_max = j;
           }
        }
//        System.out.println(" index max : "+index_max);
        return this.label.get(index_max);
    }
    
    private double[] hitungPriorProbability(Data data_latih){
        double prior_probability[] = new double[data_latih.getLabel().size()];
        double jumlah_data         = data_latih.getJumlahData();
        for(int i=0;i<data_latih.getLabel().size();i++){
            prior_probability[i] = this.countLabel(data_latih, this.label.get(i)) / jumlah_data;
        }
        return prior_probability;
    }
    
    private double hitungConditionalProbability(Data latih,int index_kolom,String value,String label){
       double res = 0;
       if(latih.getFitur().get(index_kolom).getTipe().equalsIgnoreCase("Kontinu")){
           // DENSITAS GAUSSIAN
           double val   = Double.parseDouble(value);
           double stdev = this.getStandardDeviation(latih, index_kolom, label);
           double mean  = this.getMean(latih, index_kolom, label);
           if(stdev != 0 && mean != 0){
              res = (1/(Math.sqrt(2*Math.PI)*stdev))*Math.exp(-(Math.pow(val-mean, 2))/(2*stdev));  
           }
       }else if(latih.getFitur().get(index_kolom).getTipe().equalsIgnoreCase("Kategori/Ordinal")){
           // NORMAL NAIVE BAYES
           int index_kolom_label = latih.getFitur().size()-1;
           double value_data_length = 0;
           int index = 0;
         
           double count_label = 0;
           for(String label_value : latih.getFitur().get(index_kolom_label).getKolom_nilai()){
               if(label.equalsIgnoreCase(label_value)){
                  count_label++; 
               }
           }
           
           for(String value_data : latih.getFitur().get(index_kolom).getKolom_nilai()){
               if(value_data.equalsIgnoreCase(value) && latih.getFitur().get(index_kolom_label).getKolom_nilai().get(index).equalsIgnoreCase(label)){
                   value_data_length++;
               }
               index++;
           }
           
           if(count_label != 0){
               res = value_data_length/count_label;
           }

       }
       return res;
    }
    
    private double getMean(Data latih, int index, String label){
        double res = 0;
        double same_label = 0;
        int i = 0;
        int index_label = latih.getFitur().size()-1;
        for(String value : latih.getFitur().get(index).getKolom_nilai()){
            if(latih.getFitur().get(index_label).getKolom_nilai().get(i).equalsIgnoreCase(label)){
               res = res + Double.parseDouble(value);
               same_label++;
            }
            i++;
        }
        if(same_label != 0){
           res = (res/same_label);   
        }else{
            res = 0;
        }
        return res;
    }
    
    private double getStandardDeviation(Data latih, int index, String label){
        double res = 0;
        double jumlah_data = latih.getJumlahData();
        double same_label = 0;
        double x = 0;
        int i = 0;
        int index_label = latih.getFitur().size()-1;
        double xsquare = 0;
        for(String value : latih.getFitur().get(index).getKolom_nilai()){
            if(latih.getFitur().get(index_label).getKolom_nilai().get(i).equalsIgnoreCase(label)){
               same_label++;
               x       = x + Double.parseDouble(value);
               xsquare = xsquare + Math.pow(Double.parseDouble(value), 2);
            }
            i++;
        }
        if(same_label > 1){
            res = Math.sqrt((same_label*xsquare - Math.pow(x, 2))/(same_label * (same_label-1)));   
        }else{
            res = 0;
        }
        return res;
    }
    
    private int countLabel(Data data, String label){
        int count = 0;
        int index_label         = data.getFitur().size()-1;
        for(String label_value : data.getFitur().get(index_label).getKolom_nilai()){
            if(label.equalsIgnoreCase(label_value)){
                count++;
            }
        }
        return count;
    }
    
}
