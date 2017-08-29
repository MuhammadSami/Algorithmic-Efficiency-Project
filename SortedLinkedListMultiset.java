import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;



public class SortedLinkedListMultiset<T> extends Multiset<T>
{
    protected Node head;

    protected Node tail;

    protected int size;
    
	public SortedLinkedListMultiset() {
        head = null; 
        tail = null;
        size = 0;
	} // end of SortedLinkedListMultiset()
	
	public void insertNode(T item) {
	    	
	    	Node newNode = new Node(item);
    		
	    	if(head == null) {        	
	        	head = newNode;
	            tail = newNode;
	    	}
	    	else { // insert middle
	    		Node currNode = head;
	    		int index = 0;
	            while (currNode != null) {
	        		if(((String) currNode.data).compareTo((String) item) > 0){
	        			break;
	        		}
	        		index++;
	        		currNode = currNode.getNext();        
	            } 
	            
	            if(index == 0) { // insert head
		    		newNode.setNext(head);
		            head.setPrev(newNode);
		            head = newNode;
		    	}
		    	else if (index == size) { // insert tail
		    		newNode.setPrev(tail);
		            tail.setNext(newNode);
		            tail = newNode;
		    	}
		    	else { // insert middle
		    		Node prevNode = currNode.getPrev();
		    		
		    		newNode.setNext(currNode);
		    		newNode.setPrev(prevNode);
		    		
		    		prevNode.setNext(newNode);
		    		currNode.setPrev(newNode);	    		
		    	}
	    	}    
	    	
	    	newNode.add();
	    	size++;
	    }
	    
	 
	    public Node getNode(T item) {
	        Node currNode = head;
	        int count=0;
	        while (currNode != null) {
        		if(currNode.getValue().equals(item)){
        			break;
        		}
        		currNode = currNode.getNext();        
	        }
	        return currNode;
	    }
	    	    
	    public void deleteNode(Node node) {

	        if (size == 1) 
	        {
	            head = tail= null;
	        }
	        else if(node == head) { // delete head
	        	Node temp = head;
	            head = temp.getNext();
	            temp.setPrev(null);
	            temp = null;
	    	}
	    	else if(node == tail){ // delete tail
	        	Node temp = tail;
	        	tail = temp.getPrev();
	        	tail.setNext(null); 
	        	temp = null;
	    	}
	    	else { // delete middle
	            Node prevNode = node.getPrev();
	            Node nextNode = node.getNext();
	            prevNode.setNext(nextNode);
	            nextNode.setPrev(prevNode);
	            
	            node = null;
	    	}	 
	        size--;
	    }
		
	public void add(T item) {
		
		Node temp = getNode(item);
		
		 if(temp == null) {
			 insertNode(item);
		 }
        // otherwise, add node to the head of list.
        else {
        	temp.add();
        }
        
	} // end of add()
	
	public int search(T item) {
        Node currNode = head;
        int count=0;
        while (currNode != null) {
        		if(currNode.getValue().equals(item)){
        			count = currNode.getCount();
        			break;
        		}
        	currNode = currNode.getNext();
        }
        return count;
	} // end of add()
	
	
	public void removeOne(T item) {
        Node temp = getNode(item);

        if(temp == null) {
        	return;
        }
        if(temp.getCount() > 1) {
        	temp.minus();
        }
        else {
        	deleteNode(temp);
        }
	} // end of removeOne()


	
	public void removeAll(T item) {
        Node temp = getNode(item);

        if(temp == null) {
        	return;
        }
        else {
        	deleteNode(temp);
        }  
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
        Node currNode = head;
        

        while (currNode != null) {
        	System.out.println(currNode.getValue() + printDelim + currNode.getCount());
            currNode = currNode.getNext();
        }
            
            
        
	} // end of print()
	
    public class Node
    {
        /** Stored value of node. */
        private T data;
        /** Reference to next node. */
        private Node next;
        /** Reference to previous node. */
        private Node prev;

        private int count;
        
        public Node(T value) {
            data = value;
            next = null;
            prev = null;
        }
        public T getValue() {
            return data;
        }


        public Node getNext() {
            return next;
        }
        
        
        public Node getPrev() {
            return prev;
        }


        public void setValue(T value) {
           data = value;
        }


        public void setNext(Node Next) {
            next = Next;
        }
        
        public void setPrev(Node Prev) {
            prev = Prev;
        }
 
        public int getCount() {
           return count;
        }
        
        public void add() {
            count++;
        }
        
        public void minus() {
            count--;
        }
        
    }
} // end of class SortedLinkedListMultiset