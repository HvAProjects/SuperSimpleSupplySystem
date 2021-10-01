package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetLabelsResponse{

	@SerializedName("count")
	private Integer count;

	@SerializedName("tags")
	private List<TagsItem> tags;
}