package com.channel.application.youtube.config;


/**
 * Static container class for holding a reference to your YouTube Developer Key.
 */
public class YouTubeApi {
    
    public static String TAG = YouTubeApi.class.getSimpleName();
  /**
     * Please replace this with a valid API key which is enabled for the
     * YouTube Data API v3 service. Go to the
     * <a href="https://console.developers.google.com/">Google Developers Console</a>
     * to register a new developer key.
     */
    public static final String DEVELOPER_API_KEY = "AIzaSyAYgHbHDXV1x-wSdJPqdPiwq-2GgdWEqWk";

    public static final String API_KEY = "AIzaSyDH3naOGPlOL175VfhVaRrzr0438MymNxM";
    
    public static String CHANNEL_ID = "UCoMdktPbSTixAyNGwb-UYkQ"; //here you should use your channel id for testing purpose you can use this api also
    public static String CHANNLE_GET_SEARCH_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&order=date&channelId=" + CHANNEL_ID + "&maxResults=20&key=" + DEVELOPER_API_KEY + "";
    public static final String YOUTUBE_CHANNEL_ID = "https://www.googleapis.com/youtube/v3/channels?part=snippet,contentDetails,statistics&id=UC2H7DyQrnr2RA4RSMF0B4ZA&key=" + DEVELOPER_API_KEY;
    public static final String YOUTUBE_POPULAR_VIDEO_URL = "hhttps://www.googleapis.com/youtube/v3/videos?part=snippet,contentDetails,statistics&chart=mostPopular&regionCode=ID&maxResults=150&key=" + DEVELOPER_API_KEY;
    public static final String YOUTUBE_SEARCH_VIDEO_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=150&key=" + DEVELOPER_API_KEY;
    public static final String YOUTUBE_VIDEO_DETAILS_URL = "https://www.googleapis.com/youtube/v3/videos?part=snippet,contentDetails,statistics&key=" + DEVELOPER_API_KEY;
    public static final String YOUTUBE_URL_VIDEO = "http://www.youtube.com/watch?v=";
    
    public static final String EXTRA_LOCAL_ONLY = "android.intent.extra.LOCAL_ONLY";

    public static final String YOUTUBE_WEB_URL = "https://www.youtube.com/";
    public static final String YOUTUBE_USER_ID = "maulana8608";
    public static final String YOUTUBE_CHANNEL_URL = "https://www.youtube.com/watch?v=QrAsNq5h7hU";
    public static final String YOUTUBE_VIDEO_ID = "QrAsNq5h7hU";
    public static final String YOUTUBE_BERANDA = "PLiKkX4KV1eFLUxsoE7fIDx5RDSC0qOdC4";
    
}
