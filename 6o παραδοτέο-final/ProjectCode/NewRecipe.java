import java.util.ArrayList;
import java.util.List;

public class NewRecipe extends Recipes {

    public void collectRecipeInformation() {
        System.out.println("Enter the name of the recipe:");
        String recipeName = scanner.nextLine();
        setName(recipeName);

        System.out.println("Enter the cooking time (in minutes):");
        int cookingTime = readChoice();
        setCookingTime(cookingTime);

        System.out.println("Enter the serving size:");
        int servingSize = readChoice();
        setServingSize(servingSize);

        List<Ingredient> ingredients = collectIngredients();
        setIngredients(ingredients);

        List<String> instructions = collectInstructions();
        setInstructions(instructions);
    }

    private List<Ingredient> collectIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        boolean isAddingIngredients = true;
        while (isAddingIngredients) {
            System.out.println("Enter the ingredient name:");
            String ingredientName = scanner.nextLine();

            System.out.println("Enter the quantity:");
            double quantity = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter the unit of measurement:");
            String unitOfMeasurement = scanner.nextLine();

            Ingredient ingredient = new Ingredient(ingredientName, quantity, unitOfMeasurement);
            ingredients.add(ingredient);

            System.out.println("Do you want to add another ingredient? (yes/no)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("no")) {
                isAddingIngredients = false;
            }
        }
        return ingredients;
    }

    private List<String> collectInstructions() {
        List<String> instructions = new ArrayList<>();
        boolean isAddingInstructions = true;
        while (isAddingInstructions) {
            System.out.println("Enter the instruction:");
            String instruction = scanner.nextLine();
            instructions.add(instruction);

            System.out.println("Do you want to add another instruction? (yes/no)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("no")) {
                isAddingInstructions = false;
            }
        }
        return instructions;
    }
}