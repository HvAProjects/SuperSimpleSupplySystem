package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class SelectedImages{

	@SerializedName("nutrition")
	private Nutrition nutrition;

	@SerializedName("ingredients")
	private Ingredients ingredients;

	@SerializedName("front")
	private Front front;
}