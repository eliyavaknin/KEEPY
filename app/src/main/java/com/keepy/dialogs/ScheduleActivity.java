package com.keepy.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.keepy.R;
import com.keepy.adapters.ScheduleRvAdapter;
import com.keepy.models.ServiceRequest;

import java.util.ArrayList;
import java.util.List;


public class ScheduleActivity extends AlertDialog {

    public ScheduleActivity(Context context,
                            List<ServiceRequest> requestList,
                            boolean isClientView) {
        super(context);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_schedule, null, false);
        if (requestList == null)
            requestList = new ArrayList<>();
        setButton(BUTTON_POSITIVE, "CLOSE", (dialog, which) -> {
            dialog.dismiss();
        });
        setTitle("Keepy - Schedule");
        ArrayList<ServiceRequest> requestListApp = new ArrayList<>();
        for (ServiceRequest request : requestList) {
            if (request.getStatus() == ServiceRequest.Status.APPROVED) {
                requestListApp.add(request);
            }
        }
        if (requestListApp.size() == 0) {
            setMessage("No Scheduled Services");
        } else {
            setView(view);
            RecyclerView rv = view.findViewById(R.id.scheduleRv);
            rv.setLayoutManager(new LinearLayoutManager(context));
            rv.setAdapter(new ScheduleRvAdapter(
                    requestListApp,
                    isClientView
            ));
        }

    }
}
