int led13 = 13;
int led12 = 12;
int led11 = 11;
int bpm = 120;
int top = 1300;
int bottom = 1074;

void setup() {
  // put your setup code here, to run once:
  pinMode(led13, OUTPUT);
  pinMode(led12, OUTPUT);
  pinMode(led11, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  countChains(1);
  // turn the LED on (HIGH is the voltage level)
  delay(60000/bpm/2);                       // wait for a second
  digitalWrite(led13, LOW);    // turn the LED off by making the voltage LOW
  delay(60000/bpm/2);      

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
  return 2;
}

