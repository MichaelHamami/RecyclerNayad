package com.example.hamami.recyclernayad;

import android.media.MediaMetadataRetriever;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //recyclerview objects
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    //model object for our list data
    private List<MyList> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing views
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        //loading list view item with this function
        loadRecyclerViewItem();

        // load data file
        MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
        metaRetriever.setDataSource(filePath);

        String out = "";
        // get mp3 info

        // convert duration to minute:seconds
        String duration =
                metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//        Log.v("time", duration);
        Toast.makeText(this,"time:"+duration,Toast.LENGTH_SHORT).show();
        long dur = Long.parseLong(duration);
        String seconds = String.valueOf((dur % 60000) / 1000);

//        Log.v("seconds", seconds);
        Toast.makeText(this,"seconds:"+seconds,Toast.LENGTH_SHORT).show();
        String minutes = String.valueOf(dur / 60000);
        out = minutes + ":" + seconds;
        if (seconds.length() == 1) {
            txtTime.setText("0" + minutes + ":0" + seconds);
        }else {
            txtTime.setText("0" + minutes + ":" + seconds);
        }
//        Log.v("minutes", minutes);
        Toast.makeText(this,"minutes:"+minutes,Toast.LENGTH_SHORT).show();
        // close object
        metaRetriever.release();
    }




    private void loadRecyclerViewItem() {
        //you can fetch the data from server or some apis
        //for this tutorial I am adding some dummy data directly
        for (int i = 1; i <= 9; i++) {
            MyList myList = new MyList(
                    "Dummy Heading " + i,
                    "time" +i+" "
            );
            list.add(myList);
        }

        adapter = new CustomAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }
}
