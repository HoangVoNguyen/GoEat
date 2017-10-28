package com.example.hoang.goeat;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.goeat.DataItem;
import com.example.hoang.goeat.DataItemAdapter;
import com.example.hoang.goeat.SampleDataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Locale;

@SuppressWarnings("FieldCanBeLocal")
public class DetailActivity extends AppCompatActivity {
    private TextView tvName, tvUrl;
    private ImageView itemImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        String itemId = getIntent().getExtras().getString(DataItemAdapter.ITEM_ID_KEY);
//        DataItem item = SampleDataProvider.dataItemMap.get(itemId);
        DataItem item = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_KEY);
        if (item == null) {
            throw new AssertionError("Null data item received!");
        }

        tvName = (TextView) findViewById(R.id.tvItem);

        itemImage = (ImageView) findViewById(R.id.itemImage);
        tvUrl = (TextView) findViewById(R.id.tvUrl);

        tvName.setText(item.getItem());
        tvUrl.setText(item.getUrl());

//        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
//        tvPrice.setText(nf.format(item.getPrice()));

        InputStream inputStream = null;
        try {
            String imageFile = item.getImage();
            inputStream = getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            itemImage.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
