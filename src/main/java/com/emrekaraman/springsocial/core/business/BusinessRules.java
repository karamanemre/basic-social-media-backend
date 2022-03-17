package com.emrekaraman.springsocial.core.business;

import com.emrekaraman.springsocial.core.utilities.Result;

public class BusinessRules {
    public static Result run(Result... logics) {
        for (Result logic : logics) {
            if (!logic.isSuccess()) {
                return logic;
            }
        }
        return null;
    }
}
