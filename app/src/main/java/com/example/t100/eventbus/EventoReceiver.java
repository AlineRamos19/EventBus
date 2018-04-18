package com.example.t100.eventbus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;



public class EventoReceiver extends BroadcastReceiver {

    EventBus bus = EventBus.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {

        Event event = null;

        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            event = new Event("Carregando!");

        } else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            event = new Event("Desconectado!");
        }

        bus.post(event);
    }
}
