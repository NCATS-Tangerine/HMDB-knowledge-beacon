package apimodels;

import apimodels.BeaconKnowledgeMapObject;
import apimodels.BeaconKnowledgeMapPredicate;
import apimodels.BeaconKnowledgeMapSubject;
import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * BeaconKnowledgeMapStatement
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-05-25T14:12:37.234Z")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class BeaconKnowledgeMapStatement   {
  @JsonProperty("subject")
  private BeaconKnowledgeMapSubject subject = null;

  @JsonProperty("predicate")
  private BeaconKnowledgeMapPredicate predicate = null;

  @JsonProperty("object")
  private BeaconKnowledgeMapObject object = null;

  @JsonProperty("frequency")
  private Integer frequency = null;

  @JsonProperty("description")
  private String description = null;

  public BeaconKnowledgeMapStatement subject(BeaconKnowledgeMapSubject subject) {
    this.subject = subject;
    return this;
  }

   /**
   * Get subject
   * @return subject
  **/
  @Valid
  public BeaconKnowledgeMapSubject getSubject() {
    return subject;
  }

  public void setSubject(BeaconKnowledgeMapSubject subject) {
    this.subject = subject;
  }

  public BeaconKnowledgeMapStatement predicate(BeaconKnowledgeMapPredicate predicate) {
    this.predicate = predicate;
    return this;
  }

   /**
   * Get predicate
   * @return predicate
  **/
  @Valid
  public BeaconKnowledgeMapPredicate getPredicate() {
    return predicate;
  }

  public void setPredicate(BeaconKnowledgeMapPredicate predicate) {
    this.predicate = predicate;
  }

  public BeaconKnowledgeMapStatement object(BeaconKnowledgeMapObject object) {
    this.object = object;
    return this;
  }

   /**
   * Get object
   * @return object
  **/
  @Valid
  public BeaconKnowledgeMapObject getObject() {
    return object;
  }

  public void setObject(BeaconKnowledgeMapObject object) {
    this.object = object;
  }

  public BeaconKnowledgeMapStatement frequency(Integer frequency) {
    this.frequency = frequency;
    return this;
  }

   /**
   * the frequency of statements of the specified relationship within the given beacon 
   * @return frequency
  **/
    public Integer getFrequency() {
    return frequency;
  }

  public void setFrequency(Integer frequency) {
    this.frequency = frequency;
  }

  public BeaconKnowledgeMapStatement description(String description) {
    this.description = description;
    return this;
  }

   /**
   * a description of the nature of the relationship 
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
    BeaconKnowledgeMapStatement beaconKnowledgeMapStatement = (BeaconKnowledgeMapStatement) o;
    return Objects.equals(subject, beaconKnowledgeMapStatement.subject) &&
        Objects.equals(predicate, beaconKnowledgeMapStatement.predicate) &&
        Objects.equals(object, beaconKnowledgeMapStatement.object) &&
        Objects.equals(frequency, beaconKnowledgeMapStatement.frequency) &&
        Objects.equals(description, beaconKnowledgeMapStatement.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subject, predicate, object, frequency, description);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeaconKnowledgeMapStatement {\n");
    
    sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
    sb.append("    predicate: ").append(toIndentedString(predicate)).append("\n");
    sb.append("    object: ").append(toIndentedString(object)).append("\n");
    sb.append("    frequency: ").append(toIndentedString(frequency)).append("\n");
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

