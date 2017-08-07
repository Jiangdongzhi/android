package com.dorian.dorianns;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubmitFragment extends Fragment implements View.OnClickListener {

    public SubmitFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_submit, container, false);
        Button submitButton = (Button) layout.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onClick(View v) {
        View view = getView(); // In Fragment class, need to use getView() to get the layout for searching components.
        if (view != null) {
            TextView tv = (TextView) view.findViewById(R.id.textView);
            tv.setText("Button submitted");
        }
    }
}
