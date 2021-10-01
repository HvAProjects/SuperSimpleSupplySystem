package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class ThreatenedSpecies{

	@SerializedName("ingredient")
	private String ingredient;

	@SerializedName("value")
	private Integer value;
}