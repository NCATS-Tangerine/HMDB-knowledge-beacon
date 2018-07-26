package apimodels;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * BeaconConcept
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-07-24T21:10:45.082Z")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class BeaconConcept   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("categories")
  private List<String> categories = null;

  @JsonProperty("description")
  private String description = null;

  public BeaconConcept id(String id) {
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

  public BeaconConcept name(String name) {
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

  public BeaconConcept categories(List<String> categories) {
    this.categories = categories;
    return this;
  }

  public BeaconConcept addCategoriesItem(String categoriesItem) {
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

  public BeaconConcept description(String description) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BeaconConcept beaconConcept = (BeaconConcept) o;
    return Objects.equals(id, beaconConcept.id) &&
        Objects.equals(name, beaconConcept.name) &&
        Objects.equals(categories, beaconConcept.categories) &&
        Objects.equals(description, beaconConcept.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, categories, description);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeaconConcept {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    categories: ").append(toIndentedString(categories)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

