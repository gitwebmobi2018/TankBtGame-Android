package com.battle.tanks90;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Parse.initialize(this, "x8VSs3ZcJsT0H7Qh9FJNPPax1l4huciTS4eUOizs",
				"aVrYAjDapokvk8scARAZTpLFVhaF5cLk5VHOw0T5");
		ParseInstallation.getCurrentInstallation().saveInBackground();
	}

}
