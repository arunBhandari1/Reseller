package com.example.reseller;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEditShoeActivity extends AppCompatActivity
{
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String STYLE_CODE = "STYLE_CODE";
    public static final String SHOE_NAME = "SHOE_NAME";
    public static final String SHOE_SIZE = "SHOE_SIZE";
    public static final String SHOE_PRICE = "SHOE_PRICE";
    public static final String SHOE_QUANTITY = "SHOE_QUANTITY";


    private EditText style_code, shoe_name, shoe_size, shoe_price, shoe_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shoe);
        style_code = findViewById(R.id.stylecode_textview);
        shoe_name = findViewById(R.id.shoename_textview);
        shoe_size = findViewById(R.id.shoesize_textview);
        shoe_price = findViewById(R.id.shoeprice_textview);
        shoe_quantity = findViewById(R.id.shoequantity_textview);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID))
        {
            setTitle("Edit Sneaker");
            style_code.setText(intent.getStringExtra(STYLE_CODE));
            shoe_name.setText(intent.getStringExtra(SHOE_NAME));
            Double shoesize = intent.getDoubleExtra(SHOE_SIZE, 0.2);
            shoe_size.setText(shoesize.toString());
            Double shoeprice = intent.getDoubleExtra(SHOE_PRICE, 0.2);

            shoe_price.setText(shoeprice.toString());
            Integer shoequantity = intent.getIntExtra(SHOE_QUANTITY, -1);
            shoe_quantity.setText(shoequantity.toString());
        } else
        {
            setTitle("Add shoe");
        }

        final Button button = findViewById(R.id.enter_button);

        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(style_code.getText()))
                {
                    setResult(RESULT_CANCELED, replyIntent);
                } else
                {

                    String stylecode = style_code.getText().toString();
                    String shoename = shoe_name.getText().toString();
                    String shoesize = shoe_size.getText().toString();
                    String shoeprice = shoe_price.getText().toString();
                    String shoequantity = shoe_quantity.getText().toString();

                    replyIntent.putExtra(STYLE_CODE, stylecode);
                    replyIntent.putExtra(SHOE_NAME, shoename);
                    replyIntent.putExtra(SHOE_SIZE, shoesize);
                    replyIntent.putExtra(SHOE_PRICE, shoeprice);
                    replyIntent.putExtra(SHOE_QUANTITY, shoequantity);

                    String Stylecode = getIntent().getStringExtra(STYLE_CODE);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });


    }

}
