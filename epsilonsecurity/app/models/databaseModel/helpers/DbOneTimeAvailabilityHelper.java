package models.databaseModel.helpers;

import io.ebean.Expr;
import models.databaseModel.scheduling.DbOneTimeAvailability;
import models.databaseModel.scheduling.DbUserTeam;

import java.util.ArrayList;
import java.util.List;

/**
 * CRUD operations for DbOneTimeAvailability Class
 */
public final class DbOneTimeAvailabilityHelper {

    private DbOneTimeAvailabilityHelper() {

    }

    public static void createDbOneTimeAvailability(DbOneTimeAvailability dbOneTimeAvailability) {
        dbOneTimeAvailability.save();
    }

    public static void deleteDbOneTimeAvailability(DbOneTimeAvailability dbOneTimeAvailability) {
        dbOneTimeAvailability.delete();
    }

    public static DbOneTimeAvailability readDbOneTimeAvailabilityById(Integer id) {
        return DbOneTimeAvailability.find.byId(id);
    }

    /**
     * returns a list just all DbOneTimeAvailability
     *
     * @return
     */
    public static List<DbOneTimeAvailability> readAllDbOneTimeAvailability() {
        return DbOneTimeAvailability.find.all();
    }

    public static List<DbOneTimeAvailability> readAllDbOneTimeAvailabilityByUserId(Integer userId){
        List<DbOneTimeAvailability> listOfAvailabilityByUser = new ArrayList<>();
        List<DbUserTeam>  associatedUserTeam = DbUserTeamHelper.readAllDbUserTeamsByUserId(userId);
        for(DbUserTeam userTeam : associatedUserTeam){
            listOfAvailabilityByUser.addAll
                    (DbOneTimeAvailabilityHelper.readDbOneTimeAvailabilityByUserTeamId(userTeam.getId()));
        }
        return listOfAvailabilityByUser;
    }

    public static List<DbOneTimeAvailability> readDbOneTimeAvailabilityByUserTeamId(Integer userTeamId) {
        List<DbOneTimeAvailability> dbOneTimeAvailabilityList = DbOneTimeAvailability.find
                .query()
                .where()
                .eq(DbOneTimeAvailability.COLUMN_USER_TEAM_ID, userTeamId)
                .findList();

        return dbOneTimeAvailabilityList;
    }

    public static List<DbOneTimeAvailability> readDbOneTimeAvailabilityByTimeRange(Long timeStart, Long timeEnd) {
        List<DbOneTimeAvailability> dbOneTimeAvailabilityList = DbOneTimeAvailability.find
                .query()
                .where()
                .disjunction()
                .add(Expr.between
                        (DbOneTimeAvailability.COLUMN_TIME_START, DbOneTimeAvailability.COLUMN_TIME_END, timeStart))
                .add(Expr.between
                        (DbOneTimeAvailability.COLUMN_TIME_START, DbOneTimeAvailability.COLUMN_TIME_END, timeEnd))
                .findList();

        return dbOneTimeAvailabilityList;
    }

}
