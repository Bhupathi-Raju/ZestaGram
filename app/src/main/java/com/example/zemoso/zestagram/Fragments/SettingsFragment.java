package com.example.zemoso.zestagram.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.zemoso.zestagram.Interfaces.setObjectInterface;
import com.example.zemoso.zestagram.R;
import com.example.zemoso.zestagram.ThemeSelector;

import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements setObjectInterface,CompoundButton.OnCheckedChangeListener{

    private ThemeSelector selector;
    private CheckBox theme1,theme2,theme3,selected;
    private Button save;
    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         theme1 = view.findViewById(R.id.theme_1);
         theme2 = view.findViewById(R.id.theme_2);
         theme3 = view.findViewById(R.id.theme_3);
         save   = view.findViewById(R.id.save);
    }

    @Override
    public void onResume() {
        super.onResume();
        theme1.setOnCheckedChangeListener(this);
        theme2.setOnCheckedChangeListener(this);
        theme3.setOnCheckedChangeListener(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  if(selected == theme1)
                      selector.setColor(1);
                  else if(selected == theme2)
                      selector.setColor(2);
                  else if(selected ==theme3)
                      selector.setColor(3);
                  getFragmentManager().popBackStack("home",0);
            }
        });
    }

    public void setObject(ThemeSelector selector)
    {
        this.selector=selector;
        Log.d("bind",String.valueOf(selector.getColor()));
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
       if(compoundButton.isChecked())
       {
           if(selected!=null)
               selected.setChecked(false);
           selected = (CheckBox)compoundButton;
       }
    }

}
