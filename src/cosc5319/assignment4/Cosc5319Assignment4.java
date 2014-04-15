// Franklin Leung
package cosc5319.assignment4;

public class Cosc5319Assignment4 {

    public static void main(String[] args) {
        AVLTree myTree = new AVLTree();
        
        System.out.println("Inserting data...");
        myTree.InsertNode(new Widget(10, "10", 10));
        myTree.InsertNode(new Widget(15, "15", 15));
        myTree.InsertNode(new Widget(13, "13", 13));
        myTree.InsertNode(new Widget(12, "12", 12));
        myTree.InsertNode(new Widget(11, "11", 11));
//        myTree.InsertNode(new Widget(15, "Fifteen", 16));
//        myTree.InsertNode(new Widget(2, "Two", 22));
//        myTree.InsertNode(new Widget(15, "Whatever1", 8745));
//        myTree.InsertNode(new Widget(3, "Whatever2", 5124));
//        myTree.InsertNode(new Widget(4, "Whatever3", 553));
        System.out.println("Insertion done.");
        
        myTree.PrintTree();
        myTree.InOrderTraversal();
    }
    
}
