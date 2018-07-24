package apimodels;

import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * BeaconKnowledgeMapPredicate
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-05-25T14:12:37.234Z")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class BeaconKnowledgeMapPredicate   {
  @JsonProperty("edge_label")
  private String edgeLabel = null;

  @JsonProperty("relation")
  private String relation = null;

  public BeaconKnowledgeMapPredicate edgeLabel(String edgeLabel) {
    this.edgeLabel = edgeLabel;
    return this;
  }

   /**
   * Human readable name of the 'minimal' standard Biolink Model predicate relationship name.   See [Biolink Model](https://biolink.github.io/biolink-model)  for the full list of terms. 
   * @return edgeLabel
  **/
    public String getEdgeLabel() {
    return edgeLabel;
  }

  public void setEdgeLabel(String edgeLabel) {
    this.edgeLabel = edgeLabel;
  }

  public BeaconKnowledgeMapPredicate relation(String relation) {
    this.relation = relation;
    return this;
  }

   /**
   * Human readable name of a 'maximal' Biolink Model or  beacon-specific (or Reasoner-specific) predicate relationship name. 
   * @return relation
  **/
    public String getRelation() {
    return relation;
  }

  public void setRelation(String relation) {
    this.relation = relation;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BeaconKnowledgeMapPredicate beaconKnowledgeMapPredicate = (BeaconKnowledgeMapPredicate) o;
    return Objects.equals(edgeLabel, beaconKnowledgeMapPredicate.edgeLabel) &&
        Objects.equals(relation, beaconKnowledgeMapPredicate.relation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(edgeLabel, relation);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeaconKnowledgeMapPredicate {\n");
    
    sb.append("    edgeLabel: ").append(toIndentedString(edgeLabel)).append("\n");
    sb.append("    relation: ").append(toIndentedString(relation)).append("\n");
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

