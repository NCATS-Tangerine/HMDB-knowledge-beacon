package apimodels;

import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * BeaconPredicate
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-11-01T22:26:35.485Z")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class BeaconPredicate   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("uri")
  private String uri = null;

  @JsonProperty("edge_label")
  private String edgeLabel = null;

  @JsonProperty("relation")
  private String relation = null;

  @JsonProperty("local_id")
  private String localId = null;

  @JsonProperty("local_uri")
  private String localUri = null;

  @JsonProperty("local_relation")
  private String localRelation = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("frequency")
  private Integer frequency = null;

  public BeaconPredicate id(String id) {
    this.id = id;
    return this;
  }

   /**
   * CURIE-encoded identifier of predicate relation 
   * @return id
  **/
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BeaconPredicate uri(String uri) {
    this.uri = uri;
    return this;
  }

   /**
   * The predicate URI which should generally resolves to the  full semantic description of the predicate relation
   * @return uri
  **/
    public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public BeaconPredicate edgeLabel(String edgeLabel) {
    this.edgeLabel = edgeLabel;
    return this;
  }

   /**
   * A predicate edge label which must be taken from the minimal predicate ('slot') list of the [Biolink Model](https://biolink.github.io/biolink-model). 
   * @return edgeLabel
  **/
    public String getEdgeLabel() {
    return edgeLabel;
  }

  public void setEdgeLabel(String edgeLabel) {
    this.edgeLabel = edgeLabel;
  }

  public BeaconPredicate relation(String relation) {
    this.relation = relation;
    return this;
  }

   /**
   * The predicate relation, with the preferred format being a CURIE where one exists, but strings/labels acceptable. This relation  may be equivalent to the edge_label (e.g. edge_label: has_phenotype, relation: RO:0002200), or a more specific relation in cases where the source provides more granularity  (e.g. edge_label: molecularly_interacts_with, relation: RO:0002447) 
   * @return relation
  **/
    public String getRelation() {
    return relation;
  }

  public void setRelation(String relation) {
    this.relation = relation;
  }

  public BeaconPredicate localId(String localId) {
    this.localId = localId;
    return this;
  }

   /**
   * CURIE-encoded identifier of the locally defined predicate relation. Such terms should be formally documented as mappings in the [Biolink Model](https://biolink.github.io/biolink-model) 
   * @return localId
  **/
    public String getLocalId() {
    return localId;
  }

  public void setLocalId(String localId) {
    this.localId = localId;
  }

  public BeaconPredicate localUri(String localUri) {
    this.localUri = localUri;
    return this;
  }

   /**
   * The predicate URI which should generally resolves  to the local predicate relation
   * @return localUri
  **/
    public String getLocalUri() {
    return localUri;
  }

  public void setLocalUri(String localUri) {
    this.localUri = localUri;
  }

  public BeaconPredicate localRelation(String localRelation) {
    this.localRelation = localRelation;
    return this;
  }

   /**
   * human readable name of the locally defined predicate relation 
   * @return localRelation
  **/
    public String getLocalRelation() {
    return localRelation;
  }

  public void setLocalRelation(String localRelation) {
    this.localRelation = localRelation;
  }

  public BeaconPredicate description(String description) {
    this.description = description;
    return this;
  }

   /**
   * human readable definition of predicate relation  provided by this beacon 
   * @return description
  **/
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BeaconPredicate frequency(Integer frequency) {
    this.frequency = frequency;
    return this;
  }

   /**
   * the number of statement entries using the specified predicate in the given beacon knowledge base
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
    BeaconPredicate beaconPredicate = (BeaconPredicate) o;
    return Objects.equals(id, beaconPredicate.id) &&
        Objects.equals(uri, beaconPredicate.uri) &&
        Objects.equals(edgeLabel, beaconPredicate.edgeLabel) &&
        Objects.equals(relation, beaconPredicate.relation) &&
        Objects.equals(localId, beaconPredicate.localId) &&
        Objects.equals(localUri, beaconPredicate.localUri) &&
        Objects.equals(localRelation, beaconPredicate.localRelation) &&
        Objects.equals(description, beaconPredicate.description) &&
        Objects.equals(frequency, beaconPredicate.frequency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uri, edgeLabel, relation, localId, localUri, localRelation, description, frequency);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeaconPredicate {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
    sb.append("    edgeLabel: ").append(toIndentedString(edgeLabel)).append("\n");
    sb.append("    relation: ").append(toIndentedString(relation)).append("\n");
    sb.append("    localId: ").append(toIndentedString(localId)).append("\n");
    sb.append("    localUri: ").append(toIndentedString(localUri)).append("\n");
    sb.append("    localRelation: ").append(toIndentedString(localRelation)).append("\n");
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

