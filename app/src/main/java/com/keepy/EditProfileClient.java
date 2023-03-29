package com.keepy;

import android.annotation.SuppressLint;
<<<<<<< HEAD
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
=======
import android.app.ProgressDialog;
import android.os.Bundle;
>>>>>>> origin/master
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
<<<<<<< HEAD
import com.keepy.dialogs.CalendarActivity;
=======
import com.keepy.dialogs.ScheduleActivity;
>>>>>>> origin/master
import com.keepy.models.ServiceRequest;
import com.keepy.models.User;
import com.keepy.viewmodel.AppViewModel;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import java.util.function.Predicate;
=======
>>>>>>> origin/master

public class EditProfileClient extends AppCompatActivity {
    private CheckBox mIsDogisterBtn;
    private CheckBox mIsHouseKeeperBtn;
    private CheckBox mIsTherapistBtn;
    private CheckBox mIsBabysitterBtn;
    private CheckBox mIsBabysitsdisabilitiesBtn;

    ArrayAdapter<String> adapterItems;


    private boolean mIsDogister = false;
    private boolean mIsHouseKeeper = false;
    private boolean mIsTherapist = false;
    private boolean mIsBabysitter = false;
    private boolean mIsBabysitsdisabilities = false;

    private AppViewModel viewModel;

    Spinner location_Edit;
<<<<<<< HEAD
    TextInputEditText nameTv, phoneTv;
=======
    TextInputEditText nameTv, phoneTv, aboutTv;
>>>>>>> origin/master


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_client);
        viewModel = new ViewModelProvider(this).get(AppViewModel.class);

        location_Edit = findViewById(R.id.locationEdit_Client);
        nameTv = findViewById(R.id.FullNameEdit_Client);
        phoneTv = findViewById(R.id.PhoneEdit_Client);
<<<<<<< HEAD

=======
        aboutTv = findViewById(R.id.AboutMeEdit_Client);
>>>>>>> origin/master
        findViewById(R.id.logoutBtn).setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
        });
        findViewById(R.id.profileBtn).setOnClickListener(v -> {
        });
        findViewById(R.id.homeBtn).setOnClickListener(v -> {
            finish();
        });

        findViewById(R.id.scheduleBtn).setOnClickListener(v -> {
            openSchedule();
        });


        mIsDogisterBtn = findViewById(R.id.Dogister_ClientBtn);
        mIsHouseKeeperBtn = findViewById(R.id.HouseKeeper_Client_Btn);
        mIsTherapistBtn = findViewById(R.id.Therapist_Client_Btn);
        mIsBabysitterBtn = findViewById(R.id.Babysitter_ClientBtn);
        mIsBabysitsdisabilitiesBtn = findViewById(R.id.Babysitsdisabilities_Client_Btn);

<<<<<<< HEAD

        ProgressDialog dialog = new ProgressDialog(this);

        Button saveChangesBtn = findViewById(R.id.saveChanges_Client);
        saveChangesBtn.setEnabled(false);
=======
        ProgressDialog dialog = new ProgressDialog(this);

        Button saveChangesBtn = findViewById(R.id.saveChanges_Client);
>>>>>>> origin/master
        saveChangesBtn.setOnClickListener(v -> {
            saveChanges();
        });
        viewModel.loadingLivaData.observe(this, isLoading -> {
            if (isLoading != null) {
<<<<<<< HEAD
                dialog.setTitle(Constants.AppName);
=======
                dialog.setTitle("Keepy");
>>>>>>> origin/master
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
<<<<<<< HEAD
=======
            aboutTv.setText(user.getmAboutMe());
>>>>>>> origin/master

            handleClientPrefs(user);
        });

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, Constants.places);
        location_Edit.setAdapter(adapterItems);


        mIsDogisterBtn.setOnClickListener(v -> {
<<<<<<< HEAD
            saveChangesBtn.setEnabled(true);
=======
>>>>>>> origin/master
            mIsDogister = !mIsDogister;
        });

        mIsHouseKeeperBtn.setOnClickListener(v -> {
<<<<<<< HEAD
            saveChangesBtn.setEnabled(true);
=======
>>>>>>> origin/master
            mIsHouseKeeper = !mIsHouseKeeper;
        });

        mIsTherapistBtn.setOnClickListener(v -> {
            mIsTherapist = !mIsTherapist;
<<<<<<< HEAD
           saveChangesBtn.setEnabled(true);

        });
        mIsBabysitterBtn.setOnClickListener(v -> {
            mIsBabysitter = !mIsBabysitter;
            saveChangesBtn.setEnabled(true);
=======
        });
        mIsBabysitterBtn.setOnClickListener(v -> {
            mIsBabysitter = !mIsBabysitter;
>>>>>>> origin/master
        });

        mIsBabysitsdisabilitiesBtn.setOnClickListener(v -> {
            mIsBabysitsdisabilities = !mIsBabysitsdisabilities;
<<<<<<< HEAD
            saveChangesBtn.setEnabled(true);
        });

        nameTv.addTextChangedListener(MainActivity.getProfileTextWatcher(this, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return viewModel.userLiveData.getValue()!= null &&
                        s.equals(viewModel.userLiveData.getValue().getmFullName());
            }
        }));
        phoneTv.addTextChangedListener(MainActivity.getProfileTextWatcher(this, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return viewModel.userLiveData.getValue()!= null &&
                        s.equals(viewModel.userLiveData.getValue().getmPhone());
            }
        }));
        location_Edit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(viewModel.userLiveData.getValue()!=null &&location_Edit.getSelectedItem().toString().equals(viewModel.userLiveData.getValue().getmLocation()))
                    return;
                findViewById(R.id.saveChanges_Client).setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
=======
        });

>>>>>>> origin/master

    }


    public void openSchedule() {
        User user = viewModel.userLiveData.getValue();
        List<ServiceRequest> incomingRequests = viewModel.incomingRequests.getValue();
        List<ServiceRequest> outgoingRequests = viewModel.outgoingRequests.getValue();
        if (user == null || (incomingRequests == null && outgoingRequests == null)) {
            Snackbar.make(findViewById(R.id.mainLayout), "You need to be logged in to use these features", Snackbar.LENGTH_LONG).show();
            return;
        }
<<<<<<< HEAD
        CalendarActivity calendarDialog = new CalendarActivity(
                this,
                viewModel,
                true,
                outgoingRequests,
                null);
        calendarDialog.show();

    }



    private void showToastPhoneError() {
        Snackbar.make(findViewById(R.id.parentLayoutEditClient), "Phone number must be israeli number, 10 digits long!", Snackbar.LENGTH_LONG).show();
    }

    /*
     * This method is used to validate the phone number
     * the phone number must be israeli number, 10 digits long
     */
    private boolean isValidPhone(String phone) {
        if (!Constants
                .Utils
                .isIsraeliPhoneNumber(phone)
        ) {
            showToastPhoneError();
            return false;
        }
        return true;
=======
        ScheduleActivity dialog;
        if (outgoingRequests != null && outgoingRequests.size() > 0) {
            dialog = new ScheduleActivity(this, outgoingRequests, true);
            dialog.show();
        }
>>>>>>> origin/master
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
<<<<<<< HEAD

        boolean hasPhoneTv = phoneTv.getText() != null && !TextUtils.isEmpty(phoneTv.getText().toString());
        if (hasPhoneTv) {
            if (!isValidPhone(phoneTv.getText().toString()))
                return;
=======
        if (phoneTv.getText() != null) {
>>>>>>> origin/master
            String phone = phoneTv.getText().toString();
            old.setmPhone(phone);
        }
        String location = Constants.places[location_Edit.getSelectedItemPosition()];
        old.setmLocation(location);
<<<<<<< HEAD
        List<String> clientPrefs = new ArrayList<>();

        if (mIsDogister)
            clientPrefs.add(Constants.DogisterType);
        if (mIsHouseKeeper)
            clientPrefs.add(Constants.HouseKeeperType);
        if (mIsTherapist)
            clientPrefs.add(Constants.TherapistType);
        if (mIsBabysitter)
            clientPrefs.add(Constants.BabySitterType);
        if (mIsBabysitsdisabilities)
            clientPrefs.add(Constants.BabySitterDisabilitiesType);
        old.setClientPrefs(clientPrefs);
        viewModel.saveUser(old);
        Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show();
=======
        if (aboutTv.getText() != null) {
            String about = aboutTv.getText().toString();
            old.setmAboutMe(about);
        }

        List<String> clientPrefs = new ArrayList<>();

        if (mIsDogister) {
            clientPrefs.add("dogister");
        }
        if (mIsHouseKeeper) {
            clientPrefs.add("housekeeper");
        }
        if (mIsTherapist) {
            clientPrefs.add("therapist");
        }
        if (mIsBabysitter) {
            clientPrefs.add("babysitter");
        }
        if (mIsBabysitsdisabilities) {
            clientPrefs.add("babysitter_disabilities");
        }
        old.setClientPrefs(clientPrefs);

        viewModel.saveUser(old);
>>>>>>> origin/master
    }

    private void handleClientPrefs(User user) {
        if (user.getClientPrefs() != null && !user.getClientPrefs().isEmpty()) {

<<<<<<< HEAD
            if (user.getClientPrefs().contains(Constants.DogisterType)) {
=======
            if (user.getClientPrefs().contains("dogister")) {
>>>>>>> origin/master
                mIsDogister = true;
                mIsDogisterBtn.setChecked(true);
            }

<<<<<<< HEAD
            if (user.getClientPrefs().contains(Constants.BabySitterType)) {
=======
            if (user.getClientPrefs().contains("babysitter")) {
>>>>>>> origin/master
                mIsBabysitter = true;
                mIsBabysitterBtn.setChecked(true);
            }

<<<<<<< HEAD
            if (user.getClientPrefs().contains(Constants.BabySitterDisabilitiesType)) {
=======
            if (user.getClientPrefs().contains("babysitter_disabilities")) {
>>>>>>> origin/master
                mIsBabysitsdisabilities = true;
                mIsBabysitsdisabilitiesBtn.setChecked(true);
            }

<<<<<<< HEAD
            if (user.getClientPrefs().contains(Constants.HouseKeeperType)) {
=======
            if (user.getClientPrefs().contains("housekeeper")) {
>>>>>>> origin/master
                mIsHouseKeeper = true;
                mIsHouseKeeperBtn.setChecked(true);
            }

<<<<<<< HEAD
            if (user.getClientPrefs().contains(Constants.TherapistType)) {
=======
            if (user.getClientPrefs().contains("therapist")) {
>>>>>>> origin/master
                mIsTherapist = true;
                mIsTherapistBtn.setChecked(true);
            }

        }
    }
}