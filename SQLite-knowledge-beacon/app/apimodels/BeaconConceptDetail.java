package apimodels;

import com.fasterxml.jackson.annotation.*;
import java.util.Set;
import javax.validation.*;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * Any other metadata returned by the beacon as tag &#x3D; value  
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-07-24T21:10:45.082Z")

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class BeaconConceptDetail   {
  @JsonProperty("tag")
  private String tag = null;

  @JsonProperty("value")
  private String value = null;

  public BeaconConceptDetail tag(String tag) {
    this.tag = tag;
    return this;
  }

   /**
   * property name 
   * @return tag
  **/
    public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public BeaconConceptDetail value(String value) {
    this.value = value;
    return this;
  }

   /**
   * property value 
   * @return value
  **/
    public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BeaconConceptDetail beaconConceptDetail = (BeaconConceptDetail) o;
    return Objects.equals(tag, beaconConceptDetail.tag) &&
        Objects.equals(value, beaconConceptDetail.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tag, value);
  }

  @SuppressWarnings("StringBufferReplaceableByString")
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeaconConceptDetail {\n");
    
    sb.append("    tag: ").append(toIndentedString(tag)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

