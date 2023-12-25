package com.example.luck;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }
    static boolean isplay;
    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void  onCreate(){
        Context context;
        player=MediaPlayer.create( this, R.raw.music);
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        if(!player.isPlaying()){
            player.start();
            isplay=player.isPlaying();
        }
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onDestroy(){
        player.stop();
        isplay=player.isPlaying();
        player.release();
        super.onDestroy();
    }
}
