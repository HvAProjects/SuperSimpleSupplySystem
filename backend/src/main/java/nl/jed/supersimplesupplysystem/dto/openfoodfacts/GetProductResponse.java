package nl.jed.supersimplesupplysystem.dto.openfoodfacts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.jed.supersimplesupplysystem.models.ProductType;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class GetProductResponse {
    @JsonProperty("status")
    private int status;

    @JsonProperty("status_verbose")
    private String verbose_status;

    @JsonProperty("product")
    private Product product;

    @JsonProperty("status")
    public int getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(int status) {
        this.status = status;
    }

    @JsonProperty("status_verbose")
    public String getVerboseStatus() {
        return verbose_status;
    }

    @JsonProperty("status_verbose")
    public void setVerboseStatus(String verboseStatus) {
        this.verbose_status = verboseStatus;
    }

    @JsonProperty("product")
    public Product getProduct() {
        return product;
    }

    @JsonProperty("product")
    public void setProduct(Product product) {
        this.product = product;
    }

    public class Product {

        @JsonProperty("product_quantity")
        private int product_quantaty;

        @JsonProperty("brands")
        private String brands;

        @JsonProperty("product_name")
        private String product_name;

        @JsonProperty("product_quantity")
        public int getProductQuantity() {
            return product_quantaty;
        }

        @JsonProperty("product_quantity")
        public void setProductQuantity(int productQuantity) {
            this.product_quantaty = productQuantity;
        }

        @JsonProperty("brands")
        public String getBrands() {
            return brands;
        }

        @JsonProperty("brands")
        public void setBrands(String brands) {
            this.brands = brands;
        }

        @JsonProperty("product_name")
        public String getProduct_name() {
            return product_name;
        }

        @JsonProperty("product_name")
        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }
    }

    public ProductType toProductType() {
        ProductType productType = new ProductType();
        productType.setName(this.product.product_name);
        productType.setUnit_amount(this.product.product_quantaty);
        return productType;
    }
}
