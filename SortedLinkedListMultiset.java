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
	
	public void swap(Node node1, Node node2) {
	    if (node1 == null || node2 == null) {
	        throw new IllegalArgumentException("The nodes can't be null");
	    }

	    if (node1 == node2) {
	        return;
	    }

	    // make sure node1 is before node2
	    if (node1.getPrev() == node2) {
	        Node temp = node2;
	        node2 = node1;
	        node1 = temp;
	    }

	    Node node1Prev = node1.getPrev();
	    Node node1Next = node1.getNext();
	    Node node2Prev = node2.getPrev();
	    Node node2Next = node2.getNext();

	    node1.setNext(node2Next);
	    if (node2Next != null) {
	        node2Next.setPrev(node1);
	    }

	    node2.setPrev(node1Prev);
	    if (node1Prev != null) {
	        node1Prev.setNext(node2);
	    }

	    if (node1 == node2Prev) {
	        node1.setPrev(node2);
	        node2.setNext(node1);
	    } else {
	        node1.setPrev(node2Prev);
	        if (node2Prev != null) {
	            node2Prev.setNext(node1);
	        }

	        node2.setNext(node1Next);
	        if (node1Next != null) {
	            node1Next.setPrev(node2);
	        }
	    }

	    if (node1 == head) {
	        head = node2;
	    } else if (node2 == head) {
	        head = node1;
	    }
	}
	public void sort() {
	    Node currNode = head;
		if(currNode == null) {
	        throw new RuntimeException("List is empty");
	    }
	    
		while(currNode != null) {
		    for(Node prev=currNode; prev != null && prev.prev != null; prev = prev.prev) {
		        if(((String) prev.data).compareTo((String) prev.prev.data) <= 0) {
		            if (prev==currNode) {
		                currNode=prev.prev;
		            }
		            swap(prev,prev.prev);
		        }
		    }
		    currNode = currNode.next;
		}
	}
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
        sort();
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
    	System.out.println("Array is "+a);
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
} // end of class SortedLinkedListMultiset