// Franklin Leung
package cosc5319.assignment4;

import java.util.Stack;

public class AVLTree {
    private TreeNode root = new TreeNode(null);
    private TreeNode head = new TreeNode(null);
    private TreeNode P = new TreeNode(null);    // pointer for traversal
    
    public AVLTree() {
            head.setLTag(false);    // left child yes/no
            head.setLLink(head);    // link to left child
            head.setWidget(null);   // datum
            head.setRLink(head);    // link to right child
            head.setRTag(true);     // right child yes/no
    }

    public TreeNode getHead() {
        return head;
    }

    public TreeNode getRoot() {
        return root;
    }
    
    public void InsertAVLTree(Widget w) {
        TreeNode Q = new TreeNode(w); // node to be inserted
        boolean found;

        if (head.getLTag() == false) {      // if head's LTag is false,
            root = Q;                       //   then no tree exists
            root.setLLink(head);
            root.setRLink(head);
            head.setLLink(root);
            head.setLTag(true);
        } else {
            found = false;
            P = root;
            do {
                if (w.getPrice() < P.getPrice()) { // new node value is lower
                    if (P.getLTag() == false) { // empty left subtree
                        Q.setAllAttr(P.getLTag(), P.getLLink(), P, false);
                        P.setLLink(Q);
                        P.setLTag(true);
                        found = true;
                    } else { // pointer node has left child, traverse down the left
                        P = P.getLLink();
                    }
                } else { // new node value is higher
                    if (P.getRTag() == false) { // empty right subtree
                        Q.setAllAttr(false, P, P.getRLink(), P.getRTag());
                        P.setRLink(Q);
                        P.setRTag(true);
                        if (Q.getRTag()) {
                            TreeNode t = InOrderSuccessor(P);
                            t.setLLink(Q);
                        }
                        found = true;
                    } else { // pointer node has right child, traverse down the right
                        P = P.getRLink();
                    }
                }
            } while (!found); // stop when spot found
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

    public TreeNode FindCustomerRecursive(TreeNode searchPoint, int price) {
        if (price == searchPoint.getPrice())
            return searchPoint;
        else if (price < searchPoint.getPrice())
        {
            if (!searchPoint.getLTag())
                return null;
            else
                return FindCustomerRecursive(searchPoint.getLLink(), price);
        }
        else
        {
            if (!searchPoint.getRTag())
                return null;
            else
                return FindCustomerRecursive(searchPoint.getRLink(), price);
        }
    }

    public TreeNode InOrderPredecessor(TreeNode P) {
        TreeNode Q = P.getLLink();
        if (P.getLTag())
            while (Q.getRTag())
                Q = Q.getRLink();
        return Q;
    }

    public TreeNode InOrderSuccessor(TreeNode P) {
        TreeNode Q = P.getRLink();
        if (P.getRTag())
            while (Q.getLTag())
                Q = Q.getLLink();
        return Q;
    }
     
    public void InOrderTraversal() {
        TreeNode T = this.root;
        
        if (T != null) {
            while (T.getLTag() == true) {
                T = T.getLLink();   // drill down to node with smallest value
            }
            do {
                System.out.println(T.getPrice());
                T = InOrderSuccessor(T);
            } while (T != this.head);
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
                else if (searchPoint.getRTag())
                    searchPoint = searchPoint.getRLink();
                else
                    return null;
            }
            else
            {
                if (price == searchPoint.getLLink().getPrice())
                    return searchPoint;
                else if (searchPoint.getLTag())
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

            if (!temp.getLTag() && !temp.getRTag())
                DeleteNodeNoChildren(deleteRoot, parent, dir);
            else if (!temp.getLTag() && temp.getRTag())
                DeleteNodeRightChild(deleteRoot, parent, dir);
            else if (temp.getLTag() && !temp.getRTag())
                DeleteNodeLeftChild(deleteRoot, parent, dir);
            else if (temp.getLTag() && temp.getRTag())
                DeleteNodeBothChildren(deleteRoot, parent, dir);
            System.out.println("$" + price + " deleted.");
        }
    }

    public void DeleteNodeNoChildren(boolean deleteRoot, TreeNode parent, int direction) {
        if (!deleteRoot) {
            if (direction == 1) {
                parent.setRTag(parent.getRLink().getRTag());
                parent.setRLink(parent.getRLink().getRLink());
            } else {
                parent.setLTag(parent.getLLink().getLTag());
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
                if (!parent.getRLink().getLTag())
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
        if (tempNode.getLLink() != null && tempNode.getLTag())
        {
            while (tempNode.getLTag() && tempNode.getLLink() != null)
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
            tempNode.setLTag(nodeToBeDeleted.getLTag());
            tempNode.setRTag(nodeToBeDeleted.getRTag());
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
            tempNode.setLTag(nodeToBeDeleted.getLTag());
        }
    }
    
    public void PrintTree() {
        PrintTreeR(this.root, 0);
    }
    
    private void PrintTreeR(TreeNode n, int level) {
        if (n == null)
            return;
        if (n.getRTag() == true)
            PrintTreeR(n.getRLink(), level+1);
        if (level != 0) {
            for (int i=0; i<level; i++) {
                System.out.print("|\t");
            }
            System.out.println("|-------" + n.getPrice());
        } else {
            System.out.println(n.getPrice());
        }
        if (n.getLTag() == true)
            PrintTreeR(n.getLLink(), level+1);
    }
}

