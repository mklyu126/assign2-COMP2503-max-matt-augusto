package mru;

public class SLL<T extends Comparable<T>> {
    private Node<T> start;
    int length;

    public SLL() {
        start = null;
        length = 0;
    }

    public boolean isEmpty() {
        return (start == null);
    }

    public void addToEnd(T data) {
        Node<T> nodeToAdd = new Node<>(data);
        if (start != null) {
            Node<T> curr = start;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(nodeToAdd);
        } else
            start = nodeToAdd;
        length++;
    }

    public void addToStart(T data) {
        Node<T> nodeToAdd = new Node<>(data);
        if (isEmpty())
            start = nodeToAdd;
        else {
            nodeToAdd.setNext(start);
            start = nodeToAdd;
        }
        length++;
    }

    public void addAt(int index, T data) {
        if (length == 0 || index <= 0) {
            addToStart(data);
        } else if (length <= index) {
            addToEnd(data);
        } else {
            Node<T> nodeToAdd = new Node<>(data);
            Node<T> curr = start;
            for (int count = 0; count < index - 1; count++) {
                curr = curr.getNext();
            }
            nodeToAdd.setNext(curr.getNext());
            curr.setNext(nodeToAdd);
            length++;
        }
    }

    public void addInOrder(T data) {
        Node<T> nodeToAdd = new Node<>(data);
        nodeToAdd.setData(data);

        if (isEmpty() || data.compareTo(start.getData()) <= 0) { // if list is empty or passed in data is less than or
                                                                 // equal to the start node data

            nodeToAdd.setNext(start);
            start = nodeToAdd;
        } else {
            Node<T> curr = start;
            while (curr.getNext() != null && data.compareTo(curr.getNext().getData()) > 0) {
                curr = curr.getNext();
            }
            nodeToAdd.setNext(curr.getNext());
            curr.setNext(nodeToAdd);
        }
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
}