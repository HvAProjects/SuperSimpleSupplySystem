package nl.soffware.supersimplesupplysystem.dto.openfoodfacts;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Product{

	@SerializedName("vitamins_tags")
	private List<Object> vitaminsTags;

	@SerializedName("allergens_from_ingredients")
	private String allergensFromIngredients;

	@SerializedName("ingredients_original_tags")
	private List<String> ingredientsOriginalTags;

	@SerializedName("category_properties")
	private CategoryProperties categoryProperties;

	@SerializedName("allergens_lc")
	private String allergensLc;

	@SerializedName("nutriments")
	private Nutriments nutriments;

	@SerializedName("codes_tags")
	private List<String> codesTags;

	@SerializedName("nutrient_levels")
	private NutrientLevels nutrientLevels;

	@SerializedName("additives_tags")
	private List<Object> additivesTags;

	@SerializedName("brands_tags")
	private List<String> brandsTags;

	@SerializedName("image_ingredients_thumb_url")
	private String imageIngredientsThumbUrl;

	@SerializedName("last_image_dates_tags")
	private List<String> lastImageDatesTags;

	@SerializedName("nucleotides_tags")
	private List<Object> nucleotidesTags;

	@SerializedName("popularity_key")
	private Long popularityKey;

	@SerializedName("ingredients_n_tags")
	private List<String> ingredientsNTags;

	@SerializedName("countries")
	private String countries;

	@SerializedName("labels")
	private String labels;

	@SerializedName("image_front_small_url")
	private String imageFrontSmallUrl;

	@SerializedName("ingredients_from_or_that_may_be_from_palm_oil_n")
	private Double ingredientsFromOrThatMayBeFromPalmOilN;

	@SerializedName("nutrition_grade_fr")
	private String nutritionGradeFr;

	@SerializedName("cities_tags")
	private List<Object> citiesTags;

	@SerializedName("data_quality_tags")
	private List<String> dataQualityTags;

	@SerializedName("nova_groups_tags")
	private List<String> novaGroupsTags;

	@SerializedName("ingredients_text_en")
	private String ingredientsTextEn;

	@SerializedName("checkers_tags")
	private List<Object> checkersTags;

	@SerializedName("pnns_groups_1_tags")
	private List<String> pnnsGroups1Tags;

	@SerializedName("states_tags")
	private List<String> statesTags;

	@SerializedName("languages_codes")
	private LanguagesCodes languagesCodes;

	@SerializedName("generic_name_fr")
	private String genericNameFr;

	@SerializedName("additives_original_tags")
	private List<Object> additivesOriginalTags;

	@SerializedName("ingredients")
	private List<IngredientsItem> ingredients;

	@SerializedName("origins")
	private String origins;

	@SerializedName("packaging_text_nl")
	private String packagingTextNl;

	@SerializedName("brands")
	private String brands;

	@SerializedName("labels_lc")
	private String labelsLc;

	@SerializedName("languages")
	private Languages languages;

	@SerializedName("ciqual_food_name_tags")
	private List<String> ciqualFoodNameTags;

	@SerializedName("ingredients_text_with_allergens")
	private String ingredientsTextWithAllergens;

	@SerializedName("manufacturing_places")
	private String manufacturingPlaces;

	@SerializedName("ingredients_text_with_allergens_nl")
	private String ingredientsTextWithAllergensNl;

	@SerializedName("categories_hierarchy")
	private List<String> categoriesHierarchy;

	@SerializedName("categories_old")
	private String categoriesOld;

	@SerializedName("editors_tags")
	private List<String> editorsTags;

	@SerializedName("origins_tags")
	private List<Object> originsTags;

	@SerializedName("nutriscore_grade")
	private String nutriscoreGrade;

	@SerializedName("additives_old_tags")
	private List<Object> additivesOldTags;

	@SerializedName("nutrition_grades")
	private String nutritionGrades;

	@SerializedName("ingredients_debug")
	private List<String> ingredientsDebug;

	@SerializedName("correctors_tags")
	private List<String> correctorsTags;

	@SerializedName("emb_codes_tags")
	private List<Object> embCodesTags;

	@SerializedName("debug_param_sorted_langs")
	private List<String> debugParamSortedLangs;

	@SerializedName("image_front_thumb_url")
	private String imageFrontThumbUrl;

	@SerializedName("ingredients_tags")
	private List<String> ingredientsTags;

	@SerializedName("completeness")
	private Double completeness;

	@SerializedName("expiration_date")
	private String expirationDate;

	@SerializedName("image_nutrition_url")
	private String imageNutritionUrl;

	@SerializedName("packagings")
	private List<PackagingsItem> packagings;

	@SerializedName("nova_group")
	private Double novaGroup;

	@SerializedName("nutrition_data_prepared")
	private String nutritionDataPrepared;

	@SerializedName("nutriscore_score")
	private Double nutriscoreScore;

	@SerializedName("languages_tags")
	private List<String> languagesTags;

	@SerializedName("serving_size")
	private String servingSize;

	@SerializedName("ingredients_text_fr")
	private String ingredientsTextFr;

	@SerializedName("ingredients_ids_debug")
	private List<String> ingredientsIdsDebug;

	@SerializedName("rev")
	private Double rev;

	@SerializedName("ecoscore_grade")
	private String ecoscoreGrade;

	@SerializedName("allergens_from_user")
	private String allergensFromUser;

	@SerializedName("ingredients_from_palm_oil_tags")
	private List<Object> ingredientsFromPalmOilTags;

	@SerializedName("traces_lc")
	private String tracesLc;

	@SerializedName("labels_hierarchy")
	private List<Object> labelsHierarchy;

	@SerializedName("no_nutrition_data")
	private String noNutritionData;

	@SerializedName("product_name_nl")
	private String productNameNl;

	@SerializedName("packaging_text")
	private String packagingText;

	@SerializedName("nova_group_tags")
	private List<String> novaGroupTags;

	@SerializedName("generic_name_en")
	private String genericNameEn;

	@SerializedName("obsolete")
	private String obsolete;

	@SerializedName("unknown_nutrients_tags")
	private List<Object> unknownNutrientsTags;

	@SerializedName("nutriscore_score_opposite")
	private Double nutriscoreScoreOpposite;

	@SerializedName("states")
	private String states;

	@SerializedName("last_edit_dates_tags")
	private List<String> lastEditDatesTags;

	@SerializedName("image_ingredients_url")
	private String imageIngredientsUrl;

	@SerializedName("data_quality_bugs_tags")
	private List<Object> dataQualityBugsTags;

	@SerializedName("environment_impact_level_tags")
	private List<Object> environmentImpactLevelTags;

	@SerializedName("origins_old")
	private String originsOld;

	@SerializedName("other_nutritional_substances_tags")
	private List<Object> otherNutritionalSubstancesTags;

	@SerializedName("vitamins_prev_tags")
	private List<Object> vitaminsPrevTags;

	@SerializedName("nova_groups")
	private String novaGroups;

	@SerializedName("pnns_groups_2_tags")
	private List<String> pnnsGroups2Tags;

	@SerializedName("serving_quantity")
	private String servingQuantity;

	@SerializedName("image_nutrition_thumb_url")
	private String imageNutritionThumbUrl;

	@SerializedName("data_quality_info_tags")
	private List<String> dataQualityInfoTags;

	@SerializedName("last_modified_t")
	private Double lastModifiedT;

	@SerializedName("packaging")
	private String packaging;

	@SerializedName("amino_acids_prev_tags")
	private List<Object> aminoAcidsPrevTags;

	@SerializedName("emb_codes_orig")
	private String embCodesOrig;

	@SerializedName("image_thumb_url")
	private String imageThumbUrl;

	@SerializedName("allergens")
	private String allergens;

	@SerializedName("ingredients_analysis_tags")
	private List<String> ingredientsAnalysisTags;

	@SerializedName("additives_old_n")
	private Double additivesOldN;

	@SerializedName("data_sources_tags")
	private List<String> dataSourcesTags;

	@SerializedName("max_imgid")
	private String maxImgid;

	@SerializedName("ingredients_n")
	private Double ingredientsN;

	@SerializedName("stores_tags")
	private List<String> storesTags;

	@SerializedName("allergens_hierarchy")
	private List<String> allergensHierarchy;

	@SerializedName("ecoscore_tags")
	private List<String> ecoscoreTags;

	@SerializedName("nutrition_data")
	private String nutritionData;

	@SerializedName("packaging_text_en")
	private String packagingTextEn;

	@SerializedName("additives_debug_tags")
	private List<Object> additivesDebugTags;

	@SerializedName("languages_hierarchy")
	private List<String> languagesHierarchy;

	@SerializedName("unique_scans_n")
	private Double uniqueScansN;

	@SerializedName("id")
	private String id;

	@SerializedName("amino_acids_tags")
	private List<Object> aminoAcidsTags;

	@SerializedName("image_small_url")
	private String imageSmallUrl;

	@SerializedName("image_url")
	private String imageUrl;

	@SerializedName("image_ingredients_small_url")
	private String imageIngredientsSmallUrl;

	@SerializedName("purchase_places")
	private String purchasePlaces;

	@SerializedName("entry_dates_tags")
	private List<String> entryDatesTags;

	@SerializedName("traces_tags")
	private List<Object> tracesTags;

	@SerializedName("image_nutrition_small_url")
	private String imageNutritionSmallUrl;

	@SerializedName("generic_name_nl")
	private String genericNameNl;

	@SerializedName("ingredients_text_with_allergens_en")
	private String ingredientsTextWithAllergensEn;

	@SerializedName("categories_lc")
	private String categoriesLc;

	@SerializedName("manufacturing_places_tags")
	private List<Object> manufacturingPlacesTags;

	@SerializedName("categories_properties_tags")
	private List<String> categoriesPropertiesTags;

	@SerializedName("allergens_tags")
	private List<String> allergensTags;

	@SerializedName("product_quantity")
	private String productQuantity;

	@SerializedName("sortkey")
	private Double sortkey;

	@SerializedName("traces_from_ingredients")
	private String tracesFromIngredients;

	@SerializedName("nutrition_data_prepared_per")
	private String nutritionDataPreparedPer;

	@SerializedName("origins_lc")
	private String originsLc;

	@SerializedName("nutrient_levels_tags")
	private List<String> nutrientLevelsTags;

	@SerializedName("categories")
	private String categories;

	@SerializedName("interface_version_created")
	private String interfaceVersionCreated;

	@SerializedName("creator")
	private String creator;

	@SerializedName("purchase_places_tags")
	private List<Object> purchasePlacesTags;

	@SerializedName("ecoscore_data")
	private EcoscoreData ecoscoreData;

	@SerializedName("stores")
	private String stores;

	@SerializedName("traces_from_user")
	private String tracesFromUser;

	@SerializedName("nutrition_score_warning_fruits_vegetables_nuts_estimate_from_ingredients_value")
	private Double nutritionScoreWarningFruitsVegetablesNutsEstimateFromIngredientsValue;

	@SerializedName("countries_tags")
	private List<String> countriesTags;

	@SerializedName("data_sources")
	private String dataSources;

	@SerializedName("nova_group_debug")
	private String novaGroupDebug;

	@SerializedName("ingredients_text")
	private String ingredientsText;

	@SerializedName("ingredients_text_debug")
	private String ingredientsTextDebug;

	@SerializedName("lc")
	private String lc;

	@SerializedName("created_t")
	private Double createdT;

	@SerializedName("compared_to_category")
	private String comparedToCategory;

	@SerializedName("selected_images")
	private SelectedImages selectedImages;

	@SerializedName("origins_hierarchy")
	private List<Object> originsHierarchy;

	@SerializedName("data_quality_errors_tags")
	private List<Object> dataQualityErrorsTags;

	@SerializedName("pnns_groups_1")
	private String pnnsGroups1;

	@SerializedName("complete")
	private Double complete;

	@SerializedName("pnns_groups_2")
	private String pnnsGroups2;

	@SerializedName("labels_tags")
	private List<Object> labelsTags;

	@SerializedName("data_quality_warnings_tags")
	private List<String> dataQualityWarningsTags;

	@SerializedName("packaging_tags")
	private List<String> packagingTags;

	@SerializedName("update_key")
	private String updateKey;

	@SerializedName("known_ingredients_n")
	private Double knownIngredientsN;

	@SerializedName("minerals_prev_tags")
	private List<Object> mineralsPrevTags;

	@SerializedName("product_name_en")
	private String productNameEn;

	@SerializedName("_keywords")
	private List<String> keywords;

	@SerializedName("categories_properties")
	private CategoriesProperties categoriesProperties;

	@SerializedName("informers_tags")
	private List<String> informersTags;

	@SerializedName("last_editor")
	private String lastEditor;

	@SerializedName("emb_codes")
	private String embCodes;

	@SerializedName("product_name_fr")
	private String productNameFr;

	@SerializedName("generic_name")
	private String genericName;

	@SerializedName("traces")
	private String traces;

	@SerializedName("ingredients_that_may_be_from_palm_oil_n")
	private Double ingredientsThatMayBeFromPalmOilN;

	@SerializedName("traces_hierarchy")
	private List<Object> tracesHierarchy;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("ingredients_text_nl")
	private String ingredientsTextNl;

	@SerializedName("minerals_tags")
	private List<Object> mineralsTags;

	@SerializedName("obsolete_since_date")
	private String obsoleteSinceDate;

	@SerializedName("photographers_tags")
	private List<String> photographersTags;

	@SerializedName("countries_lc")
	private String countriesLc;

	@SerializedName("states_hierarchy")
	private List<String> statesHierarchy;

	@SerializedName("interface_version_modified")
	private String interfaceVersionModified;

	@SerializedName("_id")
	private String _id;

	@SerializedName("code")
	private String code;

	@SerializedName("ingredients_from_palm_oil_n")
	private Double ingredientsFromPalmOilN;

	@SerializedName("misc_tags")
	private List<String> miscTags;

	@SerializedName("ecoscore_score")
	private Double ecoscoreScore;

	@SerializedName("link")
	private String link;

	@SerializedName("additives_n")
	private Double additivesN;

	@SerializedName("environment_impact_level")
	private String environmentImpactLevel;

	@SerializedName("categories_tags")
	private List<String> categoriesTags;

	@SerializedName("unknown_ingredients_n")
	private Double unknownIngredientsN;

	@SerializedName("nutrition_score_warning_fruits_vegetables_nuts_estimate_from_ingredients")
	private Double nutritionScoreWarningFruitsVegetablesNutsEstimateFromIngredients;

	@SerializedName("nutrition_data_per")
	private String nutritionDataPer;

	@SerializedName("lang")
	private String lang;

	@SerializedName("additives_prev_original_tags")
	private List<Object> additivesPrevOriginalTags;

	@SerializedName("ingredients_hierarchy")
	private List<String> ingredientsHierarchy;

	@SerializedName("image_front_url")
	private String imageFrontUrl;

	@SerializedName("scans_n")
	private Double scansN;

	@SerializedName("countries_hierarchy")
	private List<String> countriesHierarchy;

	@SerializedName("quantity")
	private String quantity;

	@SerializedName("nucleotides_prev_tags")
	private List<Object> nucleotidesPrevTags;

	@SerializedName("last_modified_by")
	private String lastModifiedBy;

	@SerializedName("popularity_tags")
	private List<String> popularityTags;

	@SerializedName("ingredients_that_may_be_from_palm_oil_tags")
	private List<Object> ingredientsThatMayBeFromPalmOilTags;

	@SerializedName("nutrition_grades_tags")
	private List<String> nutritionGradesTags;

	@SerializedName("nutriscore_data")
	private NutriscoreData nutriscoreData;

	@SerializedName("last_image_t")
	private Double lastImageT;

	@SerializedName("nutrition_score_beverage")
	private Double nutritionScoreBeverage;
}