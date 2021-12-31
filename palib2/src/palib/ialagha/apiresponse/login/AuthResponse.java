package palib.ialagha.apiresponse.login;

public class AuthResponse {
 private boolean success;
 Data data;
 private String message;


 // Getter Methods 

 public boolean getSuccess() {
  return success;
 }

 public Data getData() {
  return data;
 }

 public String getMessage() {
  return message;
 }

 // Setter Methods 

 public void setSuccess(boolean success) {
  this.success = success;
 }

 public void setData(Data dataObject) {
  this.data = dataObject;
 }

 public void setMessage(String message) {
  this.message = message;
 }
}
