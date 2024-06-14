
# RSA encryption-decryption application

This project features an application which enables the users to encode and decode messages using RSA. GUI contains two text fields, top for the message and bottom for the cypher. The message is encoded using the green button and decoded using the red button. Users may access the randomly generated secret key and parameter n using the grey button. In order to receive messages the keys need to be set to the same values for both clients.

## Authors

- [@DominikKlisiewicz](https://github.com/DominikKlisiewicz)


## Demo


![](https://raw.githubusercontent.com/DominikKlisiewicz/RSA_encoder_app/main/rsa.gif)
## Safety concerns and code problems

- The app DOES NOT contain a safe padding algorith. All the messages are padded using spaces " ".
- After going over the char limit the app will not function properly
- One message is always being converted to the exact same cypher

