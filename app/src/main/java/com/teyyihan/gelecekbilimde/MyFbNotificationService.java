package com.teyyihan.gelecekbilimde;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFbNotificationService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        System.out.println("teoooo geldi "+remoteMessage.getData());
        System.out.println("teoooo geldi "+remoteMessage.getData().get("url"));
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        System.out.println("teoooo geldiiii2");
    }


}
