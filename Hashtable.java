/*
A class that implements the data structure of hashtable. 
@Gigi Xiaowan Guo 
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import java.util.ArrayList;

//the node for hashtable, with key, value, and next;
class Node {

    String key;
    String value;
    Node next;
    Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
//hashtable class
public class Hashtable {
    private int ts;//table size;
    private int size; //size of record.
    private Node[] table;
    public Hashtable() 
    {
        size = 0;
        ts = 300000;
        table = new Node[ts];
        for (int i = 0; i < ts; i++)
            table[i] = null;
    }
    public int getSize() {
        return size;
    }
    public void makeEmpty(){//make every record be null
        for (int i = 0; i < ts; i++)
            table[i] = null;
    }
    public String get(String key) {//get the value from the key
        int index = pos( key ) % ts;
        if (table[index] == null)
            return null;//not in use
        else {
            Node node = table[index];
            while (node != null && !node.key.equals(key))
                node = node.next;//find the target
            if (node == null)
                return null;//not found
            else
                return node.value;
        }
    }
    public boolean containsKey(String k){
        //check if the key is in the hastable
        int index = pos(k) % ts;
        if(table[index] == null)return false;
        return true;
    }
    public void put(String key, String value) {//add a record into hashtable
        int index = (pos( key ) % ts);
        if (table[index] == null)
            table[index] = new Node(key, value);
        else {//in use
            Node node = table[index];
            while (node.next != null && !node.key.equals(key))
                node = node.next;
            if (node.key.equals(key))
                node.value = value;//change value
            else
                node.next = new Node(key, value);//record
        }
        size++;
    }
     public String remove(String key) {//remove a key from hashtable
        int index = (pos( key ) % ts);
        if (table[index] != null) {
            Node preNode = null;
            Node node = table[index];
            while (node.next != null && !node.key.equals(key)) {
                preNode = node;
                node = node.next;
            }
            if (node.key.equals(key)) {
                String ans = node.value;
                if (preNode == null)
                    table[index] = node.next;
                else
                    preNode.next = node.next;
                size--;
                return ans;
            }
        }
        return null;
    }
     private int pos(String x ) {//return the position of giving k. 
        int hashVal = x.hashCode( );
        hashVal %= ts;
        if (hashVal < 0)
            hashVal += ts;
        return hashVal;
    }
}