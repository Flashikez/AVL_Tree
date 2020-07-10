/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinarySearchTree;

import Node.Node;


/**
 *
 * @author MarekPC
 * @param <K>
 */
public class BST<K extends Comparable<K>> {

    protected Node<K> root;

    public BST() {
        root = null;

    }

    public boolean insert(K data) {
       
        return (put(data) != null);
    }

    protected Node<K> put(K data) {
        Node<K> nodeToInsert = new Node<K>(data);

        if (root == null) {
            root = nodeToInsert;
            return root;
        }

        Node<K> currentNode = root;
        Node<K> parentOfCurrent = null;
        while (currentNode != null) {
            int result = data.compareTo(currentNode.key);
            if (result < 0) {
                parentOfCurrent = currentNode;
                currentNode = currentNode.left;
            } else if (result > 0) {
                parentOfCurrent = currentNode;
                currentNode = currentNode.right;
            } else {
                // KEY ALREADY THERE
                return null;
            }

        }
        if (data.compareTo(parentOfCurrent.key) < 0) {
            parentOfCurrent.left = nodeToInsert;
            nodeToInsert.parent = parentOfCurrent;
        } else {
            parentOfCurrent.right = nodeToInsert;
            nodeToInsert.parent = parentOfCurrent;
        }
        return nodeToInsert;
    }
    
    public boolean delete(K key){
         Node<K> r = delete(root,key);
         if(r != null){
             return true;
         }else{
             return root == null;
         }
        
    }

    
    protected Node<K> delete(Node<K> startOfSearch, K key) {
        Node<K> nodeToDelete = null;
        Node<K> parentOfnodeToDelete = startOfSearch.parent;
        Node<K> currentNode = startOfSearch;

        while (currentNode != null) {
            if (key.compareTo(currentNode.key) == 0) //eureka!
            {
                nodeToDelete = currentNode;
                break;
            } else if (key.compareTo(currentNode.key) > 0) //target greater, so go right
            {
                parentOfnodeToDelete = currentNode;
                currentNode = currentNode.right;
            } else //target less, so go left
            {
                parentOfnodeToDelete = currentNode;
                currentNode = currentNode.left;
            }
        }
        // NOT FOUND
        if (nodeToDelete == null) {
            return null;
        }
        // DELETING ROOT
        if (nodeToDelete == root) {
            if (nodeToDelete.left == null && nodeToDelete.right == null) {
                root = null;
                return root;
            } else if (nodeToDelete.left != null && nodeToDelete.right != null) {
                Node<K> successor = getInOrderSuccessor(nodeToDelete.right);
                nodeToDelete.key = (K) successor.key;
                return delete(nodeToDelete.right, successor.key);

            } else {
                if (nodeToDelete.left == null) {
                    root = nodeToDelete.right;
                    nodeToDelete.right.parent = null;
                } else {
                    root = nodeToDelete.left;
                    nodeToDelete.left.parent = null;
                }
                return root;
            }

            // NOT DELETING ROOT
        } else {
            boolean isLeftOfParent = (parentOfnodeToDelete.left == nodeToDelete);

            if (nodeToDelete.left == null && nodeToDelete.right == null) {
                if (isLeftOfParent) {
                    parentOfnodeToDelete.left = null;
                } else {
                    parentOfnodeToDelete.right = null;
                }

                return parentOfnodeToDelete;
                // TWO CHILDREN
            } else if (nodeToDelete.left != null && nodeToDelete.right != null) {
                Node<K> successor = getInOrderSuccessor(nodeToDelete.right);
                nodeToDelete.key = (K) successor.key;
                return delete(nodeToDelete.right, successor.key);

                // ONE CHILD
            } else {
                if (nodeToDelete.left == null) {
                    if (isLeftOfParent) {
                        parentOfnodeToDelete.left = nodeToDelete.right;
                    } else {
                        parentOfnodeToDelete.right = nodeToDelete.right;

                    }
                    nodeToDelete.right.parent = parentOfnodeToDelete;
                    return nodeToDelete.right;
                } else {
                    if (isLeftOfParent) {
                        parentOfnodeToDelete.left = nodeToDelete.left;
                    } else {
                        parentOfnodeToDelete.right = nodeToDelete.left;

                    }
                    nodeToDelete.left.parent = parentOfnodeToDelete;
                    return nodeToDelete.left;
                }
            }
        }

    }

    protected Node<K> getInOrderSuccessor(Node<K> r) {
        if (r.left != null) {
            return getInOrderSuccessor(r.left);
        } else {
            return r;
        }
    }

    public K findKey(K key) {
        Node<K> r = (searchForKey(key));
        if (r == null) {
            return null;
        } else {
            return r.key;
        }
    }

    public boolean containsKey(K key) {
        return (searchForKey(key)) == null;
    }

    protected Node<K> searchForKey(K key) {
        if (root == null) {
            return null;
        }
        Node<K> currentNode = root;
        while (currentNode != null) {
            int result = key.compareTo(currentNode.key);
            if (result < 0) {
                currentNode = currentNode.left;
            } else if (result > 0) {
                currentNode = currentNode.right;
            } else {
                // KEY FOUND
                return currentNode;
            }
        }
        return null;
    }

    private void printTree(Node<K> root, int level) {
        if (root == null) {
            return;
        }
        printTree(root.right, level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t");
            }
            System.out.println("|------- " + root.key);
        } else {
            System.out.println(root.key);
        }
        printTree(root.left, level + 1);
    }

    public void printTree() {

        printTree(root, 0);
//        for (int i = -1000; i < 1000; i++) {
//            Node<K> kniha = searchForKey((K) new Kniha("", i));
//            if (kniha != null) {
//                System.out.println(kniha.toString());
//            }
//        }

    }

}
