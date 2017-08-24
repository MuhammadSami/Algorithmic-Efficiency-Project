import java.io.PrintStream;
import java.util.*;

public class BstMultiset<T> extends Multiset<T>
{

	public int size;
	
	protected Node mRoot;

	public BstMultiset() {
		// Implement me!
		mRoot = null;
		size = 0;		
	} // end of BstMultiset()

	public void put(){
		
	};
	
	
	public void add(T item) {
		// Implement me!
		mRoot = add(mRoot, item);
	} // end of add()

	protected Node add(Node root, T key) {
		// check if root is empty

				
		if (root == null) {
			root = new Node(key);
			root.count++;			
		}
		else if (((String)key).compareTo(((String)root.mKey)) < 0) {
			root.mLeftChild = add(root.mLeftChild, key);
		}
		else if (((String)key).compareTo(((String)root.mKey)) > 0) {
			root.mRightChild = add(root.mRightChild, key);
		}
		else {
			root.count++;
		}

		return root;
	} // end of insert()
	
	
	public int search(T item) {

		return search(mRoot, item);
		// default return, please override when you implement this method
		
	} // end of add()

		/**
     * Recursive method to search for key in tree.
     *
     * @param root Root node of tree to search 'key'.
     * @param key Key to search for.
     * @return True if key is found, otherwise false.
     */
	protected int search(Node root, T item) {
		
		// Implement me!
		if (root == null) {
			return 0;
		}

		if(((String)item).compareTo(((String)root.mKey)) == 0){
			return root.count;
		}		
		else if( search(root.mLeftChild, item) > 0){
			return search(root.mLeftChild, item);
		}
		else if( search(root.mRightChild, item) > 0){
			return search(root.mRightChild, item);
		}
		else return 0;
		
	}

	public Node findSmallest(Node root) {
		//System.out.print(((String)temp.mKey));
		if(root.mLeftChild != null)return findSmallest(root.mLeftChild);
		else return root;
	}
	
	public Node findLargest(Node root) {
		//System.out.print(((String)temp.mKey));
		if(root.mRightChild != null)return findLargest(root.mRightChild);
		else return root;
	}
	
	public void deleteNodeRight(Node root) {
		Node rmvNode; 
		Node tempNode;
		
		rmvNode = root.mRightChild;
		if(rmvNode.mLeftChild != null) {
			root.mRightChild = rmvNode.mLeftChild;
			tempNode = findLargest(root.mRightChild);
			tempNode.mRightChild = rmvNode.mRightChild;
		}
		else root.mRightChild = rmvNode.mRightChild;
		
		
		rmvNode = null;
		
	}
	
	public void deleteNodeLeft(Node root) {
		Node rmvNode; 
		Node tempNode;
		
		rmvNode = root.mLeftChild;
		if(rmvNode.mRightChild != null) {
			root.mLeftChild = rmvNode.mRightChild;		
			tempNode = findSmallest(root.mLeftChild);
			tempNode.mLeftChild = rmvNode.mLeftChild;
		}
		else root.mLeftChild = root.mLeftChild;
		
		rmvNode = null;
		
	}
	
	
	public void removeRight(Node root){
		
		if(root.mRightChild.count > 1){
			root.mRightChild.count--;
		}
	
		if(root.count == 1){			
			deleteNodeRight(root);
		}	
	}
	
	public void removeLeft(Node root){
		
		if(root.mLeftChild.count > 1){
			root.count--;
		}
	
		if(root.mRightChild.count == 1){
			deleteNodeLeft(root);
		}				
	}
	
	public void removeOne(T item) {
		// Implement me!
		removeOne(mRoot, item);
	} // end of removeOne()
	
	public void removeOne(Node root, T item) {
		
		
		if(root.mLeftChild != null) {
			if(((String)item).compareTo(((String)root.mLeftChild.mKey)) == 0){				
				removeLeft(root);			
			}				
			removeOne(root.mLeftChild, item);
		}
		
		if(root.mRightChild != null) {
			if(((String)item).compareTo(((String)root.mRightChild.mKey)) == 0){					
				removeRight(root);			
			}		
			removeOne(root.mRightChild, item);
		}		
			
		
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
		removeAll(mRoot, item);
	} // end of removeAll()
	
	public void removeAll(Node root, T item) {
		// Implement me!
		
		if(root.mLeftChild != null) {
			if(((String)item).compareTo(((String)root.mLeftChild.mKey)) == 0){				
				deleteNodeLeft(root);			
			}				
			removeAll(root.mLeftChild, item);
		}
		
		if(root.mRightChild != null) {
			if(((String)item).compareTo(((String)root.mRightChild.mKey)) == 0){					
				deleteNodeRight(root);			
			}		
			removeAll(root.mRightChild, item);
		}	
	} // end of removeAll()
	

	public void inorder() {
		inorder(mRoot);
		System.out.println("");
	} // end of inorder()


    /**
     * Inorder traversal of tree.
     *
     * @param root Root node of tree.
     */
	protected void inorder(Node root) {
		if (root == null) {
			return;
		}

		inorder(root.mLeftChild);
		// In general, might be better to use a function object here, but for this lab, we just print to stdout
		System.out.print(((String)root.mKey) + " ");
		inorder(root.mRightChild);
	} // end of inorder()
	
	public void printLeft(){
		
		printLeft(mRoot);
		System.out.println("");
	}
	
	public void printLeft(Node root){
		if (root == null) {
			return;
		}

		printLeft(root.mLeftChild);
		// In general, might be better to use a function object here, but for this lab, we just print to stdout
		System.out.print(((String)root.mKey) + " ");
		
	}
	
	public void print(PrintStream out) {
		// Implement me!
		inorder();
		//printLeft();
		//AsciiPrinter.printNode((AsciiPrinter::Node)mRoot);
	} // end of print()

	/**
 * Inner class implementation of a node.
 *
 * @author jefcha
 */
class Node
{
        /** For this lab, we only store ints, but in general we can store any object/type that is Comparable. */
        public T mKey;
        /** Reference to left child. */
        public Node mLeftChild;
        /** Reference to right child. */
        public Node mRightChild;
		
		public int count;
		
		


        /**
         * Constructor.
         *
         * @param key Key to store in node.
         */
        public Node(T key) {
                mKey = key;
                mLeftChild = null;
                mRightChild = null;
        }


        /**
         *
         * @return Key stored in node.
         */
        public T key() {
            return mKey;
        } // end of getKey()


        /**
         *
         * @return Reference to left child of node.
         */
        public Node leftChild() {
            return mLeftChild;
        } // end of leftChild()


        /**
         *
         * @return Reference to right child of node.
         */
        public Node rightChild() {
            return mRightChild;
        } // end of rightChild()
		
}	


    
		
} // end of class BstMultiset
