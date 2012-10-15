package com.ecem.crypto;

import java.util.HashMap;
import java.util.Map;


public class HW1 {

	public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

	public static void main(String[] args) {

		//String plaintext = "cowardsdiemanytimesbeforetheirdeathsthevaliantnevertasteofdeathbutonceofallthewondersthatiyethavehearditseemstomemoststrangethatmenshouldfearseeingthatdeathanecessaryendwillcomewhenitwillcome";
		//String key = "william";

		//System.out.println(vigenere(plaintext, key));
		
		//affine("xaadpevxkmkpuaxnfxvrjqxhvxmtaacpn");
		
		char[] cipher = "jwmbcofnemoazxqjlkpimmcrmtbkpugvecwsbgpzkohzvunklugvbsiovbpvzzzwavihywhaddaklvtbnbgpknqultxwtdqjmzwazwtemjqmmwusrsmnircmglgplkkhomybneiqmtapoaklvimzylyenclzwcpnkvcepkpdwxrmrditlcpazznjbnmvtkqmclnzmqlnozrengqkplglupvzcqppkadeaucszvgadnwtlnymcqssiywlpenismmlccotklkljuzdcvgudoagvbswvimkicpnxpkpzdxkzrfijmcewgkbzuvimjpouzdpoaelqzpeftyyttzkqmcmzcqynuzrlvipndakzutkkaczvwchiwzmocwsqrpazwllskazyknwssmcmzwbngfzdkzmzzunzyqytdzvnqrswxadcwiqmlvzmzmixvmlocmkwxgassqyxqtuklnyyaqwzbkzhomybgpzuicdwlaolqtqmdmgzbswlokzzeimooximoijddybazdsmmqupaaxezwjagptzmqlvjknxnuzsltrqmepkvzxmunzamgazybcwllvjcknqtmzomrbnmwywvswspdpvbqrtwtazdivzhykkar".toCharArray();
		
		//vigenereAttack(cipher, vigenereKeyLength(cipher));
		
		vigenereDecryptor(cipher, "gizli");
		
	}

	public static String vigenere(String plain, String key) {

		if(plain == null || key == null) return null;
		
		StringBuilder b = new StringBuilder();
		
		for(int i=0; i<plain.length(); i++) {
			
			int c = alphabet.indexOf(plain.charAt(i)) + alphabet.indexOf(key.charAt(i%key.length()));
			b.append(alphabet.charAt(c%26));
		}
		
		return b.toString();
	}
	
	public static void affine(String cipher) {
		
		for(int a=1; a<26; a++) {
			
			for(int b=0; b<26; b++) {
				
				System.out.println("a: " + a + " b: " + b);
				
				for(int k=0; k<cipher.length(); k++) {
					
					int c = alphabet.indexOf(cipher.charAt(k));
					c = (a*c + b)%26;
					
					System.out.print(alphabet.charAt(c));
					
				}
				
				System.out.println();
			}
		}
	}
	
	public static int vigenereKeyLength(char[] cipher) {
		
		int c = 0, key = 0;
		
		for(int i=1; i<8; i++) {
			
			char[] shiftedCipher = new char[cipher.length];
			
			for(int j=0; j<i; j++) {
				shiftedCipher[j] = '.';
			}
			
			for(int j=i; j<shiftedCipher.length; j++) {
				shiftedCipher[j] = cipher[j-i];
			}
			
			int count = 0;
			
			for(int k=0; k<cipher.length; k++) {
				
				if(cipher[k] == shiftedCipher[k]) count++;
			}
			
			System.out.println(i + ": " + count);
			
			if(count>c) {
				c = count;
				key = i;
			}
			
		}
		
		return key;
		
	}
	
	public static void vigenereAttack(char[] cipher, int keyLength) {
		
		if(keyLength < 1) return;
		
		String[] subciphers = new String[keyLength];
		
		for(int i=0; i<subciphers.length; i++)  {
			subciphers[i] = "";
		}
		
		for(int i=0; i<cipher.length; i++) {
			
			subciphers[i%subciphers.length] += cipher[i];
		}
		
		for(int i=0; i<subciphers.length; i++)  {
			System.out.print(i + " ");
			frequencyAnalysis(subciphers[i]);
		}
	}
	
	public static void vigenereDecryptor(char[] cipher, String key) {
		
		int i=0;
		
		for(char c: cipher) {
			
			int index = alphabet.indexOf(c) - alphabet.indexOf(key.charAt(i%key.length()));
			if(index<0) index += 26;
			
			System.out.print(alphabet.charAt(index));
			i++;
		}
		
	}
	
	public static void frequencyAnalysis(String s) {
		
		Map<Character, Integer> counts = new HashMap<Character, Integer>();
		
		for(char c: s.toCharArray()) {
			
			if(counts.containsKey(c)) {
				counts.put(c, counts.get(c) + 1);
			}
			else counts.put(c, 1);
		}
		
		System.out.println(counts);
	}
}
