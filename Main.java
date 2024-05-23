import java.util.Scanner;

class Node {
    String dataString;
    int dataNumber;
    Node left, right;

    public Node(String dataString, int dataNumber) {
        this.dataString = dataString;
        this.dataNumber = dataNumber;
        left = right = null;
    }
}

class BinaryTree {
    Node root;

    public BinaryTree() {
        root = null;
    }

    // Method to insert a node into the binary tree
    public void insert(String dataString, int dataNumber) {
        root = insertRec(root, dataString, dataNumber);
    }

    // A recursive function to insert a new node with given data
    private Node insertRec(Node root, String dataString, int dataNumber) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(dataString, dataNumber);
            return root;
        }

        // Otherwise, recur down the tree
        if (dataNumber < root.dataNumber)
            root.left = insertRec(root.left, dataString, dataNumber);
        else if (dataNumber > root.dataNumber)
            root.right = insertRec(root.right, dataString, dataNumber);

        // return the (unchanged) node pointer
        return root;
    }

    // Method to perform in-order traversal
    public void inorder() {
        inorderRec(root);
    }

    // A utility function to do in-order traversal of BST
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.dataString + " " + root.dataNumber + ", ");
            inorderRec(root.right);
        }
    }

    // Method to perform pre-order traversal
    public void preorder() {
        preorderRec(root);
    }

    // A utility function to do pre-order traversal of BST
    private void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.dataString + " " + root.dataNumber + ", ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // Method to perform post-order traversal
    public void postorder() {
        postorderRec(root);
    }

    // A utility function to do post-order traversal of BST
    private void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.dataString + " " + root.dataNumber + ", ");
        }
    }

    // Method to delete a node with given dataString
    public void delete(String dataString) {
        root = deleteRec(root, dataString);
    }

    // A recursive function to delete a node with given dataString
    private Node deleteRec(Node root, String dataString) {
        // Base case: if the tree is empty
        if (root == null)
            return root;

        // Otherwise, recur down the tree
        if (dataString.compareTo(root.dataString) < 0)
            root.left = deleteRec(root.left, dataString);
        else if (dataString.compareTo(root.dataString) > 0)
            root.right = deleteRec(root.right, dataString);
        else {
            // Node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.dataNumber = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.dataString);
        }
        return root;
    }

    // A utility function to find the smallest node in a subtree
    private int minValue(Node root) {
        int minVal = root.dataNumber;
        while (root.left != null) {
            minVal = root.left.dataNumber;
            root = root.left;
        }
        return minVal;
    }

    // Method to count parents in the tree
    public int countParents() {
        return countParentsRec(root);
    }

    // A recursive function to count parents in the tree
    private int countParentsRec(Node root) {
        if (root == null || (root.left == null && root.right == null))
            return 0;
        return 1 + countParentsRec(root.left) + countParentsRec(root.right);
    }

    // Method to count children in the tree
    public int countChildren() {
        return countChildrenRec(root);
    }

    // A recursive function to count children in the tree
    private int countChildrenRec(Node root) {
        if (root == null)
            return 0;
        return (root.left != null ? 1 : 0) + (root.right != null ? 1 : 0) + countChildrenRec(root.left) + countChildrenRec(root.right);
    }
}

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nBinary Tree Operations:");
            System.out.println("1. Insert");
            System.out.println("2. Inorder Traversal");
            System.out.println("3. Preorder Traversal");
            System.out.println("4. Postorder Traversal");
            System.out.println("5. Delete by Name");
            System.out.println("6. Count Parents");
            System.out.println("7. Count Children");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter string data: ");
                    String dataString = scanner.next();
                    System.out.print("Enter integer data: ");
                    int dataNumber = scanner.nextInt();
                    tree.insert(dataString, dataNumber);
                    break;
                case 2:
                    System.out.println("Inorder traversal:");
                    tree.inorder();
                    break;
                case 3:
                    System.out.println("Preorder traversal:");
                    tree.preorder();
                    break;
                case 4:
                    System.out.println("Postorder traversal:");
                    tree.postorder();
                    break;
                case 5:
                    System.out.print("Enter string data to delete: ");
                    String deleteDataString = scanner.next();
                    tree.delete(deleteDataString);
                    System.out.println("Node with dataString \"" + deleteDataString + "\" deleted.");
                    break;
                case 6:
                    System.out.println("Number of parents: " + tree.countParents());
                    break;
                case 7:
                    System.out.println("Number of children: " + tree.countChildren());
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 8);

        scanner.close();
    }
}
