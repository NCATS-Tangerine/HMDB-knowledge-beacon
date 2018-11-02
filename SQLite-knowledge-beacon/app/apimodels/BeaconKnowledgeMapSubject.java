package apimodels;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * BeaconKnowledgeMapSubject
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-11-01T22:26:35.485Z")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class BeaconKnowledgeMapSubject   {
  @JsonProperty("category")
  private String category = null;

  @JsonProperty("prefixes")
  private List<String> prefixes = null;

  public BeaconKnowledgeMapSubject category(String category) {
    this.category = category;
    return this;
  }

   /**
   * the concept semantic type of a statement subject. This  should be specified as Biolink concept semantic type names like gene, pathway, etc. (see [Biolink Model](https://biolink.github.io/biolink-model) for the full list of terms) 
   * @return category
  **/
    public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public BeaconKnowledgeMapSubject prefixes(List<String> prefixes) {
    this.prefixes = prefixes;
    return this;
  }

  public BeaconKnowledgeMapSubject addPrefixesItem(String prefixesItem) {
    if (prefixes == null) {
      prefixes = new ArrayList<>();
    }
    prefixes.add(prefixesItem);
    return this;
  }

   /**
   * Get prefixes
   * @return prefixes
  **/
    public List<String> getPrefixes() {
    return prefixes;
  }

  public void setPrefixes(List<String> prefixes) {
    this.prefixes = prefixes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BeaconKnowledgeMapSubject beaconKnowledgeMapSubject = (BeaconKnowledgeMapSubject) o;
    return Objects.equals(category, beaconKnowledgeMapSubject.category) &&
        Objects.equals(prefixes, beaconKnowledgeMapSubject.prefixes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category, prefixes);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeaconKnowledgeMapSubject {\n");
    
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    prefixes: ").append(toIndentedString(prefixes)).append("\n");
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

