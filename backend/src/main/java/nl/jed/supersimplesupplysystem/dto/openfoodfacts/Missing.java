package nl.jed.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

public class Missing{

	@SerializedName("packagings")
	private Integer packagings;

	@SerializedName("origins")
	private Integer origins;

	@SerializedName("labels")
	private Integer labels;
}