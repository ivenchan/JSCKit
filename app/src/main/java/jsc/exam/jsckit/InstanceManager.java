package jsc.exam.jsckit;

import android.app.Application;

import jsc.kit.utils.BitmapCacheManager;
import jsc.kit.utils.CustomToast;

public class InstanceManager {
    private volatile static InstanceManager instance = null;
    private boolean isInitialized = false;
    private CustomToast customToast;
    private BitmapCacheManager bitmapCacheManager;

    private InstanceManager(){}

    public static InstanceManager getInstance() {
        if (instance == null){
            synchronized (InstanceManager.class){
                if (instance == null)
                    instance = new InstanceManager();
            }
        }
        return instance;
    }

    public void init(Application application){
        isInitialized =  true;
        customToast = new CustomToast(application);
        bitmapCacheManager = new BitmapCacheManager();
    }

    public CustomToast getCustomToast() {
        checkIsInitialized();
        return customToast;
    }

    public BitmapCacheManager getBitmapCacheManager() {
        checkIsInitialized();
        return bitmapCacheManager;
    }

    private void checkIsInitialized(){
        if (!isInitialized)
            throw new RuntimeException("Please init first.");
    }
}