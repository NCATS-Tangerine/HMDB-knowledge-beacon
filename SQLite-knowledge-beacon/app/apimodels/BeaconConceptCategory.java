package apimodels;

import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * BeaconConceptCategory
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-05-25T14:12:37.234Z")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class BeaconConceptCategory   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("uri")
  private String uri = null;

  @JsonProperty("category")
  private String category = null;

  @JsonProperty("local_id")
  private String localId = null;

  @JsonProperty("local_uri")
  private String localUri = null;

  @JsonProperty("local_category")
  private String localCategory = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("frequency")
  private Integer frequency = null;

  public BeaconConceptCategory id(String id) {
    this.id = id;
    return this;
  }

   /**
   * the CURIE of the concept category
   * @return id
  **/
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BeaconConceptCategory uri(String uri) {
    this.uri = uri;
    return this;
  }

   /**
   * The category URI which should generally resolves to  the full semantic description of the category
   * @return uri
  **/
    public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public BeaconConceptCategory category(String category) {
    this.category = category;
    return this;
  }

   /**
   * human readable name (\"rdfs:label\")
   * @return category
  **/
    public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public BeaconConceptCategory localId(String localId) {
    this.localId = localId;
    return this;
  }

   /**
   * the locally designated CURIE of the concept category
   * @return localId
  **/
    public String getLocalId() {
    return localId;
  }

  public void setLocalId(String localId) {
    this.localId = localId;
  }

  public BeaconConceptCategory localUri(String localUri) {
    this.localUri = localUri;
    return this;
  }

   /**
   * This locally designated category URI should generally  resolve to the full semantic description of the category
   * @return localUri
  **/
    public String getLocalUri() {
    return localUri;
  }

  public void setLocalUri(String localUri) {
    this.localUri = localUri;
  }

  public BeaconConceptCategory localCategory(String localCategory) {
    this.localCategory = localCategory;
    return this;
  }

   /**
   * human readable local semantic type name (\"rdfs:label\")
   * @return localCategory
  **/
    public String getLocalCategory() {
    return localCategory;
  }

  public void setLocalCategory(String localCategory) {
    this.localCategory = localCategory;
  }

  public BeaconConceptCategory description(String description) {
    this.description = description;
    return this;
  }

   /**
   * human readable name definition of the category
   * @return description
  **/
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BeaconConceptCategory frequency(Integer frequency) {
    this.frequency = frequency;
    return this;
  }

   /**
   * the number of concept entries of the specified type in the beacon knowledge base
   * @return frequency
  **/
    public Integer getFrequency() {
    return frequency;
  }

  public void setFrequency(Integer frequency) {
    this.frequency = frequency;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BeaconConceptCategory beaconConceptCategory = (BeaconConceptCategory) o;
    return Objects.equals(id, beaconConceptCategory.id) &&
        Objects.equals(uri, beaconConceptCategory.uri) &&
        Objects.equals(category, beaconConceptCategory.category) &&
        Objects.equals(localId, beaconConceptCategory.localId) &&
        Objects.equals(localUri, beaconConceptCategory.localUri) &&
        Objects.equals(localCategory, beaconConceptCategory.localCategory) &&
        Objects.equals(description, beaconConceptCategory.description) &&
        Objects.equals(frequency, beaconConceptCategory.frequency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uri, category, localId, localUri, localCategory, description, frequency);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeaconConceptCategory {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    localId: ").append(toIndentedString(localId)).append("\n");
    sb.append("    localUri: ").append(toIndentedString(localUri)).append("\n");
    sb.append("    localCategory: ").append(toIndentedString(localCategory)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    frequency: ").append(toIndentedString(frequency)).append("\n");
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

