package ru.mirea.gupalodr.scrollviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {
// отобразить геометрическую прогрессию со знаменателем 2 до 100 элемента.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout wrapper = findViewById(R.id.wrapper);
        BigInteger i_1 = BigInteger.valueOf(1);
        BigInteger q = BigInteger.valueOf(2);
        for (int i=1; i < 101; i++){
            View view = getLayoutInflater().inflate(R.layout.item, null, false);
            TextView text = (TextView)view.findViewById(R.id.textView);
            if (i==0) text.setText(String.format("Элемент %d = %d", i, 1));
            else text.setText(String.format("Элемент %d = %d", i, i_1.multiply(q)));
            wrapper.addView(view);
            i_1 = i_1.multiply(q);
        }

    }
}