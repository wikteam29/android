package com.example.gdfgdfgf;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private final int BASE_COLOR = Color.parseColor("#CC121212");

    private SeekBar seekAlpha;
    private TextView tvInfo;
    private TextView textZmieniany;
    private Button buttonMinus;
    private Button buttonPlus;
    private boolean updating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        seekAlpha = findViewById(R.id.seekAlpha);
        textZmieniany = findViewById(R.id.textZmieniany);
        tvInfo = findViewById(R.id.tvInfo);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPlus = findViewById(R.id.buttonPlus);
        applyAlpha(100, false, false);

        seekAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (updating) return;
                applyAlpha(progress,true,false);
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        buttonMinus.setOnClickListener(v -> {
            int actualPrecent = Color.alpha(BASE_COLOR) / 255;
            if(actualPrecent - 10 > 0){
                actualPrecent -= 10;
                applyAlpha(actualPrecent,false,false);
            }else{
                return;
            }
        });

        buttonPlus.setOnClickListener(v -> {
            int actualPrecent = Color.alpha(BASE_COLOR) / 255;
            if(actualPrecent + 10 < 100){
                actualPrecent += 10;
                applyAlpha(actualPrecent,false,false);
            }else{
                return;
            }
        });
    }
    private void applyAlpha(int percent, boolean setSeek, boolean setRadio) {
        updating = true;

        try {
            int alpha = Color.alpha(BASE_COLOR) * percent / 100;

            if (setSeek) {
                seekAlpha.setProgress(percent);
            }

//            if (percent == 100) radioGroup.check(R.id.rb100);
//            else if (percent == 75) radioGroup.check(R.id.rb75);
//            else if (percent == 50) radioGroup.check(R.id.rb50);
//            else if (percent == 25) radioGroup.check(R.id.rb25);
//            else radioGroup.clearCheck();

            tvInfo.setText("Przezroczystość panelu: " + percent + "%");

            textZmieniany.setTextColor(ColorUtils.setAlphaComponent(BASE_COLOR, alpha));
        } finally {
            updating = false;
        }
    }
}
