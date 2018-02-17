package com.porodem.criminalintento;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.List;
import java.util.UUID;

import static android.widget.CompoundButton.*;

/**
 * Created by porod on 10.02.2018.
 */

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";

    private static final int TO_FIRST_CRIME = 0;
    private static final int TO_LAST_CRIME = 1;

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton, mToFirstButton, mToLastButton;
    private CheckBox mSolvedCheckBox;



    //new instance of fragment with arguments (must called from Crime Activity)
    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID,crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID)getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //
            }
        });

        mDateButton = v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);

        mSolvedCheckBox = v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        //exersice 11
        mToLastButton = v.findViewById(R.id.button_last_crime);
        if (!mCrime.getId().equals(edgeCrime(TO_LAST_CRIME).getId())) {
            mToLastButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = CrimePagerActivity.newIntent(getActivity(), edgeCrime(TO_LAST_CRIME).getId());
                    startActivity(intent);
                }
            });
        } else mToLastButton.setEnabled(false);

        mToFirstButton = v.findViewById(R.id.button_first_crime);
        if (!mCrime.getId().equals(edgeCrime(TO_FIRST_CRIME).getId())) {
        mToFirstButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CrimePagerActivity.newIntent(getActivity(), edgeCrime(TO_FIRST_CRIME).getId());
                startActivity(intent);
            }
        });
        } else  mToFirstButton.setEnabled(false);

        return v;
    }

    //for exercise 11
    private Crime edgeCrime(int position) {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        mCrime = position<TO_LAST_CRIME ? crimes.get(0): crimes.get(crimes.size()-1);
        if (position == 0) {
            Crime c = crimes.get(0);
            return c;
        } else  return mCrime;
    }
}
