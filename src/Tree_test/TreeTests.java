/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree_test;

import AVL.AVL;
import BinarySearchTree.TreeTraversals;
import avl_tree.Kniha;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author MarekPC
 */
public class TreeTests {

    public static void test(AVL tree, int reps, double insertChance) {
        ArrayList<Kniha> arr = new ArrayList<>();
        Random rand = new Random(); // 54
        int min = -1000;
        int max = 1000;
        int inserts = 0;
        int deletes = 0;
        int pocetOperacii = reps;
        for (int i = 0; i < pocetOperacii; i++) {

            if (rand.nextDouble() < insertChance) {
                int rando = rand.nextInt((max - min) + 1) + min;
                Kniha k = new Kniha("lala", rando);
                System.out.println("INSERTING " + k);
                tree.insert(k);
                boolean there = false;
                for (Kniha kniha : arr) {
                    if(kniha.compareTo(k) == 0){
                        there = true;
                        break;
                    }
                    
                }
                if(!there)
                arr.add(k);
                else System.out.println("\n---------------- DUPLICATED -----------------");
            } else {
                if (arr.size() > 0) {
                    Kniha k = arr.remove(new Random().nextInt(arr.size()));
                    System.out.println("DELETING " + k);
                    tree.delete(k);
                }
            }

        }
         tree.printTree();
         System.out.println("\nTREE NODES COUNT :"+tree.getCount() +"\n");
        TreeTraversals.printInOrder(tree);
        TreeTraversals.printLevelOrder(tree);
         System.out.println("\nARRAY NODES COUNT :"+arr.size() +"\n");
       System.out.println("******** TEST ARRAY SORTED *********\n");
        Collections.sort(arr);
        for (Kniha kniha : arr) {
            System.out.print(kniha + "   ");
        }
        System.out.println("\n");
    }
}
