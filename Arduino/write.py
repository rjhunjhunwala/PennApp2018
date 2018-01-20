import serial
ser = serial.Serial("COM4")
#ser.close()
try:
    #while(True):
        #print(ser.read())
    ser.write('aaaaaaaaaa'.encode('utf-8'))
except:
    ser.close()
ser.close()
