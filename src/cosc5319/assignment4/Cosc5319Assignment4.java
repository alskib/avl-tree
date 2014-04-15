// Franklin Leung
package cosc5319.assignment4;

public class Cosc5319Assignment4 {

    public static void main(String[] args) {
        AVLTree myTree = new AVLTree();
        
        System.out.println("Inserting data...");
        myTree.InsertAVLTree(new Widget(10, "Ten", 1010));
        myTree.InsertAVLTree(new Widget(5, "Five", 55));
        myTree.InsertAVLTree(new Widget(15, "Fifteen", 1551));
        myTree.InsertAVLTree(new Widget(1, "One", 111));
        myTree.InsertAVLTree(new Widget(2, "Two", 22222));
        myTree.InsertAVLTree(new Widget(15, "Whatever1", 8745));
        myTree.InsertAVLTree(new Widget(3, "Whatever2", 5124));
        myTree.InsertAVLTree(new Widget(4, "Whatever3", 553));
        System.out.println("Insertion done.");
        
        myTree.PrintTree();
        myTree.InOrderTraversal();
    }
    
}
