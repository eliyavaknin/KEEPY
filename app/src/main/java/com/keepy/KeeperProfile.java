package com.keepy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.keepy.behaviour.IKeeperProfile;
import com.keepy.models.ServiceRequest;
import com.keepy.models.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;




public class KeeperProfile extends AlertDialog {
    private String removeLastIfNonAlpha(String str) {
        if (!Character.isAlphabetic(str.charAt(str.length() - 1)))
            return str.length() <= 1 ? str : str.substring(0, str.length() - 2);
        return str;
    }

    private final User client;
    private final IKeeperProfile iKeeper;

    public KeeperProfile(@NonNull Context context,
                         User client,
                         User keeper,
                         List<ServiceRequest> existingRequestsList,
                         IKeeperProfile iKeeper) {

        super(context);
        this.iKeeper = iKeeper;
        this.client = client;
        View v = getLayoutInflater().inflate(R.layout.keeper_profile, null);
        setView(v);
        setButton(BUTTON_POSITIVE, "Close", (dialog, which) -> {
            dismiss();
        });

        TextView name = v.findViewById(R.id.keeper_profile_name);
        TextView serviceTypesTv = v.findViewById(R.id.keeper_profile_services);
        TextView location = v.findViewById(R.id.keeper_profile_locations_of_operation);
        RatingBar rating = v.findViewById(R.id.keeper_profile_rating);


        Button sendRequestButton = v.findViewById(R.id.keeper_profile_send_request);
        TextView already_rated = v.findViewById(R.id.already_rated);
        RatingBar rateMe = v.findViewById(R.id.keeper_profile_submit_rating);
        name.setText(keeper.getmFullName().isEmpty() ? "Annonymous" : keeper.getmFullName());
        Collection<String> serviceTypes = keeper.getmKeeperData().getFees().keySet();
        String types = "";
        for (String type : serviceTypes) {
            types += type + ", ";
        }
        types = removeLastIfNonAlpha(types);

        serviceTypesTv.setText(types);

        location.setText(keeper.getmLocation());
        rating.setRating(keeper.getmKeeperData().getRating());
        if (client.getRatedKeepers().contains(keeper.getmEmail())) {
            already_rated.setVisibility(View.VISIBLE);
            rateMe.setVisibility(View.GONE);
        }
        rateMe.setOnRatingBarChangeListener((ratingBar, v1, b) -> {
            iKeeper.rateMe(client, keeper, v1);
            rateMe.setVisibility(View.GONE);
            already_rated.setVisibility(View.VISIBLE);
            rating.setRating(
                    (rating.getRating() * client.getRatedKeepers().size() + v1) /
                            (client.getRatedKeepers().size() + 1)
            );
            Toast.makeText(context,
                    "Thank you for rating " + keeper.getmFullName() + "!",
                    Toast.LENGTH_SHORT).show();
        });

        for (ServiceRequest sr : existingRequestsList)
            if (sr.getKeeperEmail().equals(keeper.getmEmail())) {
                sendRequestButton.setEnabled(false);
                sendRequestButton.setText("Request sent");
                return;
            }
        sendRequestButton.setOnClickListener(v1 -> {
            sendRequest(context, sendRequestButton, client, keeper);
        });
    }


    private void sendRequest(Context context,
                             Button requestButton,
                             User client,
                             User keeper) {
        ServiceRequest sr = new ServiceRequest();
        sr.setClientId(client.getmEmail());
        sr.setKeeperId(keeper.getmUserID());
        sr.setLocation(client.getmLocation());
        sr.setDate(System.currentTimeMillis());
        sr.setClientEmail(client.getmEmail());
        sr.setKeeperEmail(keeper.getmEmail());
        sr.setClientName(client.getmFullName());
        sr.setKeeperName(keeper.getmFullName());
        sr.setStatus(ServiceRequest.Status.WAITING);
        String type = "";
        Set<String> keys = keeper.getmKeeperData().getFees().keySet();
        int i = 0;

        // if the client has preferences
        // then the type of the request
        // will be the first preference
        if (client.getClientPrefs() != null) {
            for (String clientPref : client.getClientPrefs()) {
                if (keys.contains(clientPref)) {
                    type += clientPref;
                    if (i != keys.size() - 1)
                        type += ", ";
                }
                i++;
            }
            sr.setType(
                    type.equals("") ?
                            keys.toArray()[0].toString() :
                            type
            );
        } else {
            sr.setType(keys.toArray()[0].toString());
        }
        openDialogAddCommentToRequest(context, sr, keeper, requestButton);
    }

    // method used to open dialog for adding comment to request
    // and submit send request to keeper
    public void openDialogAddCommentToRequest(
            Context context,
            ServiceRequest serviceRequest,
            User keeper,
            Button requestButton) {
        AddCommentToRequestDialog addCommentToRequestDialog = new AddCommentToRequestDialog(
                context,
                serviceRequest,
                keeper,
                requestButton);
        addCommentToRequestDialog.show();
    }


    class AddCommentToRequestDialog extends android.app.AlertDialog {
        private ServiceRequest serviceRequest;
        private Button requestButton;
        private User keeper;
        private final TextView et;

        public AddCommentToRequestDialog(
                Context context,
                ServiceRequest serviceRequest,
                User keeper,
                Button requestButton
        ) {
            super(context);
            AddCommentToRequestDialog.this.setTitle("Keepy");
            AddCommentToRequestDialog.this.setMessage("Leave blank if you don't want to add a comment");
            LinearLayout ll = new LinearLayout(context);
            ll.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            ll.setOrientation(LinearLayout.VERTICAL);
            et = new EditText(context);
            et.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            int padding = 4;
            ll.setPadding(padding, padding, padding, padding);
            et.setHint("Enter comment here");
            ll.addView(et);
            AddCommentToRequestDialog.this.setView(ll);
            AddCommentToRequestDialog.this.setButton(BUTTON_POSITIVE, "Send", (dialog, which) -> {
                onCommentAdded(et.getText().toString());
            });
            AddCommentToRequestDialog.this.setButton(BUTTON_NEGATIVE, "Cancel", (dialog, which) -> {
                AddCommentToRequestDialog.this.dismiss();
            });
            this.serviceRequest = serviceRequest;
            this.requestButton = requestButton;
            this.keeper = keeper;
        }

        public void onCommentAdded(
                String comment) {
            if (comment.length() > 0) // if comment was added
                serviceRequest.setClientComment(comment);
            serviceRequest.setStatus(ServiceRequest.Status.WAITING);
            iKeeper.sendRequest(client, keeper, serviceRequest);
            requestButton.setEnabled(false);
            requestButton.setText("Request sent");

            Toast.makeText(
                    requestButton.getContext(),
                    "Request sent successfully!",
                    Toast.LENGTH_SHORT
            ).show();

            // clean here to avoid memory leaks
            // in nested classes
            requestButton = null;
            keeper = null;
            serviceRequest = null;
            AddCommentToRequestDialog.this.dismiss();
            KeeperProfile.this.dismiss();

            iKeeper.close();
        }
    }


}
