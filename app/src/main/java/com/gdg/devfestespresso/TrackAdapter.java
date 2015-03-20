package com.gdg.devfestespresso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by chris on 3/19/15.
 */
public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {

    String[] tracks;

    public TrackAdapter(Context context) {
        this.tracks = context.getResources().getStringArray(R.array.tracks);
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track, viewGroup, false);
        TrackViewHolder viewHolder = new TrackViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrackViewHolder viewHolder, int position) {
        viewHolder.textView.setText(tracks[position]);
    }

    @Override
    public int getItemCount() {
        return tracks.length;
    }

    public static class TrackViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public TrackViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.track_text);
            imageView = (ImageView) v.findViewById(R.id.track_image);
        }


    }
}