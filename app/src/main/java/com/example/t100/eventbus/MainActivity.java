package com.example.t100.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {


    Button button;
    String resultEvento = null;
    TextView postarResultado;

    private EventBus bus = EventBus.getDefault();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event) {
        resultEvento = event.getAlerta();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callResultEvent();
        bus.register(this);
    }

    private void callResultEvent() {
        button = findViewById(R.id.btn_event);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postarResultado = findViewById(R.id.txt_event_result);
                postarResultado.setText(resultEvento);
            }
        });
    }

    @Override
    protected void onStart() {

        if (!bus.isRegistered(this)) {
            bus.register(this);
        }
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        bus.unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        bus.unregister(this);
        super.onPause();
    }
}
