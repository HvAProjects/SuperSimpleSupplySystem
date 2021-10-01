package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class IngredientsItem{

	@SerializedName("percent_max")
	private String percentMax;

	@SerializedName("from_palm_oil")
	private String fromPalmOil;

	@SerializedName("percent_min")
	private String percentMin;

	@SerializedName("vegetarian")
	private String vegetarian;

	@SerializedName("percent_estimate")
	private String percentEstimate;

	@SerializedName("text")
	private String text;

	@SerializedName("id")
	private String id;

	@SerializedName("vegan")
	private String vegan;

	@SerializedName("rank")
	private Integer rank;

	@SerializedName("has_sub_ingredients")
	private String hasSubIngredients;

	@SerializedName("percent")
	private String percent;
}