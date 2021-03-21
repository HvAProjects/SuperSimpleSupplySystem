package nl.jed.supersimplesupplysystem.dto.openfoodfacts;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetBrandsResponse{

	@SerializedName("count")
	private Integer count;

	@SerializedName("tags")
	private List<TagsItem> tags;
}