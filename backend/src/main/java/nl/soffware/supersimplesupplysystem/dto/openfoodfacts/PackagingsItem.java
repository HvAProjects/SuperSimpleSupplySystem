package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class PackagingsItem{

	@SerializedName("non_recyclable_and_non_biodegradable")
	private String nonRecyclableAndNonBiodegradable;

	@SerializedName("material")
	private String material;

	@SerializedName("shape")
	private String shape;

	@SerializedName("ecoscore_shape_ratio")
	private Integer ecoscoreShapeRatio;

	@SerializedName("ecoscore_material_score")
	private Integer ecoscoreMaterialScore;
}