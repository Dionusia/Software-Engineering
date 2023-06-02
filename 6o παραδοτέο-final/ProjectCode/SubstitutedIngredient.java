public class SubstitutedIngredient extends Ingredient {
    private Ingredient originalIngredient;
    private String substitute;

    public SubstitutedIngredient(Ingredient originalIngredient, String substitute) {
        super(originalIngredient.getName(), originalIngredient.getQuantity(), originalIngredient.getUnitOfMeasurement());
        this.originalIngredient = originalIngredient;
        this.substitute = substitute;
    }

    public Ingredient getOriginalIngredient() {
        return originalIngredient;
    }

    public String getSubstitute() {
        return substitute;
    }
}