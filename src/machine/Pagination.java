package machine;


public class Pagination {
	
	private Word[] table;
	public static final int PAGING_TABLE_BEGIN = 0;
	public static final int PAGING_TABLE_END = 0x99;
	
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
		int VMNum = 0; // Just for now, when only one VM needed
		
		for (int i = 0; i < Realmachine.VIRTUAL_MACHINE_MEMORY_SIZE; i++) {
			Word[] block = Realmachine.getBlock(i + Realmachine.PAGINATION_TABLE_SIZE); //  get block from real memory
			this.table[VMNum*0x10+i].setWordHexInt(i + Realmachine.PAGINATION_TABLE_SIZE); // set block address in paging table  
			for (int j = 0; j < Realmachine.BLOCK_SIZE; j++) {
				virtualMachineMemory[i*Realmachine.BLOCK_SIZE + j] = block[j];
			}
		}	
		return virtualMachineMemory;
	}
}
