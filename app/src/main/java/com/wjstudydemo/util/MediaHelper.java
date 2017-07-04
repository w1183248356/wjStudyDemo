package com.wjstudydemo.util;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by huntero on 17-1-4.
 */

public class MediaHelper {

    MediaRecorder mRecorder = null;
    MediaPlayer mPlayer = null;

    File mMediaFile = null;
    private int mTimeSeconds;

    public interface OnTimeProcess extends MediaPlayer.OnCompletionListener{
         void onTimeProcess(int seconds);
    }

    OnTimeProcess mOnTimeProcess = null;
    Timer mTimer = null;

    public MediaHelper(final OnTimeProcess listener) {
        this.mOnTimeProcess = new OnTimeProcess() {
            @Override
            public void onTimeProcess(int seconds) {
                listener.onTimeProcess(seconds);
            }

            @Override
            public void onCompletion(MediaPlayer mp) {
                stopTimer();
                listener.onCompletion(mp);
            }
        };
    }

    public void release() {
        stopRecord();
        stopPlay();
        mTimeSeconds = 0;
    }

    public void setMediaFile(File file) {
        this.mMediaFile = file;
    }

    private void startTimer() {
        mTimer = new Timer();
        mTimeSeconds = 0;
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (mOnTimeProcess != null) {
                    mOnTimeProcess.onTimeProcess(mTimeSeconds);
                }
                mTimeSeconds++;
            }
        },0l, 1000l);
    }
    private void stopTimer() {
        if(mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }


    private void initRecorder() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setOutputFile(mMediaFile.getAbsolutePath());
    }

    long startRecord;
    public void record(File file) {
        mMediaFile = file;
        stopRecord();
        if (mRecorder == null) {
            synchronized (this) {
                if (mRecorder == null) {
                    initRecorder();
                }
            }
        }
        try {
            mRecorder.prepare();
            mRecorder.start();
            startTimer();
            startRecord = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
            stopTimer();
            startRecord = 0l;
        }

    }

    public long stopRecord() {
        long timeMillis = System.currentTimeMillis() - startRecord;
        Log.i("MediaRecord", "stopRecord: " + timeMillis);
        stopTimer();
        if (mRecorder != null) {
                synchronized (this) {
                    if(timeMillis > 1000) {
                        try {
                            mRecorder.setOnErrorListener(null);
                            mRecorder.setOnInfoListener(null);
                            mRecorder.setPreviewDisplay(null);
                            mRecorder.stop();
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        }
                    }

                    mRecorder.release();
                    mRecorder = null;
                }
        }
        startRecord = 0l;
        return timeMillis;
    }

    private boolean initPlayer() {
        if (mMediaFile == null || !mMediaFile.exists()) {
            return false;
        }
        stopPlay();
        if (mPlayer == null) {
            synchronized (this) {
                if (mPlayer == null) {
                    mPlayer = new MediaPlayer();
                }
            }
        }
//        if (mOnCompletionListener != null) {
            mPlayer.setOnCompletionListener(mOnTimeProcess);
//        }
        return true;
    }

    public int getDuration() {
        if (!initPlayer())
            return 0;

        try {
            mPlayer.setDataSource(mMediaFile.getAbsolutePath());
            mPlayer.prepare();
            return mPlayer.getDuration() / 1000;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void play() {
        if (!initPlayer())
            return;

        try {
            mPlayer.setDataSource(mMediaFile.getAbsolutePath());
            mPlayer.prepare();
            mPlayer.start();
            startTimer();
        } catch (IOException e) {
            e.printStackTrace();
            stopTimer();
        }
    }

    public void stopPlay() {
        if (mPlayer != null) {
            synchronized (this) {
                mPlayer.stop();
                mPlayer.release();
                mPlayer = null;
            }
        }
        stopTimer();
    }
}
