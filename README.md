
# timecop [![](https://jitpack.io/v/cesarferreira/timecop.svg)](https://jitpack.io/#cesarferreira/timecop)

> Count the time at any part of your app

As simple as it gets:

```java
final String LOAD_VIEWS_TAG = "load-views-time";

// start the timer
TimeCop.start(LOAD_VIEWS_TAG);

setupViews();

// stop the timer
long difference = TimeCop.stop(LOAD_VIEWS_TAG);
log("time past: " + difference + " ms");


```

# Installation

Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

Now add your dependency:
```groovy
dependencies {
  compile 'com.github.cesarferreira.timecop:library:0.3.0'
}
```
