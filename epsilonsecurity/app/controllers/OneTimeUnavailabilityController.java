package controllers;

import play.mvc.*;
import models.databaseModel.scheduling.DbOneTimeUnavailability;
import models.databaseModel.helpers.DbOneTimeUnavailabilityHelper;

public class OneTimeUnavailabilityController extends Controller {

//    TODO: Implement functionality of OneTimeUnavailabilityController
    public Result listOneTimeUnavailabilities() {
        return ok();
    }

    public Result createOneTimeUnavailability() {
        return ok();
    }

    public Result retrieveOneTimeUnavailability(Integer oneTimeUnavailabilityId) {
        return ok();
    }

    public Result deleteOneTimeUnavailability() {
        return ok();
    }

    public Result readOneTimeUnavailabilitiesByTimeRange(Long timeStart, Long timeEnd) {
        return ok();
    }
}
