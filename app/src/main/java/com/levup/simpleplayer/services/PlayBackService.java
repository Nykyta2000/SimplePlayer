package com.levup.simpleplayer.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.compat.BuildConfig;
import android.util.Log;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.PrintWriter;

public class PlayBackService extends Service implements MediaPlayer.OnPreparedListener{

    public  static final String ACTION_PLAY = BuildConfig.APPLICATION_ID + ".action.PLAY";

    public static final String TAG = PlayBackService.class.getSimpleName();

    private final IBinder mBinder = new PlayBackBinder();

    private MediaPlayer mMediaPlayer = null;


    public static Intent newInstance(Context context) {
        return new Intent(context, PlayBackService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "onUnbind()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().equals(ACTION_PLAY)) {
            mMediaPlayer = new MediaPlayer(); // initialize it here
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.prepareAsync(); // prepare async to not block main thread
        }

        return Service.START_NOT_STICKY;
    }

    public PlayBackService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }

    public class PlayBackBinder extends Binder {
        public PlayBackService getService() {
            return PlayBackService.this;
        }
    }

}
