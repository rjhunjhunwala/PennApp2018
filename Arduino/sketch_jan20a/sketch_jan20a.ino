int led13 = 13;
int bpm = 120;

void setup() {
  // put your setup code here, to run once:
  pinMode(led13, OUTPUT);
Serial.begin(9600);
}

void loop() {
 
  // put your main code here, to run repeatedly:
  digitalWrite(led13, HIGH);   // turn the LED on (HIGH is the voltage level)
while(!Serial.available()){}    
  digitalWrite(led13, LOW);    // turn the LED off by making the voltage LOW

}

void chain_1(){
  
}

void chain_2(){
  
}

void chain_3(){
  
}

