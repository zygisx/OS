package machine;


public class Pagination {
	
	private Word[] table;
	
	public Pagination(Word[] table) {
		this.table = table;
	}
	
	/* return real address */
	public int getRealAddress(byte virtualAddress) {
		byte plr = RealMachineRegisters.getPLR();
		int block = virtualAddress / 0x10;
		int offset = virtualAddress % 0x10;
		int realMemoryblock = Realmachine.getWord(plr+block).getHexValue(); // returns real memory block number
		return realMemoryblock*0x10 + offset;
	}
	
	

}
