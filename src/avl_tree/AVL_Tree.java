/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl_tree;

import AVL.AVL;
import Tree_test.TreeTests;
import BinarySearchTree.TreeTraversals;
import Node.Node;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author MarekPC
 */
public class AVL_Tree {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AVL<Kniha> strom = new AVL<>();
        
//        TreeTests.test(strom, 100, 0.55);
        Kniha kniha = new Kniha("test1", 2006);
        Kniha kniha2 = new Kniha("test2", 2009);
        strom.insert(kniha);
        strom.insert(kniha2);
        strom.printTree();
        kniha.setRok(2600);
        strom.printTree();
        

               
        
    }
   
}
