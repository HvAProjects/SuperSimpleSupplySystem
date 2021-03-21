package nl.jed.supersimplesupplysystem.dto.openfoodfacts;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetCategoriesResponse{

	@SerializedName("count")
	private int count;

	@SerializedName("tags")
	private List<TagsItem> tags;
}