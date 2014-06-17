package com.kv.facebookshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
    	private UiLifecycleHelper uiHelper;
        public PlaceholderFragment() {
        }
        
        @Override
        public void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	uiHelper = new UiLifecycleHelper(getActivity(), null);
        	uiHelper.onCreate(savedInstanceState);
        }
        
        @Override
        public void onPause() {
        	super.onPause();
        	uiHelper.onPause();
        }
        
        @Override
        public void onResume() {
        	super.onResume();
        	uiHelper.onResume();
        }
        
        @Override
        public void onActivityResult(int requestCode, int resultCode,
        		Intent data) {
        	super.onActivityResult(requestCode, resultCode, data);
        	uiHelper.onActivityResult(requestCode, resultCode, data);
        }
        
        @Override
        public void onSaveInstanceState(Bundle outState) {
        	super.onSaveInstanceState(outState);
        	uiHelper.onSaveInstanceState(outState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            rootView.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(getActivity())
			        .setLink("https://developers.facebook.com/android")
			        .setCaption("Hello")
			        .setName("Testing")
			        .build();
					uiHelper.trackPendingDialogCall(shareDialog.present());
					
				}
			});
            return rootView;
        }
    }

}
