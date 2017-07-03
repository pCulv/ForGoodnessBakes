package com.example.phil.forgoodnessbakes;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainUITest() {
        ViewInteraction imageView = onView(
                allOf(withId(R.id.nutella_cake_image),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nutella_card_view),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.nutella_cake_title), withText("Nutella Cake"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nutella_card_view),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Nutella Cake")));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.brownies),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.brownie_card_view),
                                        0),
                                0),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.brownie_title), withText("Brownie"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.brownie_card_view),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Brownie")));

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.yellow_cake_image),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.yellow_cake_card_view),
                                        0),
                                0),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withText("For Goodness Bakes"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("For Goodness Bakes")));

        ViewInteraction cardView = onView(
                withId(R.id.nutella_card_view));
        cardView.perform(scrollTo(), click());

        ViewInteraction imageView4 = onView(
                allOf(withId(R.id.nutella_image),
                        childAtPosition(
                                allOf(withId(R.id.collapsing_toolbar),
                                        childAtPosition(
                                                withId(R.id.app_bar_layout),
                                                0)),
                                0),
                        isDisplayed()));
        imageView4.check(matches(isDisplayed()));

        ViewInteraction imageView5 = onView(
                allOf(withId(R.id.nutella_image),
                        childAtPosition(
                                allOf(withId(R.id.collapsing_toolbar),
                                        childAtPosition(
                                                withId(R.id.app_bar_layout),
                                                0)),
                                0),
                        isDisplayed()));
        imageView5.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.nutella_ingredients_tv), withText("Ingredients:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scroll),
                                        0),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("Ingredients:")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.steps_tv), withText("Steps:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scroll),
                                        0),
                                2),
                        isDisplayed()));
        textView5.check(matches(withText("Steps:")));

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.collapsing_toolbar)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction cardView2 = onView(
                withId(R.id.brownie_card_view));
        cardView2.perform(scrollTo(), click());

        ViewInteraction imageView6 = onView(
                allOf(withId(R.id.brownie_image),
                        childAtPosition(
                                allOf(withId(R.id.collapsing_toolbar),
                                        childAtPosition(
                                                withId(R.id.app_bar_layout),
                                                0)),
                                0),
                        isDisplayed()));
        imageView6.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.nutella_ingredients_tv), withText("Ingredients:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scroll),
                                        0),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("Ingredients:")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.steps_tv), withText("Steps:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scroll),
                                        0),
                                2),
                        isDisplayed()));
        textView7.check(matches(withText("Steps:")));

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.collapsing_toolbar)))),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction cardView3 = onView(
                withId(R.id.yellow_cake_card_view));
        cardView3.perform(scrollTo(), click());

        ViewInteraction imageView7 = onView(
                allOf(withId(R.id.yellow_cake_recipe_view),
                        childAtPosition(
                                allOf(withId(R.id.collapsing_toolbar),
                                        childAtPosition(
                                                withId(R.id.app_bar_layout),
                                                0)),
                                0),
                        isDisplayed()));
        imageView7.check(matches(isDisplayed()));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.nutella_ingredients_tv), withText("Ingredients:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scroll),
                                        0),
                                0),
                        isDisplayed()));
        textView8.check(matches(withText("Ingredients:")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.steps_tv), withText("Steps:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scroll),
                                        0),
                                2),
                        isDisplayed()));
        textView9.check(matches(withText("Steps:")));

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.collapsing_toolbar)))),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction cardView4 = onView(
                withId(R.id.cheese_cake_card_view));
        cardView4.perform(scrollTo(), click());

        ViewInteraction imageView8 = onView(
                allOf(withId(R.id.cheeseimage),
                        childAtPosition(
                                allOf(withId(R.id.collapsing_toolbar),
                                        childAtPosition(
                                                withId(R.id.app_bar_layout),
                                                0)),
                                0),
                        isDisplayed()));
        imageView8.check(matches(isDisplayed()));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.nutella_ingredients_tv), withText("Ingredients:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scroll),
                                        0),
                                0),
                        isDisplayed()));
        textView10.check(matches(withText("Ingredients:")));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.steps_tv), withText("Steps:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scroll),
                                        0),
                                2),
                        isDisplayed()));
        textView11.check(matches(withText("Steps:")));

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.collapsing_toolbar)))),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.yellow_cake_title), withText("Yellow Cake"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.yellow_cake_card_view),
                                        0),
                                1),
                        isDisplayed()));
        textView12.check(matches(withText("Yellow Cake")));

        ViewInteraction imageView9 = onView(
                allOf(withId(R.id.cheese_cake_image),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cheese_cake_card_view),
                                        0),
                                0),
                        isDisplayed()));
        imageView9.check(matches(isDisplayed()));

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.cheese_cake_title), withText("Cheese Cake"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cheese_cake_card_view),
                                        0),
                                1),
                        isDisplayed()));
        textView13.check(matches(withText("Cheese Cake")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
