import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FoodDiary {
    private List<Meal> meals;
    private Scanner scanner;

    public FoodDiary() {
       meals = new ArrayList<>();
       this.scanner = new Scanner(System.in);
    }

    public void foodDiaryMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("--------------- FOOD DIARY ---------------");
            System.out.println("1. Display diary");
            System.out.println("2. Add food item");
            System.out.println("3. Remove food item");
            System.out.println("4. Update food item");
            System.out.println("5. Add custom meal");
            System.out.println("6. Search food item");
            System.out.println("7. Back to main menu");
            System.out.println("-------------------------------------------");
            int choice = readChoice();
            switch (choice) {
                case 1:
                    displayFoodDiary();
                    break;
                case 2:
                    recordMeal();
                    break;
                case 3:
                    removeMeal();
                    break;
                case 4:
                    updateMeal();
                    break;
                case 5:
                    addCustomMeal();
                    break;
                case 6:
                    searchBar();
                    break;
                case 7:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private int readChoice() {
        int choice = 0;
        boolean isValidInput = false;
        do {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); 
            }
        } while (!isValidInput);
        scanner.nextLine();
        return choice;
    }

    public void addMeal(Meal meal) {
       meals.add(meal);
    }

    public void removeMeal() {
            if (meals.isEmpty()) {
                System.out.println("Your food diary is empty.");
                return;
            }
        
            System.out.println("Please select a meal to remove:");
        
            // Display list of meals for the user to select
            for (int i = 0; i < meals.size(); i++) {
                System.out.println((i + 1) + ". " + meals.get(i).getFoodItem().getName());
            }
        
            // user selects food to remove
            int choice = 0;
            do {
                System.out.print("Enter your choice (1-" + meals.size() + "): ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            } while (choice < 1 || choice > meals.size());
        
            // Remove the selection of user of his list
            Meal mealToRemove = meals.get(choice - 1);
            meals.remove(mealToRemove);
            System.out.println("Removed meal: " + mealToRemove.getFoodItem().getName());
    }  

    public void updateMeal() {
        // Display list of meals
        if (meals.isEmpty()) {
            System.out.println("Your food diary is empty.");
            return;
        }
        System.out.println("Your food diary:");
        for (int i = 0; i < meals.size(); i++) {
            System.out.println((i + 1) + ". " + meals.get(i).getFoodItem().getName());
        }
    
        // Ask user to select meal to update
        int mealIndex = -1;
        while (mealIndex < 0 || mealIndex >= meals.size()) {
            System.out.println("Enter the number of the meal you want to update:");
            try {
                mealIndex = scanner.nextInt() - 1;
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
            }
        }
    
        // Ask user for new details
        Meal mealToUpdate = meals.get(mealIndex);
        FoodItem foodItem = mealToUpdate.getFoodItem();
        System.out.println("Enter new details for the selected meal:");
        System.out.println("Please enter the name of the food (currently: " + foodItem.getName() + "):");
        String name = scanner.nextLine();
    
        NutritionInfo nutritionInfo = foodItem.getNutritionInfo();
        System.out.println("Please enter the calories (currently: " + nutritionInfo.getCalories() + "):");
        int calories = scanner.nextInt();
        System.out.println("Please enter the fat (currently: " + nutritionInfo.getFat() + "):");
        int fat = scanner.nextInt();
        System.out.println("Please enter the cholesterol (currently: " + nutritionInfo.getCholesterol() + "):");
        int cholesterol = scanner.nextInt();
        System.out.println("Please enter the carbohydrates (currently: " + nutritionInfo.getCarbohydrates() + "):");
        int carbohydrates = scanner.nextInt();
        System.out.println("Please enter the fiber (currently: " + nutritionInfo.getFiber() + "):");
        int fiber = scanner.nextInt();
        System.out.println("Please enter the protein (currently: " + nutritionInfo.getProtein() + "):");
        int protein = scanner.nextInt();
        System.out.println("Please enter the vitamins (currently: " + nutritionInfo.getVitamins() + "):");
        int vitamins = scanner.nextInt();
        scanner.nextLine(); 
    
        String notes = foodItem.getNotes();
        System.out.println("Please enter the notes (currently: " + notes + "):");
        notes = scanner.nextLine();
        String tags = foodItem.getTag();
        System.out.println("Please enter the tags (currently: " + tags + "):");
        tags = scanner.nextLine();
    
        System.out.println("Please enter the category (currently: " + mealToUpdate.getCategory() + "):");
        System.out.println("Please select a category of meal or snack you want to record:");
        System.out.println("1. Breakfast");
        System.out.println("2. Lunch");
        System.out.println("3. Dinner");
        System.out.println("4. Snack");
        int categoryChoice = scanner.nextInt();
        Meal.MealCategory category = null;
        switch (categoryChoice) {
            case 1:
                category = Meal.MealCategory.BREAKFAST;
                break;
            case 2:
                category = Meal.MealCategory.LUNCH;
                break;
            case 3:
                category = Meal.MealCategory.DINNER;
                break;
            case 4:
                category = Meal.MealCategory.SNACK;
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }
    
        NutritionInfo newNutritionInfo = new NutritionInfo(calories, fat, cholesterol, carbohydrates, fiber, protein, vitamins);
        FoodItem newFoodItem = new FoodItem(name, newNutritionInfo, notes, tags);
        Meal newMeal = new Meal(category, newFoodItem);
        meals.set(mealIndex, newMeal);
    
        System.out.println("Meal successfully updated.");
    }

    public void recordMeal() {
        System.out.println("Please select a category of meal or snack you want to record:");
        System.out.println("1. Breakfast");
        System.out.println("2. Lunch");
        System.out.println("3. Dinner");
        System.out.println("4. Snack");
        int categoryChoice = scanner.nextInt();
        Meal.MealCategory category = null;
        switch (categoryChoice) {
            case 1:
                category = Meal.MealCategory.BREAKFAST;
                break;
            case 2:
                category = Meal.MealCategory.LUNCH;
                break;
            case 3:
                category = Meal.MealCategory.DINNER;
                break;
            case 4:
                category = Meal.MealCategory.SNACK;
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }
    
        System.out.println("Please select an option to add the food:");
        System.out.println("1. Manually enter the food information");
        System.out.println("2. Scan the barcode using the phone's camera");
        int optionChoice = scanner.nextInt();
        FoodItem foodItem = null;
        switch (optionChoice) {
            case 1:
                System.out.println("Please enter the name of the food:");
                String name = scanner.next();
                int calories = 0;
                while (true) {
                    try {
                        System.out.println("Please enter the calories:");
                        calories = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number for calories.");
                        scanner.nextLine(); // consume the invalid input
                    }
                }

                int fat = 0;
                while (true) {
                    try {
                        System.out.println("Please enter the fat:");
                        fat = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number for fat.");
                        scanner.nextLine(); // consume the invalid input
                    }
                }

                int cholesterol = 0;
                while (true) {
                    try {
                        System.out.println("Please enter the cholesterol:");
                        cholesterol = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number for cholesterol.");
                        scanner.nextLine(); // consume the invalid input
                    }
                }

                int carbohydrates = 0;
                while (true) {
                    try {
                        System.out.println("Please enter the carbohydrates:");
                        carbohydrates = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number for carbohydrates.");
                        scanner.nextLine(); // consume the invalid input
                    }
                }

                int fiber = 0;
                while (true) {
                    try {
                        System.out.println("Please enter the fiber:");
                        fiber = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number for fiber.");
                        scanner.nextLine(); // consume the invalid input
                    }
                }

                int protein = 0;
                while (true) {
                    try {
                        System.out.println("Please enter the protein:");
                        protein = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number for protein.");
                        scanner.nextLine(); // consume the invalid input
                    }
                }

                int vitamins = 0;
                while (true) {
                    try {
                        System.out.println("Please enter the number of vitamins:");
                        vitamins = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number for vitamins.");
                        scanner.nextLine(); // consume the invalid input
                    }
                }
                NutritionInfo nutritionInfo = new NutritionInfo(calories, fat, cholesterol, carbohydrates, fiber, protein, vitamins);
                scanner.nextLine(); // Consume the extra newline character
                System.out.println("Please enter any notes or details about the food:");
                String notes = scanner.nextLine();
                System.out.println("Please enter a custom name or tag for the food:");
                String tag = scanner.next();
                foodItem = new FoodItem(name, nutritionInfo, notes, tag);
                break;
            case 2:
                System.out.println("Barcode Implementation");
                return;
            default:
                System.out.println("Invalid choice");
                return;
        }
    
        Meal meal = new Meal(category, foodItem);
        addMeal(meal);
        System.out.println("Meal recorded successfully!");
    }

    public void addCustomMeal() {

        System.out.println("Please select a category of meal or snack you want to record:");
        System.out.println("1. Breakfast");
        System.out.println("2. Lunch");
        System.out.println("3. Dinner");
        System.out.println("4. Snack");
        int categoryChoice = scanner.nextInt();
        Meal.MealCategory category = null;
        switch (categoryChoice) {
            case 1:
                category = Meal.MealCategory.BREAKFAST;
                break;
            case 2:
                category = Meal.MealCategory.LUNCH;
                break;
            case 3:
                category = Meal.MealCategory.DINNER;
                break;
            case 4:
                category = Meal.MealCategory.SNACK;
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }
        // Ask user for new details
        System.out.println("Enter new details for the custom meal:");

        System.out.println("Please enter the name of the custom meal:");
        String name = scanner.next();
    
        System.out.println("Please enter the calories:");
        int calories = scanner.nextInt();
        System.out.println("Please enter the fat:");
        int fat = scanner.nextInt();
        System.out.println("Please enter the cholesterol:");
        int cholesterol = scanner.nextInt();
        System.out.println("Please enter the carbohydrates:");
        int carbohydrates = scanner.nextInt();
        System.out.println("Please enter the fiber:");
        int fiber = scanner.nextInt();
        System.out.println("Please enter the protein:");
        int protein = scanner.nextInt();
        System.out.println("Please enter the vitamins:");
        int vitamins = scanner.nextInt();
        scanner.nextLine(); 
    
        System.out.println("Please enter any notes for the custom meal:");
        String notes = scanner.nextLine();
        System.out.println("Please enter any tags for the custom meal:");
        String tags = scanner.nextLine();
    
        System.out.println("Please enter any dietary restrictions for the custom meal (separated by commas):");
        String dietaryRestrictionsString = scanner.nextLine();
        List<String> dietaryRestrictions = Arrays.asList(dietaryRestrictionsString.split("\\s*,\\s*"));
    
        System.out.println("Please enter the ingredients for the custom meal (one ingredient per line, end with empty line):");
        List<String> ingredients = new ArrayList<>();
        String ingredient = "";
        while (true) {
            ingredient = scanner.nextLine();
            if (ingredient.isEmpty()) {
                break;
            }
            ingredients.add(ingredient);
        }
    
        NutritionInfo nutritionInfo = new NutritionInfo(calories, fat, cholesterol, carbohydrates, fiber, protein, vitamins);
        CustomMeal customMeal = new CustomMeal(name, nutritionInfo, notes, tags, dietaryRestrictions, ingredients);
        Meal meal = new Meal(category, customMeal);
        meals.add(meal);
    
        System.out.println("Custom meal added successfully.");
    }

    
    public void searchBar() {
        //create a list manually to display
        NutritionInfo appleNutrition = new NutritionInfo(95, 3, 0, 25, 4, 10, 2);
        FoodItem apple = new FoodItem("Apple", appleNutrition, "Fresh and crunchy", null);
    
        NutritionInfo bananaNutrition = new NutritionInfo(111, 3, 0, 25, 4, 10, 1);
        FoodItem banana = new FoodItem("Banana", bananaNutrition, "Sweet and ripe", null);
    
        NutritionInfo chickenNutrition = new NutritionInfo(150, 3, 0, 25, 4, 15, 5);
        FoodItem chickenBreast = new FoodItem("Chicken Breast", chickenNutrition, "Grilled and seasoned", null);
    
        NutritionInfo riceNutrition = new NutritionInfo(76, 3, 0, 25, 4, 20, 3);
        FoodItem rice = new FoodItem("Brown Rice", riceNutrition, "Steamed and fluffy", null);
    
        // Add food items to list
        List<FoodItem> foodItems = new ArrayList<>();
        foodItems.add(apple);
        foodItems.add(banana);
        foodItems.add(chickenBreast);
        foodItems.add(rice);
    
        System.out.println("Search for a meal:");
        String query = scanner.nextLine();
    
        //food items that match the query
        List<FoodItem> matchingFoodItems = new ArrayList<>();
        for (FoodItem foodItem : foodItems) {
            if (foodItem.getName().toLowerCase().contains(query.toLowerCase())) {
                matchingFoodItems.add(foodItem);
            }
        }
    
        // Display matching food items and let user choose which one to add
        if (matchingFoodItems.isEmpty()) {
            System.out.println("No matching food items found.");
        } else {
            System.out.println("Matching food items:");
            for (int i = 0; i < matchingFoodItems.size(); i++) {
                System.out.println((i + 1) + ". " + matchingFoodItems.get(i).getName());
            }
            System.out.println("Enter the number of the food item you want to add:");
            int choice = scanner.nextInt();
            if (choice < 1 || choice > matchingFoodItems.size()) {
                System.out.println("Invalid choice.");
            } else {
                Meal meal = new Meal(Meal.MealCategory.BREAKFAST, matchingFoodItems.get(choice - 1));
                meals.add(meal);
                System.out.println("Added " + matchingFoodItems.get(choice - 1).getName() + " to your food diary.");
            }
        }
    }

    public void displayFoodDiary() {
        if (meals.isEmpty()) {
            System.out.println("Your food diary is empty.");
        } else {
            System.out.println("Your food diary:");
            Collections.sort(meals, new Comparator<Meal>() {
                @Override
                public int compare(Meal m1, Meal m2) {
                    return m1.getFoodItem().getName().compareTo(m2.getFoodItem().getName());
                }
            });
            for (Meal meal : meals) {
                System.out.println(meal.getFoodName());
            }
        }
    }
}