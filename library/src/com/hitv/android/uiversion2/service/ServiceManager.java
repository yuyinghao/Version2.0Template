package com.hitv.android.uiversion2.service;

import com.hitv.android.dlna.aidl.MusicConnect;
import com.hitv.android.dlna.aidl.MusicData;
import com.hitv.android.uiversion2.interfaces.IOnServiceConnectComplete;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ServiceManager {

	private final static String TAG = "ServiceManager";

	private final static String SERVICE_NAME = "com.hitv.android.dlna.service.musicservice";

	private Boolean mConnectComplete;

	private ServiceConnection mServiceConnection;

	private MusicConnect mMusicConnect;

	private IOnServiceConnectComplete mIOnServiceConnectComplete;

	private Context mContext;

	public ServiceManager(Context context) {
		mContext = context;
		defaultParam();
	}

	private void defaultParam() {
		mServiceConnection = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				Log.i(TAG, "onServiceDisconnected");

			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				Log.i(TAG, "onServiceConnected");
				mMusicConnect = MusicConnect.Stub.asInterface(service);
				if (mMusicConnect != null) {
					if (mIOnServiceConnectComplete != null) {
						mIOnServiceConnectComplete.OnServiceConnectComplete();
					}
				}
			}
		};

		mConnectComplete = false;
		mMusicConnect = null;
	}

	public boolean connectService() {
		if (mConnectComplete == true) {
			return true;
		}

		Intent intent = new Intent(SERVICE_NAME);
		if (mContext != null) {
			Log.i(TAG, "begin to connectService	");
			mContext.bindService(intent, mServiceConnection,
					Context.BIND_AUTO_CREATE);
			mContext.startService(intent);
			mConnectComplete = true;
			return true;
		}

		return false;
	}

	public boolean disconnectService() {
		if (mConnectComplete == false) {
			return true;
		}

		Intent intent = new Intent(SERVICE_NAME);
		if (mContext != null) {
			Log.i(TAG, "begin to disconnectService");
			mContext.unbindService(mServiceConnection);
			mMusicConnect = null;
			mConnectComplete = false;
			return true;
		}

		return false;
	}

	public void exit() {
		if (mConnectComplete) {
			try {
				mMusicConnect.exit();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Intent intent = new Intent(SERVICE_NAME);
			mContext.unbindService(mServiceConnection);
			mContext.stopService(intent);
			mMusicConnect = null;
			mConnectComplete = false;

		}
	}

	public void reset() {
		if (mConnectComplete) {
			try {
				mMusicConnect.exit();
			} catch (RemoteException e) {
			}
		}
	}

	public void setOnServiceConnectComplete(
			IOnServiceConnectComplete IServiceConnect) {
		mIOnServiceConnectComplete = IServiceConnect;
	}

	public void refreshMusicItem(MusicData musicItem) {
		if (mMusicConnect != null) {
			try {
				mMusicConnect.refreshMusicItem(musicItem);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

	public MusicData getMusicFileItem() {
		MusicData musicFileItem = null;
		try {
			musicFileItem = mMusicConnect.getMusicFileItem();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return musicFileItem;
	}

	public boolean rePlay() {
		if (mMusicConnect != null) {
			try {
				Log.i(TAG, "mMusicConnect.rePlay()");
				return mMusicConnect.rePlay();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean play() {
		if (mMusicConnect != null) {
			try {
				return mMusicConnect.play();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	public boolean pause() {
		if (mMusicConnect != null) {
			try {
				return mMusicConnect.pause();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean stop() {
		if (mMusicConnect != null) {
			try {
				return mMusicConnect.stop();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean seekTo(int rate) {
		if (mMusicConnect != null) {
			try {
				return mMusicConnect.seekTo(rate);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public int getCurPosition() {
		if (mMusicConnect != null) {
			try {
				return mMusicConnect.getCurPosition();
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}
		return 0;
	}

	public int getDuration() {
		if (mMusicConnect != null) {
			try {
				return mMusicConnect.getDuration();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}
	
	public int getAudioSessionId(){
		if (mMusicConnect != null) {
			try {
				return mMusicConnect.getAudioSessionId();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	public int getPlayState() {
		if (mMusicConnect != null) {
			try {
				return mMusicConnect.getPlayState();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return MusicPlayState.MPS_NOFILE;
	}

	public void sendPlayStateBrocast() {
		if (mMusicConnect != null) {
			try {
				mMusicConnect.sendPlayStateBrocast();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

}
