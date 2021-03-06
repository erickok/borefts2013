package nl.brouwerijdemolen.borefts2013.gui.fragments;

import java.util.Locale;

import nl.brouwerijdemolen.borefts2013.R;
import nl.brouwerijdemolen.borefts2013.api.Beer;
import nl.brouwerijdemolen.borefts2013.gui.helpers.MolenTypefaceSpan;
import nl.brouwerijdemolen.borefts2013.gui.helpers.NavigationManager;
import nl.brouwerijdemolen.borefts2013.gui.helpers.StarPersistance;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.FragmentArg;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_beer)
@OptionsMenu(R.menu.fragment_beer)
public class BeerFragment extends SherlockFragment {

	@FragmentArg
	protected Beer beer;
	@Bean
	protected StarPersistance stars;
	@ViewById
	protected TextView nameText, abvText;
	@ViewById
	protected Button brewerButton, styleButton;
	@ViewById
	protected LinearLayout tagsLayout;

	public BeerFragment() {
		setRetainInstance(true);
		setHasOptionsMenu(true);
	}

	@AfterViews
	protected void init() {
		nameText.setText(MolenTypefaceSpan.makeMolenSpannable(getActivity(), beer.getName()));
		brewerButton.setText(beer.getBrewer().getName());
		styleButton.setText(beer.getStyle().getName());
		if (beer.getAbv() >= 0)
			abvText.setText(getString(R.string.info_abvlabel, beer.getAbv()));
		else
			abvText.setVisibility(View.INVISIBLE);
		if (!TextUtils.isEmpty(beer.getTags())) {
			for (String tag : beer.getTags().split(",")) {
				addTagView(tag);
			}
		}
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		boolean isStarred = stars.isStarred(beer.getId());
		menu.findItem(R.id.action_star_off).setVisible(!isStarred);
		menu.findItem(R.id.action_star_on).setVisible(isStarred);
	}

	private void addTagView(String tag) {
		getActivity().getLayoutInflater().inflate(R.layout.widget_label, tagsLayout);
		tag = tag.toUpperCase(Locale.getDefault());
		((TextView) tagsLayout.getChildAt(tagsLayout.getChildCount() - 1)).setText(tag);
	}

	@Click
	protected void brewerButtonClicked() {
		((NavigationManager) getActivity()).openBrewer(this, beer.getBrewer());
	}

	@Click
	protected void styleButtonClicked() {
		((NavigationManager) getActivity()).openStyle(this, beer.getStyle());
	}

	@OptionsItem
	protected void actionStarOn() {
		stars.removeStar(beer.getId());
		getSherlockActivity().supportInvalidateOptionsMenu();
	}

	@OptionsItem
	protected void actionStarOff() {
		stars.addStar(beer.getId());
		getSherlockActivity().supportInvalidateOptionsMenu();
	}

	@OptionsItem
	protected void actionLocate() {
		((NavigationManager) getActivity()).openMap(this, MapFragment.BREWER_ID_THRESHOLD + beer.getBrewerId(),
				beer.getBrewer());
	}

	@Click
	protected void ratebeerButtonClicked() {
		if (beer.getRatebeerId() <= 0) {
			Toast.makeText(getActivity(), R.string.error_notcoupled, Toast.LENGTH_LONG).show();
			return;
		}
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ratebeer.com/b/" + beer.getRatebeerId()
				+ "/")).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
	}

	@Click
	protected void untappdButtonClicked() {
		if (beer.getUntappdId() <= 0) {
			Toast.makeText(getActivity(), R.string.error_notcoupled, Toast.LENGTH_LONG).show();
			return;
		}
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://untappd.com/b/b/" + beer.getUntappdId()))
				.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
	}

}
