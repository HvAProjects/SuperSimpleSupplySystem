package nl.jed.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class Front{

	@SerializedName("small")
	private Small small;

	@SerializedName("thumb")
	private Thumb thumb;

	@SerializedName("display")
	private Display display;
}