
package com.example.youhan.a4295;
 
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity
{
  @Override
  protected void onCreate(Bundle bundle)
  {
    super.onCreate(bundle);
    addPreferencesFromResource(R.xml.preferences);
  }
}

