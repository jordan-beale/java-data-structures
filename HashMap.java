package uk.ac.warwick.cs126.structures;

import uk.ac.warwick.cs126.models.Place;
import uk.ac.warwick.cs126.models.Favourite;

import uk.ac.warwick.cs126.structures.ListElement;
import uk.ac.warwick.cs126.structures.KeyValuePairLinkedList;

@SuppressWarnings("unchecked") 
public class HashMap<K extends Comparable<K>,V>{

    protected KeyValuePairLinkedList[] table;
    
    public HashMap() {
        this(1901);
    }
    
    public HashMap(int size) {
        table = new KeyValuePairLinkedList[size];
        initTable();
    }

    public V find(K key) {
        long hash_code = hash(key);
        int location = (int) (hash_code % table.length);
        ListElement<KeyValuePair<K,V>> temp = table[location].getHead();

        while(temp != null) {
            if (temp.getValue().getKey().equals(key)) {
                return temp.getValue().getValue();
            }else{
                temp = temp.getNext();
            }
        }
        return (V) "";
    }

    public Place findPlace(K key) {
        long hash_code = hash(key);
        int location = (int) (hash_code % table.length);
        ListElement<KeyValuePair<K,V>> temp = table[location].getHead();

        while(temp != null) {
            if (temp.getValue().getKey().equals(key)) {
                return (Place) temp.getValue().getValue();
            }else{
                temp = temp.getNext();
            }
        }
        return new Place("", "", 0.0f, 0.0f);
    }

    public Favourite findFavourite(K key) {
        long hash_code = hash(key);
        int location = (int) (hash_code % table.length);
        ListElement<KeyValuePair<K,V>> temp = table[location].getHead();

        while(temp != null) {
            if (temp.getValue().getKey().equals(key)) {
                return (Favourite) temp.getValue().getValue();
            }else{
                temp = temp.getNext();
            }
        }
        return null;
    }

    public MyArrayList<Favourite> findFavourite2(K key) {
        long hash_code = hash(key);
        int location = (int) (hash_code % table.length);
        ListElement<KeyValuePair<K,V>> temp = table[location].getHead();

        while(temp != null) {
            if (temp.getValue().getKey().equals(key)) {
                return (MyArrayList<Favourite>) temp.getValue().getValue();
            }else{
                temp = temp.getNext();
            }
        }
        return null;
    }
    
    protected void initTable() {
        for(int i = 0; i < table.length; i++) {
            table[i] = new KeyValuePairLinkedList<>();
        }
    }
    
    protected long hash(K key) {
        long code = key.hashCode();
        if (code < 0){
            String temp = Long.toString(Math.abs(code));
            temp += "0";
            code = Long.parseLong(temp);
        }
        return code;
    }

    public void add(K key, V value) {
        long hash_code = hash(key);
        int location = (int) (hash_code % table.length);
        
        table[location].add(key,value);
    }

    public V get(K key) {
        long hash_code = hash(key);
        int location = (int) (hash_code % table.length);
        
        ListElement<KeyValuePair> ptr = table[location].head;
        
        return (V)table[location].get(key).getValue();
    }

    public void set(K key, V val){
        long hash_code = hash(key);
        int location = (int) (hash_code % table.length);
        ListElement<KeyValuePair<K,V>> temp = table[location].getHead();

        while(temp != null) {
            if (temp.getValue().getKey().equals(key)) {
                temp.getValue().setValue(val);
                break;
            }else{
                temp = temp.getNext();
            }
        }
    }
}