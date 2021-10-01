package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class Agribalyse{

	@SerializedName("name_fr")
	private String nameFr;

	@SerializedName("ef_total")
	private String efTotal;

	@SerializedName("co2_processing")
	private String co2Processing;

	@SerializedName("code")
	private String code;

	@SerializedName("dqr")
	private String dqr;

	@SerializedName("agribalyse_food_code")
	private String agribalyseFoodCode;

	@SerializedName("is_beverage")
	private Integer isBeverage;

	@SerializedName("co2_packaging")
	private String co2Packaging;

	@SerializedName("ef_consumption")
	private String efConsumption;

	@SerializedName("co2_total")
	private String co2Total;

	@SerializedName("ef_processing")
	private String efProcessing;

	@SerializedName("ef_agriculture")
	private String efAgriculture;

	@SerializedName("score")
	private Integer score;

	@SerializedName("co2_agriculture")
	private String co2Agriculture;

	@SerializedName("ef_distribution")
	private String efDistribution;

	@SerializedName("ef_transportation")
	private String efTransportation;

	@SerializedName("co2_consumption")
	private String co2Consumption;

	@SerializedName("co2_distribution")
	private String co2Distribution;

	@SerializedName("ef_packaging")
	private String efPackaging;

	@SerializedName("co2_transportation")
	private String co2Transportation;

	@SerializedName("name_en")
	private String nameEn;
}