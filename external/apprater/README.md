AppRater
========

 * Android library that lets you prompt users to rate your application
 * The prompting dialog will be displayed (if adequate) as soon as you call ```show()``` on your AppRater instance
 * The dialog will only be shown if at least one application is available on the user's phone to handle the Intent that is defined by the target URI
 * Adapts to your application's styles and themes
 * Minimum API level: 8 (Android 2.2)

Adding AppRater as a library project
========

 1. Download all files of this project as a ZIP and extract it to a local folder
 2. In Eclipse, click ```File```, ```New``` and ```Other```
 3. In section ```Android```, choose ```Android Project from Existing Source```
 4. For ```Root Directory```, click ```Browse``` and choose the folder where you've extracted the ZIP to
 5. Check ```Copy projects into workspace``` and click ```Finish```
 6. In Eclipse's Package Explorer, right-click on your application's name
 7. Choose ```Properties``` and go to section ```Android```.
 8. In section ```Library```, click ```Add```, select ```AppRater``` and click ```OK```
 9. Click ```Apply``` and ```OK```.

Usage
========

Decide which Activity you want to appear the rating prompt in (usually your ```MainActivity.java```).

At the end of ```onCreate(...)``` or ```onPause()```, add the following:

```new AppRater(this, "com.my.package").show();```

This is the basic usage. Make sure to provide your correct application package.

You can customize the AppRater by using any of the following calls before ```show()``` (which are all optional):

```java
AppRater appRater = new AppRater(this, "com.my.package");
appRater.setDaysBeforePrompt(3);
appRater.setLaunchesBeforePrompt(7);
appRater.setPhrases(R.string.rate_title, R.string.rate_explanation, R.string.rate_now, R.string.rate_later, R.string.rate_never);
mAlertDialog = appRater.show();
```

There are three additional methods which you won't need to call, usually:
```java
appRater.setPhrases("Rate this app", "This is the explanation why you should rate our app.", "Rate now", "Later", "No, thanks");
appRater.setTargetUri("https://play.google.com/store/apps/details?id=%1$s");
appRater.setPreferenceKeys("app_rater", "flag_dont_show", "launch_count", "first_launch_time");
```

The first one lets you set the phrases as Strings directly, without referencing resources. The second one lets you enter an alternative target URI if you want to redirect the user to another appstore than Google Play (e.g. Amazon Appstore). The third method lets you change the name of the preferences, which you won't need to do, usually.

Be sure to check the JavaDoc for all these methods when using them. Don't forget to call ```show()``` which is the most important part.

License
=======

```
 Copyright 2013 delight.im

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
```
