
# timecop

As simple as it gets:

```java
final String LOAD_VIEWS_TAG           = "load-views-time";
final String DURATION_OF_ACTIVIY_TAG  = "activity-duration-time";

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // start the timer
    TimeCop.start(LOAD_VIEWS_TAG);

    setupViews();
    setupDefaultValuesForViews();

    // stop the timer
    long difference = TimeCop.stop(LOAD_VIEWS_TAG);
    log("time past: " + difference + " ms");
}


@Override
protected void onStop() {
    super.onStop();

    // stop the timer
    long difference = TimeCop.stop(DURATION_OF_ACTIVIY_TAG);
    log("time past: " + difference + " ms");
}

```
