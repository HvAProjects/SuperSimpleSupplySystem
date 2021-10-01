package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OriginsOfIngredients{

	@SerializedName("epi_score")
	private Integer epiScore;

	@SerializedName("origins_from_origins_field")
	private List<String> originsFromOriginsField;

	@SerializedName("aggregated_origins")
	private List<AggregatedOriginsItem> aggregatedOrigins;

	@SerializedName("transportation_value")
	private Integer transportationValue;

	@SerializedName("warning")
	private String warning;

	@SerializedName("epi_value")
	private Integer epiValue;

	@SerializedName("value")
	private Integer value;

	@SerializedName("transportation_score")
	private Integer transportationScore;
}