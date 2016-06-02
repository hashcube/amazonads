# Game Closure DevKit Plugin: Amazon Ads

This plugin allows you to show interstitial ads on amazon kindle devices

## Usage

Install the addon with

Include it in the `manifest.json` file under the "addons" section for your game:

~~~
{
	"dependencies": "https://github.com/hashcube/amazonads.git#master"
}
~~~

To specify your game's AppKey in the android section

~~~
	"android": {
		"versionCode": 1,
		"icons": {
			"36": "resources/icons/android36.png",
			"48": "resources/icons/android48.png",
			"72": "resources/icons/android72.png",
			"96": "resources/icons/android96.png"
		},
		"amazonAppKey": "your amazon app key"
	},
~~~

Note that the manifest keys are case-sensitive.

To show interstitials

~~~
import plugins.amazonads.amazonAd as amazon;
~~~

Cache interstitials by calling:

~~~
amazon.cacheInterstitial();
~~~

Then show them by calling:

~~~
amazon.showInterstitial();
~~~

In addition you can be notified for the following events


Ad available event:

~~~
amazon.onAdAvailable = function(ad) {
  //callback that gets called when ad is available
};
~~~

Ad Not available

~~~
amazon.onAdNotAvailable = function(ad) {
  //callback that gets called when ad is not available
};
~~~

Ad Dismissed

~~~
amazon.onAdDismissed = function(ad) {
  //callback that gets called when ad is dismissed
};
~~~
