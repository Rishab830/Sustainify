package hashTable;
import java.io.Serial;
import java.io.Serializable;

public class LinkedList implements Serializable{
    @Serial
    private static final long serialVersionUID = 497057950013689384L;
    Node head = null;

    void insert(String username, String password) {
        if(head==null) {
            head = new Node(username,password);
            return;
        }
        Node current = head;
        while(current.next!=null) {
            current = current.next;
        }
        current.next = new Node(username,password);
    }

    void insert(Node user){
        if(head==null){
            head = user;
            return;
        }
        Node current = head;
        while(current.next!=null){
            current = current.next;
        }
        current.next = user;
    }

    void delete(String username) {
        if(head==null) {
            return;
        }
        if(head.username.equals(username)) {
            head=null;
            return;
        }
        Node current = head;
        while(!current.next.username.equals(username)) {
            current = current.next;
        }
        current.next = current.next.next;
    }

    protected String getPassword(String username) {
        Node current = head;
        if(head.username.equals(username)) {
            return head.password;
        }
        while(!current.next.username.equals(username)) {
            current = current.next;
        }
        return current.next.password;
    }

    boolean exists(String username) {
        Node current = head;
        while(current!=null&&!current.username.equals(username)) {
            current = current.next;
        }
        return current != null;
    }

    Node getUser(String username) {
        Node current = head;
        while(current!=null&&!current.username.equals(username)) {
            current = current.next;
        }
        return current;
    }
}
