package com.daya.moviekataloe.repo.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.daya.moviekataloe.repo.room.DatabaseContract;
import com.daya.moviekataloe.repo.room.FavoriteDatabase;

import static com.daya.moviekataloe.repo.room.DatabaseContract.TABLE_NAME_MOVIE;
import static com.daya.moviekataloe.repo.room.DatabaseContract.TABLE_NAME_TV;


public class ContentProviderFav extends ContentProvider {

    private static final int MOVIE = 1;
    private static final int TV = 2;
    private static final UriMatcher uRiMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uRiMatcher.addURI(DatabaseContract.AUTHORITY, TABLE_NAME_MOVIE, MOVIE);
        uRiMatcher.addURI(DatabaseContract.AUTHORITY, TABLE_NAME_TV, TV);
    }

    private FavoriteDatabase db;

    @Override
    public boolean onCreate() {
        db = FavoriteDatabase.Companion.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor cursor;

        switch (uRiMatcher.match(uri)) {
            case MOVIE:
                cursor = db.favoriteDao().getAllMovieFavCursor();
                break;
            case TV:
                cursor = db.favoriteDao().getAllTVFavCursor();
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
