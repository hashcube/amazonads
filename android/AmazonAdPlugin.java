package com.tealeaf.plugin.plugins;

import com.tealeaf.logger;
import com.tealeaf.EventQueue;
import com.tealeaf.plugin.IPlugin;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.amazon.device.ads.*;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class AmazonAdPlugin implements IPlugin {

  private InterstitialAd interstitialAd;

  public class AmazonAdNotAvailable extends com.tealeaf.event.Event {

    public AmazonAdNotAvailable() {
      super("AmazonAdNotAvailable");
    }
  }

  public class AmazonAdAvailable extends com.tealeaf.event.Event {

    public AmazonAdAvailable() {
      super("AmazonAdAvailable");
    }
  }

  public class AmazonAdDismissed extends com.tealeaf.event.Event {

    public AmazonAdDismissed() {
      super("AmazonAdDismissed");
    }
  }

  private class PluginDelegate extends DefaultAdListener {

    /**
     * This event is called once an ad loads successfully.
     */
    @Override
    public void onAdLoaded(final Ad ad, final AdProperties adProperties) {
      logger.log("{amazonads} adloaded");
      EventQueue.pushEvent(new AmazonAdAvailable());
    }

    /**
     * This event is called if an ad fails to load.
     */
    @Override
    public void onAdFailedToLoad(final Ad view, final AdError error) {
      logger.log("{amazonads} ad failed to load");
      EventQueue.pushEvent(new AmazonAdNotAvailable());
    }

    /**
     * This event is called when an interstitial ad has been dismissed by the user.
     */
    @Override
    public void onAdDismissed(final Ad ad) {
      logger.log("{amazonads} ad dismissed");
      EventQueue.pushEvent(new AmazonAdDismissed());
    }
  }

  public AmazonAdPlugin() {

  }

  public void onCreateApplication(Context applicationContext) {

  }

  public void onCreate(Activity activity, Bundle savedInstanceState) {

    PackageManager manager = activity.getPackageManager();
    String appKey = "";

    //FOR DEBUGGING ONLY
    // For debugging purposes enable logging, but disable for production builds.
    AdRegistration.enableLogging(true);
    // For debugging purposes flag all ad requests as tests, but set to false for production builds.
    AdRegistration.enableTesting(true);

    this.interstitialAd = new InterstitialAd(activity);
    this.interstitialAd.setListener(new PluginDelegate())
      ;
    try {
      Bundle meta = manager.getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA).metaData;
      if (meta != null) {
        appKey = meta.get("AMAZON_APP_KEY").toString();
      }
    } catch (Exception e) {
      android.util.Log.d("EXCEPTION", "" + e.getMessage());
    }

    logger.log("{amazonad} Initializing from manifest with AppID=", appKey);

    try {
      AdRegistration.setAppKey(appKey);
    } catch (final IllegalArgumentException e) {
      android.util.Log.d("IllegalArgumentException thrown: ", e.toString());
      return;
    }
  }

  public void showInterstitial(String jsonData) {
    this.interstitialAd.showAd();
  }

  public void cacheInterstitial(String jsonData) {
    this.interstitialAd.loadAd();
  }

  public void onResume() {
  }

  public void onStart() {
  }

  public void onPause() {
  }

  public void onStop() {
  }

  public void onDestroy() {
  }

  public void onNewIntent(Intent intent) {
  }

  public void setInstallReferrer(String referrer) {
  }

  public void onActivityResult(Integer request, Integer result, Intent data) {
  }

  public boolean consumeOnBackPressed() {
    return true;
  }

  public void onBackPressed() {
  }
}
