package com.example.knockoffeksemanprojekt.Repository;


import com.example.knockoffeksemanprojekt.Model.Dish;
import com.example.knockoffeksemanprojekt.Model.Ingredient;
import com.example.knockoffeksemanprojekt.Model.MyUser;
import com.example.knockoffeksemanprojekt.Model.Recipe;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DBController {
    private JdbcTemplate jdbcTemplate;


    public DBController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public MyUser createUpdateUser (MyUser myUser){
        try {
            if(myUser.getUserId()==null){
                String sql="INSERT INTO user(userid,fname,sname,password,email,phoneNumber,weight,height,age,gender,activityLevel,goal,role) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                jdbcTemplate.update(sql, myUser.getUserId(), myUser.getFname(), myUser.getSname(), myUser.getPassword(), myUser.getEmail(), myUser.getPhoneNumber(), myUser.getWeight(), myUser.getHeight(), myUser.getAge(), myUser.getGender(), myUser.getActivityLevel(), myUser.getGoal(), myUser.getRole());
            }else{
                String sql = "UPDATE user SET fname=?,sname=?,password=?,email=?,phoneNumber=?,weight=?,height=?,age=?,gender=?,activityLevel=?,goal=?,role=? WHERE userId="+String.valueOf(myUser.getUserId());
                jdbcTemplate.update(sql, myUser.getUserId(), myUser.getFname(), myUser.getSname(), myUser.getPassword(), myUser.getEmail(), myUser.getPhoneNumber(), myUser.getWeight(), myUser.getHeight(), myUser.getAge(), myUser.getGender(), myUser.getActivityLevel(), myUser.getGoal(), myUser.getRole());
            }return myUser;
        } catch(DataAccessException e){
            throw new RuntimeException("Error creating user", e);
        }
    }
    public void deleteUserById(Long userId){
        String sql="DELETE FROM user where userId =?";
        jdbcTemplate.update(sql,userId);
    }

    public Optional<MyUser> findUserById(Long userId){
        try {
            String sql = "SELECT * FROM user WHERE userId=?";
            MyUser myUser = jdbcTemplate.queryForObject(sql, new Object[]{userId},userRowmapper());
            return Optional.ofNullable(myUser);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // Return empty Optional if no user is found
        } catch(DataAccessException e){
            throw new RuntimeException("Error accessing data while finding user", e);
        }
    }

    private RowMapper<MyUser> userRowmapper(){
        return (rs, rowNum) ->{
            MyUser myUser = new MyUser();
            myUser.setUserId(rs.getLong("userId"));
            myUser.setFname(rs.getString("fname"));
            myUser.setSname(rs.getString("sname"));
            myUser.setPassword(rs.getString("password"));
            myUser.setEmail(rs.getString("email"));
            myUser.setPhoneNumber(rs.getString("phoneNumber"));
            myUser.setWeight(rs.getDouble("weight"));
            myUser.setHeight(rs.getInt("height"));
            myUser.setAge(rs.getInt("age"));
            myUser.setGender(rs.getInt("gender"));
            myUser.setActivityLevel(rs.getInt("activityLevel"));
            myUser.setGoal(rs.getInt("goal"));
            myUser.setRole(rs.getString("role"));
            return myUser;
        };
    }


    public Ingredient createUpdateIngredient(Ingredient ingredient){
        try{
            if (ingredient.getIngredientId()==null){
                String sql="INSERT INTO ingredient(name,calories,protein,fat,carbs) VALUES (?,?,?,?,?)";
                jdbcTemplate.update(sql,ingredient.getName(),ingredient.getCalories(),ingredient.getProtein(),ingredient.getFat(),ingredient.getCarbs());
            }else{
                String sql="update ingredient set name=?,calories=?,protein=?,fat=?,carbs=? where ingredientId="+String.valueOf(ingredient.getIngredientId());
                jdbcTemplate.update(sql,ingredient.getName(),ingredient.getCalories(),ingredient.getProtein(),ingredient.getFat(),ingredient.getCarbs());
            }
            return ingredient;
        }catch(DataAccessException e){
            throw new RuntimeException("Error creating ingredient", e);
        }
    }
    public void deleteIngredientById(Long ingredientId){
        String sql="DELETE FROM ingredient where ingredientId =?";
        jdbcTemplate.update(sql,ingredientId);
    }

    public List<Dish> findAllDishes(){
        try {
            String sql="SELECT * FROM dish";
            return jdbcTemplate.query(sql,dishRowmapper());
        }catch(DataAccessException e){
            throw new RuntimeException("Error finding all dishes", e);
        }
    }

    public void deleteDishById(Long DishId){
        String sql="DELETE FROM dish where dishId =?";
        jdbcTemplate.update(sql,DishId);
    }

    public RowMapper<Dish> dishRowmapper(){
        return (rs, rowNum) ->{
            Dish dish = new Dish();
            dish.setName(rs.getString("name"));
            dish.setType(rs.getString("type"));
            return dish;
        };
    }

    public List<Recipe> findAllRecipes(){
        try {
            String sql="SELECT * FROM recipe";
            return jdbcTemplate.query(sql,recipeRowmapper());
        }catch(DataAccessException e){
            throw new RuntimeException("Error while finding all recipes",e);
        }
    }

    public Recipe createUpdateRecipe(Recipe recipe){
        try {
                if (recipe.getRecipeId()==null){
                    String sql="INSERT INTO recipe(calories,protein,fat,carbs,description) VALUES (?,?,?,?,?)";
                    jdbcTemplate.update(sql,recipe.getCalories(),recipe.getProtein(),recipe.getFat(),recipe.getCarbs(),recipe.getDescription());
                } else {
                    String sql="UPDATE recipe set calories=?,protein=?,fat=?,carbs=? where recipeId="+String.valueOf(recipe.getRecipeId());
                    jdbcTemplate.update(sql,recipe.getCalories(),recipe.getProtein(),recipe.getFat(),recipe.getCarbs(),recipe.getDescription());
                } return recipe;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteRecipeById(Long recipeId){
        String sql="DELETE FROM recipe where recipeId =?";
        jdbcTemplate.update(sql,recipeId);
    }

    public RowMapper<Recipe> recipeRowmapper(){
        return(rs, rowNum) ->{
            Recipe recipe = new Recipe();
            recipe.setCalories(rs.getInt("calories"));
            recipe.setProtein(rs.getInt("protein"));
            recipe.setFat(rs.getInt("fat"));
            recipe.setCarbs(rs.getInt("carbs"));
            recipe.setDescription(rs.getString("description"));
            return recipe;
        };
    }

}

