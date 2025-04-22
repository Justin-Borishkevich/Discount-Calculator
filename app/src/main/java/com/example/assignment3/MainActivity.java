package com.example.assignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //  Assignment 2
    //  activity_main.xml
    //  Justin Borishkevich

    TextView ViewSeek, ViewDiscount, ViewFinalPrice;
    Button buttonReset, buttonCalculate;
    SeekBar seekBar;
    EditText editText;
    RadioGroup radioGroup;
    RadioButton radioButton, buttonCustom;

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

        ViewSeek = findViewById(R.id.textViewSeek);
        ViewDiscount = findViewById(R.id.textViewDiscount);
        ViewFinalPrice = findViewById(R.id.textViewFinalPrice);
        seekBar = findViewById(R.id.seekBarSlide);
        editText = findViewById(R.id.editText);
        radioGroup = findViewById(R.id.radioGroup);
//        buttonCustom = findViewById(R.id.buttonCustom);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ViewSeek.setText(String.valueOf(progress+ "%"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.buttonCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String input = editText.getText().toString();

                    if (!input.isEmpty()) {
                        double price = Double.parseDouble(input);
                        if (radioGroup.getCheckedRadioButtonId() == R.id.buttonCustom){
                            double discountValue = (double) seekBar.getProgress();
                            discountValue = discountValue * 0.01;
                            discountValue = price * discountValue;
                            price = price - discountValue;
                            ViewDiscount.setText(String.format("%.2f", discountValue));
                        } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonTen) {
                            double discountValue = 0.1;
                            discountValue = price * discountValue;
                            price = price - discountValue;
                            ViewDiscount.setText(String.format("%.2f", discountValue));
                        } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonFifteen) {
                            double discountValue = 0.15;
                            discountValue = price * discountValue;
                            price = price - discountValue;
                            ViewDiscount.setText(String.format("%.2f", discountValue));
                        } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonEighteen) {
                            double discountValue = 0.18;
                            discountValue = price * discountValue;
                            price = price - discountValue;
                            ViewDiscount.setText(String.format("%.2f", discountValue));
                        }

                        ViewFinalPrice.setText(String.format("%.2f", price));


                    } else {
                        throw new IllegalArgumentException("No input!");
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid input! Please enter a valid price.", Toast.LENGTH_SHORT).show();
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), "No input provided!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    // A generic catch for any unexpected exceptions
                    Toast.makeText(getApplicationContext(), "An unexpected error occurred.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewSeek.setText("25%");
                editText.setText("");
                radioButton = findViewById(R.id.radioButtonTen);
                radioButton.setChecked(true);
                ViewFinalPrice.setText("0.00");
                ViewDiscount.setText("0.00");
                seekBar.setProgress(25);
            }
        });

    }
}