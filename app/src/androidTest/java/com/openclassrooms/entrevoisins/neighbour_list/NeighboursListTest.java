
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int FAVORITE_ITEMS_COUNT = 2;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(Matchers.allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
      onView(Matchers.allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(Matchers.allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(Matchers.allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void myNeighboursList_itemsOnclickAction_startDetailActivity() {
        onView(Matchers.allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(Matchers.allOf(withId(R.id.activity_detail_aboutMe_title), isDisplayed())).check(matches(withText(R.string.aboutMe)));
        onView(Matchers.allOf(withId(R.id.activity_detail_name_txt), isDisplayed())).check(matches(withText("Caroline")));
    }

    @Test
    public void myFavoriteTab_contains_onlyAddFavorites() {
        onView(Matchers.allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(Matchers.allOf(withId(R.id.activity_detail_favorite_btn), isDisplayed())).perform(( click()));
        Espresso.pressBack();
        onView(Matchers.allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(Matchers.allOf(withId(R.id.activity_detail_favorite_btn), isDisplayed())).perform(( click()));
        Espresso.pressBack();
        onView(Matchers.allOf(withId(R.id.main_content), isDisplayed())).perform(swipeLeft());
        onView(Matchers.allOf(withId(R.id.item_list_name),
                isDisplayed(),
                childAtPosition(childAtPosition(withId(R.id.list_neighbours),0),1))).check(matches(withText("Jack")));
        onView(Matchers.allOf(withId(R.id.item_list_name),isDisplayed(),
                childAtPosition(childAtPosition(withId(R.id.list_neighbours),1),1))).check(matches(withText("Caroline")));
        onView(Matchers.allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(FAVORITE_ITEMS_COUNT ));

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
