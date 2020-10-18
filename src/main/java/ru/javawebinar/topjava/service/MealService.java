package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository){
        this.repository = repository;
    }

    public Meal create(Meal meal, Integer userId) {
        return repository.save(meal, userId);
    }

    public void delete(int id, Integer userId) {
        if(!repository.delete(id, userId)){
            throw new NotFoundException("user not found");
        }
    }

    public Meal get(int id, Integer userId) {
        Meal meal = repository.get(id, userId);
        if (meal == null) throw new NotFoundException("meal or user not found");
        else return meal;
    }

    public Meal update(Meal meal, Integer userId) {
        Meal updatedMeal = repository.save(meal, userId);
        if (updatedMeal == null) throw new NotFoundException("meal or user not found");
        else return updatedMeal;
    }

    public Collection<Meal> getAll(Integer userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
        return repository.getAll(userId, startDate, startTime, endDate, endTime);
    }

    public Collection<Meal> getAll() {
        return repository.getAll();
    }
}