/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data;
import Model.Fitur;
import Model.Node;
import Model.Node.AttributeType;
import Model.Node.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Azhary Arliansyah
 */
public class C45 {
    
    private MatrixConfussion matriks;
    private ArrayList<String> label;
    private Map<String, Integer> classDistribution = new HashMap<>();
    private Data trainData;
    private double totalEntropy = 0.0;
    private Map<String, Set<String>> attributeValue = new HashMap<>();
    private Node tree;
    private Map<String, Double> attributeSplits = new HashMap<>();
    
    public C45(MatrixConfussion matriks, ArrayList<String> label){
        this.matriks = matriks;
        this.label = label;
    }
    
    private void fit(Data latih) {
        this.trainData = latih;
        this.classDistribution = this.countClassDistribution(latih);
        this.calculateTotalEntropy();
        this.setAttributeValues(latih);
        this.buildTree(latih, null);
    }
    
    private void displayTree() {
        Integer level = 0;
        this.traverseTree(this.tree, level);
    }
    
    private void traverseTree(Node node, Integer level) {
        if (node == null) {
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("\t");
        }
        
        switch (node.getType()) {
            case ROOT:
            case BRANCH:
                System.out.println(sb + node.getAttribute());
                Map<String, Node> childs = node.getChilds();
                for (Map.Entry<String, Node> e : childs.entrySet()) {
                    System.out.println(sb + "Traverse to " + e.getKey());
                    this.traverseTree(e.getValue(), level + 1);
                }
                break;
                
            case LEAF:
                System.out.println(sb + "LABEL: " + node.getLabel());
                break;
        }
    }
    
    public String predict(Data data) {
        for (int i = 0; i < data.getJumlahData(); i++) {
            for (int j = 0; j < data.getJumlahFitur(); j++) {
                System.out.print(data.getFitur().get(j).getKolom_nilai().get(i) + ", ");
            }
            System.out.println();
        }
        return this.traverse(this.tree, data, null);
    }
    
    private String traverse(Node node, Data data, String attr) {
        System.out.println("NODE ATTR " + attr + ": " + (node != null));
        if (node != null) {

            switch (node.getType()) {
                case ROOT:
                case BRANCH:
                    if (attr == null) {
                        attr = node.getAttribute();
                        System.out.println("SET ATTR: " + attr);
                    }
                    final int ATTRIBUTE_INDEX = 
                            this.getFiturIndexByName(data, attr);
                    final String VALUE = data.getFitur()
                                            .get(ATTRIBUTE_INDEX)
                                            .getKolom_nilai()
                                            .get(0);
                    
                    Node childNode;
                    System.out.println("NODE ATTR TYPE: " + node.getAttributeType().toString());
                    switch (node.getAttributeType()) {
                        case CONTINUOUS:
                            final double threshold = Double.parseDouble(VALUE);
                            if (threshold < node.getThreshold()) {
                                childNode = node.getLeftChild();
                            } else {
                                childNode = node.getRightChild();
                            }
                            return this.traverse(childNode, data, 
                                    childNode.getAttribute());
                            
                        case DISCRETE:
                            Map<String, Node> childs = node.getChilds();
                            childNode = childs.get(VALUE);
                            System.out.println("TRAVERSE TO VALUE: " + VALUE);
                            if (childNode.getAttribute() == null) {
                                if (childNode.getLabel() == null) {
                                    return "h3h3";
                                }
                            }
                            return this.traverse(childNode, data, 
                                    childNode.getAttribute());
                    }
                    
                    break;
                
                case LEAF:
                    return node.getLabel();
            }
        }
        
        return "h1h1";
    }
    
    private void buildTree(Data data, Node parent) {
        if (data.getJumlahData() <= 0) {
            System.out.println("EXIT: NUMBER OF RECORDS 0");
            return;
        }
        
        Set<String> excludedAttributes;
        if (parent == null) {
            excludedAttributes  = new HashSet<>();
        } else {
            excludedAttributes = parent.getExcludedAttributes();
        }
        
        Map<String, Double> attributeGain = this.calculateAttributeGain(data, 
                excludedAttributes);
        System.out.println("EXCLUDED ATTRIBUTES: " + excludedAttributes);
        System.out.println("ATTRIBUTE GAINS: " + attributeGain);
        Map<String, Double> currentLevelSplits = 
                new HashMap<>(this.attributeSplits);
        
        if (attributeGain.size() <= 0) {
            return;
        }
        
        List<Map.Entry<String, Double>> sortedAttr = 
                MathFx.sortMapDouble(attributeGain, "DESC");
        Map.Entry<String, Double> entry = sortedAttr.get(0);
        
        String attr = entry.getKey();
        final String ATTRIBUTE_TYPE = data.getFitur().get(0).getTipe();
        if (this.attributeValue.containsKey(attr)) {
            Type t = null;
            AttributeType at = null;
            Node node = new Node(attr, t, at);
            excludedAttributes.add(attr);
            node.setExcludedAttributes(excludedAttributes);
            if (this.tree == null || parent == null) {
                t = Type.ROOT;
                node.setType(t);
                this.tree = node;
                System.out.println("ATTACH ROOT");
            } else {
                parent.setAttribute(attr);
                node = parent;
                t = Type.BRANCH;
                node.setType(t);
                System.out.println("ATTACH BRANCH");
            }
            
            switch (ATTRIBUTE_TYPE.toLowerCase()) {
                //<editor-fold defaultstate="collapsed" desc="continuous split">
                case "kontinu":
                    node.setAttributeType(AttributeType.CONTINUOUS);
                    if (currentLevelSplits.containsKey(attr)) {
                        node.setThreshold(currentLevelSplits.get(attr));
                    }
                    
                    List<Data> branches = this.splitBranch(data, attr, 
                            currentLevelSplits.get(attr));
                    Data left = branches.get(0);
                    Data right = branches.get(1);
                    
                    Map<String, Integer> leftDistribution = 
                            this.countClassDistribution(left);
                    if (left.getJumlahData() > 0) {
                        Node leftChild = new Node();
                        if (leftDistribution.size() == 1) {
                            // create leaf
                            leftChild.setType(Type.LEAF);
                            List<String> key = new ArrayList<>(leftDistribution
                                    .keySet());
                            leftChild.setLabel(key.get(0));
                        } else {
                            // create branch
                            leftChild.setType(Type.BRANCH);
                            this.buildTree(left, leftChild);
                        }
                        
                        node.setLeftChild(leftChild);
                    }
                    
                    
                    Map<String, Integer> rightDistribution = 
                            this.countClassDistribution(right);
                    if (right.getJumlahData() > 0) {
                        Node rightChild = new Node();
                        if (rightDistribution.size() == 1) {
                            // create leaf
                            rightChild.setType(Type.LEAF);
                            List<String> key = new ArrayList<>(rightDistribution
                                    .keySet());
                            rightChild.setLabel(key.get(0));
                        } else {
                            // create branch
                            rightChild.setType(Type.BRANCH);
                            this.buildTree(right, rightChild);
                        }
                        
                        node.setRightChild(rightChild);
                    }
                    break;
                //</editor-fold>
                    
                case "kategori/ordinal":
                    System.out.println("TYPE: CATEGORICAL");
                    node.setAttributeType(AttributeType.DISCRETE);
                    //<editor-fold defaultstate="collapsed" desc="discrete split">
                    Set<String> attrValue = this.attributeValue.get(attr);
//                    System.out.println(attr + " VALUES: " + attrValue);
                    for (String val : attrValue) {
//                        System.out.println("GET BIN FOR " + val);
                        Map<String, Data> bin = this.getBin(data, attr, val);
                        Map<String, Data> tempBin = new HashMap<>(bin); // for removing purpose
                        for (Map.Entry<String, Data> e : tempBin.entrySet()) {
                            if (e.getValue().getJumlahFitur() <= 0) {
                                System.out.println("REMOVING " + e.getKey() + 
                                        " BECAUSE ATTR NUM " + e.getValue().getJumlahFitur());
                                bin.remove(e.getKey());
                            }
                        }
                            
//                        System.out.println(val + " ~> " + bin);
                        Node child = new Node();
                        child.setExcludedAttributes(excludedAttributes);
//                        System.out.println("BINSIZE: " + bin.size());
                        System.out.println("BINSIZE");
                        if (bin.size() == 1) {
                            child.setType(Type.LEAF);
                            child.setLabel(bin.entrySet()
                                        .iterator()
                                        .next()
                                        .getKey());
                            System.out.println("CREATE LEAF NODE");
                        } else {
                            child.setType(Type.BRANCH);
                            Data childData = null;
                            for (Map.Entry<String, Data> e : bin.entrySet()) {
                                System.out.println("DATA ENTRY FOR CHILD");
                                Data nodeData = e.getValue();
                                if (nodeData.getJumlahFitur() <= 0) {
                                    System.out.println("NUMATTR 0");
                                    continue;
                                }
                                
                                if (childData == null) {
                                    System.out.println("IFNODEATTR: " + e.getValue().getJumlahFitur());
                                    System.out.println("IFNODENUMATTR: " + e.getValue().getJumlahData());
                                    childData = e.getValue();
                                } else {
                                    System.out.println("ELSENODE");
                                    
                                    final int NUM_ATTRIBUTES = e.getValue()
                                            .getJumlahFitur();
                                    System.out.println("NODENUMATTR: " + NUM_ATTRIBUTES);
                                    System.out.println("NODENUMDATA: " + nodeData.getJumlahData());
                                    for (int i = 0; i < nodeData
                                            .getJumlahData(); i++) {
                                        System.out.println("CHILSATTRSNUM: " + childData.getJumlahFitur());
                                        for (int j = 0; j < NUM_ATTRIBUTES; 
                                                j++) {
                                            childData.getFitur()
                                                    .get(j)
                                                    .getKolom_nilai()
                                                    .add(nodeData.getFitur()
                                                            .get(j)
                                                            .getKolom_nilai()
                                                            .get(i));
                                        }
                                    }

                                }
                            }
                            System.out.println("ENDENTRYCHILD");
                            if (childData != null && 
                                    childData.getJumlahFitur() > 0 && 
                                    childData.getJumlahData() > 0) {
                                System.out.println("call build tree");
                                this.buildTree(childData, child);
                            }
                        }
                        
                        System.out.println("CHILD ATTR: " + child.getAttribute());
                        node.addChild(val, child);
                    }
                    // </editor-fold>
                    break;
            }
            
            
        }
    }
    
    private int getFiturIndexByName(Data data, String attr) {
        for (int i = 0; i < data.getJumlahFitur(); i++) {
            if (data.getFitur().get(i).getNama_fitur().equals(attr)) {
                return i;
            }
        }
        return -1;
    }
    
    private Map<String, Data> getBin(Data data, String attr, String binValue) {
        Map<String, Data> dist = new HashMap<>();
        final int NUM_ATTRIBUTES = data.getJumlahFitur();
        
        final int attrIdx = this.getFiturIndexByName(data, attr);
        System.out.println("ATTRIDX: " + attrIdx);
        System.out.println("JUMLAH: " + data.getJumlahData());
        for (int i = 0; i < data.getJumlahData(); i++) {
            System.out.println(data.getJumlahFitur() + " :: " + data.getFitur().get(attrIdx).getKolom_nilai().get(i));
            String value = data.getFitur().get(attrIdx).getKolom_nilai().get(i);
            String label = data.getFitur().get(NUM_ATTRIBUTES - 1)
                    .getKolom_nilai().get(i);
            Data bin;
            if (dist.containsKey(label)) {
                bin = dist.get(label);
            } else {
                bin = new Data();
            }
            
            if (value.equals(binValue)) {
                for (int j = 0; j < NUM_ATTRIBUTES; j++) {
                    Fitur newAttr;
                    if (j >= bin.getJumlahFitur()) {
                        newAttr = new Fitur();
                        newAttr.setNama_fitur(data.getFitur().get(j)
                                .getNama_fitur());
                        newAttr.setTipe(data.getFitur().get(j).getTipe());
                        if (j == NUM_ATTRIBUTES - 1) {
                            newAttr.setTipe("Label");
                        }
                        bin.getFitur().add(newAttr);
                    }
                    bin.getFitur().get(j).getKolom_nilai().add(data.getFitur()
                            .get(j).getKolom_nilai().get(i));
                }
            }
//            System.out.println(label + " ~dist~>> " + bin);
            dist.put(label, bin);
        }
        
        System.out.println("ENDFORDIST");
        return dist;
    }
    
    private Map<String, Double> calculateAttributeGain(Data data, 
            Set<String> excludedAttributes) {
        Map<String, Double> attributeGain = new HashMap<>();
//        System.out.println("ATTRS TO BE USED TO CALCULATE GAIN: " + data.getJumlahFitur());
        for (int i = 0; i < data.getFitur().size(); i++) {
            String attr = data.getFitur().get(i).getNama_fitur();
            if (excludedAttributes == null || 
                    !excludedAttributes.contains(attr)) {
                String attributeType = data.getFitur().get(i).getTipe();
//                System.out.println("CALCULATE GAIN: " + attr);
//                System.out.println("CALCULATE GAIN TYPE: " + attributeType);
                switch (attributeType.toLowerCase()) {
                    case "kontinu":
                        Map.Entry<String, Double> entry = 
                                this.calculateGainContinuous(data, attr);
                        attributeGain.put(attr, entry.getValue());
                        this.attributeSplits.put(attr, 
                                Double.parseDouble(entry.getKey()));
                        break;
                        
                    case "kategori/ordinal":
//                        System.out.println("PUT GAIN " + attr);
                        attributeGain.put(attr, this.calculateGain(data, attr));
                        break;
                }
                
            }
        }
        return attributeGain;
    }
    
    private List<Data> splitBranch(Data data, String attr, double split) {
        final int attrIdx = this.getFiturIndexByName(data, attr);
        final int NUM_ATTRIBUTES = data.getJumlahFitur();
        Data left = new Data();
        Data right = new Data();
        
        //<editor-fold defaultstate="collapsed" desc="split">
        for (int i = 0; i < data.getJumlahData(); i++) {
            double val = Double.parseDouble(data.getFitur().get(attrIdx)
                    .getKolom_nilai().get(i));
            if (val > split) {
                // right
                for (int j = 0; j < NUM_ATTRIBUTES; j++) {
                    Fitur newAttr;
                    if (j >= right.getJumlahFitur()) {
                        newAttr = new Fitur();
                        newAttr.setNama_fitur(data.getFitur().get(j)
                                .getNama_fitur());
                        if (j == NUM_ATTRIBUTES - 1) {
                            newAttr.setTipe("Label");
                        }
                        right.getFitur().add(newAttr);
                    }

                    right.getFitur().get(j).getKolom_nilai().add(data
                            .getFitur().get(j).getKolom_nilai().get(i));
                }

            } else {
                // left
                for (int j = 0; j < NUM_ATTRIBUTES; j++) {
                    Fitur newAttr;
                    if (j >= right.getJumlahFitur()) {
                        newAttr = new Fitur();
                        newAttr.setNama_fitur(data.getFitur().get(j)
                                .getNama_fitur());
                        if (j == NUM_ATTRIBUTES - 1) {
                            newAttr.setTipe("Label");
                        }
                        left.getFitur().add(newAttr);
                    }

                    left.getFitur().get(j).getKolom_nilai().add(data
                            .getFitur().get(j).getKolom_nilai().get(i));
                }

            }
        }
        //</editor-fold>
    
        List<Data> result = new ArrayList<>();
        result.add(left);
        result.add(right);
        return result;
    }
    
    private Map.Entry<String, Double> 
        calculateGainContinuous(Data data, String attr) {
        int attrIdx = this.getFiturIndexByName(data, attr);
        List<Double> splits = MathFx.getPossibleSplitsStr(data.getFitur()
                .get(attrIdx).getKolom_nilai());
        Map<String, Double> temporaryGain = new HashMap<>();
        
        for (double split : splits) {
            List<Data> branches = this.splitBranch(data, attr, split);
            Data left = branches.get(0);
            Data right = branches.get(1);
            
            double leftEntropy = this.calculateSplitEntropy(left);
            double rightEntropy = this.calculateSplitEntropy(right);
            int totalData = data.getJumlahData();
            double info = (left.getJumlahData() / totalData) * leftEntropy + 
                    (right.getJumlahData() / totalData) * rightEntropy;
            double gain = this.totalEntropy - info;
            temporaryGain.put(String.valueOf(split), gain);
        }
        List<Map.Entry<String, Double>> result = 
                MathFx.sortMapDouble(temporaryGain, "DESC");
        return result.get(0);
    }
    
    private double calculateGain(Data data, String attr) {
        Map<String, Map<String, Integer>> totalAttributeSamples = 
                new HashMap<>();
        Map<String, Map<String, Double>> attributesEntropy = 
                this.calculateAttributeEntropy(data, totalAttributeSamples);
        Double[] gain = new Double[]{this.totalEntropy};
        Map<String, Double> attributeEntropy = attributesEntropy.get(attr);
        Map<String, Integer> totalSamples = totalAttributeSamples.get(attr);
        
        final int TOTAL_SAMPLES = data.getJumlahData();
        attributeEntropy.forEach((attrVal, entropy) -> {
            double divResult = (double)totalSamples.get(attrVal) / 
                    (double)TOTAL_SAMPLES;
            gain[0] += (divResult * entropy);
        });
        
        return gain[0];
    }
    
    private double calculateSplitEntropy(Data data) {
        Map<String, Integer> classDistribution = 
                this.countClassDistribution(data);
        Double[] entropy = new Double[]{null};
        classDistribution.forEach((key, val) -> {
            double divResult = (double)val / (double)data.getJumlahData();
            double currentEntropy = divResult * Math.log(divResult);
            if (entropy[0] == null) {
                entropy[0] = (-1) * currentEntropy;
            } else {
                entropy[0] += ((-1) * currentEntropy);
            }
        });
        return entropy[0];
    }
    
    private Map<String, Map<String, Double>> calculateAttributeEntropy(
            Data data, Map<String, Map<String, Integer>> 
                    totalAttributeSamples) {
        Map<String, Map<String, Double>> attributeEntropy = new HashMap<>();
        for (int i = 0; i < data.getJumlahFitur() - 1; i++) {
            String attr = data.getFitur().get(i).getNama_fitur();
            attributeEntropy.put(attr, this.calculateEntropy(data, attr, 
                    totalAttributeSamples));
        }
        return attributeEntropy;
    }
    
    private Map<String, Double> calculateEntropy(Data data, String attr, 
            Map<String, Map<String, Integer>> totalAttributeSamples) {
        Map<String, Double> entropy = new HashMap<>();
        Map<String, Map<String, Integer>> totalSamples = new HashMap<>();
        Map<String, Integer> freq = totalAttributeSamples.get(attr);
        if (freq == null) {
            freq = new HashMap<>();
        }
        Map<String, Map<String, Map<String, Integer>>> valueDistribution =
                this.countValueDistribution(data);
        for (Map.Entry<String, Map<String, Map<String, Integer>>> e : 
                valueDistribution.entrySet()) {
            Map<String, Integer> dist = e.getValue().get(attr);
            for (Map.Entry<String, Integer> entry : dist.entrySet()) {
                String k = entry.getKey();
                Integer v = freq.get(k);
                if (v != null) {
                    v += entry.getValue();
                } else {
                    v = entry.getValue();
                }
                freq.put(k, v);
            }
            totalSamples.put(attr, freq);
        }
        
        totalAttributeSamples.put(attr, freq);
        valueDistribution.forEach((key, value) -> {
            Map<String, Integer> dist = value.get(attr);
            dist.forEach((k, v) -> {
                double divResult = (double)v / (double)totalSamples.get(attr)
                        .get(k);
                double currentEntropy = divResult * Math.log(divResult);
                Double val = entropy.get(k);
                if (val == null) {
                    entropy.put(k, (-1) * currentEntropy);
                } else {
                    entropy.put(k, val + ((-1) * currentEntropy));
                }
            });
        });
        return entropy;
    }
    
    private Map<String, Map<String, Map<String, Integer>>> 
        countValueDistribution(Data data) {
        Map<String, Map<String, Map<String, Integer>>> valueDistribution = 
                new HashMap<>();
        for (String cls : data.getLabel()) {
            valueDistribution.put(cls, new HashMap<>());
        }

        final int ROW_LENGTH = data.getFitur().size();
        for(int i = 0; i < data.getJumlahData(); i++) {
            String label = data.getFitur().get(ROW_LENGTH - 1)
                        .getKolom_nilai().get(i);
            for(int j = 0; j < ROW_LENGTH - 1; j++) {
                String attr = data.getFitur().get(j).getNama_fitur();
                String value = data.getFitur().get(j).getKolom_nilai().get(i);
                this.incrementDistribution(valueDistribution, label, attr, 
                        value);
            }
        }
        return valueDistribution;
    }
        
    private void incrementDistribution(
            Map<String, Map<String, Map<String, Integer>>> valueDistribution, 
            String label, String attr, String value) {
        Map<String, Map<String, Integer>> map = valueDistribution.get(label);
        Map<String, Integer> dist = new HashMap<>();
        if (map.containsKey(attr)) {
            dist = map.get(attr);
            if (dist.containsKey(value)) {
                dist.computeIfPresent(value, (k, v) -> v + 1);
            } else {
                dist.put(value, 1);
            }
        } else {
            dist.put(value, 1);
        }
        map.put(attr, dist);
        valueDistribution.put(label, map);
    }
    
    private void setAttributeValues(Data data) {
        this.attributeValue = new HashMap<>();
        for(int i = 0; i < data.getJumlahData(); i++) {
            Set<String> set;
            for(int j = 0; j < data.getFitur().size(); j++) {
                String attr = data.getFitur().get(j).getNama_fitur();
                if (this.attributeValue.containsKey(attr)) {
                    set = this.attributeValue.get(attr);
                } else {
                    set = new HashSet<>();
                }
                String val = data.getFitur().get(j).getKolom_nilai().get(i);
                set.add(val);
                this.attributeValue.put(attr, set);
            }
        }
    }
    
    private void calculateTotalEntropy() {
        final int TOTAL_SAMPLES = this.trainData.getJumlahData();
        this.totalEntropy = 0.0;
        this.classDistribution.forEach((key, value) -> {
           double divResult = (double)value / (double)TOTAL_SAMPLES;
           this.totalEntropy += (-1) * divResult * Math.log(divResult);
        });
    }
    
    private Map<String, Integer> countClassDistribution(Data data) {
        Map<String, Integer> map = new HashMap<>();
        int classIdx = data.getFitur().size() - 1;
        List<String> classes = data.getFitur().get(classIdx).getKolom_nilai();
        for (String cls : classes) {
            if (map.containsKey(cls)) {
                int c = map.get(cls);
                map.put(cls, ++c);
            } else {
                map.put(cls, 1);
            }
        }
        return map;
    }
    
    public MatrixConfussion doC45(Data latih, Data uji){
        
        // COBA DATA LATIH DAN UJI DI-CLONE
        
        System.out.println("DOC45");
        this.fit(latih);
        System.out.println("TRAINING COMPLETE");
        this.displayTree();
        
//        for (int i = 0; i < uji.getJumlahData(); i++) {
//            System.out.println("ROW " + i);
//            for (int j = 0; j < uji.getJumlahFitur(); j++) {
//                System.out.print(uji.getFitur().get(j).getKolom_nilai().get(i) + ", ");
//            }
//            System.out.println();
//        }
        
        System.out.println("JUMLAH DATA UJI: " + uji.getJumlahData());
        final int NUM_ROWS = uji.getJumlahData();
        final int NUM_ATTRS = uji.getJumlahFitur();
        for (int i = 0; i < NUM_ROWS; i++) {
            
            System.out.println("FOR LOOP " + i);

            Data row = new Data();
            for (int j = 0; j < NUM_ATTRS; j++) {
                Fitur newAttr = new Fitur();
                newAttr.setTipe(uji.getFitur().get(j).getTipe());
                newAttr.setNama_fitur(uji.getFitur().get(j).getNama_fitur());
                row.getFitur().add(newAttr);
                row.getFitur().get(j).getKolom_nilai().add(
                        uji.getFitur().get(j).getKolom_nilai().get(i));
            }
            System.out.println("ROWDATANUM: " + row.getJumlahData());
            String predicted = this.predict(row);
            System.out.println("PREDICTED LABEL: " + predicted);
            String actual = uji.getFitur().get(uji.getJumlahFitur() - 1)
                            .getKolom_nilai().get(i);
            System.out.println(actual + "::" + predicted + " --> " + (actual.equals(predicted)));
            this.matriks.setMatriksConfussion(predicted, actual);
        }
        
        return this.matriks;
    }
    
}
