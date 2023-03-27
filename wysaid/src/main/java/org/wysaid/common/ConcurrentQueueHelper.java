package org.wysaid.common;

import android.util.Log;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentQueueHelper {
    protected ConcurrentLinkedQueue<Runnable> mQueue = new ConcurrentLinkedQueue<>();

    public boolean isEmpty() {
        return this.mQueue.isEmpty();
    }

    public void offer(Runnable runnable) {
        this.mQueue.offer(runnable);
    }

    public void consumeLast() {
        Runnable poll;
        do {
            try {
                poll = this.mQueue.poll();
                if (poll != null) {
                    poll.run();
                    continue;
                }
            } catch (Throwable th) {
                Log.e("libCGE_java", "ConcurrentQueueHelper: " + th.getLocalizedMessage());
                return;
            }
        } while (poll != null);
    }

    public void consume() {
        Runnable poll;
        do {
            try {
                poll = this.mQueue.poll();
                if (poll != null) {
                    poll.run();
                    continue;
                }
            } catch (Throwable th) {
                Log.e("libCGE_java", "ConcurrentQueueHelper: " + th.getLocalizedMessage());
                return;
            }
        } while (poll != null);
    }

}
