/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Node;


/**
 *
 * @author MarekPC
 * @param <K>
 */
public class Node<K extends Comparable<K>> {
    public K key;
    public Node<K> left,right,parent;
    
    
    public Node(K key){
        this.key = key;
        
    }
    public int getSubtreeHeight(){
//        System.out.println("SUBHEIGHT FOR "+key);
        
        int leftSubTree = 1;
        int rightSubTree = 1;
        if(left != null) leftSubTree += left.getSubtreeHeight();
        if(right != null) rightSubTree += right.getSubtreeHeight();
        
        return Math.max(leftSubTree,rightSubTree);
    }
    
    @Override
    public String toString() {
        return "INFO FOR NODE WITH KEY "+key + " PARENT: "+((parent == null)?"null":parent.key) +"  LEFT: "+((left == null)?"null":left.key)+"  RIGHT: "+((right == null)?"null":right.key);
    }
    
    
}
