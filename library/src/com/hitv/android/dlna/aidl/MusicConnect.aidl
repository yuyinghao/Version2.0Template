package com.hitv.android.dlna.aidl;

import com.hitv.android.dlna.aidl.MusicData;
interface MusicConnect {

   void refreshMusicItem(in MusicData musicItem);
   
   MusicData getMusicFileItem();
   
   boolean rePlay();
   
   boolean play();
   
   boolean pause();
   
   boolean stop();
   
   boolean seekTo(int rate);
   
   int getCurPosition();
   
   int getAudioSessionId();
   
   int getDuration();
   
   int getPlayState();
   
   void sendPlayStateBrocast();
   
   void exit();
}


