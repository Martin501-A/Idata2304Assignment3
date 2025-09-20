import socket

class Server:
    """Represents a tcp server with ipv4 ip that holds clients for the network with a single socket"""

    def __init__(self, host):
        """Creates a Server with a host ip address"""
        self.host = host
        self.createSocket()

    def createSocket(self):
        """Creates the socket for the server"""
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def bindSocket(self, port=1238):
        """Binds the socket to the given port"""
        self.socket.bind((self.host, port))

    def listenForConnection(self):
        """Listens for a connection"""
        self.socket.listen()
        print(f"Server listening on {self.socket.getsockname()}")

    def acceptConnection(self):
        """Accepts a connection and returns the connection and connection address"""
        conn, addr = self.socket.accept()
        print(f"Connection accepted from {self.host}")
        return conn, addr

    def closeConnection(self, conn):
        """Closes the connection and removes it from the clientlist"""
        conn.close()

    def handleClient(self, conn):
        """Handles a client connection by sending a the message back to the client.
        Needs to be implemented by subclasses"""
        try:
            dataHasNotBeenRecieved = False
            while not dataHasNotBeenRecieved:
                data = conn.recv(1024)
                if not data:
                    dataHasNotBeenRecieved = True
                conn.sendall(data)
        except Exception as e:
            print("Error with client communication")

    def closeSocket(self):
        """Closes the socket"""
        self.socket.close()

def main():
    host = "127.0.0.1"
    port = 65432
    server = Server(host)
    try:
        server.bindSocket(port)
        server.listenForConnection()
        conn, addr = server.acceptConnection()
        server.handleClient(conn)
    except Exception as e:
        print("Server error", e)
    finally:
        server.closeSocket()

if __name__ == "__main__":
    main()