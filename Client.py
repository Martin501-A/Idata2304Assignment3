import socket


class Client:
    """Represents a tcp client with ipv4 ip in a computer network with a single socket"""

    def __init__(self):
        self.createSocket(socket.AF_INET, socket.SOCK_STREAM)

    def createSocket(self, ipType, transportProtocol):
        """Creates a Socket object with a ip protocol(IP Address) and a transport protocol(Transport layer)"""
        self.socket = socket.socket(ipType, transportProtocol)

    def connectToServer(self, host, port=1238):
        """Connects the client to a server using a server host address and a port number"""
        self.socket.connect((host, port))

    def closeSocket(self):
        """Closes the socket"""
        self.socket.close()

    def sendData(self, data):
        """Sends data to the Server"""
        self.socket.send(data.encode())

    def receiveData(self, bufferSize=4096):
        """Receives data from the Server"""
        return self.socket.recv(bufferSize)

    def getSocket(self):
        """Returns the socket object"""
        return self.socket

def main():
    client = Client()
    host = "127.0.0.1"
    port = 1238
    message = ("TURN ON")
    try:
        client.connectToServer(host, port)
        client.sendData(message)
        response = client.receiveData()
        print("Recieved data from server")
        print(response.decode())
    except Exception as e:
        print("Error connecting and sending to server")
    finally:
        client.closeSocket()
if __name__ == "__main__":
    main()
