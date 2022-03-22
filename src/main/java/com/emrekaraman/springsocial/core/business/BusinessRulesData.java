package com.emrekaraman.springsocial.core.business;

import com.emrekaraman.springsocial.core.utilities.DataResult;
import com.emrekaraman.springsocial.core.utilities.Result;

public class BusinessRulesData {
    public static DataResult run(DataResult... logics) {
        for (DataResult logic : logics) {
            if (!logic.isSuccess()) {
                return logic;
            }
        }
        return null;
    }
}
