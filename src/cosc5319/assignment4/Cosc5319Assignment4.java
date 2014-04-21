// Franklin Leung
package cosc5319.assignment4;

public class Cosc5319Assignment4 {

    public static void main(String[] args) {
        AVLTree myTree = new AVLTree();
        
        System.out.println("Inserting data...");
        myTree.InsertNode(10, "10", 10);
        myTree.InsertNode(15, "15", 15);
        myTree.InsertNode(13, "13", 13);
        myTree.InsertNode(12, "12", 12);
        myTree.InsertNode(11, "11", 11);
        myTree.InsertNode(15, "Fifteen", 16);
        myTree.InsertNode(2, "Two", 22);
        myTree.InsertNode(15, "Whatever1", 8745);
        myTree.InsertNode(3, "Whatever2", 5124);
        myTree.InsertNode(4, "Whatever3", 553);
        // Deleting the root doesn't seem to work...
        myTree.DeleteByPrice(15);
        myTree.DeleteByPrice(13);
        myTree.DeleteByPrice(5124);
        System.out.println("Insertion done.");
        
        if (myTree.BinarySearch(15))
            System.out.println("Number found.");
        else
            System.out.println("Number not found.");
        myTree.PrintTree();
        myTree.InOrderTraversal();
    }
    
}
