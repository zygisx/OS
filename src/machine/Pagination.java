package machine;


public class Pagination {
	
	private Word[] table;
	public static final int PAGING_TABLE_BEGIN = 0;
	public static final int PAGING_TABLE_END = 0xff;
	
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
	
	public Word[] getVirtualMachineMemory() {
		Word[] virtualMachineMemory = 
				new Word[Realmachine.BLOCK_SIZE * Realmachine.VIRTUAL_MACHINE_MEMORY_SIZE];
		
		/* FOR NOW NEW VIRTUAL MACHINES MEMORY ALLOCATED AFTER PAGINATION TABLE 
		 * 
		 * TODO ask lecture how allocate memory, random ?
		 * 
		 */
		for (int i = PAGING_TABLE_END + 1; i < Realmachine.VIRTUAL_MACHINE_MEMORY_SIZE; i++) {
			Word[] block = Realmachine.getBlock(i);
			for (int j = 0; j < Realmachine.BLOCK_SIZE; j++) {
				virtualMachineMemory[i*Realmachine.BLOCK_SIZE + j] = block[j];
			}
		}	
		return virtualMachineMemory;
	}
	
	

}
