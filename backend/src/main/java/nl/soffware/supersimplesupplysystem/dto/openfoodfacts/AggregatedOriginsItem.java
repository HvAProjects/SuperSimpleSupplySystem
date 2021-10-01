package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class AggregatedOriginsItem{

	@SerializedName("origin")
	private String origin;

	@SerializedName("percent")
	private Integer percent;
}