int led13 = 13;
int bpm = 120;

void setup() {
  // put your setup code here, to run once:
  pinMode(led13, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(led13, HIGH);   // turn the LED on (HIGH is the voltage level)
  delay(60000/bpm/2);                       // wait for a second
  digitalWrite(led13, LOW);    // turn the LED off by making the voltage LOW
  delay(60000/bpm/2);      

}

void chain_1(){
  
}

void chain_2(){
  
}

void chain_3(){
  
}

