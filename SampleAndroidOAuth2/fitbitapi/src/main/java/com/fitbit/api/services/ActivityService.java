package com.fitbit.api.services;

import com.fitbit.api.exceptions.MissingScopesException;
import com.fitbit.api.exceptions.TokenExpiredException;
import com.fitbit.api.loaders.ResourceLoaderFactory;
import com.fitbit.api.loaders.ResourceLoaderResult;
import com.fitbit.api.models.DailyActivitySummary;
import com.fitbit.authentication.Scope;

import android.app.Activity;
import android.content.Loader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jboggess on 10/3/16.
 */
public class ActivityService {

    private final static String ACTIVITIES_URL = "https://api.fitbit.com/1/user/-/activities/date/%s.json";
    private static final ResourceLoaderFactory<DailyActivitySummary> USER_ACTIVITIES_LOADER_FACTORY = new ResourceLoaderFactory<>(ACTIVITIES_URL, DailyActivitySummary.class);
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public static Loader<ResourceLoaderResult<DailyActivitySummary>> getDailyActivitySummaryLoader(Activity activityContext, Date date) throws MissingScopesException, TokenExpiredException {
        return USER_ACTIVITIES_LOADER_FACTORY.newResourceLoader(activityContext, new Scope[]{Scope.activity}, dateFormat.format(date));
    }

}
