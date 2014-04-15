// Franklin Leung
package cosc5319.assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class AVLTree {
    private TreeNode root;
    private TreeNode head;
    private TreeNode P;            // pointer for traversal
    private TreeNode rootAbove;    // used for AVL rotation
    private ArrayList<TreeNode> st = new ArrayList<TreeNode>();
    
    public AVLTree() {
            root = new TreeNode(null);
            head = root;
    }

    public TreeNode getHead() {
        return head;
    }

    public TreeNode getRoot() {
        return root;
    }
    
    public void InsertNode(Widget w) {
        InsertNode(w, root.getLLink());
    }
    
    public void InsertNode(Widget w, TreeNode tempNode) {
        if (this.root.getLLink() == null) {
            this.root.setLLink(new TreeNode(w));
            return;
        }
        
        if (w.getPrice() < tempNode.getPrice()) {
            if (tempNode.getLLink() == null) {
                tempNode.setLLink()
        }

        if (root == null) {      // if head's LLink is false,
            root = Q;                       //   then no tree exists
            root.setLLink(null);
            root.setRLink(null);
            st.add(root);
            System.out.println("Inserted root (" + w.getPrice() + ").");
        } else {
            found = false;
            P = root;
            do {
                if (w.getPrice() < P.getPrice()) {  // new node value is lower
                    
                    if (P.getLLink() == null) {     // no left child
                        Q.setAllAttr(null, null);
                        P.setLLink(Q);
                        found = true;
                        st.add(Q);
//                        System.out.println("Added: " + Q.getPrice() + " if/if");
                    } else { // pointer node has left child, traverse down the left
//                        if (P != root) 
//                            st.add(P);
////                            System.out.println("Added: " + P.getPrice() + " if/else"); }
                        P = P.getLLink();
                    }
                } else { // new node value is higher
                    if (P.getRLink() == null) {     // no right child
                        Q.setAllAttr(null, null);
                        P.setRLink(Q);
//                        if (Q.getRLink() != null) {
//                            TreeNode t = InOrderSuccessor(P);
//                            t.setLLink(Q);
//                        }
                        found = true;
                        st.add(Q);
//                        System.out.println("Added: " + Q.getPrice() + " else/if");
                    } else { // pointer node has right child, traverse down the right
                        if (P != root)
                            st.add(P);
//                            System.out.println("Added: " + P.getPrice() + " else/else"); }
                        P = P.getRLink();
                    }
                }
            } while (!found); // stop when spot found
            TreeNode tempBase, tempRootAbove;
            System.out.println("Inserted " + w.getPrice());
            for (TreeNode t : st)
                System.out.println("[I] Iterator: " + t.getPrice());

            if (st.size() >= 2) {
                for (int i = st.size()-1; i > 0; i--) {
                    tempBase = st.get(i);
                    System.out.println("--------------------");
                    System.out.println("Got base " + tempBase.getPrice());
                    tempRootAbove = st.get(i-1);
                    System.out.println("Got root " + tempRootAbove.getPrice());
                    System.out.println("--------------------");
                    rotate(tempBase, tempRootAbove);
                }
            }
        }
        
        
        
    }
    
    public TreeNode BinarySearch(int price) {
        TreeNode P = this.getRoot();
        do {
            if (price == P.getPrice())
                return P;
            else if (price < P.getPrice())
                P = InOrderPredecessor(P);
            else
                P = InOrderSuccessor(P);
        } while (P != this.getHead());
        return null;
    }

    public TreeNode FindCustomerIterative(int price) {
        return BinarySearch(price);
    }

    public TreeNode InOrderPredecessor(TreeNode P) {
        TreeNode Q = P.getLLink();
        if (P.getLLink() != null)
            while (Q.getRLink() != null)
                Q = Q.getRLink();
        return Q;
    }

    public TreeNode InOrderSuccessor(TreeNode P) {
        TreeNode Q = P.getRLink();
        if (P.getRLink() != null)
            while (Q.getLLink() != null)
                Q = Q.getLLink();
        return Q;
    }
     
    public void InOrderTraversal() {
        TreeNode t = this.root;
        Stack<TreeNode> st = new Stack<TreeNode>();
        while (true) {
            if (t != null) {
                st.push(t);
                t = t.getLLink();
            } else {
                if (st.empty())
                    break;
                t = st.pop();
                System.out.println(t.getPrice());
                t = t.getRLink();
            }
        }
    }

    public TreeNode FindParent(TreeNode searchPoint, int price) {
        while (true)
        {
            if (price == searchPoint.getPrice())
                return searchPoint;
            else if (searchPoint.getPrice() > price)
            {
                if (price == searchPoint.getRLink().getPrice())
                    return searchPoint;
                else if (searchPoint.getRLink() != null)
                    searchPoint = searchPoint.getRLink();
                else
                    return null;
            }
            else
            {
                if (price == searchPoint.getLLink().getPrice())
                    return searchPoint;
                else if (searchPoint.getLLink() != null)
                    searchPoint = searchPoint.getLLink();
                else
                    return null;
            }
        }
    }

    public void DeleteTreeNode(int price) {
        boolean deleteRoot = false;
        int dir = 0; // direction: 0 is go left, 1 is go right
        TreeNode parent;
        TreeNode temp;

        parent = FindParent(root, price);
        if (parent == null)
            System.out.println("Item with price $" + price + " not found.");
        else {
            if (price == this.getHead().getLLink().getPrice())
            {
                System.out.println("Match found - deleting $" + price + ".");
                deleteRoot = true;
            }
            if (price > parent.getPrice()&& !deleteRoot)
                dir = 1;
            if (!deleteRoot)
            {
                if (dir == 1)
                    temp = parent.getRLink();
                else
                    temp = parent.getLLink();
            }
            else
                temp = parent;

            if (temp.getLLink() == null && temp.getRLink() == null)
                DeleteNodeNoChildren(deleteRoot, parent, dir);
            else if (temp.getLLink() == null && temp.getRLink() != null)
                DeleteNodeRightChild(deleteRoot, parent, dir);
            else if (temp.getLLink() != null && temp.getRLink() == null)
                DeleteNodeLeftChild(deleteRoot, parent, dir);
            else if (temp.getLLink() != null && temp.getRLink() != null)
                DeleteNodeBothChildren(deleteRoot, parent, dir);
            System.out.println("$" + price + " deleted.");
        }
    }

    public void DeleteNodeNoChildren(boolean deleteRoot, TreeNode parent, int direction) {
        if (!deleteRoot) {
            if (direction == 1) {
                parent.setRLink(parent.getRLink().getRLink());
            } else {
                parent.setLLink(parent.getLLink().getLLink());
            }
        } else {
            head.setLLink(head);
            root = null;
        }
    }

    public void DeleteNodeRightChild(boolean deleteRoot, TreeNode parent, int direction) {
        if (!deleteRoot) {
            if (direction == 1) {
                parent.setRLink(parent.getRLink().getRLink());
                if (parent.getRLink().getLLink() == null)
                    parent.getRLink().setLLink(parent);
                else {
                    parent.getLLink().getRLink().setLLink(parent.getLLink().getLLink());
                    parent.setLLink(parent.getLLink().getRLink());
                }
            } else {
                head.setLLink(parent.getRLink());
                root = parent.getRLink();
                root.setLLink(null);
            }
        }
    }

    public void DeleteNodeLeftChild(boolean deleteRoot, TreeNode parent, int direction) {
        if (!deleteRoot) {
            if (direction == 1) {
                parent.getRLink().getLLink().setRLink(parent.getRLink().getRLink());
                parent.setRLink(parent.getRLink().getLLink());
            } else {
                parent.getLLink().getLLink().setLLink(parent.getLLink().getLLink());
                parent.setLLink(parent.getLLink().getLLink());
            }
        } else {
            head.setLLink(parent.getLLink());
            root = parent.getLLink();
            root.setRLink(null);
        }
    }

    public void DeleteNodeBothChildren(boolean deleteRoot, TreeNode parent, int direction) {
        TreeNode nodeToBeDeleted, tempNode;
        if (!deleteRoot) {
            if (direction == 1)
                nodeToBeDeleted = parent.getRLink();
            else
                nodeToBeDeleted = parent.getLLink();
        } else
            nodeToBeDeleted = parent;
        tempNode = nodeToBeDeleted.getRLink();
        if (tempNode.getLLink() != null)
        {
            while (tempNode.getLLink() != null)
            {
                tempNode = tempNode.getLLink();
            }
            if (!deleteRoot) {
                if (direction == 1)
                    parent.setRLink(tempNode);
                else
                    parent.setLLink(tempNode);
            } else {
                head.setLLink(tempNode);
                root = tempNode;
            }
            // transfer attributes
            tempNode.setLLink(nodeToBeDeleted.getLLink());
            tempNode.setRLink(nodeToBeDeleted.getRLink());
            nodeToBeDeleted.getLLink().setRLink(tempNode);
        } else {
            if (!deleteRoot) {
                if (direction == 1)
                    parent.setRLink(tempNode);
                else
                    parent.setLLink(tempNode);
            } else 
                root = tempNode;
            tempNode.setLLink(nodeToBeDeleted.getLLink());
        }
    }
    
    public void PrintTree() {
        System.out.println("******************************");
        PrintTreeR(this.root, 0);
        System.out.println("******************************");
    }
    
    private void PrintTreeR(TreeNode n, int level) {
        if (n == null)
            return;
        if (n.getRLink() != null)
            PrintTreeR(n.getRLink(), level+1);
        if (level != 0) {
            for (int i=0; i<level; i++) {
                System.out.print("|\t");
            }
            System.out.println("|-------" + n.getPrice());
        } else {
            System.out.println(n.getPrice());
        }
        if (n.getLLink() != null)
            PrintTreeR(n.getLLink(), level+1);
    }
    
    private void rotate(TreeNode rotateBase, TreeNode rootAbove) {
        int balance = rotateBase.getBalance();
        
//        if (Math.abs(balance) < 2)
//            System.out.println("No rotate.");
        
        TreeNode child;
        
        
        child = (balance < 0) ? rotateBase.getLLink() : rotateBase.getRLink();
        if (child == null)
            return;
        int childBalance = child.getBalance();
        TreeNode grandChild = null;
        
        // left-left
        if (balance < -1 && childBalance < 0) {
            System.out.println("Left-left.");
            if (rootAbove != this.root && rootAbove.getRLink() == rotateBase) {
                rootAbove.setRLink(child);
            } else {
                rootAbove.setLLink(child);
            }
            grandChild = child.getRLink();
            child.setRLink(rotateBase);
            rotateBase.setLLink(grandChild);
            return;
        }
        
        // right-right
        else if (balance > 1 && childBalance > 0) {
            System.out.println("Right-right.");
            if (rootAbove != this.root && rootAbove.getRLink() == rotateBase) {
                rootAbove.setRLink(child);
            } else {
                rootAbove.setLLink(child);
            }
            grandChild = child.getLLink();
            child.setLLink(rotateBase);
            rotateBase.setRLink(grandChild);
            return;
        }
        
        // left-right
        else if (balance < -1 && childBalance > 0) {
            System.out.println("Left-right.");
            grandChild = child.getRLink();
            rotateBase.setLLink(grandChild);
            child.setRLink(grandChild.getLLink());
            grandChild.setLLink(child);
            rotate(rotateBase, rootAbove);
            return;
        }
        
        // right-left
        else if (balance > 1 && childBalance < 0) {
            System.out.println("Right-left.");
            grandChild = child.getLLink();
            rotateBase.setRLink(grandChild);
            child.setLLink(grandChild.getRLink());
            grandChild.setRLink(child);
            rotate(rotateBase, rootAbove);
            return;
        }
    }
}

