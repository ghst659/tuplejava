package tc.tuple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Tuple<E extends Comparable<E>>
    implements Iterable<E>, Comparable<Tuple<E>> {

    private List<E> a = null;

    /**
     * Primary constructor, including constructor with no args.
     * @param k varargs of Strings to form the tuple
     */
    @SafeVarargs
    public Tuple(E...k) {
        if (k != null) {
            this.a = new ArrayList<>(k.length);
            for (E x: k) {
                this.a.add(x);
            }
        }
    }
    /**
     * Constructor from list.
     * @param lst List of elements.
     */
    public Tuple(List<E> lst) {
        if (lst != null) {
            this.a = new ArrayList<>(lst);
        } else {
            this.a = new ArrayList<>(0);
        }
    }
    /**
     * Retrieve the number of elements in the tuple
     * @return number of elements, or zero if no data in tuple
     */
    public int size() {
        int result = 0;
        if (this.a != null) {
            result = this.a.size();
        }
        return result;
    }
    /**
     * Get element i of the tuple
     * @param i index of element to be retrieved.
     * @return element i of the tuple, or null if nonexistent
     */
    public E get(int i) {
        E result = null;
        if (this.a != null && i >= 0 && i < this.a.size()) {
            result = this.a.get(i);
        }
        return result;
    }

    /**
     * Compares this element to Tuple b.
     * @param b the Tuple against which this is compared.
     * @return -1, 0 or 1 if this is less than, equal to or greater than b.
     */
    public int compareTo(Tuple<E> b) {
        if (this == b) {
            return 0;
        }
        int aLen = a.size();
        int bLen = b.size();
        int common = Math.min(aLen, bLen);
        for (int i = 0; i < common; ++i) {
            int elementComparison = a.get(i).compareTo(b.get(i));
            if (elementComparison != 0) {
                return elementComparison;
            }
        }
        if (aLen > common) {
            return 1;
        }
        if (bLen > common) {
            return -1;
        }
        return 0;
    }
    /**
     * override of equals to do string equal comparison.
     */
    @Override
    public boolean equals(Object oy) {
        boolean equality = (this == oy); // object ref equality means we are done
        if (!equality && oy != null && this.getClass().isInstance(oy)) {
            @SuppressWarnings("unchecked")
            Tuple<E> y = (Tuple<E>) oy;
            equality = (this.a.size() == y.a.size()); // check same tuple length
            for (int i = 0; equality && i < this.a.size(); ++i) {
                equality = this.a.get(i).equals(y.a.get(i)); // loop until mismatch found
            }
        }
        return equality;
    }
    /**
     * override of hashCode to enable use as Map or Set key.
     */
    @Override
    public int hashCode() {
        int result = 19;
        if (this.a != null) {
            for (E elem : this.a) {
                result = ((result * 29) + elem.hashCode());
            }
        }
        return result;
    }
    /**
     * Produces (a,b,c) without quotes
     */
    @Override
    public String toString() {
        StringBuffer rbuf = new StringBuffer("(");
        boolean first = true;
        for (E elem : this.a) {
            if (! first) {
                rbuf.append(",");
            }
            rbuf.append(elem.toString());
            first = false;
        }
        rbuf.append(")");
        String result = rbuf.toString();
        return result;
    }
    /**
     * Convert tuple to a list
     * @return list of E, containing tuple elements.
     */
    public List<E> toList() {
        return new ArrayList<>(this.a);
    }
    /**
     * Implements iteration over tuple elements.
     */
    @Override
    public Iterator<E> iterator() {
        return this.a.iterator();
    }
}