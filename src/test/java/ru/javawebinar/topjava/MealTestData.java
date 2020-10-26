package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ID = START_SEQ + 2;

    public static final Meal meal = new Meal(ID, LocalDateTime.of(2020, Month.JUNE, 1, 14, 0), "Breakfast", 1000);

    public static Meal getNew() {
        return new Meal(ID, LocalDateTime.of(2020, Month.JUNE, 2, 15, 0), "Dinner", 400);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal);
        updated.setCalories(800);
        updated.setDescription("UpdatedDescription");
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected){
        assertThat(actual).isEqualTo(expected);
    }
}
