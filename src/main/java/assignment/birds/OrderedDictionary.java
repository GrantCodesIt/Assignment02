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
     * //@throws birds.DictionaryException
     */
    @Override
    public void insert(BirdRecord r) throws DictionaryException {
        // Write this method
        Node current = root;
        //Node record = new Node(r);
        int comparison;
        if (root.isEmpty()) {
            root = new Node(r);
        }
        else {
            while (true) {
                //DataKey k = current.getData().getDataKey();
                // Need to figure out why k is becoming NULL causing an error in the compareTo function
                comparison = current.getData().getDataKey().compareTo(r.getDataKey());
                if (comparison == 0) {
                    throw new DictionaryException("Already Exists");
                }
                if (comparison == 1) { // goes to left side if x < current
                    // look at others to see how to insert
                    if (current.hasLeftChild()) {
                        current = current.getLeftChild();
                    } else {
                        current.setLeftChild(new Node(r));
                        break;
                    }
                } else if (comparison == -1) { // goes to right side if x > current
                    if (current.hasRightChild()) {
                        current = current.getRightChild();
                    } else {
                        current.setRightChild(new Node(r));
                        break;
                    }
                }
            }
        }
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
        // set predecssor.next == null
        // Grab predecessor to k
        // If has one, check if it has a left child, which it removes. And if not we remove right child
        Node node = root;
        node.equals(predecessor(k));
        if (node.hasLeftChild() == true) {
            node.setLeftChild(null);
        } else if (node.hasRightChild() == true) {
            node.setRightChild(null);
        }

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
    public BirdRecord successor(DataKey k) throws DictionaryException {
        // Write this method
        // find(key).next and return it
        // iterate through previous and find key
        // check next if has left or right child and return it
        Node current = root;
        int comparison;
        if (root.isEmpty()) {
            throw new DictionaryException("There is no record matches the given key");
        }

        while (true) {
            comparison = current.getData().getDataKey().compareTo(k);
            if (comparison == 0) { // key found
                // Check if has left sucessor
                if (current.hasLeftChild() == true) {
                    current = current.getLeftChild();
                    return current.getData();
                }
                // If not check if has right sucessor
                else if (current.hasRightChild() == true) {
                    return current.getData();
                }
                // If no sucessor throw error
                else
                    throw new DictionaryException("There is no sucessor to this key");
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
     * Returns the predecessor of k (the record from the ordered dictionary with
     * largest key smaller than k; it returns null if the given key has no
     * predecessor. The given key DOES NOT need to be in the dictionary.
     *
     * @param k
     * @return
     * @throws birds.DictionaryException
     */
    @Override
    public BirdRecord predecessor(DataKey k) throws DictionaryException {
        // Write this method
        // use iterator to find key location data, and use the next iterator to stop one short and return that
        //
        Node current = root;
        Node previous = current;
        int comparison = current.getData().getDataKey().compareTo(k);
        //Case 1: If empty
        if (root.isEmpty()) {
            throw new DictionaryException("There is no previous record matches the given key");
        }
        //Case 2: Only one node
        if (comparison == 0) {
            throw new DictionaryException("There is no previous record matches the given key");
        }
        //Case 3: Search Tree for key and return previous
        while (true) {

            comparison = current.getData().getDataKey().compareTo(k);
            if (comparison == 0) { // key found
                return previous.getData();
            }
            if (comparison == 1) {
                if (current.getLeftChild() == null) {
                    // Key not found
                    throw new DictionaryException("There is no previous record matches the given key");
                }
                previous = current;
                current = current.getLeftChild();
            } else if (comparison == -1) {
                if (current.getRightChild() == null) {
                    // Key not found
                    throw new DictionaryException("There is no previous record matches the given key");
                }
                previous = current;
                current = current.getRightChild();
            }
        }

    }

    /**
     * Returns the record with smallest key in the ordered dictionary. Returns
     * null if the dictionary is empty.
     *
     * @return
     */
    @Override
    public BirdRecord smallest() throws DictionaryException {
        // Write this method
        // Iterate through all records and keep smallest datakey k
        // Grab and return root node
        Node current = root;
        if (root.isEmpty() == true) {
            throw new DictionaryException("It is empty");
        }
        while(true) {
            if(current.hasLeftChild() == true) {
                current = current.getLeftChild();
            }
            else
                break;
        }
        return current.getData();
        // Need to REWRITE and iterate through the left most of the tree

        // change this statement
    }

    /*
     * Returns the record with largest key in the ordered dictionary. Returns
     * null if the dictionary is empty.
     */
    @Override
    public BirdRecord largest() throws DictionaryException {
        Node current = root;
        int comparison;
        if (root.isEmpty() == true) {
            throw new DictionaryException("There is no largest record with the given key");
        }

        while (true) {
            // Goes to right side
            if (current.hasRightChild() == true) {
                current = current.getRightChild();
            }
            else
                break;
            // If there is no more children than it is at the right most side
        }
        return current.getData();
    }
        /* Returns true if the dictionary is empty, and true otherwise. */
        @Override
        public boolean isEmpty () {
            return root.isEmpty();
        }
    }
