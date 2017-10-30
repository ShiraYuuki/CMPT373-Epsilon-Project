package models.databaseModel.helpers;


import models.databaseModel.scheduling.DbShift;
import models.databaseModel.scheduling.query.QDbShift;
import models.databaseModel.scheduling.query.QDbUser;

import java.util.List;


/**
 * CRUD operations for DbShift class
 */
public final class DbShiftHelper {

    private DbShiftHelper() {

    }

    public static void createDbShift(DbShift dbShift) {
        dbShift.save();
    }

    public static DbShift readDbShiftByName(String shiftName) {
        DbShift dbShift = new QDbShift()
                .name
                .eq(shiftName)
                .findUnique();

        return dbShift;
    }

    /**
     * returns a List of DBshift by timeStart, timeEnd
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public static List<DbShift> readDbShiftByTime(Long timeStart, Long timeEnd){
        List<DbShift> dbShiftList = new QDbShift()
                .timeStart.lessOrEqualTo(timeStart)
                .and()
                .timeEnd.greaterOrEqualTo(timeStart)
                .and()
                .timeStart.lessOrEqualTo(timeStart)
                .and()
                .timeEnd.greaterOrEqualTo(timeEnd)
                .findList();

        return dbShiftList;
    }

    public static void deleteDbShiftByName(DbShift dbShift) {
        dbShift.delete();
    }

    /**
     * returns a list of all DbShift
     * @return
     */
    public static List<DbShift> readAllDbShift() {
        List<DbShift> dbShift = new QDbShift()
                .findList();

        return dbShift;
    }

}
