/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AVL;

import BinarySearchTree.BST;
import Node.Node;

/**
 *
 * @author MarekPC
 * @param <K>
 */
public class AVL<K extends Comparable<K>> extends BST<K> {

    int count;

    public AVL() {
        super();
        count = 0;
    }

    @Override
    public boolean insert(K data) {

//        System.out.println("INSERTING "+data);
        Node<K> inserted = super.put(data);
        if (inserted == null) {
            return false;
        } else {
            count++;
            checkBalance(inserted);
            return true;
        }
    }

    
    @Override
    public boolean delete(K key) {
        boolean ret = true;
        Node<K> r = super.delete(root, key);
        if (r != null) {
            checkBalance(r);
            ret = true;
        } else {
            if (root == null) {
                ret = true;
            }
        }
        if (ret) {
            count--;
        }
        return ret;
    }

    public int getCount() {
        return count;
    }

    private int getSubHeight(Node<K> node) {
        if (node == null) {
            return 0;
        }
        return node.getSubtreeHeight();
    }

    private int getBalanceFactor(Node<K> node) {
        if (node == null) {
            return 0;
        }
        return getSubHeight(node.left) - getSubHeight(node.right);
    }

    private void checkBalance(Node<K> node) {
        int b_factor;
        while (node != null) {
            b_factor = getBalanceFactor(node);
            if (Math.abs(b_factor) > 1) {
                balanceNode(node);
            }
            node = node.parent;
        }

    }

    private void balanceNode(Node<K> node) {
//        System.out.println("\n----   BEFORE BALANCE  "+ node.key+"     ----\n ");
//        printTree();
//        

        if (getBalanceFactor(node) > 1) { // LEFT SUBTREE BIGGER
            if (getSubHeight(node.left.left) > getSubHeight(node.left.right)) {//RR-ROTATE
//                System.out.println("\n--- PERFORMING RIGHT ROTATE \n\n");
                node = rotateRight(node);
            } else { // L-R rotate
//                System.out.println("\n--- PERFORMING LEFT ROTATE ON"+node.left+"   \n\n");
                node.left = rotateLeft(node.left);

//                printTree();
//                System.out.println("\n--- PERFORMING RIGHT ROTATEON"+node+" \n\n");
                node = rotateRight(node);
            }
        } else { // RIGHT SUBTREE BIGGER
            if (getSubHeight(node.right.left) < getSubHeight(node.right.right)) { //LL-ROTATE
//                System.out.println("\n--- LL BRANCH PERFORMING LEFT ROTATE  \n\n");
                node = rotateLeft(node);
            } else { // R-L rotate
//                System.out.println("\n--- RL BRANCH PERFORMING RIGHT ROTATE \n\n");
                node.right = rotateRight(node.right);
//                printTree();
//                System.out.println("\n---RL BRANCH PERFORMING LEFT ROTATE  \n\n");
                node = rotateLeft(node);
            }
        }
        if (node.parent == null) {
            root = node;
        }
//        System.out.println("\n\n\n----   AFTER BALANCE  "+"     ----\n ");
//        printTree();
    }

    private Node<K> rotateRight(Node<K> node) {
        Node<K> newSubRoot = node.left;

        newSubRoot.parent = node.parent;
        node.parent = newSubRoot;

        if (newSubRoot.right != null) {
            newSubRoot.right.parent = node;
        }

        node.left = newSubRoot.right;
        newSubRoot.right = node;

        if (newSubRoot.parent != null) {
            if (newSubRoot.parent.key.compareTo(newSubRoot.key) < 0) {
                newSubRoot.parent.right = newSubRoot;
            } else {
                newSubRoot.parent.left = newSubRoot;
            }

        }

        return newSubRoot;
    }

    private Node<K> rotateLeft(Node<K> node) {
        Node<K> newSubRoot = node.right;
        newSubRoot.parent = node.parent;
        node.right = newSubRoot.left;
        node.parent = newSubRoot;

        if (newSubRoot.left != null) {
            newSubRoot.left.parent = node;
        }

        newSubRoot.left = node;
        if (newSubRoot.parent != null) {
            if (newSubRoot.parent.key.compareTo(newSubRoot.key) < 0) {
                newSubRoot.parent.right = newSubRoot;
            } else {
                newSubRoot.parent.left = newSubRoot;
            }
        }
        return newSubRoot;

    }

}
