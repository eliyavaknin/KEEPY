package com.keepy;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.keepy.dialogs.ScheduleActivity;
import com.keepy.models.ServiceRequest;
import com.keepy.models.User;
import com.keepy.viewmodel.AppViewModel;

import java.util.HashMap;
import java.util.List;

public class EditProfileKeepy extends AppCompatActivity {
    private Button mIsDogisterBtn;
    private Button mIsHouseKeeperBtn;
    private Button mIsTherapistBtn;
    private Button mIsBabysitterBtn;
    private Button mIsBabysitsdisabilitiesBtn;

    ArrayAdapter<String> adapterItems;


    private boolean mIsDogister = false;
    private boolean mIsHouseKeeper = false;
    private boolean mIsTherapist = false;
    private boolean mIsBabysitter = false;
    private boolean mIsBabysitsdisabilities = false;


    private AppViewModel viewModel;

    Spinner location_Edit;
    TextInputEditText nameTv, phoneTv, aboutTv;

    EditText dogisterPrice_et, therapistPrice_et,
            babysitterPrice_et, babysitterDisabilitiesPrice_et,
            houseKeeperPrice_et;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_keepy);
        viewModel = new ViewModelProvider(this).get(AppViewModel.class);

        location_Edit = findViewById(R.id.locationEdit_Keeper);
        nameTv = findViewById(R.id.FullNameEdit_Keeper);
        phoneTv = findViewById(R.id.PhoneEdit_Keeper);
        aboutTv = findViewById(R.id.AboutMeEdit_Keeper);
        findViewById(R.id.homeBtn).setOnClickListener(v -> {
            finish();
        });
        findViewById(R.id.scheduleBtn).setOnClickListener(v -> {
            openSchedule();
        });
        dogisterPrice_et = findViewById(R.id.dogisterPrice_et);
        therapistPrice_et = findViewById(R.id.therapistKeeperPrice_et);
        babysitterPrice_et = findViewById(R.id.babysitterPrice_et);
        babysitterDisabilitiesPrice_et = findViewById(R.id.babysitterDisabilitiesPrice_et);
        houseKeeperPrice_et = findViewById(R.id.houseKeeperPrice_et);

        mIsDogisterBtn = findViewById(R.id.Dogister_KeeperBtn);
        mIsHouseKeeperBtn = findViewById(R.id.HouseKeeper_Keeper_Btn);
        mIsTherapistBtn = findViewById(R.id.Therapist_Keeper_Btn);
        mIsBabysitterBtn = findViewById(R.id.Babysitter_KeeperBtn);
        mIsBabysitsdisabilitiesBtn = findViewById(R.id.Babysitsdisabilities_Keeper_Btn);

        ProgressDialog dialog = new ProgressDialog(this);

        Button saveChangesBtn = findViewById(R.id.saveChanges_Keeper);
        saveChangesBtn.setOnClickListener(v -> {
            saveChanges();
        });
        viewModel.loadingLivaData.observe(this, isLoading -> {
            if (isLoading != null) {
                dialog.setTitle("Keepy");
                dialog.setMessage(isLoading);
                dialog.show();
                return;
            }
            dialog.dismiss();
        });
        viewModel.userLiveData.observe(this, user -> {
            nameTv.setText(user.getmFullName());
            phoneTv.setText(user.getmPhone());
            for (int i = 0; i < Constants.places.length; i++) {
                if (Constants.places[i].equals(user.getmLocation())) {
                    location_Edit.setSelection(i, true);
                    break;
                }
            }
            aboutTv.setText(user.getmAboutMe());

            handleKeeperFees(user);
        });

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, Constants.places);
        location_Edit.setAdapter(adapterItems);


        mIsDogisterBtn.setOnClickListener(v -> {
            mIsDogister = !mIsDogister;
            if (mIsDogister) {
                mIsDogisterBtn.setBackgroundColor(Constants.ColorSelected);
                mIsDogisterBtn.setTextColor(Color.WHITE);
                return;
            }
            mIsDogisterBtn.setTextColor(Color.BLACK);
            mIsDogisterBtn.setBackgroundColor(Constants.ColorUnselected);
        });

        mIsHouseKeeperBtn.setOnClickListener(v -> {
            mIsHouseKeeper = !mIsHouseKeeper;
            if (mIsHouseKeeper) {
                mIsHouseKeeperBtn.setBackgroundColor(Constants.ColorSelected);
                mIsHouseKeeperBtn.setTextColor(Color.WHITE);
                return;
            }
            mIsHouseKeeperBtn.setTextColor(Color.BLACK);
            mIsHouseKeeperBtn.setBackgroundColor(Constants.ColorUnselected);
        });

        mIsTherapistBtn.setOnClickListener(v -> {
            mIsTherapist = !mIsTherapist;
            if (mIsTherapist) {
                mIsTherapistBtn.setBackgroundColor(Constants.ColorSelected);
                mIsTherapistBtn.setTextColor(Color.WHITE);
                return;
            }
            mIsTherapistBtn.setTextColor(Color.BLACK);
            mIsTherapistBtn.setBackgroundColor(Constants.ColorUnselected);
        });
        mIsBabysitterBtn.setOnClickListener(v -> {
            mIsBabysitter = !mIsBabysitter;
            if (mIsBabysitter) {
                mIsBabysitterBtn.setBackgroundColor(Constants.ColorSelected);
                mIsBabysitterBtn.setTextColor(Color.WHITE);
                return;
            }
            mIsBabysitterBtn.setTextColor(Color.BLACK);
            mIsBabysitterBtn.setBackgroundColor(Constants.ColorUnselected);
        });

        mIsBabysitsdisabilitiesBtn.setOnClickListener(v -> {
            mIsBabysitsdisabilities = !mIsBabysitsdisabilities;
            if (mIsBabysitsdisabilities) {
                mIsBabysitsdisabilitiesBtn.setTextColor(Color.WHITE);
                mIsBabysitsdisabilitiesBtn.setBackgroundColor(Constants.ColorSelected);
                return;
            }
            mIsBabysitsdisabilitiesBtn.setTextColor(Color.BLACK);
            mIsBabysitsdisabilitiesBtn.setBackgroundColor(Constants.ColorUnselected);
        });


    }

    public void openSchedule() {
        User user = viewModel.userLiveData.getValue();
        List<ServiceRequest> incomingRequests = viewModel.incomingRequests.getValue();
        List<ServiceRequest> outgoingRequests = viewModel.outgoingRequests.getValue();
        if (user == null || (incomingRequests == null && outgoingRequests == null)) {
            Snackbar.make(findViewById(R.id.mainLayout), "You need to be logged in to use these features", Snackbar.LENGTH_LONG).show();
            return;
        }

        ScheduleActivity dialog;
        if (incomingRequests != null && incomingRequests.size() > 0) {
            dialog = new ScheduleActivity(EditProfileKeepy.this, incomingRequests, false);
            dialog.show();
        }

    }

    private void saveChanges() {
        User old = viewModel.userLiveData.getValue();
        if (old == null) {
            Toast.makeText(this, "Some unknown error has occured, please try again later", Toast.LENGTH_LONG).show();
            return;
        }
        if (nameTv.getText() != null) {
            String name = nameTv.getText().toString();
            old.setmFullName(name);
        }
        if (phoneTv.getText() != null) {
            String phone = phoneTv.getText().toString();
            old.setmPhone(phone);
        }
        String location = Constants.places[location_Edit.getSelectedItemPosition()];
        old.setmLocation(location);
        if (aboutTv.getText() != null) {
            String about = aboutTv.getText().toString();
            old.setmAboutMe(about);
        }

        String dogisterPrice = dogisterPrice_et.getText().toString();
        String babySitterPrice = babysitterPrice_et.getText().toString();
        String babySitterDisabilitiesPrice = babysitterDisabilitiesPrice_et.getText().toString();
        String houseKeeperPrice = houseKeeperPrice_et.getText().toString();
        String therapistPrice = therapistPrice_et.getText().toString();

        HashMap<String, Integer> fees = new HashMap<>();

        if (mIsDogister) {
            try {
                Integer newDogisterPrice = Integer.parseInt(dogisterPrice);
                fees.put("dogister", newDogisterPrice);
            } catch (Exception ignored) {
            }
        }
        if (mIsBabysitter) {
            try {
                Integer newBabySitterPrice = Integer.parseInt(babySitterPrice);
                fees.put("babysitter", newBabySitterPrice);
            } catch (Exception ignored) {
            }
        }
        if (mIsBabysitsdisabilities) {
            try {
                Integer newBabySitterDisabilitiesPrice = Integer.parseInt(babySitterDisabilitiesPrice);
                fees.put("babysitter_disabilities", newBabySitterDisabilitiesPrice);
            } catch (Exception ignored) {
            }
        }
        if (mIsHouseKeeper) {
            try {
                Integer newHouseKeeperPrice = Integer.parseInt(houseKeeperPrice);
                fees.put("housekeeper", newHouseKeeperPrice);
            } catch (Exception ignored) {
            }
        }
        if (mIsTherapist) {
            try {
                Integer newTherapistPrice = Integer.parseInt(therapistPrice);
                fees.put("therapist", newTherapistPrice);
            } catch (Exception ignored) {
            }
        }

        KeeperData data = new KeeperData();
        data.setFees(fees);
        if (old.getmKeeperData() != null)
            data.setRating(old.getmKeeperData().getRating());
        old.setmKeeperData(data);
        viewModel.saveUser(old);
    }

    private void handleKeeperFees(User user) {
        if (user.getmKeeperData() != null
                && user.getmKeeperData().getFees() != null) {

            HashMap<String, Integer> fees = user.getmKeeperData().getFees();

            if (fees.containsKey("dogister")) {
                Integer dogisterFee = fees.get("dogister");
                dogisterPrice_et.setText(String.valueOf(dogisterFee));
                mIsDogister = true;
                mIsDogisterBtn.setBackgroundColor(Constants.ColorSelected);
                mIsDogisterBtn.setTextColor(Color.WHITE);
            }
            if (fees.containsKey("babysitter")) {
                Integer babysitterFee = fees.get("babysitter");
                babysitterPrice_et.setText(String.valueOf(babysitterFee));
                mIsBabysitter = true;
                mIsBabysitterBtn.setBackgroundColor(Constants.ColorSelected);
                mIsBabysitterBtn.setTextColor(Color.WHITE);
            }
            if (fees.containsKey("babysitter_disabilities")) {
                Integer babysitterDisabilitiesFee = fees.get("babysitter_disabilities");
                babysitterDisabilitiesPrice_et.setText(String.valueOf(babysitterDisabilitiesFee));
                mIsBabysitsdisabilities = true;
                mIsBabysitsdisabilitiesBtn.setBackgroundColor(Constants.ColorSelected);
                mIsBabysitsdisabilitiesBtn.setTextColor(Color.WHITE);
            }
            if (fees.containsKey("housekeeper")) {
                Integer houseKeeperFees = fees.get("housekeeper");
                houseKeeperPrice_et.setText(String.valueOf(houseKeeperFees));
                mIsHouseKeeper = true;
                mIsHouseKeeperBtn.setBackgroundColor(Constants.ColorSelected);
                mIsHouseKeeperBtn.setTextColor(Color.WHITE);
            }
            if (fees.containsKey("therapist")) {
                Integer therapistFees = fees.get("therapist");
                therapistPrice_et.setText(String.valueOf(therapistFees));
                mIsTherapist = true;
                mIsTherapistBtn.setBackgroundColor(Constants.ColorSelected);
                mIsTherapistBtn.setTextColor(Color.WHITE);
            }
        }
    }
}