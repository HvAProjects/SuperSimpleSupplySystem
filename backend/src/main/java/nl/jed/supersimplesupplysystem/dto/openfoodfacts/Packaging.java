package nl.jed.supersimplesupplysystem.dto.openfoodfacts;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Packaging{

	@SerializedName("packagings")
	private List<PackagingsItem> packagings;

	@SerializedName("score")
	private Integer score;

	@SerializedName("warning")
	private String warning;

	@SerializedName("non_recyclable_and_non_biodegradable_materials")
	private Integer nonRecyclableAndNonBiodegradableMaterials;

	@SerializedName("value")
	private Integer value;
}