package com.imi_gma.notiply;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import com.imi_gma.notiply.Views.MainActivity;

@RunWith(AndroidJUnit4.class)

public class testMainActivity {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testBtnPageHost(){
        onView(withId(R.id.btnPageHost))
                .perform(click());

        onView(withId(R.id.imageView))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testBtnPageJoin(){
        onView(withId(R.id.btnPageJoin))
                .perform(click());

        onView(withId(R.id.txt_WifiInfoText))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testTxtSetUserName(){
        String s = "TestName";
        onView(withId(R.id.editUserName)).perform(clearText(), typeText(s));
        // TODO: correct assertion
    }

}
