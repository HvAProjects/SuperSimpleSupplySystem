package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TagsItem{

	@SerializedName("known")
	private int known;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("url")
	private String url;

	@SerializedName("products")
	private int products;

	@SerializedName("sameAs")
	private List<String> sameAs;
}