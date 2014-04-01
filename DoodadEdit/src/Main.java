import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import de.peeeq.jmpq.JmpqEditor;

public class Main {
	
	public static void main(String[] args) throws IOException{
		
		
		
		
//		BinFileReader r = new BinFileReader(new File("C:/Users/Crigges/Desktop/Wurstfree Maps/war3map.doo"));
//		FileWriter w = new FileWriter(new File("C:/Users/Crigges/Desktop/Wurstfree Maps/war3map.txt"));
//		w.write("File ID:\t" + r.readFourchar());
//		w.write(System.lineSeparator());
//		w.write("Version:\t" + r.readInt());
//		w.write(System.lineSeparator());
//		w.write("Subversion:\t" + r.readInt());
//		w.write(System.lineSeparator());
//		int doodadCount = r.readInt();
//		w.write("DooCount:\t" + doodadCount);
//		w.write(System.lineSeparator());
//		for(int i = 1; i <= doodadCount; i++){
//			w.write(r.readFourchar() + System.lineSeparator());
//			w.write(r.readInt() + System.lineSeparator());
//			w.write(String.valueOf(r.readFloat()) + System.lineSeparator());
//			w.write(String.valueOf(r.readFloat()) + System.lineSeparator());
//			w.write(String.valueOf(r.readFloat()) + System.lineSeparator());
//			w.write(String.valueOf(r.readFloat()) + System.lineSeparator());
//			w.write(String.valueOf(r.readFloat()) + System.lineSeparator());
//			w.write(String.valueOf(r.readFloat()) + System.lineSeparator());
//			w.write(String.valueOf(r.readFloat()) + System.lineSeparator());
//			w.write("TreeFlag:" + Byte.toString(r.readByte()) + System.lineSeparator());
//			w.write(Byte.toString(r.readByte()) + System.lineSeparator());
//			w.write(r.readInt() + System.lineSeparator());
//			w.write(r.readInt() + System.lineSeparator());
//			w.write(r.readInt() + System.lineSeparator());
//		}
//		w.write(r.readInt() + System.lineSeparator());
//		w.write(r.readInt() + System.lineSeparator());
//		w.close();
//		BinFileReader r1 = new BinFileReader(new File("C:/Users/Crigges/Desktop/Wurstfree Maps/war3map.doo"));
//		BinFileReader r2 = new BinFileReader(new File("C:/Users/Crigges/Desktop/war3map.doo"));
//		for(int i = 1; i <= 50; i++){
//			byte b1 = r1.readByte();
//			byte b2 = r2.readByte();
//			if(!(b1 == b2)){
//				System.out.println(b1 + " vs " + b2);
//			}else{
//				System.out.println("equal");
//			}
//		}
		DoodadFile df = new DoodadFile();
		JmpqEditor map1 = new JmpqEditor(new File("C:/Users/Crigges/Desktop/drtz7.w3x"));
		map1.extractFile("war3map.doo", new File("C:/Users/Crigges/Desktop/war3map.doo"));
		try {
			df.readFile(new File("C:/Users/Crigges/Desktop/war3map.doo"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Doodad d : df){
			if(d.xpos > -5000 && d.xpos < 5000 && d.ypos > -5000 && d.ypos < 5000 && d.percentHP == -1){
				d.xscale *= 0.1f;
				d.yscale *= 0.1f;
			}
		}
		df.writeFile(new File("C:/Users/Crigges/Desktop/Wurstfree Maps/war3map.doo"));
		//System.out.println(bytesToHex(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(7654.32f).array()));
	}
	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
