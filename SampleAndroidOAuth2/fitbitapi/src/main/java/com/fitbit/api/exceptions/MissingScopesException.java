package com.fitbit.api.exceptions;

import com.fitbit.authentication.Scope;

import java.util.Collection;

/**
 * Created by jboggess on 9/19/16.
 */
public class MissingScopesException extends FitbitAPIException {

    private Collection<Scope> scopes;

    public MissingScopesException(Collection<Scope> scopes) {
        this.scopes = scopes;
    }

    public Collection<Scope> getScopes() {
        return scopes;
    }
}
