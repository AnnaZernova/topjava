package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.InMemoryMealRepository;
import ru.javawebinar.topjava.dao.Repository;
import ru.javawebinar.topjava.model.Meal;

import static org.slf4j.LoggerFactory.getLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;


public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    Repository dao = new InMemoryMealRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.info("getAll");

        String action = req.getParameter("action");

        if(action == null) {
            req.setAttribute("mealList", dao.getAllMeals());
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        }
        else if(action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(Objects.requireNonNull(req.getParameter("mealId")));
            dao.deleteMeal(mealId);
            resp.sendRedirect("mealList");
        }
        else {
            Meal meal = action.equalsIgnoreCase("create") ?
                    new Meal(LocalDateTime.now(),"",1000) :
                    dao.getMealById(Integer.parseInt(Objects.requireNonNull(req.getParameter("mealId"))));
            req.setAttribute("meal", meal);
            req.getRequestDispatcher("/mealEdit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String mealId = req.getParameter("mealId");
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("datetime-local"));

        Meal meal = new Meal(localDateTime, req.getParameter("description"), Integer.parseInt(req.getParameter("calories")),
                                mealId.isEmpty() ? null : Integer.parseInt(mealId));

        dao.save(meal);
        resp.sendRedirect("mealList");
    }
}
