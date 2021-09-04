import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class MyListIteratorTest {

    @Test
    public void testRemove() {

        MYList<Integer> myList = new MYList<>(1, 5, 8, 22, 85);
        Iterator<Integer> it = myList.iterator();
        it.next();
        it.next();
        it.remove();
        Assert.assertEquals(myList,
                    new MYList<>(1, 5, 8, 85));

        // добавление элемента
        //-----------------------------------------------
        myList.add(77);
        Assert.assertEquals(myList.size(), 5);
        it = myList.iterator();
        Assert.assertEquals(it.next(), (Integer) 77);
        //-----------------------------------------------


        // удаление элемента из головы списка
        it = myList.iterator();
        for(int i = 0; i < myList.size() - 1; ++i) {
            it.next();
        }
        it.remove();
        Assert.assertEquals("Equals of length", myList.size(), 4);
        Assert.assertEquals(it.next(), Integer.valueOf(1));
    }

    @Test
    public void testNext() {

        Integer[] myArr = {1, 8, 9, 10, 78};

        MYList<Integer> myList = new MYList<>(myArr);
        int len = myList.size();
        Iterator<Integer> it = myList.iterator();

        for(int i = 0; i < len; ++i) {
            Assert.assertEquals(myArr[len - i - 1], it.next());
        }
    }

    @Test
    public void testHasNext() {
        MYList<Integer> myList = new MYList<>(8, 55, 4, 9, 10);
        Iterator<Integer> it = myList.iterator();

        Assert.assertTrue(it.hasNext());
        for(int i = 0; i < myList.size(); ++i) {
          Assert.assertTrue(it.hasNext());
          it.next();
        }
        Assert.assertFalse(it.hasNext());
    }
}
