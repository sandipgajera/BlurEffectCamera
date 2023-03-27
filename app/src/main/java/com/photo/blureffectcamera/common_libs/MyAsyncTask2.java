package com.photo.blureffectcamera.common_libs;

import android.os.Handler;
import android.os.Message;
import android.os.Process;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MyAsyncTask2<Params, Progress, Result> {
	private static final int CORE_POOL_SIZE;
	private static final int CPU_COUNT;
	private static final int KEEP_ALIVE = 1;
	private static final String LOG_TAG = "AsyncTask";
	private static final int MAXIMUM_POOL_SIZE;
	private static final int MESSAGE_POST_PROGRESS = 2;
	private static final int MESSAGE_POST_RESULT = 1;
	public static final Executor SERIAL_EXECUTOR;
	public static final Executor THREAD_POOL_EXECUTOR;
	private static volatile Executor sDefaultExecutor;
	private static final InternalHandler sHandler;
	private static final BlockingQueue<Runnable> sPoolWorkQueue;
	private static final ThreadFactory sThreadFactory;
	private final AtomicBoolean mCancelled;
	private final FutureTask<Result> mFuture;
	private volatile Status mStatus;
	private final AtomicBoolean mTaskInvoked;
	private final WorkerRunnable<Params, Result> mWorker;
    public final static int FLAG_KEY_MEDIA_NEXT = 1 << 7;

	static class ThreadRunnableManager implements ThreadFactory {
		private final AtomicInteger mCount;

		ThreadRunnableManager() {
			this.mCount = new AtomicInteger(MyAsyncTask2.MESSAGE_POST_RESULT);
		}

		public Thread newThread(Runnable r) {
			return new Thread(r, "AsyncTask #" + this.mCount.getAndIncrement());
		}
	}

	class FutureTaskManager extends FutureTask<Result> {
		FutureTaskManager(Callable x0) {
			super(x0);
		}

		protected void done() {
			try {
				MyAsyncTask2.this.postResultIfNotInvoked(get());
			} catch (InterruptedException e) {
			} catch (ExecutionException e2) {
				throw new RuntimeException(
						"An error occured while executing doInBackground()",
						e2.getCause());
			} catch (CancellationException e3) {
				MyAsyncTask2.this.postResultIfNotInvoked(null);
			}
		}
	}

	static class StatusRunnableManager {
		static final int[] $SwitchMap$com$lyrebirdstudio$common_libs$MyAsyncTask2$Status;

		static {
			$SwitchMap$com$lyrebirdstudio$common_libs$MyAsyncTask2$Status = new int[Status
					.values().length];
			try {
				$SwitchMap$com$lyrebirdstudio$common_libs$MyAsyncTask2$Status[Status.RUNNING
						.ordinal()] = MyAsyncTask2.MESSAGE_POST_RESULT;
			} catch (NoSuchFieldError e) {
			}
			try {
				$SwitchMap$com$lyrebirdstudio$common_libs$MyAsyncTask2$Status[Status.FINISHED
						.ordinal()] = MyAsyncTask2.MESSAGE_POST_PROGRESS;
			} catch (NoSuchFieldError e2) {
			}
		}
	}

	private static class AsyncTaskResult<Data> {
		final Data[] mData;
		final MyAsyncTask2 mTask;

		AsyncTaskResult(MyAsyncTask2 task, Data... data) {
			this.mTask = task;
			this.mData = data;
		}
	}

	
	private static class InternalHandler extends Handler {
		public void handleMessage(final Message message) {
			final AsyncTaskResult asyncTaskResult = (AsyncTaskResult) message.obj;
			switch (message.what) {
			default: {
			}
			case 1: {
				asyncTaskResult.mTask.finish(asyncTaskResult.mData[0]);
			}
			case 2: {
				asyncTaskResult.mTask.onProgressUpdate(asyncTaskResult.mData);
			}
			}
		}
	}

	private static class SerialExecutor implements Executor {
		Runnable mActive;
		final ArrayDeque<Runnable> mTasks;

		
		class OfferManager implements Runnable {
			final Runnable val$r;

			OfferManager(Runnable runnable) {
				this.val$r = runnable;
			}

			public void run() {
				try {
					this.val$r.run();
				} finally {
					SerialExecutor.this.scheduleNext();
				}
			}
		}

		private SerialExecutor() {
			this.mTasks = new ArrayDeque();
		}

		public synchronized void execute(Runnable r) {
			this.mTasks.offer(new OfferManager(r));
			if (this.mActive == null) {
				scheduleNext();
			}
		}

		protected synchronized void scheduleNext() {
			Runnable runnable = (Runnable) this.mTasks.poll();
			this.mActive = runnable;
			if (runnable != null) {
				MyAsyncTask2.THREAD_POOL_EXECUTOR.execute(this.mActive);
			}
		}
	}

	public enum Status {
		PENDING, RUNNING, FINISHED
	}

	private static abstract class WorkerRunnable<Params, Result> implements
            Callable<Result> {
		Params[] mParams;

		private WorkerRunnable() {
		}
	}

	class RunnableWorker extends WorkerRunnable<Params, Result> {
		RunnableWorker() {
			super();
		}

		public Result call() throws Exception {
			MyAsyncTask2.this.mTaskInvoked.set(true);
			Process.setThreadPriority(10);
			return MyAsyncTask2.this.postResult(MyAsyncTask2.this
					.doInBackground(this.mParams));
		}
	}

	protected abstract Result doInBackground(Params... paramsArr);

	static {
		CPU_COUNT = Runtime.getRuntime().availableProcessors();
		CORE_POOL_SIZE = CPU_COUNT + MESSAGE_POST_RESULT;
		MAXIMUM_POOL_SIZE = (CPU_COUNT * MESSAGE_POST_PROGRESS)
				+ MESSAGE_POST_RESULT;
		sThreadFactory = new ThreadRunnableManager();
		sPoolWorkQueue = new LinkedBlockingQueue(FLAG_KEY_MEDIA_NEXT);
		THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE,
				MAXIMUM_POOL_SIZE, 1, TimeUnit.SECONDS, sPoolWorkQueue,
				sThreadFactory);
		SERIAL_EXECUTOR = new SerialExecutor();
		sHandler = new InternalHandler();
		sDefaultExecutor = SERIAL_EXECUTOR;
	}

	public static void init() {
		sHandler.getLooper();
	}

	public static void setDefaultExecutor(Executor exec) {
		sDefaultExecutor = exec;
	}

	public MyAsyncTask2() {
		this.mStatus = Status.PENDING;
		this.mCancelled = new AtomicBoolean();
		this.mTaskInvoked = new AtomicBoolean();
		this.mWorker = new RunnableWorker();
		this.mFuture = new FutureTaskManager(this.mWorker);
	}

	private void postResultIfNotInvoked(Result result) {
		if (!this.mTaskInvoked.get()) {
			postResult(result);
		}
	}

	private Result postResult(Result result) {
		MyAsyncTask2.sHandler.obtainMessage(1,
				(Object) new AsyncTaskResult(this, new Object[] { result }))
				.sendToTarget();
		return result;
	}

	public final Status getStatus() {
		return this.mStatus;
	}

	protected void onPreExecute() {
	}

	protected void onPostExecute(Result result) {
	}

	protected void onProgressUpdate(Progress... progressArr) {
	}

	protected void onCancelled(Result result) {
		onCancelled();
	}

	protected void onCancelled() {
	}

	public final boolean isCancelled() {
		return this.mCancelled.get();
	}

	public final boolean cancel(boolean mayInterruptIfRunning) {
		this.mCancelled.set(true);
		return this.mFuture.cancel(mayInterruptIfRunning);
	}

	public final Result get() throws InterruptedException, ExecutionException {
		return this.mFuture.get();
	}

	public final Result get(long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		return this.mFuture.get(timeout, unit);
	}

	public final MyAsyncTask2<Params, Progress, Result> execute(
			Params... params) {
		return executeOnExecutor(sDefaultExecutor, params);
	}

	public final MyAsyncTask2<Params, Progress, Result> executeOnExecutor(
            Executor exec, Params... params) {
		if (this.mStatus != Status.PENDING) {
			switch (StatusRunnableManager.$SwitchMap$com$lyrebirdstudio$common_libs$MyAsyncTask2$Status[this.mStatus
					.ordinal()]) {
			case MESSAGE_POST_RESULT /* 1 */:
				throw new IllegalStateException(
						"Cannot execute task: the task is already running.");
			case MESSAGE_POST_PROGRESS /* 2 */:
				throw new IllegalStateException(
						"Cannot execute task: the task has already been executed (a task can be executed only once)");
			}
		}
		this.mStatus = Status.RUNNING;
		onPreExecute();
		this.mWorker.mParams = params;
		exec.execute(this.mFuture);
		return this;
	}

	public static void execute(Runnable runnable) {
		sDefaultExecutor.execute(runnable);
	}

	protected final void publishProgress(Progress... values) {
		if (!isCancelled()) {
			sHandler.obtainMessage(MESSAGE_POST_PROGRESS,
					new AsyncTaskResult(this, values)).sendToTarget();
		}
	}

	private void finish(Result result) {
		if (isCancelled()) {
			onCancelled(result);
		} else {
			onPostExecute(result);
		}
		this.mStatus = Status.FINISHED;
	}

}
