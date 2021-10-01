package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class CategoryProperties{

	@SerializedName("ciqual_food_name:en")
	private String ciqualFoodNameEn;

	@SerializedName("ciqual_food_name:fr")
	private String ciqualFoodNameFr;
}