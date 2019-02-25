#include <ESP8266HTTPClient.h>
#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>

// WiFi parameters
const char* ssid = "Xiu";
const char* password = "123456789";
 HTTPClient http;
//host to send data
const char* host= "http://192.168.137.1:1234/QLDA/Getstatus.php";
void setup() {
  pinMode(4,OUTPUT);
  Serial.begin(115200);
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  //while (WiFi.status() != WL_CONNECTED) {
 //   delay(500);
 //   Serial.print(".");
 // }
delay(3000);
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}
void loop() {

  http.begin(host);
  http.addHeader("Content-Type", "application/x-www-form-urlencoded");
  int httpCode=http.GET();
  String payload=http.getString(); // get data from webhost continously
  Serial.print(payload);
  if(payload == "1")  // if data == 1 -> LED ON
  {
    digitalWrite(4,HIGH);
    }
   else if (payload == "0") // if data == 0 -> LED OFF
   {
    digitalWrite(4,LOW);
    }
  delay(500);
   http.end();

}
