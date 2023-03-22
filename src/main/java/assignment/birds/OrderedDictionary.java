package assignment.birds;

import java.util.Iterator;

public class OrderedDictionary implements OrderedDictionaryADT {

    Node root;

    OrderedDictionary() {
        root = new Node();
    }

    /**
     * Returns the Record object with key k, or it returns null if such a record
     * is not in the dictionary.
     *
     * @param k
     * @return
     * @throws assignment/birds/DictionaryException.java
     */
    @Override
    public BirdRecord find(DataKey k) throws DictionaryException {
        Node current = root;
        int comparison;
        if (root.isEmpty()) {         
            throw new DictionaryException("There is no record matches the given key");
        }

        while (true) {
            comparison = current.getData().getDataKey().compareTo(k);
            if (comparison == 0) { // key found
                return current.getData();
            }
            if (comparison == 1) {
                if (current.getLeftChild() == null) {
                    // Key not found
                    throw new DictionaryException("There is no record matches the given key");
                }
                current = current.getLeftChild();
            } else if (comparison == -1) {
                if (current.getRightChild() == null) {
                    // Key not found
                    throw new DictionaryException("There is no record matches the given key");
                }
                current = current.getRightChild();
            }
        }

    }

    /**
     * Inserts r into the ordered dictionary. It throws a DictionaryException if
     * a record with the same key as r is already in the dictionary.
     *
     * @param r
     * @throws birds.DictionaryException
     */
    @Override
    public void insert(BirdRecord r) throws DictionaryException {
        // Write this method
        DataKey key = new DataKey();
        key = r.getDataKey();
        if(find(key) == null) {
            r = new Node().getData();
        }
        //use node.setLeftChild()
        else
            throw new DictionaryException("Already Exists in the Dictionary");
    }

    /**
     * Removes the record with Key k from the dictionary. It throws a
     * DictionaryException if the record is not in the dictionary.
     *
     * @param k
     * @throws birds.DictionaryException
     */
    @Override
    public void remove(DataKey k) throws DictionaryException {
        int keyIndex = find(k);
        // set predecssor.next == null
        predecessor(k).next == null;
    }

    /**
     * Returns the successor of k (the record from the ordered dictionary with
     * smallest key larger than k); it returns null if the given key has no
     * successor. The given key DOES NOT need to be in the dictionary.
     *
     * @param k
     * @return
     * @throws birds.DictionaryException
     */
    @Override
    public BirdRecord successor(DataKey k) throws DictionaryException{
        // Write this method
        // find(key).next and return it
        // use iterator to find key, than use another iterator from there to go to the next one and return that

        if(find(k) != null) {

            //use k.data to find location
            Node current = root;
            int comparison;
            while (true) {
                comparison = current.getData().getDataKey().compareTo(k);
                if (comparison == 0) { // key found
                    return current.getLeftChild().getData();
                }
            }
        }
        else
            return null; // change this statement
    }

   
    /**
     * Returns the predecessor of k (the record from the ordered dictionary with
     * largest key smaller than k; it returns null if the given key has no
     * predecessor. The given key DOES NOT need to be in the dictionary.
     *
     * @param k
     * @return
     * @throws birds.DictionaryException
     */
    @Override
    public BirdRecord predecessor(DataKey k) throws DictionaryException{
        // Write this method
        // use iterator to find key location data, and use the next iterator to stop one short and return that
        //
        if(isEmpty() == true) {
            return null;
        }
        Node current = root;
        Node previous = current;
        while(true) {
            previous = current;
            current = current.get;
            if(current == record) {
                return previous.birdrecord;
            }
        }
        return previous.birdrecord; // change this statement
    }

    /**
     * Returns the record with smallest key in the ordered dictionary. Returns
     * null if the dictionary is empty.
     *
     * @return
     */
    @Override
    public BirdRecord smallest() throws DictionaryException{
        // Write this method
        // Iterate through all records and keep smallest datakey k
        // Grab and return root node

        if(root.isEmpty() == true) {
            return null;
        }
        // change this statement
    }

    /*
	 * Returns the record with largest key in the ordered dictionary. Returns
	 * null if the dictionary is empty.
     */
    @Override
    public BirdRecord largest() throws DictionaryException{
        // Write this method
        // Finds the right most child or
        // Iterate through all records and keeps largest datakey k
        return null; // change this statement
    }
      
    /* Returns true if the dictionary is empty, and true otherwise. */
    @Override
    public boolean isEmpty (){
        return root.isEmpty();
    }
}
