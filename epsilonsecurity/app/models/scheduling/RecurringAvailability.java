package models.scheduling;

import models.TimeBlock;
import models.UserIdable;
import models.id.RecurringAvailabilityId;
import models.id.UserId;
import javax.persistence.Entity;
import javax.persistence.Id;
import io.ebean.*;

import javax.annotation.Nonnull;
import java.util.Date;
@Entity
public class RecurringAvailability extends Model implements UserIdable {
    @Id
    @Nonnull private RecurringAvailabilityId id;
    @Nonnull private UserId userId;

    @Nonnull private Date day;
    @Nonnull private int Frequency;

    @Nonnull private TimeBlock recurTimeBlock;
    @Nonnull private TimeBlock shiftTimeBlock;

    public RecurringAvailability(@Nonnull RecurringAvailabilityId id, @Nonnull UserId userId, @Nonnull Date day, @Nonnull int frequency, @Nonnull TimeBlock recurTimeBlock, @Nonnull TimeBlock shiftTimeBlock) {
        this.id = id;
        this.userId = userId;
        this.day = day;
        Frequency = frequency;
        this.recurTimeBlock = recurTimeBlock;
        this.shiftTimeBlock = shiftTimeBlock;
    }

    public RecurringAvailabilityId getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public Date getDay() {
        return day;
    }

    public int getFrequency() {
        return Frequency;
    }

    public TimeBlock getRecurTimeBlock() {
        return recurTimeBlock;
    }

    public TimeBlock getShiftTimeBlock() {
        return shiftTimeBlock;
    }

    public static Finder<Integer, RecurringAvailability> find = new Finder<>(RecurringAvailability.class);

}
