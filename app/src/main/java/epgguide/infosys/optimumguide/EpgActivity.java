package epgguide.infosys.optimumguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import epgguide.infosys.optimumguide.epg.EpgView;

import in.sathishkod.epgguide.R;

public class EpgActivity extends AppCompatActivity {

    private EpgView mEpgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epg);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mEpgView = (EpgView) findViewById(R.id.epg_view);
    }
}
