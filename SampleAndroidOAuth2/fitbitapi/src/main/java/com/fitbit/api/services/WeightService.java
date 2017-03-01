package com.fitbit.api.services;

import com.fitbit.api.exceptions.MissingScopesException;
import com.fitbit.api.exceptions.TokenExpiredException;
import com.fitbit.api.loaders.ResourceLoaderFactory;
import com.fitbit.api.loaders.ResourceLoaderResult;
import com.fitbit.api.models.WeightLogs;
import com.fitbit.authentication.Scope;

import android.app.Activity;
import android.content.Loader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jboggess on 10/3/16.
 */
public class WeightService {

    private final static String WEIGHT_URL = "https://api.fitbit.com/1/user/-/body/log/weight/date/%s/%s.json";
    private static final ResourceLoaderFactory<WeightLogs> WEIGHT_LOG_LOADER_FACTORY = new ResourceLoaderFactory<>(WEIGHT_URL, WeightLogs.class);
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public static Loader<ResourceLoaderResult<WeightLogs>> getWeightLogLoader(Activity activityContext, Date startDate, int calendarDateType, int number) throws MissingScopesException, TokenExpiredException {
        String periodSuffix = "d";
        switch (calendarDateType) {
            case Calendar.WEEK_OF_YEAR:
                periodSuffix = "w";
                break;
            case Calendar.MONTH:
                periodSuffix = "m";
                break;
        }

        return WEIGHT_LOG_LOADER_FACTORY.newResourceLoader(
                activityContext,
                new Scope[]{Scope.weight},
                dateFormat.format(startDate),
                String.format(Locale.US, "%d%s", number, periodSuffix));
    }

}
