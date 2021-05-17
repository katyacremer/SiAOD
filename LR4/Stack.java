public class Stack<T>{
    private static class Node<U>{
        U item;
        Node<U> next;

        Node(){
            item = null;
            next = null;
        }

        Node(U item, Node<U> next){
            this.item = item;
            this.next = next;
        }

        boolean end(){
            return (item == null && next == null);
        }
    }

    private Node<T> top = new Node<T>();

    public void push(T item){
        top = new Node<T>(item, top);
    }

    public T pop(){
        T result = top.item;
        if(!top.end())
            top = top.next;
        return result;
    }

    public T peek(){
        T result = top.item;
        return result;
    }

    public boolean isEmpty(){
        return top.end();
    }
}