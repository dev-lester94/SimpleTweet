package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;

    //Pass in the context and list of tweets


    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    //For each row, inflate a layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    //Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }




    //Define a viewholder

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView tvTimeStamp;
        TextView tvName;
        ImageView ivMediaImage;
        ImageView ivRetweeted;
        TextView tvRetweetedCount;
        ImageView ivFavorited;
        TextView tvFavoritedCount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvName = itemView.findViewById(R.id.tvName);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
            ivMediaImage = itemView.findViewById(R.id.ivMediaImage);
            ivRetweeted = itemView.findViewById(R.id.ivRetweeted);
            tvRetweetedCount = itemView.findViewById(R.id.tvRetweetCount);
            ivFavorited = itemView.findViewById(R.id.ivFavorited);
            tvFavoritedCount = itemView.findViewById(R.id.tvFavoritedCount);
        }

        public void bind(Tweet tweet) {
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
            tvBody.setText(tweet.body);
            tvName.setText(tweet.user.name);
            tvScreenName.setText("@" + tweet.user.screenName);
            tvTimeStamp.setText(tweet.getFormattedTimestamp());
            if(tweet.mediaUrl.equals("")){
                ivMediaImage.setVisibility(View.GONE);
            }else{
                ivMediaImage.setVisibility(View.VISIBLE);
                Glide.with(context).load(tweet.mediaUrl).into(ivMediaImage);
            }

            if(tweet.retweeted){
                ivRetweeted.setImageResource(R.drawable.ic_retweeted);
            }else{
                ivRetweeted.setImageResource(R.drawable.ic_defaultretweet);
            }

            tvRetweetedCount.setText(""+tweet.retweetCount);

            if(tweet.favorited){
                ivFavorited.setImageResource(R.drawable.ic_favorited);
            }else{
                ivFavorited.setImageResource(R.drawable.ic_defaultfavorite);
            }

            tvFavoritedCount.setText(""+tweet.favoriteCount);



        }
    }
}
