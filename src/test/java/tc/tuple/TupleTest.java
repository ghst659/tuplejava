package tc.tuple;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TupleTest {
    @Test
    public void testSize_Empty() {
        Tuple<Integer> t = new Tuple<>();
        Assert.assertEquals(0, t.size());
    }
    @Test
    public void testVarargs() {
        Tuple<String> t = new Tuple<>("P", "Q", "R");
        Assert.assertEquals(3, t.size());
        Assert.assertEquals("P", t.get(0));
        Assert.assertEquals("Q", t.get(1));
        Assert.assertEquals("R", t.get(2));
    }
    @Test
    public void testArray() {
        final Integer[] v = {10, 20, 30, 40};
        Tuple<Integer> t = new Tuple<>(v);
        Assert.assertEquals(v.length, t.size());
        int i = 0;
        for (int e: t) {
            Assert.assertEquals(v[i].intValue(), e);
            i++;
        }
    }
    @Test
    public void testList() {
        final List<Double> v = new LinkedList<>(Arrays.asList(3.14,2.71,1.41,5.25));
        Tuple<Double> t = new Tuple<>(v);
        Assert.assertEquals(v.size(), t.size());
        for (int i = 0; i < v.size(); ++i) {
            Assert.assertEquals(v.get(i), t.get(i));
        }
    }
    @Test
    public void testEquals() {
        Tuple<Integer> t0 = new Tuple<>();
        Tuple<Integer> t1 = new Tuple<>(19);
        Tuple<Integer> t2 = new Tuple<>(19);
        Tuple<Integer> t8 = new Tuple<>(19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19);
        Integer t3 = 19;
        Tuple<Integer> t4 = new Tuple<>();
        Tuple<Long> t5 = new Tuple<>(19L);
        List<Integer> inul = null;
        Tuple<Integer> t6 = new Tuple<>(inul);
        Tuple<Integer> t7 = new Tuple<>(inul);
        Assert.assertFalse(t0.equals(t1));
        Assert.assertFalse(t1.equals(t0));
        Assert.assertFalse(t1.equals(t3));
        Assert.assertFalse(t3.equals(t1));
        Assert.assertTrue(t1.equals(t2));
        Assert.assertTrue(t1.equals(t1));
        Assert.assertTrue(t0.equals(t4));
        Assert.assertEquals(t0.hashCode(), t4.hashCode());
        Assert.assertFalse(t0.equals(null));
        Assert.assertFalse(t1.equals(t5));
        Assert.assertFalse(t5.equals(t1));
        Assert.assertTrue(t0.equals(t6));
        Assert.assertEquals(t0.hashCode(), t6.hashCode());
        Assert.assertTrue(t6.equals(t0));
        Assert.assertTrue(t6.equals(t7));
        Assert.assertEquals(t6.hashCode(), t7.hashCode());
        Assert.assertNotEquals(t1.hashCode(), t8.hashCode());
    }
    @Test
    public void testComparison() {
        Tuple<Integer> t0 = new Tuple<>(5);
        Tuple<Integer> t1 = new Tuple<>(3);
        Tuple<Integer> t2 = new Tuple<>(7);
        Tuple<Integer> t3 = new Tuple<>(5, 5);
        Tuple<Integer> t4 = new Tuple<>(5, 1);
        Tuple<Integer> t5 = new Tuple<>(3, 5);
        Tuple<Integer> t6 = new Tuple<>(5, 1);
        Tuple<Integer> t7 = new Tuple<>();
        Assert.assertEquals(0, t0.compareTo(t0));

        Assert.assertEquals( 1, t0.compareTo(t1));
        Assert.assertEquals(-1, t1.compareTo(t0));

        Assert.assertEquals(-1, t0.compareTo(t2));
        Assert.assertEquals( 1, t2.compareTo(t0));

        Assert.assertEquals(0, t4.compareTo(t6));
        Assert.assertEquals(0, t6.compareTo(t4));

        Assert.assertEquals(-1, t0.compareTo(t3));
        Assert.assertEquals( 1, t3.compareTo(t0));

        Assert.assertEquals( 1, t3.compareTo(t4));
        Assert.assertEquals(-1, t4.compareTo(t3));

        Assert.assertEquals( 1, t4.compareTo(t5));
        Assert.assertEquals(-1, t5.compareTo(t4));

        Assert.assertEquals( 1, t3.compareTo(t5));
        Assert.assertEquals(-1, t5.compareTo(t3));

        Assert.assertEquals( 1, t2.compareTo(t3));
        Assert.assertEquals(-1, t3.compareTo(t2));

        Assert.assertEquals(-1, t7.compareTo(t1));
        Assert.assertEquals( 1, t1.compareTo(t7));
    }
    @Test
    public void testValueNature() {
        final String[] v = {"T", "F", "X"};
        Tuple<String> t0 = new Tuple<>(v);
        Tuple<String> t1 = new Tuple<>(v);
        Assert.assertFalse(t0 == t1);
        Assert.assertEquals(t0, t1);
        Set<Tuple<String>> bagTree = new TreeSet<>();
        bagTree.add(t0);
        Assert.assertTrue(bagTree.contains(t0));
        Assert.assertTrue(bagTree.contains(t1));
        Set<Tuple<String>> bagHash = new HashSet<>();
        bagHash.add(t0);
        Assert.assertTrue(bagHash.contains(t0));
        Assert.assertTrue(bagHash.contains(t1));
    }
    @Test
    public void testToString() {
        Tuple<Integer> t0 = new Tuple<>();
        Tuple<Integer> t1 = new Tuple<>(19);
        Tuple<Integer> t2 = new Tuple<>(23, 29);
        Tuple<String> t3 = new Tuple<>("foo", "bar");
        List<Integer> inul = null;
        Tuple<Integer> t4 = new Tuple<>(inul);
        Assert.assertEquals("()", t0.toString());
        Assert.assertEquals("(19)", t1.toString());
        Assert.assertEquals("(23,29)", t2.toString());
        Assert.assertEquals("(foo,bar)", t3.toString());
        Assert.assertEquals("()", t4.toString());
    }
    @Test
    public void testToList() {
        List<Tuple<Integer>> tc = new LinkedList<>();
        tc.add(new Tuple<>());
        tc.add(new Tuple<>(19));
        tc.add(new Tuple<>(23, 29));
        for (Tuple<Integer> t: tc) {
            List<Integer> a = t.toList();
            Assert.assertEquals(t.size(), a.size());
            for (int j = 0; j < t.size(); ++j) {
                Assert.assertEquals(t.get(j), a.get(j));
            }
        }
    }
}
