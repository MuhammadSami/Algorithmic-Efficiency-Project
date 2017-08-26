import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class LinkedListMultiset<T> extends Multiset<T>
{

	protected Node head;

	protected Node tail;

	protected int size;
	protected int counter=0;

	public LinkedListMultiset() {
		// Implement me!
		head = null; 
		tail = null;
		size = 0;
	} // end of LinkedListMultiset()


	public void add(T item) {

		Node currNode = head;
		Node newNode = new Node(item, counter); 

		// If head is empty, then list is empty and head and tail references need to be initialised.
		if (currNode == null) {
			head = newNode;
			tail = newNode;
			newNode.counter++;
			size+=1;
		
		}
		else {
			if(currNode!=null) {
				
				for(int i = 0; i<size; i++){
					if (newNode.getValue().equals(currNode.getValue())){
						currNode.counter++;
						return;
					}
					if(currNode.getNext()==null) break;
					currNode = currNode.getNext();
				}
				newNode.setPrev(null);
				newNode.setNext(head);
				newNode.counter++;
				currNode.setPrev(newNode.next);
				head = newNode;
				size+=1;
			}
		}
	}// end of add()

	public int search(T item) {
		Node currNode = head;
		int counter=0;
		for (int i = 0; i < size; i++) {
			if(currNode.getValue().equals(item)){
				counter = currNode.counter;
			}
			currNode = currNode.getNext();
		}
		return counter;
		
	} // end of add()



	public void removeOne(T item) {
		Node currNode = head;

		// check if value is head node
		if (currNode.getValue().equals(item)) {
			// check if length of 1
			if (size == 1) 
			{
				if(currNode.counter == 1){
					head = tail = null;
				}else{
					currNode.counter--;
					return;
				}
			}
			else 
			{
				if(currNode.counter == 1){
					head = currNode.getNext();
					head.setPrev(null);
					currNode = null;
					
				}else{
					currNode.counter--;
					return;
				}
			}
			size--;
		}
		// search for value in rest of list
		else {
			currNode = currNode.getNext();

			while (currNode != null) {
				if (currNode.getValue().equals(item)) {
					if(currNode.counter == 1){
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
					}else if(currNode.counter >1){
						currNode.counter--;
						break;
					}
				currNode = currNode.getNext();
				}	
			}
		}
	}// end of removeOne()

	public Node removeNode(Node current)
	{
		if(current.getNext() ==  null)
		{
			Node previous = current.getPrev();
			previous.setNext(null);
			current.setPrev(null);
			tail = previous;
		}
		else if (current.getPrev() == null)
		{
			Node next = current.getNext();
			next.setPrev(null);
			current.setNext(null);
		}
		else
		{
			Node next = current.getNext();
			Node previous = current.getPrev();
			previous.setNext(next);
			next.setPrev(previous);
			current.setPrev(null);
			current.setNext(null);
		}
		size = size - 1;
		return current;
	}
	public ArrayList<String> getNodes(T item){
		Node currNode = head;
		ArrayList<String> elements = new ArrayList<String>();
		for (int i = 0; i <size; i++) {
			if(currNode.getValue().equals(item)){
				String nodeValue = ""+currNode.getValue();
				elements.add(nodeValue);
			}
			currNode = currNode.getNext();
		}

		return elements;
	}

	public void removeAll(T item) {

		ArrayList<String> a = getNodes(item);
		System.out.println(""+a);
		for(String element: a){
			removeOne((T) element);
		}

	} // end of removeAll()


	public void print(PrintStream out) {
		Node currNode = head;
		StringBuffer str = new StringBuffer();
		while (currNode != null) {
			str.append(currNode.getValue() + " | "+currNode.getCounter()+"\n");
			currNode = currNode.getNext();
		}

		System.out.println(""+str.toString());
	} // end of print()

	public class Node
	{
		/** Stored value of node. */
		private T data;
		private int counter;
		/** Reference to next node. */
		private Node next;
		/** Reference to previous node. */
		private Node prev;

		public Node(T value, int count) {
			counter = count;
			data = value;
			next = null;
			prev = null;
		}
		public int getCounter(){
			return counter;
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