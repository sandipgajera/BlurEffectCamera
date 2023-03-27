package org.wysaid.view;

import android.content.Context;
import android.media.AudioRecord;
import android.os.Process;
import android.util.AttributeSet;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public class CameraRecordGLSurfaceView extends CameraGLSurfaceViewWithTexture {

    public AudioRecordRunnable mAudioRecordRunnable;

    public Thread mAudioThread;

    public final Object mRecordStateLock = new Object();

    public boolean mShouldRecord = false;

    public interface EndRecordingCallback {
        void endRecordingOK();
    }

    public interface StartRecordingCallback {
        void startRecordingOver(boolean z);
    }

    public CameraRecordGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public synchronized boolean isRecording() {
        return this.mShouldRecord;
    }

    public void startRecording(String str) {
        startRecording(str, (StartRecordingCallback) null);
    }



    public void startRecording(final String filename, final StartRecordingCallback recordingCallback) {

        queueEvent(new Runnable() {
            @Override
            public void run() {

                if (mFrameRecorder == null) {
                    Log.e(LOG_TAG, "Error: startRecording after release!!");
                    if (recordingCallback != null) {
                        recordingCallback.startRecordingOver(false);
                    }
                    return;
                }

                if (!mFrameRecorder.startRecording(30, filename)) {
                    Log.e(LOG_TAG, "start recording failed!");
                    if (recordingCallback != null)
                        recordingCallback.startRecordingOver(false);
                    return;
                }
                Log.i(LOG_TAG, "glSurfaceView recording, file: " + filename);
                synchronized (mRecordStateLock) {
                    mShouldRecord = true;
                    mAudioRecordRunnable = new AudioRecordRunnable(recordingCallback);
                    if (mAudioRecordRunnable.audioRecord != null) {
                        mAudioThread = new Thread(mAudioRecordRunnable);
                        mAudioThread.start();
                    }
                }
            }
        });
    }

    public void endRecording() {
        endRecording((EndRecordingCallback) null, true);
    }

    public void endRecording(EndRecordingCallback endRecordingCallback) {
        endRecording(endRecordingCallback, true);
    }


    public void endRecording(final EndRecordingCallback callback, final boolean shouldSave) {
        Log.i(LOG_TAG, "notify quit...");
        synchronized (mRecordStateLock) {
            mShouldRecord = false;
        }

        if (mFrameRecorder == null) {
            Log.e(LOG_TAG, "Error: endRecording after release!!");
            return;
        }

        joinAudioRecording();

        queueEvent(new Runnable() {
            @Override
            public void run() {
                if (mFrameRecorder != null)
                    mFrameRecorder.endRecording(shouldSave);
                if (callback != null) {
                    callback.endRecordingOK();
                }
            }
        });
    }


    public void onRelease() {
        synchronized (this.mRecordStateLock) {
            this.mShouldRecord = false;
        }
        joinAudioRecording();
        super.onRelease();
    }

    public void stopPreview() {
        synchronized (this.mRecordStateLock) {
            if (this.mShouldRecord) {
                Log.e("libCGE_java", "The camera is recording! cannot stop!");
            } else {
                super.stopPreview();
            }
        }
    }

    public void joinAudioRecording() {
        Thread thread = this.mAudioThread;
        if (thread != null) {
            try {
                thread.join();
                this.mAudioThread = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class AudioRecordRunnable implements Runnable {
        private static final int sampleRate = 44100;
        ShortBuffer audioBuffer;
        ByteBuffer audioBufferRef;
        public AudioRecord audioRecord;
        int bufferReadResult;
        int bufferSize;
        public volatile boolean isInitialized;
        StartRecordingCallback recordingCallback;

        private AudioRecordRunnable(StartRecordingCallback startRecordingCallback) {
            StartRecordingCallback startRecordingCallback2;
            this.recordingCallback = startRecordingCallback;
            try {
                this.bufferSize = AudioRecord.getMinBufferSize(sampleRate, 16, 2);
                Log.i("libCGE_java", "audio min buffer size: " + this.bufferSize);
                this.audioRecord = new AudioRecord(1, sampleRate, 16, 2, this.bufferSize);
                ByteBuffer order = ByteBuffer.allocateDirect(this.bufferSize * 2).order(ByteOrder.nativeOrder());
                this.audioBufferRef = order;
                this.audioBuffer = order.asShortBuffer();
            } catch (Exception unused) {
                AudioRecord audioRecord2 = this.audioRecord;
                if (audioRecord2 != null) {
                    audioRecord2.release();
                    this.audioRecord = null;
                }
            }
            if (this.audioRecord == null && (startRecordingCallback2 = this.recordingCallback) != null) {
                startRecordingCallback2.startRecordingOver(false);
                this.recordingCallback = null;
            }
        }


        public void run() {
            Process.setThreadPriority(-19);
            this.isInitialized = false;
            if (this.audioRecord == null) {
                this.recordingCallback.startRecordingOver(false);
                this.recordingCallback = null;
                return;
            }
            while (this.audioRecord.getState() == 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.isInitialized = true;
            try {
                this.audioRecord.startRecording();
                if (this.audioRecord.getRecordingState() != 3) {
                    StartRecordingCallback startRecordingCallback = this.recordingCallback;
                    if (startRecordingCallback != null) {
                        startRecordingCallback.startRecordingOver(false);
                        this.recordingCallback = null;
                        return;
                    }
                    return;
                }
                StartRecordingCallback startRecordingCallback2 = this.recordingCallback;
                if (startRecordingCallback2 != null) {
                    startRecordingCallback2.startRecordingOver(true);
                    this.recordingCallback = null;
                }
                while (true) {
                    synchronized (CameraRecordGLSurfaceView.this.mRecordStateLock) {
                        if (!CameraRecordGLSurfaceView.this.mShouldRecord) {
                            this.audioRecord.stop();
                            this.audioRecord.release();
                            Log.i("libCGE_java", "Audio thread end!");
                            return;
                        }
                    }
                }
            } catch (Exception unused) {
                StartRecordingCallback startRecordingCallback3 = this.recordingCallback;
                if (startRecordingCallback3 != null) {
                    startRecordingCallback3.startRecordingOver(false);
                    this.recordingCallback = null;
                }
            }
        }
    }
}
