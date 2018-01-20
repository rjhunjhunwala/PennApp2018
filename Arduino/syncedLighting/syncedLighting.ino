int led13 = 13;
int led12 = 12;
int led11 = 11;
int bpm = 120;
int top = 1300;
int bottom = 1074;
int width = top - bottom;

void setup() {
Serial.begin(9600);
 bpm = 120;
  // put your setup code here, to run once:
  pinMode(led13, OUTPUT);
  pinMode(led12, OUTPUT);
  pinMode(led11, OUTPUT);
}

void loop() {
  if(Serial.read()!=-1){
  
  // put your main code here, to run repeatedly:
  countChains(1);
  // turn the LED on (HIGH is the voltage level)
  delay(100);                       // wait for a second
  digitalWrite(led13, LOW);                 // turn the LED off by making the voltage LOW
  digitalWrite(led12, LOW);
  digitalWrite(led11, LOW);
  //delay(60000/bpm/2);      
  }
}

void countChains(int i){
  int chains = parseRange(i);
  digitalWrite(led13, HIGH);
  if (chains > 1){
    digitalWrite(led12, HIGH);
  }
  if (chains > 2){
    digitalWrite(led11, HIGH);
  }
}

 int parseRange(int freq){
 return floor((freq-bottom)/(width/1.0)*3.0);
}
