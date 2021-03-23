package eg.edu.alexu.csd.filestructure.redblacktree;
import org.junit.Assert;

public class node  {
	public static void main(String[] args) {
		RedBlackTree<Integer, Integer> tree= new RedBlackTree<Integer, Integer>();
		 tree.insert(30,50); 
	        tree.insert(20,30); 
	        tree.insert(10,20); 
	        tree.insert(15,40);
	        tree.insert(70,70);
	        tree.insert(60,60);
	        tree.insert(80,80);
		tree.insert(60,60);
		tree.insert(80,80);
	        System.out.println("******************************************************");
	        System.out.println("Root : "+tree.getRoot().getKey());
			System.out.println("left : "+tree.getRoot().getLeftChild().getKey());
			System.out.println("Right : "+tree.getRoot().getRightChild().getKey());

	        System.out.println("******************************************************");
		tree.inorderRec((myNode) tree.getRoot());
	}
}
