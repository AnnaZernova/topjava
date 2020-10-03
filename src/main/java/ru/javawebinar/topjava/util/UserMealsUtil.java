package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    //return filtered list with excess
    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        //count actual calories per day
        Map<LocalDate,Integer> caloriesPerDayFact = new HashMap<>();

        meals.forEach (
            meal -> caloriesPerDayFact.merge(Year.of(meal.getDateTime().getYear()).atDay(meal.getDateTime().getDayOfYear()),
                    meal.getCalories(), Integer::sum)
        );

        //filter and create new list with excess
        List<UserMealWithExcess> mealsWithExcess = new ArrayList<>();

        meals.forEach (
            meal -> {
                if (meal.getDateTime().toLocalTime().isAfter(startTime) && meal.getDateTime().toLocalTime().isBefore(endTime)) {
                    if (caloriesPerDay < caloriesPerDayFact.get(meal.getDateTime().toLocalDate())) {
                        mealsWithExcess.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), true));
                    } else
                        mealsWithExcess.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), false));
                }
            }
        );

        return mealsWithExcess;
    }

    //return filtered list with excess
    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        //count actual calories per day
        Map<LocalDate,Integer> caloriesPerDayFact = meals
                .stream()
                .collect(Collectors.toMap(meal->Year.of(meal.getDateTime().getYear()).atDay(meal.getDateTime().getDayOfYear()),
                        UserMeal::getCalories, Integer::sum));

        //stream with meal with excess
        Stream stream1 = meals.stream()
                .filter(meal -> (meal.getDateTime().toLocalTime().isAfter(startTime) && meal.getDateTime().toLocalTime().isBefore(endTime)))
                .filter(meal -> caloriesPerDay < caloriesPerDayFact.get(meal.getDateTime().toLocalDate()))
                .map(meal -> new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), true));

        //stream with meal without excess
        Stream stream2 = meals.stream()
                .filter(meal -> (meal.getDateTime().toLocalTime().isAfter(startTime) && meal.getDateTime().toLocalTime().isBefore(endTime)))
                .filter(meal -> caloriesPerDay >= caloriesPerDayFact.get(meal.getDateTime().toLocalDate()))
                .map(meal -> new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), false));

        Stream<UserMealWithExcess> streams = Stream.concat(stream1, stream2);

        return streams.collect(Collectors.toList());

    }
}
