/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl_tree;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MarekPC
 */
public class Kniha implements Comparable<Kniha> {

    private String nazov;
    private int rok;
    
    public Kniha(String nazov,int rok){
        this.nazov = nazov;
        this.rok = rok;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    @Override
    public int compareTo(Kniha t)  {
        if(t.rok == this.rok){
                return 0;
        }else if(this.rok < t.rok){
            return -1;
        }else return 1;
    }

    @Override
    public String toString() {
//        return nazov +" "+rok;
        return ""+rok;
    }
    
}
