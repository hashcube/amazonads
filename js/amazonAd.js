import util.setProperty as setProperty;

var onAdDismissed, onAdAvailable, onAdNotAvailable;

var AmazonAd = Class(function () {
  this.init = function(opts) {

    setProperty(this, "onAdDismissed", {
      set: function(f) {
        if (typeof f === "function") {
          onAdDismissed = f;
        } else {
          onAdDismissed = null;
        }
      },
      get: function() {
        return onOfferClose;
      }
    });

    setProperty(this, "onAdAvailable", {
      set: function(f) {
        if (typeof f === "function") {
          onAdAvailable = f;
        } else {
          onAdAvailable = null;
        }
      },
      get: function() {
        return onAdAvailable;
      }
    });

    setProperty(this, "onAdNotAvailable", {
      set: function(f) {
        if (typeof f === "function") {
          onAdNotAvailable = f;
        } else {
          onAdNotAvailable = null;
        }
      },
      get: function() {
        return onAdNotAvailable;
      }
    });

    NATIVE.events.registerHandler("AmazonAdDismissed", function() {
      logger.log("{amazonad} ad dismissed ");
      if (typeof onAdDismissed === "function") {
        onAdDismissed();
      }
    });

    NATIVE.events.registerHandler("AmazonAdAvailable", function() {
      logger.log("{amazonad} ad available");
      if (typeof onAdAvailable === "function") {
        onAdAvailable("amazon");
      }
    });

    NATIVE.events.registerHandler("AmazonAdNotAvailable", function() {
      logger.log("{amazonad} ad not available");
      if (typeof onAdNotAvailable === "function") {
        onAdNotAvailable();
      }
    });

  }
  this.showInterstitial = function() {
    NATIVE.plugins.sendEvent("AmazonAdPlugin", "showInterstitial", JSON.stringify({}));
  };

  this.cacheInterstitial = function() {
    NATIVE.plugins.sendEvent("AmazonAdPlugin", "cacheInterstitial", JSON.stringify({}));
  }
});

exports = new AmazonAd();
