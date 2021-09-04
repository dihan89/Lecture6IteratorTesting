import java.util.*;

// Node
class NodeList<T>{
    private T value;
    private NodeList next;
    NodeList(T value){
        this.value = value;
        this.next = null;
    }
    NodeList<T> next(){
        return next;
    }
    T value(){
        return value;
    }
    void setNext(NodeList<T> node){
        next = node;
    }
}

class MYList<T> implements Iterable {
    private NodeList<T> head;
    private NodeList<T> tail;
    private int length;
    private NodeList<T> currentElem;
    private NodeList<T> prevElem;
    private int currentPosition;

    public void resetIterator(){
        currentElem = null;
        prevElem = null;
        currentPosition = -1;
    }


    public boolean equals(Object list) {
        if (list == null) {
            return false;
        }
        if (list.getClass() != this.getClass()) {
            return false;
        }
        if (list == this) {
            return true;
        }
        MYList<T> otherList = (MYList<T>)list;
        if (otherList.length != this.length) {
            return false;
        }

        NodeList currentNodeThis = this.head;
        NodeList currentNodeOther = otherList.head;

        for(int i = 0; i < length; ++i) {
            if (currentNodeThis.value() != currentNodeOther.value()) {
                return false;
            }
            currentNodeThis = currentNodeThis.next();
            currentNodeOther = currentNodeOther.next();

            //System.err.println(" i = " + i);
        }

        return true;
    }

    int size(){
        return length;
    }

    NodeList<T> getHead(){
        return head;
    }
    public void removeHead(){
        if (head != null){
            if(currentElem == head)
                currentElem = head.next();
            head = head.next();
            currentPosition--;
            length--;
        }
    }

    void add(T value){
        NodeList<T> currentNode = new NodeList(value);
        if (head == null){
            head = currentNode;
            currentElem = head;
            currentPosition = -1;
        }
        else{
            tail.setNext(currentNode);
        }
        tail = currentNode;
        length++;
    }
    public MyListIterator<T> iterator(){
        return new MyListIterator(this);
    }
    private void decrementLength() throws IndexOutOfBoundsException {
        if (length > 0)
            length--;
        else throw new IndexOutOfBoundsException("length < 0");
    }
    private void changeTail(NodeList<T> newTail){
        tail = newTail;
    }
    MYList(){
        length = 0;
        currentPosition = -1;
    }
    MYList(T...array) {
        length = 0;
        currentPosition = -1;

        for (int i = 0; i < array.length; i++) {
            this.add(array[i]);
        }
    }

    private class MyListIterator<T> implements Iterator{
        private int position;
        private ArrayList<NodeList<T>> elems;
        private MYList<T> myList;
        private int length;

        public boolean hasNext(){
            return (position > 0) ? true: false;
        }
        public T next(){
            return elems.get(--position).value();
        }
        public void remove() {
            if (position == 0){
                myList.removeHead();
            } else{
                elems.get(position - 1).setNext(elems.get(position).next());
                myList.decrementLength();
                if (position == length - 1)
                    myList.changeTail(elems.get(position - 1));
            }
            length --;
        }

        MyListIterator(MYList<T> myList){
            this.myList = myList;
            elems = new ArrayList<NodeList<T>>(myList.size());
            length = myList.size();
            position = length;
            NodeList<T> current = myList.getHead();
            for (int i = 0; i < myList.size(); ++i){
                elems.add(current);
                current = current.next();
            }
        }
    }
}



public class Lecture6IteratorTesting {

    public static void main(String args[]) {
        System.out.println("Hello! Lecture 6 iterator.");

        MYList<String> myList2 = new MYList(){{
            add("ZLO");
            add("NEUDACHA");
            add("ZLO");
            add("DOBRO");
            add("DOBRO");
            add("UDACHA");
            add("NEUDACHA");
            add("ZLO");
            add("KRASOTA");
            add("NEUDACHA");
            add("KRASOTA");
            add("ZLO");
            add("NEUDACHA");
            add("DOBRO");
        }};

        System.out.println("Reverse order");

        Iterator<String> iter = myList2.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        iter = myList2.iterator();
        while (iter.hasNext()) {
            String curStr = iter.next();
            if (curStr.equals("ZLO") || curStr.equals("NEUDACHA")) {
                iter.remove();
            }
        }
        /*myList2.add("POBEDA");
        iter = myList2.iterator();
        System.out.println(myList2.size());
        System.out.println("- after delete \"ZLO\" in reverse order" );
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }*/
    }
}
