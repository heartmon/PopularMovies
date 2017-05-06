package com.heartmon.android.popularmovies.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by heartmon on 5/5/2017.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface Local {
}
