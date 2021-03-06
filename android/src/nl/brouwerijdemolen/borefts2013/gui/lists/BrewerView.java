/* 
 * Copyright 2010-2013 Eric Kok et al.
 * 
 * Transdroid is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Transdroid is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Transdroid.  If not, see <http://www.gnu.org/licenses/>.
 */
package nl.brouwerijdemolen.borefts2013.gui.lists;

import nl.brouwerijdemolen.borefts2013.R;
import nl.brouwerijdemolen.borefts2013.api.Brewer;
import nl.brouwerijdemolen.borefts2013.gui.helpers.ApiQueue;
import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;

/**
 * View that represents some {@link Brewer} object and displays name, origin and logo.
 * @author Eric Kok
 */
@EViewGroup(R.layout.list_item_brewer)
public class BrewerView extends RelativeLayout {

	@Bean
	protected ApiQueue apiQueue;
	@ViewById
	protected NetworkImageView logoImage;
	@ViewById
	protected TextView nameText, originText;

	public BrewerView(Context context) {
		super(context);
	}

	public void bind(Brewer brewer) {
		//nameText.setText(MolenTypefaceSpan.makeMolenSpannable(getContext(), brewer.getShortName()));
		nameText.setText(brewer.getShortName());
		originText.setText(getResources().getString(R.string.info_origin, brewer.getCity(), brewer.getCountry()));
		logoImage.setImageUrl(brewer.getLogoFullUrl(), apiQueue.getImageLoader());
	}

}
