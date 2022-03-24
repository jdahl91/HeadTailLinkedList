/**
 * A class that implements the HeadTailListInterface.
 * Entries can only be added or removed from the beginning or end of the list.
 * Entries can be accessed in any position.
 * Entries begin at index 0.
 * Behind the scenes data structure is Linked Nodes.
 * 
 * @author Joakim Dahl
 */
public class LinkedHeadTailList<T> implements HeadTailListInterface<T> {

	private Node head, tail;
   
	@Override
	public void addFront(T newEntry) {
		Node newNode = new Node(newEntry);
		
		if(head==null) {
			tail = newNode;

		} else {
			newNode.next = head;
		}
		head = newNode;
	}
   
	@Override
	public void addBack(T newEntry) {
		if(head==null) {
			addFront(newEntry);

		} else if(head.next==null) {
			Node newNode = new Node(newEntry);
			
			head.next = newNode;
			tail = head.next;
		} else {
			Node newNode = new Node(newEntry);
			
			tail.next = newNode;
			tail = tail.next;
		}
	}

	@Override
	public T removeFront() {
		if(head==null) {
			return null;
			
		} else if(head.next==null) {
			T removedItem = head.data;
			clear();
			
			return removedItem;
			
		} else {
			T removedItem = head.data;
			head = head.next;
			
			return removedItem;
		}
	}
	
	@Override
	public T removeBack() {
		if(head==null) {
			return removeFront();
			
		} else if(head.next==null) {
			return removeFront();
			 
		} else {
			Node current = head, previous = null;
			
			while(current!=tail) {
				previous = current;
				current = current.next;
			}
			
			previous.next = null;
			tail = previous;
			
			return current.data;
		}
	}
	
	@Override
	public void clear() {
		head = null;
		tail = null;
		
	}
	
	@Override
	public T getEntry(int givenPosition) {
		Node current = head;
		
		if(givenPosition < 0) {
			return null;
		}
		
		for(int i=0; i<givenPosition; i++) {
			if(current.next!=null) {
				current = current.next;
			} else {
				return null;
			}
		}
		return current.data;
	}
	
	@Override
	public void display() {
		Node current = head;
		
		System.out.print("[");
		while(current!=null) {
			if(current.next==null) {
				System.out.print(current.data);
			} else {
				System.out.print(current.data + ", ");
			}
			current = current.next;
		}
		if(head!=null) {
			System.out.print("]\thead=" + head.data + " tail=" + tail.data + "\n");
		} else {
			System.out.print("]\n");
		}
	}
	
	@Override
	public int indexOf(T anEntry) {
		Node current = head;
		int indexCount = 0;
		
		while(current!=null) {
			if(current.data.equals(anEntry) ) {
				return indexCount;
			}
			current = current.next;
			indexCount++;
		}
		return -1;
	}
	
	@Override
	public int lastIndexOf(T anEntry) {
		Node current = head;
		int indexCount = 0;
		int lastPosition = -1;
		
		while(current!=null) {
			if(current.data.equals(anEntry) ) {
				lastPosition = indexCount;
			}
			current = current.next;
			indexCount++;
		}
		return lastPosition;
	}
	
	@Override
	public boolean contains(T anEntry) {
		if(indexOf(anEntry) >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int size() {
		Node current = head;
		int sizeCount = 0;
		
		while(current!=null) {
			sizeCount++;
			current = current.next;
		}
		return sizeCount;
	}
	
	@Override
	public boolean isEmpty() {
		if(head==null) {
			return true;
		} else {
			return false;
		}
	} 
	

	private class Node {
		private T data; 
		private Node next; 

		private Node(T dataPortion) {
			data = dataPortion;
			next = null;
		}

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		} 
		
	} // end Node class

} // end LinkedHeadTailList class
