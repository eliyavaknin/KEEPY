package com.keepy;

import android.annotation.SuppressLint;
import android.graphics.Color;

public class Constants {


    @SuppressLint("SimpleDateFormat")
    public static String getDateStringFromTimeMilies(long timeMillies) {
        return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm")
                .format(new java.util.Date(timeMillies));
    }
    public static String[] places = {"Tel aviv", "Jerusalem", "Ramat gan", "Givatayim", "Bnei brak", "Haifa", "Ashdod", "Ashkelon",
            "Ramat hasharon", "Rishone lezion", "Bat yam", "Holon", "Netanya", "Raanana", "Hadera", "Binyamina", "Zichron"
            , "Rehovot", "Nes ziona", "Eilat", "Petah tiqwa", "Beer sheva", "Afula", "Akko", "Arad", "Beit shemesh", "Elad"
            , "Herzliya", "Kfar sava", "Lod", "Ramla", "Netivot", "Nof hagalil", "Or yehuda", "Yehud", "Yavne"};

    public static final String UsersCollection = "Users";
    public static final String UsersCollectionEmailField = "mEmail";

    public static final String ServiceRequestsCollection_outgoing = "outgoingRequests";
    public static final String ServiceRequestsCollection_incoming = "incomingRequests";

    public static final String ServiceRequestsCollection_outgoing_clientIdField = "clientId";
    public static final String ServiceRequestsCollection_outgoing_keeperIdField = "keeperId";
    public static final String ServiceRequestsCollection_outgoing_dateField = "date";
    public static final String ServiceRequestsCollection_outgoing_requestDateField = "requestDate";
    public static final String ServiceRequestsCollection_outgoing_clientCommentField = "clientComment";

    public static final String ServiceRequestsCollection_incoming_clientIdField = "clientId";
    public static final String ServiceRequestsCollection_incoming_keeperIdField = "keeperId";
    public static final String ServiceRequestsCollection_incoming_dateField = "date";
    public static final String ServiceRequestsCollection_incoming_requestDateField = "requestDate";


    static class Utils {
        public static String getKeeperNameAsAppropriateString(String keeperType) {
            switch (keeperType) {
                case "dogister":
                    return "Dogister";
                case "therapist":
                    return "Therapist";
                case "babysitter":
                    return "Babysitter";
                case "babysitter_disabilities":
                    return "Babysitter for children with disabilities";
                case "housekeeper":
                    return "Housekeeper";
                default:
                    return "Keeper";
            }
        }

        public static String getKeeperTypeFromAppropriateString(String keeperName) {
            switch (keeperName) {
                case "Dogister":
                    return "dogister";
                case "Therapist":
                    return "therapist";
                case "Babysitter":
                    return "babysitter";
                case "Babysitter for children with disabilities":
                    return "babysitter_disabilities";
                case "Housekeeper":
                    return "housekeeper";
                default:
                    return "keeper";
            }
        }
    }




    /*
        Rating recommendation min requirement & status field & colors
     */

    public static final float RatingRecommendationMinRequirement = 4.5f;

    public static final String ServiceRequestsCollection_statusField = "status";

    public static final int ColorSelected = Color.parseColor("#5793a8");
    public static final int ColorUnselected = Color.parseColor("#8692f7");

}
