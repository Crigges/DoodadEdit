import java.util.Iterator;
import java.util.LinkedList;

public class Doodad {
	String objectId;
	int variation;
	float xpos, ypos, zpos;
	float angle;
	float xscale, yscale, zscale;
	byte flag;
	byte percentHP;
	int tablePointer;
	boolean hasSets = false;
	LinkedList<ItemSet> sets;
	int setAmount;
	int unknownData;
	int number;
	
	public boolean isDestructable(){
		return percentHP != -1;
	}

	public Doodad(BinFileReader r){
		objectId = r.readFourchar();
		variation = r.readInt();
		xpos = r.readFloat();
		ypos = r.readFloat();
		zpos = r.readFloat();
		angle = r.readFloat();
		xscale = r.readFloat();
		yscale = r.readFloat();
		zscale = r.readFloat();
		flag = r.readByte();
		percentHP = r.readByte();
		tablePointer = r.readInt();
		if (tablePointer != -1){
			hasSets = true;
			sets = new LinkedList<ItemSet>();
			setAmount = r.readInt();
			for(int i = 1; i <= setAmount; i++){
				sets.add(new ItemSet(r));
			}
		}
		unknownData = r.readInt();
		number = r.readInt();
	}
	
	public void writeToFile(BinFileWriter w){
		w.writeFourchar(objectId);
		w.writeInt(variation);
		w.writeFloat(xpos);
		w.writeFloat(ypos);
		w.writeFloat(zpos);
		w.writeFloat(angle);
		w.writeFloat(xscale);
		w.writeFloat(yscale);
		w.writeFloat(zscale);
		w.writeByte(flag);
		w.writeByte(percentHP);
		w.writeInt(tablePointer);
		if(hasSets){
			w.writeInt(setAmount);
			for(ItemSet is: sets){
				w.writeFourchar(is.setId);
				w.writeInt(is.dropChance);
			}
		}
		w.writeInt(unknownData);
		w.writeInt(number);
	}
	
	@Override
	public boolean equals(Object obj){
		Doodad doo = (Doodad) obj;
		boolean canBe = objectId.equals(doo.objectId) &&
				variation == doo.variation &&
				xpos == doo.xpos &&
				ypos == doo.ypos &&
				zpos == doo.zpos &&
				angle == doo.angle &&
				xscale == doo.xscale &&
				yscale == doo.yscale &&
				zscale == doo.zscale &&
				flag == doo.flag &&
				percentHP == doo.percentHP &&
				tablePointer == doo.tablePointer &&
				setAmount == doo.setAmount &&
				number == doo.number;
		if(canBe){
			if(hasSets){
				Iterator<ItemSet> i = sets.iterator();
				for(ItemSet s : doo.sets){
					if(!i.next().equals(s)){
						return false;
					}
				}
			}
			return true;
		}else{
			return false;
		}
	}
	
	public class ItemSet{
		String setId;
		int dropChance;
		
		public ItemSet(BinFileReader r){
			setId = r.readFourchar();
			dropChance = r.readInt();
		}
		
		public boolean equals(Object obj){
			ItemSet set = (ItemSet) obj;
			return setId.equals(set.setId) && dropChance == set.dropChance;
		} 
		
	}
	
}
