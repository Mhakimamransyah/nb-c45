/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data;
import Model.Fitur;
import java.awt.Font;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M.Hakim Amransyah
 */
public class ValidationController extends SwingWorker{
    
    private Data data;
    private Data data_supply;
    private HashMap<String, String> konfig;
    private JButton tombol_mulai;
    private JPanel panel_log;
    private MatrixConfussion matriks;
    private JTable matrix_table;
    
    public ValidationController(Data data, HashMap<String, String> konfig,JButton btn, 
        JPanel panel_log, JTable mc){
        this.data = data;
        this.konfig = konfig;
        this.tombol_mulai = btn;
        this.panel_log = panel_log;
        this.matrix_table = mc;
        this.matriks = new MatrixConfussion(data);      
    }
    
    private void doCrossValidation(){
        Data data_latih;
        Data data_uji;
        int kfold = Integer.parseInt(this.konfig.get("Kfold"));
        int fold_data = this.data.getJumlahData()/kfold;
        this.writeLogProcess("-- Cross Validation --");
        
        int iter_fold = 0;
        int index_awal_data_uji  = 0;
        int index_akhir_data_uji = fold_data;
        long waktu_eksekusi       = System.currentTimeMillis();
        while(iter_fold < kfold){
           data_uji      = this.getPartData(index_awal_data_uji, index_akhir_data_uji,"uji");
           data_latih    = this.getPartData(index_awal_data_uji, index_akhir_data_uji,"latih");
           this.writeLogProcess(" ");
           this.writeLogProcess("K : "+(iter_fold+1));
           this.writeLogProcess("Jumlah Data Uji   : "+data_uji.getJumlahData()+" ( "+index_awal_data_uji+" - "+index_akhir_data_uji+")");
           this.writeJumlahSebaranLabel(data_uji, "Uji");
           this.writeLogProcess("Jumlah Data Latih : "+data_latih.getJumlahData());
           this.writeJumlahSebaranLabel(data_latih, "Latih");
           this.writeLogProcess(" ");
           
           
           if(this.konfig.get("Algoritma").equalsIgnoreCase("Naive Bayes")){
              this.matriks = new NaiveBayes(this.matriks, this.data.getLabel()).doNaiveBayes(data_latih, data_uji);
           }else if(this.konfig.get("Algoritma").equalsIgnoreCase("C4.5")){
              this.matriks = new C45(this.matriks, this.data.getLabel()).doC45(data_latih, data_uji);
           }
        
           index_awal_data_uji = index_akhir_data_uji;
           index_akhir_data_uji = index_akhir_data_uji + fold_data;
           iter_fold++;
        }
        this.writeLogProcess("Waktu Eksekusi : "+(double)(System.currentTimeMillis()-waktu_eksekusi)/1000+" detik");
        this.setMatriksConfussion();
    }
    
    public void setMatriksConfussion(){
        DefaultTableModel tabel = (DefaultTableModel) this.matrix_table.getModel();
        tabel.setRowCount(this.matriks.getLabel().size()+2);
        tabel.setColumnCount(this.matriks.getLabel().size()+2);
        String mc[][] = this.matriks.getMatriks();
        for(int i=0;i<mc.length;i++){
            for(int j=0;j<mc[i].length;j++){
               tabel.setValueAt(mc[i][j], i, j);
            }
        }     
    }
    
    private Data getPartData(int index_awal, int index_akhir, String jenis){
        Data data = new Data();
        Fitur temp;
        if(jenis.equalsIgnoreCase("uji")){
           for(int i=0;i<this.data.getJumlahFitur();i++){
               temp = new Fitur();
               temp.setTipe(this.data.getFitur().get(i).getTipe());
               for(int j=index_awal;j<index_akhir;j++){
                 temp.tambah_nilai(this.data.getFitur().get(i).getKolom_nilai().get(j));
               }
               data.tambah_fitur(temp);
           }
        }else if(jenis.equalsIgnoreCase("latih")){
           for(int i=0;i<this.data.getJumlahFitur();i++){
              temp = new Fitur();
              temp.setTipe(this.data.getFitur().get(i).getTipe());
              for(int j=0;j<this.data.getJumlahData();j++){
                  if(!(j>=index_awal && j <index_akhir)){
                       temp.tambah_nilai(this.data.getFitur().get(i).getKolom_nilai().get(j));
                  }
              }
              data.tambah_fitur(temp);
           }           
        }else{
           for(int i=0;i<this.data.getJumlahFitur();i++){
              temp = new Fitur();
              temp.setTipe(this.data.getFitur().get(i).getTipe());
              for(int j=index_awal;j<index_akhir;j++){
                  temp.tambah_nilai(this.data.getFitur().get(i).getKolom_nilai().get(j));
              }
              data.tambah_fitur(temp);
           }
        }      
        return data;
    }
    
    private void doSupplyTest(){
        this.panel_log.removeAll();
        this.writeLogProcess("-- Supply Testing Set --");
        this.writeLogProcess(" ");
        this.writeLogProcess("Jumlah Data Latih : "+this.data.getJumlahData());
        this.writeJumlahSebaranLabel(this.data, "Latih");
        this.writeLogProcess(" ");
        this.writeLogProcess("Jumlah Data Uji : "+this.data_supply.getJumlahData());
        this.writeJumlahSebaranLabel(this.data_supply, "Latih");
        long waktu_eksekusi       = System.currentTimeMillis();
        if(this.konfig.get("Algoritma").equalsIgnoreCase("Naive Bayes")){
         this.matriks = new NaiveBayes(this.matriks, this.data.getLabel()).doNaiveBayes(this.data, this.data_supply);       
        }else if(this.konfig.get("Algoritma").equalsIgnoreCase("C4.5")){ }    
        this.writeLogProcess("Waktu Eksekusi : "+(double)(System.currentTimeMillis()-waktu_eksekusi)/1000+" detik");
        this.setMatriksConfussion();
    }
    
    private void doSplitValidation(){
       
        double ratio = Double.parseDouble(this.konfig.get("Ratio").toString()) / 100;
        int posisi_akhir_data_latih = (int) (this.data.getJumlahData() * ratio);
        this.panel_log.removeAll();
        this.writeLogProcess("-- Split Validation --");
        this.writeLogProcess(" ");
        
        Data data_latih = this.getPartData(0, posisi_akhir_data_latih,"");
        Data data_uji   = this.getPartData(posisi_akhir_data_latih, this.data.getJumlahData(),"");
        this.writeLogProcess("Jumlah Data Latih : "+data_latih.getJumlahData());
        this.writeJumlahSebaranLabel(data_latih, "Latih");
        this.writeLogProcess(" ");
        this.writeLogProcess("Jumlah Data Uji   : "+data_uji.getJumlahData());
        this.writeJumlahSebaranLabel(data_uji, "Uji");
     
        
        this.writeLogProcess(" ");
          
        long waktu_eksekusi       = System.currentTimeMillis();
        if(this.konfig.get("Algoritma").equalsIgnoreCase("Naive Bayes")){
           this.matriks = new NaiveBayes(this.matriks, this.data.getLabel()).doNaiveBayes(data_latih, data_uji);     
        }else if(this.konfig.get("Algoritma").equalsIgnoreCase("C4.5")){
           this.matriks = new C45(this.matriks, this.data.getLabel()).doC45(data_latih, data_uji);
        }    
        this.writeLogProcess("Waktu Eksekusi : "+(double)(System.currentTimeMillis()-waktu_eksekusi)/1000+" detik");
       
        this.setMatriksConfussion();
    }
    
    private void writeJumlahSebaranLabel(Data data, String jenis){
        this.writeLogProcess("Jumlah Sebaran Label Data "+jenis);
        int index = 0;
        for(String value : data.getLabel()){
            this.writeLogProcess(value+" => "+data.getJumlahSebaranLabel()[index]);
            index++;
        }
    }
    
    public void writeLogProcess(String text){
        JLabel label =new JLabel(" "+text);
        label.setFont(new Font("Serif",Font.PLAIN,12));
        this.panel_log.add(label);
        this.panel_log.revalidate();
        this.panel_log.repaint();
    }
    
    
    public void setData_supply(Data data_supply) {
        this.data_supply = data_supply;
    }
    

    @Override
    protected Object doInBackground() throws Exception {
        this.tombol_mulai.setEnabled(false);
        this.panel_log.removeAll();
        if(this.konfig.get("Tipe").equalsIgnoreCase("Split")){
            this.doSplitValidation();
        }else if(this.konfig.get("Tipe").equalsIgnoreCase("Cross")){
            this.doCrossValidation();
        }else if(this.konfig.get("Tipe").equalsIgnoreCase("Supply")){
            this.doSupplyTest();
        }
        return null;
    }
    
    @Override
    protected void done(){
        this.tombol_mulai.setEnabled(true);
    }
}
