/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Azhary Arliansyah
 */
public class Node {
    
    public enum Type {
        ROOT, BRANCH, LEAF
    }
    
    public enum AttributeType {
        CONTINUOUS, DISCRETE
    }
    
    private String attribute;
    private String label;
    private Type nodeType;
    private AttributeType nodeAttributeType;
    private Map<String, Node> childNodes;
    private Set<String> parentExcludedAttributes;
    private double threshold;
    private Node leftChild;
    private Node rightChild;
    
    public Node(String attribute, Type nodeType, 
            AttributeType nodeAttributeType) {
        this.attribute = attribute;
        this.childNodes = new HashMap<>();
        this.nodeType = nodeType;
        this.nodeAttributeType = nodeAttributeType;
        this.label = null;
    }
    
    public Node(String label) {
        this.label = label;
        this.childNodes = new HashMap<>();
        this.nodeType = Type.LEAF;
        this.nodeAttributeType = null;
    }
    
    public Node() {
        this.childNodes = new HashMap<>();
        this.label = null;
    }
    
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
    
    public double getThreshold() {
        return this.threshold;
    }
    
    public void setExcludedAttributes(Set<String> attrs) {
        this.parentExcludedAttributes = attrs;
    }
    
    public Set<String> getExcludedAttributes() {
        return this.parentExcludedAttributes;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    
    public String getAttribute() {
        return this.attribute;
    }
    
    public void setChilds(Map<String, Node> childs) {
        this.childNodes = childs;
    }
    
    public void addChild(String branch, Node child) {
        this.childNodes.put(branch, child);
    }
    
    public Node getChild(String value) {
        return this.childNodes.get(value);
    }
    
    public Map<String, Node> getChilds() {
        return this.childNodes;
    }
    
    public void setType(Type t) {
        this.nodeType = t;
    }
    
    public Type getType() {
        return this.nodeType;
    }
    
    public void setAttributeType(AttributeType at) {
        this.nodeAttributeType = at;
    }
    
    public AttributeType getAttributeType() {
        return this.nodeAttributeType;
    }
    
    public void setLeftChild(Node node) {
        this.leftChild = node;
    }
    
    public Node getLeftChild() {
        return this.leftChild;
    }
    
    public void setRightChild(Node node) {
        this.rightChild = node;
    }
    
    public Node getRightChild() {
        return this.rightChild;
    }
}

