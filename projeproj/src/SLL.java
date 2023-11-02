import java.util.Comparator;

public class SLL<T extends Comparable<T>> {
    private Node<T> start, tail;
    int length;
    Comparator<T> comp;
    

    public SLL() {
        start = null;
        tail = null;
        length = 0;
        comp = null;
        
    }
    
    public SLL(Comparator<T> comp) {
    	this();
    	this.comp = comp;
    }

    public boolean isEmpty() {
        return (start == null);
    }

    public void addToEnd(T data) {
        addToEnd(new Node<T>(data));
    }

    public void addToStart(T data) {
        addToStart(new Node<T> (data));
    }

    public void addAt(int index, T data) {
    	addAt(index, new Node<T> (data));
    }

    public void addInOrder(T data) {
    	addInOrder(new Node<T> (data));
    }
    
    public Node<T> get(int index) {
        Node<T> curr = start;

        if (index < length && index >= 0) {
            curr = start;
            for (int count = 0; count < index; count++) {
                curr = curr.getNext();
            }
        }
        return curr;

    }

    public boolean contains(T data) {
        Node<T> curr = start;
        while (curr != null) {
            if (curr.getData().equals(data)) {
                return true; // if element is found in list
            }
            curr = curr.getNext();
        }
        return false; // if element is not found in list
    }

    public int size() {
        Node<T> curr = start;
        int length = 0;

        while (curr != null) {
            length++;
            curr = curr.getNext();
        }

        return length;
    }

    public void print() {
        Node<T> curr = start;

        System.out.println("Start->");

        for (int count = 0; count < length; count++) {
            System.out.println("[" + curr.getData() + "]->");
            curr = curr.getNext();
        }

        if (curr == null) {
            System.out.println("null");
        }

        else {
            System.out.print("[" + curr.getData() + "]");
        }
    }
    
    private void addToEnd(Node<T> n) {
    	if(tail == null) {
    		start = n;
    		tail = n;
    	} else {
    		tail.setNext(n);
    		tail = n;
    	}
    	length ++;
    }
    
    private void addToStart(Node<T> n) {
    	if(start == null) {
    		start = n;
    		tail = n;
    	}else {
    		n.setNext(start);
    		start = n;
    	}
    	length ++;
    }
    
    private void addAt(int index, Node<T> n) {
    	if (length == 0 || index <= 0) {
            addToStart(n);
        } else if (length <= index) {
            addToEnd(n);
        } else {
            Node<T> curr = start;
            for (int count = 0; count < index - 1; count++) {
                curr = curr.getNext();
            }
            n.setNext(curr.getNext());
            curr.setNext(n);
            length++;
        }
    }
    
    private void addInOrder(Node<T> n) {
        if (isEmpty()) {
            this.addToStart(n);
            return;
        }
        if (compare(n.getData(), start.getData()) <= 0) {
            // N is ordered before the head
            this.addToStart(n);
            return;
        }
        else if (compare(n.getData(), tail.getData())  >= 0){
            // N is ordered after the tail
            this.addToEnd(n);
        }
        else {// We need to walk the list
            Node<T> current = start;
            Node<T> nextNode = current.getNext();
                while (nextNode != null) {
                    if(compare(n.getData(), current.getData()) >= 0 && compare(n.getData(), nextNode.getData())<= 0) {
                        current.setNext(n);
                        n.setNext(nextNode);
                        return;
                    }
                    current = nextNode;
                    nextNode = nextNode.getNext();
                    length++;
            }
        } 
        
    }
    /*
     * code from instructional assistant Jordan Pratt
     */
    public int compare(T t1, T t2) {
    	if(comp == null) {
    		return t1.compareTo(t2);
    	}
    	else {
    		return comp.compare(t1, t2);
    	}
    }
}