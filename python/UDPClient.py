import socket

target_host = "127.0.0.1"
target_port =123

# create socket object
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# connect socket
client.connect((target_host, target_port))

# send data
client.sendto("AAANNNSSS", (target_host, target_port))

# get data
data, addr = client.recfrom(4096)

print data
