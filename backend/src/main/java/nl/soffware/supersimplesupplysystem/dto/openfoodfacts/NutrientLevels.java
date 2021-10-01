package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class NutrientLevels{

	@SerializedName("sugars")
	private String sugars;

	@SerializedName("salt")
	private String salt;

	@SerializedName("fat")
	private String fat;

	@SerializedName("saturated-fat")
	private String saturatedFat;
}