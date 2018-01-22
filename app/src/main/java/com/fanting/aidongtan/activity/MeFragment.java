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
	private TextView tv_wallet,tv_tuan,tv_fav;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_me, null);
		textView=view.findViewById(R.id.textView);
		textView.setOnClickListener(this);

		tv_wallet=view.findViewById(R.id.tv_wallet);
		tv_wallet.setOnClickListener(this);
		tv_tuan=view.findViewById(R.id.tv_tuan);
		tv_tuan.setOnClickListener(this);
		tv_fav=view.findViewById(R.id.tv_fav);
		tv_fav.setOnClickListener(this);
		return view;
	}


	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.textView:
				startActivity(new Intent(getActivity(),LoginActivity.class));
				break;
		/*	case R.id.tv_health:
				//startActivity(new Intent(getActivity(),HealthDataActivity.class));
				break;*/
			case R.id.tv_wallet:
				startActivity(new Intent(getActivity(),WalletActivity.class));
				break;
			case R.id.tv_tuan:
				startActivity(new Intent(getActivity(),GroupBuyActivity.class));
				break;
			case R.id.tv_fav:
				startActivity(new Intent(getActivity(),MyCollectionActivity.class));
				break;
		}
	}
}
