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
  
  char inByte = ' ';
  if(Serial.available() > 0){ // only send data back if data has been sent
    bpm = Serial.read();
  }
  
      digitalWrite(13, HIGH);
      if (parseRange(1050)> 1){
        digitalWrite(led12, HIGH);
      }
      if (parseRange(1050) > 2){
        digitalWrite(led11,HIGH);
      }
      delay(60000/2/bpm);
      digitalWrite(13,LOW);
      digitalWrite(12,LOW);
      digitalWrite(11,LOW);
      delay(60000/2/bpm);
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


