from threading import Thread

from Server import Server
from TV import TV
from TVCommands import FirstCommand, SecondCommand

class TVServer(Server, TV):
    """Represents """

    def __init__(self, host, channel):
        Server.__init__(self, host)
        TV.__init__(self, channel)

    def handleClient(self, connection):
        """Handles the client connection for the TV from a client"""
        try:
            dataHasNotBeenRecieved = False
            while not dataHasNotBeenRecieved:
                data = connection.recv(1024)
                if not data:
                    dataHasNotBeenRecieved = True
                data = self.handleData(data)
                print(data)
                self.returnData(connection, data)
        except Exception as e:
            connection.sendall("Could not read Data".encode())
        finally:
            connection.close()

    def handleData(self, data):
        """Handles data for the server"""
        data = data.decode()
        message = data.strip().split(" ")
        message[0] = FirstCommand(message[0])
        message[1] = SecondCommand(message[1])
        data = self.handleMessage(message)
        return data

    def handleMessage(self, data):
        match data[0]:
            case FirstCommand.GET:
                return self.handleGet(data)
            case FirstCommand.TURN:
                return self.handleTurn(data)
            case FirstCommand.SWITCH:
                return self.handleSwitch(data)
        return FirstCommand.INVALID

    def handleGet(self, data):
        """Handles GET data for the server"""
        match data[1]:
            case SecondCommand.CHANNEL:
                return self.getActiveChannel()
            case SecondCommand.CHANNELS:
                return self.getChannels()
        return SecondCommand.INVALID

    def handleTurn(self, data):
        """Handles TURN data for the server"""
        match data[1]:
            case SecondCommand.ON:
                self.turnOn()
                return "Turned ON"
            case SecondCommand.OFF:
                self.turnOff()
                return "Turned OFF"
        return SecondCommand.INVALID

    def handleSwitch(self, data):
        """Handles Switch data for the server"""
        match data[1]:
            case SecondCommand.UP:
                return self.setNextChannel()
            case SecondCommand.DOWN:
                return self.setNextChannel()
        return SecondCommand.INVALID

    def returnData(self, connection, data):
        """Returns the handled data to the client"""
        connection.sendall(data.encode())

def main():
    try:
        host = "127.0.0.1"
        server = TVServer(host, 4)
        server.bindSocket()
        client_threads = []
        while True:
            server.listenForConnection()
            conn = server.acceptConnection()[0]
            thread = Thread(target=server.handleClient, args=(conn,), daemon=True)
            thread.start()

            client_threads = [t for t in client_threads if t.is_alive()]
    except Exception as e:
        print("Error with server")

if __name__ == "__main__":
    main()


