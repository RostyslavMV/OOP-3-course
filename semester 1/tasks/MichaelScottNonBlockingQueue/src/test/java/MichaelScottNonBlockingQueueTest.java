import com.rmv.oop.task6.MichaelScottNonBlockingQueue;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@ExtendWith(SpringExtension.class)
public class MichaelScottNonBlockingQueueTest {

    private MichaelScottNonBlockingQueue<Integer> queue;

    private boolean valuePresent[] = new boolean[5000];

    @Test
    void enqueueDequeueTest() {
        queue = new MichaelScottNonBlockingQueue<>();
        enqueue10Threads();
        dequeue10Threads();
        for (int i = 0; i < 5000; i++) {
            assertTrue(valuePresent[i]);
        }
        assertNull(queue.dequeue());
    }

    private void enqueue10Threads() {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            int leftBound = 500 * j;
            int rightBound = 500 * (j + 1);
            threads[j] = new Thread(() -> {
                for (int i = leftBound; i < rightBound; i++) {
                    queue.enqueue(i);
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

    private void dequeue10Threads() {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            int leftBound = 500 * j;
            int rightBound = 500 * (j + 1);
            threads[j] = new Thread(() -> {
                int current;
                for (int i = leftBound; i < rightBound; i++) {
                    current = queue.dequeue();
                    valuePresent[current] = true;
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
