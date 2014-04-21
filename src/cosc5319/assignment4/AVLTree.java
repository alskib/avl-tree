// Franklin Leung
package cosc5319.assignment4;

import java.util.Stack;

public class AVLTree {
    private TreeNode head = new TreeNode(null);
    
    public AVLTree() {
            head = new TreeNode(null);
    }

    public TreeNode getHead() {
        return head;
    }
    
    public void InsertNode(int wid, String desc, int price) {
        InsertNode(new TreeNode(new Widget(wid, desc, price)), head.getLLink());
    }
    
    private void InsertNode(TreeNode n, TreeNode tempNode) {
        if (this.head.getLLink() == null) {
            this.head.setLLink(n);
            return;
        }
        
        if (n.getPrice() < tempNode.getPrice()) {
            if (tempNode.getLLink() == null) {
                tempNode.setLLink(n);
                return;
            }
            InsertNode(n, tempNode.getLLink());
        } else {
            if (tempNode.getRLink() == null) {
                tempNode.setRLink(n);
                return;
            }
            InsertNode(n, tempNode.getRLink());
        }
        
        if (tempNode == this.head.getLLink())
            rotate(this.head.getLLink(), this.head);
        
        if (tempNode.getLLink() != null)
            rotate(tempNode.getLLink(), tempNode);
        
        if (tempNode.getRLink() != null)
            rotate(tempNode.getRLink(), tempNode);
    }
    
    public boolean BinarySearch(int price) {
        TreeNode P = this.head.getLLink();
        do {
            if (P == null)
                return false;
            if (price == P.getPrice())
                return true;
            else if (price < P.getPrice())
                P = InOrderPredecessor(P);
            else
                P = InOrderSuccessor(P);
        } while (P != this.head.getLLink());
        return false;
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
        TreeNode t = this.head.getLLink();
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
    
    public void DeleteByPrice (int price) {
        DeleteByNode(price, this.head.getLLink());
    }
    
    private void DeleteByNode(int price, TreeNode tempNode) {
        if (tempNode == null)
            return;
        
//        if (tempNode != this.head)
        
        // false looks left, true looks right
        boolean direction = false;
        if (price > tempNode.getPrice() && tempNode != this.head)
            direction = true;
        
        TreeNode child;
        if (direction == true)
            child = tempNode.getRLink();
        else
            child = tempNode.getLLink();
        
        if (child == null)
            return;
        
        if (tempNode == this.head && child.getBalance() == 0 && child.getPrice() == price) {
            TreeNode newRoot = child.getLLink();
            if (newRoot == null) {
                this.head.setLLink(null);
                return;
            } else {
                ReallyDelete(tempNode, child, false);
                return;
            }
        } else if (price == child.getPrice()) {
            ReallyDelete(tempNode, child, direction);
        } else {
            DeleteByNode(price, child);
        }
    }
    
    private void ReallyDelete(TreeNode parent, TreeNode remove, boolean direction) {
        TreeNode temp = null;
        TreeNode left = remove.getLLink();
        TreeNode right = remove.getRLink();
        
        if (left != null || right != null)
            temp = findSuccessor(remove);
        
        if (direction && (parent != this.head))
            parent.setRLink(temp);
        else
            parent.setLLink(temp);
        
        if (temp != null) {
            if (temp != left)
                temp.setLLink(remove.getLLink());
            if (temp != right)
                temp.setRLink(remove.getRLink());
        }
        
        remove.setLLink(null);
        remove.setRLink(null);
    }
    
    private TreeNode findSuccessor(TreeNode node) {
        TreeNode temp = node;
        TreeNode parent = null;
        
        boolean direction = false;
        if (temp.getBalance() > 0)
            direction = true;
        
        parent = temp;
        if (direction == true)
            temp = temp.getRLink();
        else
            temp = temp.getLLink();
        
        if (temp == null)
            return temp;
        
        while ((temp.getRLink() != null && direction == false ||
                temp.getLLink() != null && direction == true)) {
            parent = temp;
            if (direction == true)
                temp = temp.getLLink();
            else
                temp = temp.getRLink();
        }
        
        if (temp == parent.getLLink()) {
            parent.setLLink(temp.getRLink());
            temp.setRLink(null);
        } else {
            parent.setRLink(temp.getLLink());
            temp.setLLink(null);
        }
        
        return temp;
    }
    
    public void PrintTree() {
        System.out.println("******************************");
        PrintTreeR(this.head.getLLink(), 0);
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
            if (n != null)
                System.out.println("|-------" + n.getPrice());
        } else {
            if (n != null)
                System.out.println(n.getPrice());
        }
        if (n.getLLink() != null)
            PrintTreeR(n.getLLink(), level+1);
    }
    
    private void rotate(TreeNode rotateBase, TreeNode rootAbove) {
        int balance = rotateBase.getBalance();
        
        TreeNode child = (balance < 0) ? rotateBase.getLLink() : rotateBase.getRLink();
        
        if (child == null)
            return;
        
        int childBalance = child.getBalance();
        TreeNode grandChild = null;
        
        // left-left
        if (balance < -1 && childBalance < 0) {
            System.out.println("Left-left.");
            if (rootAbove != this.head && rootAbove.getRLink() == rotateBase)
                rootAbove.setRLink(child);
            else
                rootAbove.setLLink(child);
            grandChild = child.getRLink();
            child.setRLink(rotateBase);
            rotateBase.setLLink(grandChild);
            return;
        }
        
        // right-right
        else if (balance > 1 && childBalance > 0) {
            System.out.println("Right-right.");
            if (rootAbove != this.head && rootAbove.getRLink() == rotateBase)
                rootAbove.setRLink(child);
            else
                rootAbove.setLLink(child);
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

