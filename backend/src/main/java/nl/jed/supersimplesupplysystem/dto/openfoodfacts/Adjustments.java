package nl.jed.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class Adjustments{

	@SerializedName("origins_of_ingredients")
	private OriginsOfIngredients originsOfIngredients;

	@SerializedName("packaging")
	private Packaging packaging;

	@SerializedName("threatened_species")
	private ThreatenedSpecies threatenedSpecies;

	@SerializedName("production_system")
	private ProductionSystem productionSystem;
}