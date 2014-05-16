<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:android="http://schemas.android.com/apk/res/android">

  <xsl:param name="amazonAppKey" />

  <xsl:output indent="yes" />
  <xsl:template match="comment()" />

  <xsl:template match="meta-data[@android:name='AMAZON_APP_KEY']">
    <meta-data android:name="AMAZON_APP_KEY" android:value="{$amazonAppKey}"/>
  </xsl:template>

  <xsl:template match="activity[@android:name='com.amazon.device.ads.AdActivity']">
    <activity android:name="com.amazon.device.ads.AdActivity"
              android:configChanges="keyboardHidden|orientation|screenSize"/>
  </xsl:template>

  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()" />
    </xsl:copy>
  </xsl:template>
</xsl:stylesheet>
