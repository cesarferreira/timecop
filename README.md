
# timecop [![](https://jitpack.io/v/cesarferreira/timecop.svg)](https://jitpack.io/#cesarferreira/timecop)

> Count the time from any part of your app

As simple as it gets:

```java
// start the timer
TimeCop.getInstance(ctx)
	.start(LOAD_VIEWS_TAG);

// do some work
setupViews();

// stop the timer
TimeCop.getInstance(ctx)
	.stopAndDisplayLog(LOAD_VIEWS_TAG);

// outputs =>  D/TIMECOP: 668 ms - forKey: LOAD_VIEWS_TAG

```

## Installation

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
	compile 'com.github.cesarferreira:timecop:0.3.3'
}
```
