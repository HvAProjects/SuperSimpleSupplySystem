package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class FrontNl{

	@SerializedName("rev")
	private String rev;

	@SerializedName("sizes")
	private Sizes sizes;

	@SerializedName("imgid")
	private String imgid;

	@SerializedName("normalize")
	private String normalize;

	@SerializedName("y1")
	private String y1;

	@SerializedName("white_magic")
	private String whiteMagic;

	@SerializedName("y2")
	private String y2;

	@SerializedName("angle")
	private String angle;

	@SerializedName("x1")
	private String x1;

	@SerializedName("geometry")
	private String geometry;

	@SerializedName("x2")
	private String x2;
}