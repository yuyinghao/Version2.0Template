package com.hitv.android.uiversion2.process;

import android.app.Instrumentation;

public class KeyProcess {
	public static boolean sendKeyDownUpSync(int key) {
		boolean is = false;
		try {
			Instrumentation inst = new Instrumentation();
			inst.sendKeyDownUpSync(key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}
}