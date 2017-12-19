package com.fanting.aidongtan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanting.aidongtan.R;

public class MeFragment extends Fragment implements View.OnClickListener {

	private TextView textView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_me, null);
		textView=view.findViewById(R.id.textView);
		textView.setOnClickListener(this);
		return view;
	}


	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.textView:
				startActivity(new Intent(getActivity(),LoginActivity.class));
				break;
		}
	}
}
