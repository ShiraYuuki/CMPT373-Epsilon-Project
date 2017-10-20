package models.databaseModel.helpers;


import io.ebean.Expr;
import models.databaseModel.scheduling.DbShift;
import models.databaseModel.scheduling.query.QDbShift;

import javax.annotation.Nonnull;
import java.util.List;


/**
 * CRUD operations for DbShift class
 */
public final class DbShiftHelper {

    private DbShiftHelper() {
    }

    /**
     * creates a DbShift from name, timeStart, timeEnd
     * @param name
     * @param timeStart
     * @param timeEnd
     */
    public static void createDbShift(@Nonnull String name, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        DbShift dbShift = new DbShift(name, timeStart, timeEnd);
        dbShift.save();
    }

    /**
     * finds a DbShift by DbShiftId
     * @param id
     * @return
     */
    public static DbShift readDbShiftByName(@Nonnull String shiftName) {
        DbShift dbShift = DbShift.find
                .query()
                .where()
                .eq(DbShift.FORM_COLUMN_NAME, shiftName)
                .findOne();
        return dbShift;
    }

    /**
     * returns a List of DBshift by timeStart, timeEnd
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public static List<DbShift> readDbShiftByTime(Integer timeStart, Integer timeEnd){
        return new QDbShift().
                timeStart.lessOrEqualTo(timeStart).and().
                timeEnd.greaterOrEqualTo(timeStart).and().
                timeStart.lessOrEqualTo(timeStart).and().
                timeEnd.greaterOrEqualTo(timeEnd).
                findList();
    }

    /**
     * deletes a DbShift by DbShiftId
     * @param id
     */
    public static void deleteDbShiftByName(@Nonnull String name) {
        DbShift dbShift = readDbShiftByName(name);
        dbShift.delete();
    }

    /**
     * returns a list of all DbShift
     * @return
     */
    public static List<DbShift> readAllDbShift() {
        List<DbShift> dbShift = DbShift.find.all();
        return dbShift;
    }

}
