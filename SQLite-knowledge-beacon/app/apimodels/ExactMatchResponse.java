package apimodels;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * ExactMatchResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-05-25T14:12:37.234Z")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class ExactMatchResponse   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("within_domain")
  private Boolean withinDomain = null;

  @JsonProperty("has_exact_matches")
  private List<String> hasExactMatches = null;

  public ExactMatchResponse id(String id) {
    this.id = id;
    return this;
  }

   /**
   * A given [CURIE](https://www.w3.org/TR/curie/) identifier. 
   * @return id
  **/
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ExactMatchResponse withinDomain(Boolean withinDomain) {
    this.withinDomain = withinDomain;
    return this;
  }

   /**
   * True if the knowledge source is aware of this identifier, and has the capacity to return the identified concept. Otherwise, false. 
   * @return withinDomain
  **/
    public Boolean isWithinDomain() {
    return withinDomain;
  }

  public void setWithinDomain(Boolean withinDomain) {
    this.withinDomain = withinDomain;
  }

  public ExactMatchResponse hasExactMatches(List<String> hasExactMatches) {
    this.hasExactMatches = hasExactMatches;
    return this;
  }

  public ExactMatchResponse addHasExactMatchesItem(String hasExactMatchesItem) {
    if (hasExactMatches == null) {
      hasExactMatches = new ArrayList<>();
    }
    hasExactMatches.add(hasExactMatchesItem);
    return this;
  }

   /**
   * List of [CURIE](https://www.w3.org/TR/curie/) identifiers of a exactly matching concepts. 
   * @return hasExactMatches
  **/
    public List<String> getHasExactMatches() {
    return hasExactMatches;
  }

  public void setHasExactMatches(List<String> hasExactMatches) {
    this.hasExactMatches = hasExactMatches;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExactMatchResponse exactMatchResponse = (ExactMatchResponse) o;
    return Objects.equals(id, exactMatchResponse.id) &&
        Objects.equals(withinDomain, exactMatchResponse.withinDomain) &&
        Objects.equals(hasExactMatches, exactMatchResponse.hasExactMatches);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, withinDomain, hasExactMatches);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExactMatchResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    withinDomain: ").append(toIndentedString(withinDomain)).append("\n");
    sb.append("    hasExactMatches: ").append(toIndentedString(hasExactMatches)).append("\n");
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

