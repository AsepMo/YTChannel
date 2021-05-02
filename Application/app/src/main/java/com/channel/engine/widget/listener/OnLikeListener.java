package com.channel.engine.widget.listener;

import com.channel.engine.widget.LikeButton;

public interface OnLikeListener {
    void liked(LikeButton likeButton);
    void unLiked(LikeButton likeButton);
}
