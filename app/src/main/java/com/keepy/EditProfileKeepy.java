package com.keepy;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileKeepy extends AppCompatActivity {
    private Button mIsDogisterBtn;
    private Button mIsHouseKeeperBtn;
    private Button mIsTherapistBtn;
    private Button mIsBabysitterBtn;
    private Button mIsBabysitsdisabilitiesBtn;
    String[] items = {"Tel aviv" , "Jerusalem"  , "Ramat gan","Givatayim" , "Bnei brak" , "Haifa"  , "Ashdod","Ashkelon" ,
            "Ramat hasharon","Rishone lezion" ,"Bat yam" ,"Holon" , "Netanya" ,"Raanana" , "Hadera" , "Binyamina" ,"Zichron"
    ,"Rehovot" , "Nes ziona" , "Eilat" , "Petah tiqwa" , "Beer sheva" , "Afula" , "Akko" , "Arad" , "Beit shemesh" , "Elad"
    ,"Herzliya" , "Kfar sava" , "Lod" , "Ramla" ,"Netivot" , "Nof hagalil" , "Or yehuda" , "Yehud" , "Yavne"};

    AutoCompleteTextView location_Edit;
    ArrayAdapter <String> adapterItems;


    private boolean mIsDogister = false;
    private boolean mIsHouseKeeper = false;
    private boolean mIsTherapist = false;
    private boolean mIsBabysitter = false;
    private boolean mIsBabysitsdisabilities = false;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_keepy);

        location_Edit = findViewById(R.id.locationEdit);


        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        location_Edit.setAdapter(adapterItems);

        location_Edit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext() , "item " +item , Toast.LENGTH_LONG).show();
            }
        });
        mIsDogisterBtn = findViewById(R.id.Dogister);
        mIsDogisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsDogister = !mIsDogister;
                if (mIsDogister) {
                    mIsDogisterBtn.setBackground(getDrawable(R.drawable.blue_button_background));
                    return;
                }
                mIsDogisterBtn.setBackground(getDrawable(R.drawable.grey_button_background));
            }
        });

        mIsHouseKeeperBtn = findViewById(R.id.HouseKeeper);
        mIsHouseKeeperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsHouseKeeper = !mIsHouseKeeper;
                if (mIsHouseKeeper) {
                    mIsHouseKeeperBtn.setBackground(getDrawable(R.drawable.blue_button_background));
                    return;
                }
                mIsHouseKeeperBtn.setBackground(getDrawable(R.drawable.grey_button_background));
            }
        });

        mIsTherapistBtn = findViewById(R.id.Therapist);
        mIsTherapistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsTherapist = !mIsTherapist;
                if (mIsTherapist) {
                    mIsTherapistBtn.setBackground(getDrawable(R.drawable.blue_button_background));
                    return;
                }
                mIsTherapistBtn.setBackground(getDrawable(R.drawable.grey_button_background));
            }
        });

        mIsBabysitterBtn = findViewById(R.id.Babysitter);
        mIsBabysitterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsBabysitter = !mIsBabysitter;
                if (mIsBabysitter) {
                    mIsBabysitterBtn.setBackground(getDrawable(R.drawable.blue_button_background));
                    return;
                }
                mIsBabysitterBtn.setBackground(getDrawable(R.drawable.grey_button_background));
            }
        });

        mIsBabysitsdisabilitiesBtn = findViewById(R.id.Babysitsdisabilities);
        mIsBabysitsdisabilitiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsBabysitsdisabilities = !mIsBabysitsdisabilities;
                if (mIsBabysitsdisabilities) {
                    mIsBabysitsdisabilitiesBtn.setBackground(getDrawable(R.drawable.blue_button_background));
                    return;
                }
                mIsBabysitsdisabilitiesBtn.setBackground(getDrawable(R.drawable.grey_button_background));
            }
        });

    }
}