// Franklin Leung
package cosc5319.assignment4;

public class Cosc5319Assignment4 {

    public static void main(String[] args) {
        AVLTree myTree = new AVLTree();
        
        System.out.println("Inserting data...");
        myTree.InsertNode(new Widget(10, "Ten", 1010));
        myTree.InsertNode(new Widget(5, "Five", 1515));
        myTree.InsertNode(new Widget(1, "One", 1313));
        myTree.InsertNode(new Widget(1, "One", 1212));
        myTree.InsertNode(new Widget(1, "One", 1111));
//        myTree.InsertNode(new Widget(15, "Fifteen", 1551));
//        myTree.InsertNode(new Widget(2, "Two", 22222));
//        myTree.InsertNode(new Widget(15, "Whatever1", 8745));
//        myTree.InsertNode(new Widget(3, "Whatever2", 5124));
//        myTree.InsertNode(new Widget(4, "Whatever3", 553));
        System.out.println("Insertion done.");
        
        myTree.PrintTree();
        myTree.InOrderTraversal();
    }
    
}
