import com.rmv.oop.task5.model.LockFreeSkipList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Slf4j
class LockFreeSkipListTest {

    private LockFreeSkipList<Integer> list;

    @Test
    void insertAndContains() {
        list = new LockFreeSkipList<>();
        insert10Threads();
        for (int i = 0; i < 5000; i++) {
            assertTrue(list.contains(i));
        }
        for (int i = 5000; i < 6000; i++) {
            assertFalse(list.contains(i));
        }
    }

    @Test
    void remove() {
        list = new LockFreeSkipList<>();
        insert10Threads();
        remove10Threads();
        for (int i = 0; i < 5000; i++) {
            assertFalse(list.contains(i));
        }
        for (int i = 5000; i < 6000; i++) {
            assertFalse(list.remove(i));
        }
    }

    @Test
    void insertAfterRemove() {
        list = new LockFreeSkipList<>();
        insert10Threads();
        remove10Threads();
        insert10ThreadsOtherValues();
        for (int i = 0; i < 5000; i++) {
            assertFalse(list.contains(i));
        }
        for (int i = 5000; i < 10000; i++) {
            assertTrue(list.contains(i));
        }
    }

    private void insert10Threads() {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            int leftBound = 500 * j;
            int rightBound = 500 * (j + 1);
            threads[j] = new Thread(() -> {
                for (int i = leftBound; i < rightBound; i++) {
                    assertTrue(list.add(i));
                }
            });
            threads[j].start();
        }
        try {
            for (int i = 0; i < 10; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
    }

    private void insert10ThreadsOtherValues() {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            int leftBound = 500 * j + 5000;
            int rightBound = 500 * (j + 1) + 5000;
            threads[j] = new Thread(() -> {
                for (int i = leftBound; i < rightBound; i++) {
                    assertTrue(list.add(i));
                }
            });
            threads[j].start();
        }
        try {
            for (int i = 0; i < 10; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
    }

    private void remove10Threads() {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            int leftBound = 500 * j;
            int rightBound = 500 * (j + 1);
            threads[j] = new Thread(() -> {
                for (int i = leftBound; i < rightBound; i++) {
                    assertTrue(list.remove(i));
                }
            });
            threads[j].start();
        }
        try {
            for (int i = 0; i < 10; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
    }


}
