package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "products",
        "id",
        "url",
        "name",
        "known"
})
public class Tag {
    @SerializedName("products")
    private Integer products;
    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;
    @SerializedName("name")
    private String name;
    @SerializedName("known")
    private Integer known;
}
