package com.example.clevertaptestapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.clevertap.android.sdk.CTInboxListener;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.inbox.CTInboxMessage;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements CTInboxListener {

    public Button bt1,bt2,bt3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());

        if (clevertapDefaultInstance != null) {
            //Set the Notification Inbox Listener
            clevertapDefaultInstance.setCTNotificationInboxListener(this);
            //Initialize the inbox and wait for callbacks on overridden methods
            clevertapDefaultInstance.initializeInbox();
        }

    findViewById(R.id.messagecount).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d("Clevertap","message count "+clevertapDefaultInstance.getInboxMessageCount());
        }
    });
        findViewById(R.id.allinbox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String allMessage = "";
                String abc = "";
                Log.d("Clevertap","all messages  "+clevertapDefaultInstance.getAllInboxMessages());
                ArrayList<CTInboxMessage> allInboxMessages = clevertapDefaultInstance.getAllInboxMessages();
                for(int i=0; i < clevertapDefaultInstance.getInboxMessageCount();i++){
                    allMessage = "Inbox Message "+ (i+1) +"\nTitle "+ allInboxMessages.get(i).getInboxMessageContents().get(0).getTitle()
                            +"\nMessage " + allInboxMessages.get(i).getInboxMessageContents().get(0).getMessage()+"\n"+  "Links: "+ allInboxMessages.get(i).getInboxMessageContents().get(0).getLinks();

                }
                System.out.println("Shikha All Inbox: " + allMessage );
                System.out.println("Shikha ABC: " + abc);
            }

        });

        findViewById(R.id.messageid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Clevertap","messageid  "+clevertapDefaultInstance.getAllInboxMessages().get(0).getMessageId());

                Log.d("Clevertap","message count "+clevertapDefaultInstance.getInboxMessageForId(clevertapDefaultInstance.getAllInboxMessages().get(0).getMessageId()));

                Log.d("Clevertap","Hyyyy  "+clevertapDefaultInstance.getAllInboxMessages().get(0).getTitle());
                Log.d("Clevertap","Karthik  "+clevertapDefaultInstance.getAllInboxMessages().get(0).getBody());

            }
        });


        //Raise Notification Viewed event for Inbox Message. Message id should be a String
        clevertapDefaultInstance.pushInboxNotificationViewedEvent(clevertapDefaultInstance.getAllInboxMessages().get(0).getMessageId());
//Raise Notification Clicked event for Inbox Message. Message id should be a String.
        clevertapDefaultInstance.pushInboxNotificationClickedEvent(clevertapDefaultInstance.getAllInboxMessages().get(0).getMessageId());

    }


    @Override
    public void inboxDidInitialize() {

    }

    @Override
    public void inboxMessagesDidUpdate() {

    }
}
