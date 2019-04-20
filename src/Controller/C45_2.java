/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Node.Type;
import Model.Data;
import Model.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Azhary Arliansyah
 */
public class C45_2 {
        
    private final String[] attributes = new String[] {
        "pendidikan", "pekerjaan", "penghasilan", "ptr", "ventilasi", 
        "pencahayaan", "kelembaban", "lantai", "dinding", "atap"
    };
    
    private Map<Integer, Integer> classDistribution;
    private List<Data> dataList;
    private double totalEntropy;
    private Map<String, Set<Integer>> attributeValue;
    private Node tree;
    
    public C45_2() {
        this.classDistribution = new HashMap<>();
    }
    
    public void fit(List<Data> data) {
        this.dataList = data;
        this.classDistribution = this.countClassDistribution(data);
        this.calculateTotalEntropy();
        this.setAttributeValues(data);
        Integer level = 0;
        this.buildTree(data, null, level);
    }
    
    public void showTree() {
        this.traverseTree(this.tree);
    }
    
    private void traverseTree(Node node) {
        if (node != null) {
            System.out.println(node.getAttribute() + ":");
            for (Map.Entry<String, Node> n: node.getChilds().entrySet()) {
                System.out.print(n.getKey() + "=" + n.getValue().getAttribute() + 
                       ":" + n.getValue().getLabel() + ", ");
            }
            System.out.println();
            Type t = node.getType();
            switch (t) {
                case ROOT:
                case BRANCH:
                    Map<String, Node> childs = node.getChilds();
                    childs.forEach((branchValue, branchNode) -> {
                       System.out.println(t + ": " + branchNode.getAttribute() + ": "
                               + branchValue);
                       this.traverseTree(branchNode);
                    });
                    break;
                    
                case LEAF:
                    System.out.println("LEAF: " + node.getLabel());
                    break;
            }
        } else {
            System.out.println("The tree has not been planted");
        }
    }
    
    public boolean predict(Data data) {
        return this.traverse(this.tree, data);
    }
    
    private boolean traverse(Node node, Data data) {
        if (node != null) {
            Type t = node.getType();
            switch (t) {
                case ROOT:
                case BRANCH:
                    String attr = node.getAttribute();
                    Integer attrValue = (Integer)Reflector.callUserFunc(
                            Data.class, data, 
                            "get" + StringUtils.capitalize(attr));
                    Map<String, Node> childs = node.getChilds();
                    Node childNode = childs.get(attrValue);
                    if (childNode.getAttribute() == null) {
                        if (childNode.getLabel() == null) {
                            return false;
                        }
//                        return data.getIspa() == childNode.getLabel();
                    }
                    return this.traverse(childNode, data);
                    
                case LEAF:
//                    return data.getIspa() == node.getLabel();
            }
        } else {
            System.out.println("The tree has not been planted");
        }
        
        return false;
    }
    
    private void buildTree(List<Data> data, Node parent, Integer level) {
        if (data.size() <= 0) {
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
        
        if (attributeGain.size() <= 0) {
            return;
        }
                
        // mendapatkan atribut dengan gain tertinggi
        List<Map.Entry<String, Double>> sortedAttr = 
                MathFx.sortMapDouble(attributeGain, "DESC");
        Map.Entry<String, Double> entry = sortedAttr.get(0);
        String attr = entry.getKey();
        // mendapatkan semua kemungkinan nilai pada atribut tersebut
        if (this.attributeValue.containsKey(attr)) {
            Type t = null;
            Node node = new Node(attr, t);
            excludedAttributes.add(attr);
            node.setExcludedAttributes(excludedAttributes);
            if (this.tree == null || parent == null) {
                t = Type.ROOT;
                node.setType(t);
                this.tree = node;
            } else {
                parent.setAttribute(attr);
                node = parent;
                t = Type.BRANCH;
                node.setType(t);
            }
            
            Map<Integer, Node> childs = new HashMap<>();
            Set<Integer> attrValue = this.attributeValue.get(attr);
            
            // membuat cabang per kemungkinan nilai atribut
            for (int val : attrValue) {
                Map<Integer, List<Data>> bin = this.getBin(data, attr, val);
                Node child = new Node();
                child.setExcludedAttributes(excludedAttributes);
                if (bin.size() == 1) {
                    child.setType(Type.LEAF);
//                    child.setLabel(bin.entrySet()
//                                    .iterator()
//                                    .next()
//                                    .getKey());
                } else {
                    child.setType(Type.BRANCH);
                    List<Data> childData = new ArrayList<>();
                    for (Map.Entry<Integer, List<Data>> e : bin.entrySet()) {
                        childData = Stream.of(childData, e.getValue())
                                    .flatMap(x -> x.stream())
                                    .collect(Collectors.toList());
                    }
                    
                    if (childData.size() > 0) {
                        this.buildTree(childData, child, ++level);
                    }
                }
                
//                node.addChild(val, child);
            }
            
        }
    }
    
    private void setAttributeValues(List<Data> data) {
        this.attributeValue = new HashMap<>();
        for (String attr : this.attributes) {
            data.stream().forEach(row -> {
                Set<Integer> set;
                if (this.attributeValue.containsKey(attr)) {
                    set = this.attributeValue.get(attr);
                } else {
                    set = new HashSet<>();
                }
                String attrName = StringUtils.capitalize(attr);
                set.add((Integer)Reflector.callUserFunc(Data.class, row, "get"
                        + attrName));
                this.attributeValue.put(attr, set);
            }); 
        }
        
    }
    
    private void getContinuousBin(List<Data> data, String attr, 
            double threshold) {
        
    }
    
    private Map<Integer, List<Data>> getBin(List<Data> data, String attr, 
            int binValue) {
        Map<Integer, List<Data>> dist = new HashMap<>();
        String attrName = StringUtils.capitalize(attr);
        data.stream().forEach(row -> {
            Integer label = null;// = row.getIspa();
            Object value = Reflector.callUserFunc(Data.class, 
                    row, "get" + attrName);
            if ((int)value == binValue) {
                List<Data> records;
                if (dist.containsKey(label)) {
                    records = dist.get(label);
                } else {
                    records = new ArrayList<>();
                }
                records.add(row);
                dist.put(label, records);
            }
        });
        
        return dist;
    }
    
    private Map<Integer, Integer> countClassDistribution(
            List<Data> data) {
        Map<Integer, Integer> map = new HashMap<>();
        data.stream().forEach(row -> {
            Integer cls = null;// = row.getIspa();
            if (map.containsKey(cls)) {
                int c = map.get(cls);
                map.put(cls, ++c);
            } else {
                map.put(cls, 1);
            }
        });
        return map;
    }
        
    private void calculateTotalEntropy() {
        final int TOTAL_SAMPLES = this.dataList.size();
        this.totalEntropy = 0;
        this.classDistribution.forEach((key, value) -> {
            double divResult = (double)value / (double)TOTAL_SAMPLES;
            this.totalEntropy += (-1) * divResult * Math.log(divResult);
        });
    }
    
    // completed
    private Map<String, Double> calculateAttributeGain(List<Data> data, 
            Set<String> excludedAttributes) {
        Map<String, Double> attributeGain = new HashMap<>();
        for (String attr : this.attributes) {
            if (excludedAttributes == null || 
                    !excludedAttributes.contains(attr)) {
                attributeGain.put(attr, this.calculateGain(data, attr));
            }
        }
        
        return attributeGain;
    }
    
    // completed
    private double calculateGain(List<Data> data, String attr) {
        Map<String, Map<Integer, Integer>> totalAttributeSamples = 
                new HashMap<>();
        Map<String, Map<Integer, Double>> attributesEntropy = 
                this.calculateAttributeEntropy(data, totalAttributeSamples);
        
        Double[] gain = new Double[]{this.totalEntropy};
        Map<Integer, Double> attributeEntropy = attributesEntropy.get(attr);
        Map<Integer, Integer> totalSamples = totalAttributeSamples.get(attr);
        
        final int TOTAL_SAMPLES = data.size();
        attributeEntropy.forEach((attrVal, entropy) -> {
            double divResult = (double)totalSamples.get(attrVal) / 
                    (double)TOTAL_SAMPLES;
            gain[0] += (divResult * entropy);
        });
        return gain[0];
    }
    
    // completed
    private Map<String, Map<Integer, Double>> calculateAttributeEntropy(
            List<Data> data, Map<String, Map<Integer, Integer>> 
                    totalAttributeSamples) {
        Map<String, Map<Integer, Double>> attributeEntropy = new HashMap<>();
        for (String attr : this.attributes) {
            attributeEntropy.put(attr, this.calculateEntropy(data, attr, 
                    totalAttributeSamples));
        }
        
        return attributeEntropy;
    }
    
    // completed
    private Map<Integer, Double> calculateEntropy(List<Data> data, 
            String attr, Map<String, Map<Integer, Integer>> 
                    totalAttributeSamples) {
        Map<Integer, Double> entropy = new HashMap<>();
        Map<String, Map<Integer, Integer>> totalSamples = new HashMap<String, 
                Map<Integer, Integer>>();
        Map<Integer, Integer> freq = totalSamples.get(attr);
        if (freq == null) {
            freq = new HashMap<>();
        }
        
        Map<Integer, Map<String, Map<Integer, Integer>>> valueDistribution = 
                this.countValueDistribution(data);
        for (Map.Entry<Integer, Map<String, Map<Integer, Integer>>> e : 
                valueDistribution.entrySet()) {
            Map<Integer, Integer> dist = e.getValue().get(attr);
            for (Map.Entry<Integer, Integer> entry : dist.entrySet()) {
                int k = entry.getKey();
                Integer v = freq.get(k);
                if (v == null) {
                    freq.put(k, entry.getValue());
                } else {
                    freq.put(k, v + entry.getValue());
                }
            }
            totalSamples.put(attr, freq);
        } 
        
        totalAttributeSamples.put(attr, freq);
        valueDistribution.forEach((key, value) -> {
            Map<Integer, Integer> dist = value.get(attr);
            dist.forEach((k, v) -> {
                double divResult = (double)v / 
                        (double)totalSamples.get(attr).get(k);
                double currEntropy = divResult * Math.log(divResult);
                Double val = entropy.get(k);
                if (val == null) {
                    entropy.put(k, (-1) * currEntropy);
                } else {
                    entropy.put(k, val + currEntropy);
                }
            });
        });
        return entropy;
    }
    
    // completed
    private Map<Integer, Map<String, Map<Integer, Integer>>> 
        countValueDistribution(List<Data> data) {
        Map<Integer, Map<String, Map<Integer, Integer>>> valueDistribution 
                = new HashMap<>();
        valueDistribution.put(1, new HashMap<>());
        valueDistribution.put(2, new HashMap<>());
        
        final int ROW_LENGTH = 12;
        for (int i = 0; i < data.size(); i++) {
            Data row = data.get(i);
//            int label = row.getIspa();
//            this.incrementDistribution(valueDistribution, label, "pendidikan", 
//                    row.getPendidikan());
//            this.incrementDistribution(valueDistribution, label, "pekerjaan", 
//                    row.getPekerjaan());
//            this.incrementDistribution(valueDistribution, label, "penghasilan", 
//                    row.getPenghasilan());
//            this.incrementDistribution(valueDistribution, label, "ptr", 
//                    row.getPtr());
//            this.incrementDistribution(valueDistribution, label, "ventilasi", 
//                    row.getVentilasi());
//            this.incrementDistribution(valueDistribution, label, "pencahayaan", 
//                    row.getPencahayaan());
//            this.incrementDistribution(valueDistribution, label, "kelembaban", 
//                    row.getKelembaban());
//            this.incrementDistribution(valueDistribution, label, "lantai", 
//                    row.getLantai());
//            this.incrementDistribution(valueDistribution, label, "dinding", 
//                    row.getDinding());
//            this.incrementDistribution(valueDistribution, label, "atap", 
//                    row.getAtap());
        }
        
        return valueDistribution;
    }
    
    // completed
    private void incrementDistribution(Map<Integer, Map<String, 
            Map<Integer, Integer>>> valueDistribution, int label, String attr, 
            int value) {
        Map<String, Map<Integer, Integer>> map = 
                valueDistribution.get(label);
        int intVal = value;
        Map <Integer, Integer> dist = new HashMap<>();
        if (map.containsKey(attr)) {
            dist = map.get(attr);
            if (dist.containsKey(intVal)) {
                dist.computeIfPresent(intVal, (k, v) -> v + 1);
            } else {
                dist.put(intVal, 1);
            }
        } else {
            dist.put(intVal, 1);
        }
        map.put(attr, dist);
        valueDistribution.put(label, map);
    }
}
