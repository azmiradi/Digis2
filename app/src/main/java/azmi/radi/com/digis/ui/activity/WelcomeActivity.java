package azmi.radi.com.digis.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import azmi.radi.com.digis.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
       setContentView(R.layout.activity_welcome);
    }
    void hideStatusBar() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

    }


    public void getStart(View view) {
        Intent goMainIntent=new Intent(this, MainActivity.class);
        startActivity(goMainIntent);
        finish();
    }
}
