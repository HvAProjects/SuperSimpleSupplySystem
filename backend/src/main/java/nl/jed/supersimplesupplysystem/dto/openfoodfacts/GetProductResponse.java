package nl.jed.supersimplesupplysystem.dto.openfoodfacts;

import com.google.gson.annotations.SerializedName;
import nl.jed.supersimplesupplysystem.models.ProductType;

public class GetProductResponse{

	private static final int PRODUCT_FOUND = 1;

	@SerializedName("product")
	private Product product;

	@SerializedName("code")
	private String code;

	@SerializedName("status_verbose")
	private String statusVerbose;

	@SerializedName("status")
	private Integer status;

	public ProductType toProductType() {
		if (this.status != PRODUCT_FOUND) { // Product not found
			return null;
		}
		ProductType productType = new ProductType();
		productType.setBarcode(this.product.getCode());
		productType.setName(this.product.getProductName());
		productType.setImageUrl(this.product.getImageUrl());
		productType.setBrands(this.product.getBrands());
		productType.setQuantity(this.product.getQuantity());
		return productType;
	}
}