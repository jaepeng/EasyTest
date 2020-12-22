package com.example.easytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG="jae";
    public List<Photo> mSelectedPhotos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EasyPhotos.createAlbum(this, false, GlideEngine.getInstance())
                .setCount(9)
                .start(new SelectCallback() {
                    @Override
                    public void onResult(ArrayList<Photo> photos, boolean isOriginal) {
                        mSelectedPhotos.clear();
                        mSelectedPhotos.addAll(photos);
                        Log.d(TAG, "onResult: Size:"+mSelectedPhotos.size()+" " +
                                "Path:"+mSelectedPhotos.get(0).path+"" +
                                " uri:"+mSelectedPhotos.get(0).uri);
                    }
                });
    }
}