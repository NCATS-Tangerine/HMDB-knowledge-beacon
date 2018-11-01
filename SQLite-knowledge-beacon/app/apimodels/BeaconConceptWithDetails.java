package apimodels;

import apimodels.BeaconConceptDetail;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * BeaconConceptWithDetails
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-11-01T22:26:35.485Z")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class BeaconConceptWithDetails   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("uri")
  private String uri = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("symbol")
  private String symbol = null;

  @JsonProperty("categories")
  private List<String> categories = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("synonyms")
  private List<String> synonyms = null;

  @JsonProperty("exact_matches")
  private List<String> exactMatches = null;

  @JsonProperty("details")
  private List<BeaconConceptDetail> details = null;

  public BeaconConceptWithDetails id(String id) {
    this.id = id;
    return this;
  }

   /**
   * local object CURIE for the concept in the specified knowledge source database 
   * @return id
  **/
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BeaconConceptWithDetails uri(String uri) {
    this.uri = uri;
    return this;
  }

   /**
   * (optional) equivalent to expansion of the CURIE 
   * @return uri
  **/
    public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public BeaconConceptWithDetails name(String name) {
    this.name = name;
    return this;
  }

   /**
   * canonical human readable name of the concept 
   * @return name
  **/
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BeaconConceptWithDetails symbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

   /**
   * (optional) symbol, used for genomic data 
   * @return symbol
  **/
    public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public BeaconConceptWithDetails categories(List<String> categories) {
    this.categories = categories;
    return this;
  }

  public BeaconConceptWithDetails addCategoriesItem(String categoriesItem) {
    if (categories == null) {
      categories = new ArrayList<>();
    }
    categories.add(categoriesItem);
    return this;
  }

   /**
   * concept semantic type 'category'. Should be specified from the [Biolink Model](https://biolink.github.io/biolink-model). 
   * @return categories
  **/
    public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public BeaconConceptWithDetails description(String description) {
    this.description = description;
    return this;
  }

   /**
   * (optional) narrative concept definition 
   * @return description
  **/
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BeaconConceptWithDetails synonyms(List<String> synonyms) {
    this.synonyms = synonyms;
    return this;
  }

  public BeaconConceptWithDetails addSynonymsItem(String synonymsItem) {
    if (synonyms == null) {
      synonyms = new ArrayList<>();
    }
    synonyms.add(synonymsItem);
    return this;
  }

   /**
   * list of synonyms for concept 
   * @return synonyms
  **/
    public List<String> getSynonyms() {
    return synonyms;
  }

  public void setSynonyms(List<String> synonyms) {
    this.synonyms = synonyms;
  }

  public BeaconConceptWithDetails exactMatches(List<String> exactMatches) {
    this.exactMatches = exactMatches;
    return this;
  }

  public BeaconConceptWithDetails addExactMatchesItem(String exactMatchesItem) {
    if (exactMatches == null) {
      exactMatches = new ArrayList<>();
    }
    exactMatches.add(exactMatchesItem);
    return this;
  }

   /**
   * List of [CURIE](https://www.w3.org/TR/curie/)  identifiers of concepts thought to be exactly matching concepts, [*sensa*-SKOS](http://www.w3.org/2004/02/skos/core#exactMatch). This is generally the same list returned by the /exact_matches endpoint (given the concept 'id' as input) 
   * @return exactMatches
  **/
    public List<String> getExactMatches() {
    return exactMatches;
  }

  public void setExactMatches(List<String> exactMatches) {
    this.exactMatches = exactMatches;
  }

  public BeaconConceptWithDetails details(List<BeaconConceptDetail> details) {
    this.details = details;
    return this;
  }

  public BeaconConceptWithDetails addDetailsItem(BeaconConceptDetail detailsItem) {
    if (details == null) {
      details = new ArrayList<>();
    }
    details.add(detailsItem);
    return this;
  }

   /**
   * Get details
   * @return details
  **/
  @Valid
  public List<BeaconConceptDetail> getDetails() {
    return details;
  }

  public void setDetails(List<BeaconConceptDetail> details) {
    this.details = details;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BeaconConceptWithDetails beaconConceptWithDetails = (BeaconConceptWithDetails) o;
    return Objects.equals(id, beaconConceptWithDetails.id) &&
        Objects.equals(uri, beaconConceptWithDetails.uri) &&
        Objects.equals(name, beaconConceptWithDetails.name) &&
        Objects.equals(symbol, beaconConceptWithDetails.symbol) &&
        Objects.equals(categories, beaconConceptWithDetails.categories) &&
        Objects.equals(description, beaconConceptWithDetails.description) &&
        Objects.equals(synonyms, beaconConceptWithDetails.synonyms) &&
        Objects.equals(exactMatches, beaconConceptWithDetails.exactMatches) &&
        Objects.equals(details, beaconConceptWithDetails.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uri, name, symbol, categories, description, synonyms, exactMatches, details);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeaconConceptWithDetails {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
    sb.append("    categories: ").append(toIndentedString(categories)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    synonyms: ").append(toIndentedString(synonyms)).append("\n");
    sb.append("    exactMatches: ").append(toIndentedString(exactMatches)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

