package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class EcoscoreData{

	@SerializedName("score")
	private Integer score;

	@SerializedName("adjustments")
	private Adjustments adjustments;

	@SerializedName("grade")
	private String grade;

	@SerializedName("missing")
	private Missing missing;

	@SerializedName("agribalyse")
	private Agribalyse agribalyse;

	@SerializedName("status")
	private String status;

	@SerializedName("missing_data_warning")
	private Integer missingDataWarning;
}