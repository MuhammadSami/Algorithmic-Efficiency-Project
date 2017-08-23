import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class LinkedListMultiset<T> extends Multiset<T>
{

    protected Node head;

    protected Node tail;

    protected int size;
	
    public LinkedListMultiset() {
		// Implement me!
        head = null; 
        tail = null;
        size = 0;
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {
	
		Node newNode = new Node(item);
        // If head is empty, then list is empty and head and tail references need to be initialised.
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        // otherwise, add node to the head of list.
        else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
        
        size+=1;
 
	} // end of add()
	
	public int search(T item) {
        Node currNode = head;
        int count=0;
        for (int i = 0; i < size; i++) {
        		if(currNode.getValue().equals(item)){
        			count++;
        		}
        	currNode = currNode.getNext();
        }
        return count;
	} // end of add()
	
	
	public void removeOne(T item) {
        Node currNode = head;

    
        // check if value is head node
        if (currNode.getValue().equals(item)) {
            // check if length of 1
            if (size == 1) 
            {
                head = tail= null;
            }
            else 
            {
                head = currNode.getNext();
                head.setPrev(null);
                currNode = null;
            }
            
            size--;
       
        }
        // search for value in rest of list
        else {
            currNode = currNode.getNext();

            while (currNode != null) {
                if (currNode.getValue().equals(item)) {
                    Node prevNode = currNode.getPrev();
                    prevNode.setNext(currNode.getNext());
                    // check if tail
                    if (currNode.getNext() != null) {
                    	currNode.getNext().setPrev(prevNode);
                    }
                    else {
                    	tail = prevNode;
                    }
                    currNode = null;
                    size--;
                    break;
                }
 
                currNode = currNode.getNext();
            }	
        }
	} // end of removeOne()

	public ArrayList<String> getNodes(T item){
        Node currNode = head;
        ArrayList<String> elements = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
        		if(currNode.getValue().equals(item)){
        			String nodeValue = ""+currNode.getValue();
        			elements.add(nodeValue);
        		}
        	currNode = currNode.getNext();
        }

       return elements;
	}
	
	public void removeAll(T item) {
        Node currNode = head;

    	ArrayList<String> a = getNodes(item);
    	for(int i=0; i<a.size(); i++) {

        	if(currNode.getValue().equals(item))
        	{
        		removeOne(item);
        	}
        	
        	currNode = currNode.getNext();
        }
        
       
        
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
        Node currNode = head;
        
        List<String> list = new ArrayList<String>();
        StringBuffer str = new StringBuffer();

        while (currNode != null) {
            list.add(currNode.getValue() +"");
            currNode = currNode.getNext();
        }
        list
        .stream()
        .collect(Collectors.groupingBy(s -> s))
        .forEach((k, v) -> System.out.println(k+printDelim+v.size()));
            
            
        
	} // end of print()
	
    public class Node
    {
        /** Stored value of node. */
        private T data;
        /** Reference to next node. */
        private Node next;
        /** Reference to previous node. */
        private Node prev;

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
        
    }
	
} // end of class LinkedListMultiset