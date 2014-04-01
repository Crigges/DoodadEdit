import java.awt.Polygon;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


public class DoodadFile implements Iterable<Doodad>{
	//Header
	String fileId = "W3do";
	int fileVersion = 8;
	int subVersion = 11;
	int doodadCount = 0;
	int highstDoodadNumber = 0;
	//Content
	LinkedList<Doodad> doodadList = new LinkedList<Doodad>();
	DynamicArray<Doodad> doodadArray;
	Polygon p;
	
	public DoodadFile(){
	}
	
	public void readFile(File doo) throws IOException{
		BinFileReader r = new BinFileReader(doo);
		if (!r.readFourchar().equals(fileId)){
			throw new IOException("Invaild File Type");
		}
		fileVersion = r.readInt();
		subVersion = r.readInt();
		doodadCount = r.readInt();
		doodadArray = new DynamicArray<Doodad>(doodadCount, 100);
		for(int i = 1; i <= doodadCount; i++){
			Doodad d = new Doodad(r);
			doodadList.add(d);
			doodadArray.set(d.number, d);
			if(d.number > highstDoodadNumber){
				highstDoodadNumber = d.number;
			}
		}
		r.close();
	}
	
	public void writeFile(File doo){
		if(doo.exists()){
			doo.delete();
		}
		BinFileWriter w = new BinFileWriter(doo);
		w.writeFourchar(fileId);
		w.writeInt(fileVersion);
		w.writeInt(subVersion);
		w.writeInt(doodadCount);
		for(Doodad d : doodadList){
			d.writeToFile(w);
		}
		w.writeInt(0);
		w.writeInt(0);
		w.close();
	}

	public boolean hasEqualDoodad(Doodad d){
		if (doodadArray.get(d.number) != null){
			return doodadArray.get(d.number).equals(d);
		}else{
			return false;
		}
	}
	
	public void addDoodad(Doodad d){
		highstDoodadNumber++;
		doodadCount++;
		d.number = highstDoodadNumber;
		doodadList.add(d);
		doodadArray.set(d.number, d);
	}
	
	@Override
	public Iterator<Doodad> iterator() {
		return doodadList.iterator();
	}

}
