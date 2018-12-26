package com.battle.tanks90;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.parse.ParseBroadcastReceiver;

public class MyCustomReceiver extends ParseBroadcastReceiver {

	// @Override
	// protected void onPushReceive(Context context, Intent intent) {
	// // TODO Auto-generated method stub
	// super.onReceive(context, intent);
	// Bundle extras = intent.getExtras();
	// String jsonData = extras.getString("com.parse.Data");
	//
	// try {
	// JSONObject json = new JSONObject(jsonData);
	// String url = json.getString("url");
	// Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
	// viewIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	// context.startActivity(viewIntent);
	// } catch (JSONException e) {
	//
	// }
	// }

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);

		try {
			Bundle extras = intent.getExtras();
			String jsonData = extras.getString("com.parse.Data");
			JSONObject json = new JSONObject(jsonData);
			String url = json.getString("url");
			Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			viewIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(viewIntent);
		} catch (Exception e) {

		}
	}

}
