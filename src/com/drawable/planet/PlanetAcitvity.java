package com.drawable.planet;

import java.util.Locale;

import com.stone.songtaste.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class PlanetAcitvity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDarwToggle;

	private CharSequence mDrawTitle;
	private CharSequence mTitle;
	private String[] mPlanetTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawable_planet_activity);

		mTitle = mDrawTitle = getTitle();
		mPlanetTitles = getResources().getStringArray(R.array.planets_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens

		// Set the adapter for the list view
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawable_planet_list_item, mPlanetTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub

		}

	}

	private void selectItem(int position) {
		// update the main content by replacing fragments
		Fragment fragment=new PlanetFragment();
		Bundle args=new Bundle();
		args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
		fragment.setArguments(args);
		
		//Insert the fragment by replacing any existing fragment
	}

	public static class PlanetFragment extends Fragment {
		public static final String ARG_PLANET_NUMBER = "planet_number";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.drawable_planet_fragment,
					container, false);
			int i = getArguments().getInt(ARG_PLANET_NUMBER);
			String planet = getResources()
					.getStringArray(R.array.planets_array)[i];
			int imageId = getResources().getIdentifier(
					planet.toLowerCase(Locale.getDefault()), "drawable",
					getActivity().getPackageName());
			((ImageView) rootView.findViewById(R.id.image))
					.setImageResource(imageId);
			getActivity().setTitle(planet);
			return rootView;
		}
	}

}
