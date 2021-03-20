package nl.jed.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class NutritionNl{

	@SerializedName("orientation")
	private String orientation;

	@SerializedName("rev")
	private String rev;

	@SerializedName("imgid")
	private String imgid;

	@SerializedName("white_magic")
	private String whiteMagic;

	@SerializedName("sizes")
	private Sizes sizes;

	@SerializedName("normalize")
	private String normalize;

	@SerializedName("y1")
	private String y1;

	@SerializedName("x1")
	private String x1;

	@SerializedName("angle")
	private String angle;

	@SerializedName("y2")
	private String y2;

	@SerializedName("geometry")
	private String geometry;

	@SerializedName("x2")
	private String x2;

	@SerializedName("ocr")
	private Integer ocr;
}