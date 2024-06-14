import java.math.BigInteger;
import java.util.Base64;
import java.security.SecureRandom;

public class RSA 
{
    private BigInteger p, q, n, phi, public_key, private_key;
    private int bitlength = 1024;
    private SecureRandom secureRandom = new SecureRandom();
    private int maxMessageLength = bitlength / 8 - 11;
    
    RSA() 
    {
        p = BigInteger.probablePrime(bitlength / 2, secureRandom);
        q = BigInteger.probablePrime(bitlength / 2, secureRandom);
        this.public_key = BigInteger.valueOf(65537);
        this.n = p.multiply(q);
        this.phi = (p.subtract(BigInteger.valueOf(1))).multiply((q.subtract(BigInteger.valueOf(1))));
        if (!this.public_key.gcd(phi).equals(BigInteger.ONE)) {
            this.public_key = BigInteger.probablePrime(bitlength / 2, secureRandom); // Choose another e if gcd != 1
        }
        this.private_key = public_key.modInverse(phi);
    }
    void changeKeys(String publicKey, String privateKey, String n) 
    {
    	this.public_key = new BigInteger(publicKey);
        this.private_key = new BigInteger(privateKey);
        this.n = new BigInteger(n);
    }
    void regerateKeys()
    {
        p = BigInteger.probablePrime(bitlength / 2, secureRandom);
        q = BigInteger.probablePrime(bitlength / 2, secureRandom);
        this.public_key = BigInteger.valueOf(65537);
        this.n = p.multiply(q);
        this.phi = (p.subtract(BigInteger.valueOf(1))).multiply((q.subtract(BigInteger.valueOf(1))));
        if (!this.public_key.gcd(phi).equals(BigInteger.ONE)) {
            this.public_key = BigInteger.probablePrime(bitlength / 2, secureRandom); // Choose another e if gcd != 1
        }
        this.private_key = public_key.modInverse(phi);
    }
    BigInteger getPublicKey()
    {
        return this.public_key;
    }

    BigInteger getPrivateKey()
    {
        return this.private_key;
    }
    
    BigInteger getN()
    {
    	return this.n;
    }

    String encrypt(String message)
    {
        String encodedMessage = stringToBinary(padMessage(message));
        BigInteger messageNumber = new BigInteger(encodedMessage, 2);
        BigInteger cypherBigInt = fastModularExponentiation(messageNumber, public_key, n);
        String cyphertext = Base64.getEncoder().encodeToString(cypherBigInt.toByteArray());
        System.out.println("Długość szyfru: "+cyphertext.length());
        return cyphertext;
    }

    String decrypt(String cyphertext)
    {
        byte[] cypherBytes = Base64.getDecoder().decode(cyphertext);
        BigInteger cypherBigInt = new BigInteger(cypherBytes);
        BigInteger messageNumber = fastModularExponentiation(cypherBigInt, private_key, n);
        String binaryNumber = messageNumber.toString(2);
        String message = binaryToString(binaryNumber);
        return unpadMessage(message);
    }
    
    private String padMessage(String message)
    {
    	if (message.length() > maxMessageLength)
    	{
    		throw new IllegalArgumentException("Message is too long");
    	}
    	
    	while (message.length() < maxMessageLength)
    	{
    		message += " ";
    	}
    	return message;
    }
    
    private String unpadMessage(String message)
    {
    	return message.trim();
    }

    private String stringToBinary(String str)
    {
        String binaryString = "";
        for (char c : str.toCharArray()) {
            String binary = String.format("%8s", Integer.toBinaryString(c & 0xFF)).replace(" ", "0");
            binaryString+=binary;
        }
        return binaryString.toString();
    }
    String binaryToString(String binaryString) {
        StringBuilder stringBuilder = new StringBuilder();
        while (binaryString.length() % 8 != 0)
        {
            binaryString = "0" + binaryString;
        }
        for (int i = 0; i < binaryString.length(); i += 8) {
            String binaryChar = binaryString.substring(i, Math.min(i + 8, binaryString.length()));
            int asciiValue = Integer.parseInt(binaryChar, 2);
            char character = (char) asciiValue;
            stringBuilder.append(character);
        }

        return stringBuilder.toString();
    }

    private BigInteger fastModularExponentiation (BigInteger number, BigInteger exponent, BigInteger modValue)
    {
        String binaryExponent = exponent.toString(2);
        BigInteger result = number;
        char current_digit_ch;
        int current_digit;

        for(int i = 1; i<binaryExponent.length(); i++)
        {
            result = result.pow(2).mod(modValue);
            current_digit_ch = binaryExponent.charAt(i);
            current_digit = Character.getNumericValue(current_digit_ch);
            if (current_digit == 1)
            {
                result = result.multiply(number).mod(modValue);
            }
        }
        return result;
    }
}


