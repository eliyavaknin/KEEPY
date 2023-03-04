package com.keepy.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.keepy.Constants;
import com.keepy.R;
import com.keepy.behaviour.IRequests;
import com.keepy.models.ServiceRequest;

import java.util.List;



/*
 * This class is an adapter for the schedule recycler view
 * We adapt the RequestsRvAdapter to show the schedule of the client or the keeper
 */

public class ScheduleRvAdapter extends RequestsRvAdapter {
    private boolean clientView;

    public ScheduleRvAdapter(List<ServiceRequest> requestList, boolean clientView) {
        super(requestList, null, false, true);
        this.clientView = clientView;
    }


    @NonNull
    @Override
    public RequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // if the user is a client -> use the client view layout
        if (clientView)
            return new ScheduleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item_client, parent, false));
        else // if the user is a keeper -> use the keeper view layout
            return new ScheduleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item_keeper, parent, false));
    }


    // this class is a view holder for the schedule recycler view
    // we do not make it static because we need to access the clientView variable
    // so we need to make sure that the ScheduleRvAdapter is created before this class
    class ScheduleViewHolder extends RequestsViewHolder {

        TextView name, type, date, location,comment;



        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);

            // if the user is a client -> use the client view layout
            if (clientView) {
                name = itemView.findViewById(R.id.client_name_schedule_item_client);
                type = itemView.findViewById(R.id.service_type_schedule_item_client);
                date = itemView.findViewById(R.id.service_date_schedule_item_client);
            } else { // if the user is a keeper -> use the keeper view layout
                location = itemView.findViewById(R.id.client_location_schedule_item_keeper);
                name = itemView.findViewById(R.id.client_name_schedule_item_keeper);
                type = itemView.findViewById(R.id.service_type_schedule_item_keeper);
                date = itemView.findViewById(R.id.service_date_schedule_item_keeper);
                comment = itemView.findViewById(R.id.client_comment_schedule_item_keeper);
            }
        }

        private String removeLastIfNonAlpha(String str) {
            if (!Character.isAlphabetic(str.charAt(str.length() - 1)))
                return str.length() <= 1 ? str: str.substring(0, str.length() - 2);
            return str;
        }
        @SuppressLint("SetTextI18n") // we know that the text is not null @_@
        public void bind(ServiceRequest request,
                         IRequests iRequests) {
            name.setText("Client name: " + request.getClientName());
            type.setText("Service type: " + removeLastIfNonAlpha(request.getType()));
            if (location != null)
                location.setText("Requested service at: " + request.getLocation());
            if (comment != null)
                comment.setText("Client comment: " + request.getClientComment());
            date.setText("date: " + Constants.getDateStringFromTimeMilies(request.getDate()));
        }
    }


}
