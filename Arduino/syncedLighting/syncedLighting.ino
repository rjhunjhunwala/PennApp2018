int led13 = 13;
int led12 = 12;
int led11 = 11;
int bpm = 120;
int top = 1300;
int bottom = 1074;
int width = top - bottom;
int num = 3;

void setup() {
  Serial.begin(9600);
  // put your setup code here, to run once:
  pinMode(led13, OUTPUT);
  pinMode(led12, OUTPUT);
  pinMode(led11, OUTPUT);
  for(;;){
    if(Serial.available()>0){
int temp = Serial.read();
bpm = temp;
}
  
  
      digitalWrite(13, HIGH);
      if (num > 1){
        digitalWrite(led12, HIGH);
      }
      if (num > 2){
        digitalWrite(led11,HIGH);
      }
      delay(60000/bpm/2);
      digitalWrite(13,LOW);
      digitalWrite(12,LOW);
      digitalWrite(11,LOW);
delay(60000/bpm/2);
  }

}

void loop(){
  

}
  
    


 


