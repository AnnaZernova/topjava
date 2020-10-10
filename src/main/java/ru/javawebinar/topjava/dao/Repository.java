package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.Collection;
import java.util.List;

public interface Repository {
    public void save(Meal meal);
    public void deleteMeal(int id);
    public Meal getMealById(int id);
    public Collection<MealTo> getAllMeals();
}
