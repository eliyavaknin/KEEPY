package com.keepy;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
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
import com.keepy.dialogs.ScheduleActivity;
import com.keepy.models.ServiceRequest;
import com.keepy.models.User;
import com.keepy.viewmodel.AppViewModel;

import java.util.ArrayList;
import java.util.List;

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
    TextInputEditText nameTv, phoneTv, aboutTv;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_client);
        viewModel = new ViewModelProvider(this).get(AppViewModel.class);

        location_Edit = findViewById(R.id.locationEdit_Client);
        nameTv = findViewById(R.id.FullNameEdit_Client);
        phoneTv = findViewById(R.id.PhoneEdit_Client);
        aboutTv = findViewById(R.id.AboutMeEdit_Client);
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

        ProgressDialog dialog = new ProgressDialog(this);

        Button saveChangesBtn = findViewById(R.id.saveChanges_Client);
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

            handleClientPrefs(user);
        });

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, Constants.places);
        location_Edit.setAdapter(adapterItems);


        mIsDogisterBtn.setOnClickListener(v -> {
            mIsDogister = !mIsDogister;
        });

        mIsHouseKeeperBtn.setOnClickListener(v -> {
            mIsHouseKeeper = !mIsHouseKeeper;
        });

        mIsTherapistBtn.setOnClickListener(v -> {
            mIsTherapist = !mIsTherapist;
        });
        mIsBabysitterBtn.setOnClickListener(v -> {
            mIsBabysitter = !mIsBabysitter;
        });

        mIsBabysitsdisabilitiesBtn.setOnClickListener(v -> {
            mIsBabysitsdisabilities = !mIsBabysitsdisabilities;
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
        if (outgoingRequests != null && outgoingRequests.size() > 0) {
            dialog = new ScheduleActivity(this, outgoingRequests, true);
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
    }

    private void handleClientPrefs(User user) {
        if (user.getClientPrefs() != null && !user.getClientPrefs().isEmpty()) {

            if (user.getClientPrefs().contains("dogister")) {
                mIsDogister = true;
                mIsDogisterBtn.setChecked(true);
            }

            if (user.getClientPrefs().contains("babysitter")) {
                mIsBabysitter = true;
                mIsBabysitterBtn.setChecked(true);
            }

            if (user.getClientPrefs().contains("babysitter_disabilities")) {
                mIsBabysitsdisabilities = true;
                mIsBabysitsdisabilitiesBtn.setChecked(true);
            }

            if (user.getClientPrefs().contains("housekeeper")) {
                mIsHouseKeeper = true;
                mIsHouseKeeperBtn.setChecked(true);
            }

            if (user.getClientPrefs().contains("therapist")) {
                mIsTherapist = true;
                mIsTherapistBtn.setChecked(true);
            }

        }
    }
}