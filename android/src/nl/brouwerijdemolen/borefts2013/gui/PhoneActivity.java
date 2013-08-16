package nl.brouwerijdemolen.borefts2013.gui;

import nl.brouwerijdemolen.borefts2013.R;
import nl.brouwerijdemolen.borefts2013.gui.fragments.*;
import nl.brouwerijdemolen.borefts2013.gui.helpers.MolenTypefaceSpan;
import nl.brouwerijdemolen.borefts2013.gui.helpers.NavigationManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;

@EActivity(R.layout.activity_phone)
@OptionsMenu(R.menu.home)
public class PhoneActivity extends SherlockFragmentActivity implements NavigationManager {

	private ViewPager pager;
	private InfoFragment infoFragment = null;
	private BrewersFragment brewersFragment = null;
	private StylesFragment stylesFragment = null;
	private TwitterFragment twitterFragment = null;

	@AfterViews
	protected void init() {

		// Set up an action bar, navigation tabs and view pager
		getSupportActionBar().setTitle(MolenTypefaceSpan.makeMolenSpannable(this, getString(R.string.app_name_short)));
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		getSupportActionBar().addTab(
				getSupportActionBar().newTab().setText(R.string.action_info).setTabListener(tabListener));
		getSupportActionBar().addTab(
				getSupportActionBar().newTab().setText(R.string.action_brewers).setTabListener(tabListener));
		getSupportActionBar().addTab(
				getSupportActionBar().newTab().setText(R.string.action_styles).setTabListener(tabListener));
		getSupportActionBar().addTab(
				getSupportActionBar().newTab().setText(R.string.action_twitter).setTabListener(tabListener));

		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new TabsPagerAdapter(getSupportFragmentManager()));
		pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				getSupportActionBar().setSelectedNavigationItem(position);
			}
		});

	}

	@OptionsItem
	protected void actionSettingsSelected() {
		// TODO: Start settings activity
	}

	private class TabsPagerAdapter extends FragmentPagerAdapter {

		public TabsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return 4;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				if (infoFragment == null)
					infoFragment = InfoFragment_.builder().build();
				return infoFragment;
			case 1:
				if (brewersFragment == null)
					brewersFragment = BrewersFragment_.builder().build();
				return brewersFragment;
			case 2:
				if (stylesFragment == null)
					stylesFragment = StylesFragment_.builder().build();
				return stylesFragment;
			case 3:
				if (twitterFragment == null)
					twitterFragment = TwitterFragment_.builder().build();
				return twitterFragment;
			}
			return null;
		}
	}

	private TabListener tabListener = new TabListener() {
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// Simply more the view pager to the appropriate tab
			if (pager != null)
				pager.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// No need to do anything
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// No need to do anything
		}
	};

	@Override
	public void openBrewer(Fragment baseFragment, int brewerId) {
		PhoneContainerActivity_.intent(this).brewerId(brewerId).start();
	}

	@Override
	public void openStyle(Fragment baseFragment, int styleId) {
		PhoneContainerActivity_.intent(this).styleId(styleId).start();
	}

	@Override
	public void openBeer(Fragment baseFragment, int beerId) {
		PhoneContainerActivity_.intent(this).beerId(beerId).start();
	}
	
}