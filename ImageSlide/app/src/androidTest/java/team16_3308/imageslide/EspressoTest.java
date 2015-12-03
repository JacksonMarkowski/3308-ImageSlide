package team16_3308.imageslide;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
@RunWith(AndroidJUnit4.class)
public class EspressoTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;

    public EspressoTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    @Test
    public void menuButtonDisplayed(){
        onView(withId(R.id.menuButton)).check(matches(isDisplayed()));
    }

    @Test
    public void menuLayoutDisplayed() {
        onView(withId(R.id.menuButton)).perform(click());
        onView(withId(R.id.menuLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void settingsButtonCheck() {
        onView(withId(R.id.menuButton)).perform(click());
        onView(withId(R.id.button_settings)).check(matches(isDisplayed()));
        onView(withId(R.id.button_settings)).check(matches(withText("Settings")));
    }

    @Test
    public void imageSitesButtonCheck() {
        onView(withId(R.id.menuButton)).perform(click());
        onView(withId(R.id.button_imageSites)).check(matches(isDisplayed()));
        onView(withId(R.id.button_imageSites)).check(matches(withText("Image Sites")));
    }

    @Test
    public void scrollView() {
        onView(withId(R.id.scrollView)).check(matches(isDisplayed()));
        onView(withId(R.id.imagesLayout)).perform(scrollTo());
    }
}
