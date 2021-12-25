package it.sal.disco.unimib.filmreviewer.utils;

import android.util.Log;

import it.sal.disco.unimib.filmreviewer.customObj.Movie;

public class Constants {
    public final static String API_BASE_URL = "https://imdb-api.com/en/API/";
    //public final static String API_KEY = "k_613gh8jx";
    //public final static String API_KEY = "k_lkvlsrmu";
    public final static String API_KEY = "k_ay29tyqr";


    public final static String HEADLINES_COUNTRY = "en";


    public final static int API_MAX_FOR_PAGE = 25;



    public static String getLittleImage(String inputUrl){
        return inputUrl;
    }

    public static int[] imagePosterSize(int lambda){
        int[] aa = {0, 0};
        aa[0] = lambda*27;
        aa[1] = lambda*40;
        return aa;
    }

    //Use ONLY to pass the id, the consistency is not guaranteed for this object
    public static Movie selectedMovie = null;
}
