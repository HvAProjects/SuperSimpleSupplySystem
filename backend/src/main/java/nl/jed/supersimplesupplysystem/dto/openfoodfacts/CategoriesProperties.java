package nl.jed.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class CategoriesProperties{

	@SerializedName("agribalyse_food_code:en")
	private String agribalyseFoodCodeEn;

	@SerializedName("ciqual_food_code:en")
	private String ciqualFoodCodeEn;
}