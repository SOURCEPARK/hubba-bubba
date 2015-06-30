/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.hubbabubba.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import spark.Request;
import spark.Response;

/**
 *
 * @author smatyba
 */
public abstract class AbstractCandyService {
    public abstract Object handleGetRequest(final Request request, final Response response) throws JsonProcessingException;
}
