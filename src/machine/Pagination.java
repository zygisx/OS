package machine;

import java.util.Random;

public class Pagination {
	
	private Word[] table;
	public static final int PAGING_TABLE_BEGIN = 0;
	public static final int PAGING_TABLE_END = 0x99;
	
	public Pagination(Word[] table) {
		this.table = table;
	}
	
	/* return real address */
	public int getRealAddress(int virtualAddress) {
		byte plr = RealMachineRegisters.getPLR();
		int block = virtualAddress / Realmachine.BLOCK_SIZE;
		int offset = virtualAddress % Realmachine.BLOCK_SIZE;
		int realMemoryblock = Realmachine.getWord(plr*Realmachine.BLOCK_SIZE+block).getHexValue(); // returns real memory block number
		return realMemoryblock*Realmachine.BLOCK_SIZE + offset;
	}
	
	public Word[] getVirtualMachineMemory() {
		Word[] virtualMachineMemory = 
				new Word[Realmachine.BLOCK_SIZE * Realmachine.VIRTUAL_MACHINE_MEMORY_SIZE];
		Random rand = new Random();

		  
//		int VMNum = 0; // Just for now, when only one VM needed
//		
//		for (int i = 0; i < Realmachine.VIRTUAL_MACHINE_MEMORY_SIZE; i++) {
//			Word[] block = Realmachine.getBlock(i + Realmachine.PAGINATION_TABLE_SIZE); //  get block from real memory
//			this.table[VMNum*0x10+i].setWordHexInt(i + Realmachine.PAGINATION_TABLE_SIZE); // set block address in paging table  
//			for (int j = 0; j < Realmachine.BLOCK_SIZE; j++) {
//				virtualMachineMemory[i*Realmachine.BLOCK_SIZE + j] = block[j];
//			}
//		}	
//		return virtualMachineMemory;
		
		
		/* Choose random pagination table block */
		
		int tableNum = rand.nextInt(Realmachine.PAGINATION_TABLE_SIZE);
		while (this.isPagingTableBlockOccupied(tableNum)) {
			if (++tableNum >= Realmachine.PAGINATION_TABLE_SIZE) 
				tableNum = 0;
			/*FIXME NOT PROTECTED FROM THAT ALL PAGINATION TABLES BLOCKS ARE OCCUPIED */
		}
		/* set plr */
		RealMachineRegisters.setPLR((byte)tableNum);
		
		/* Choose random memory block for each vm memory block */
		for (int i = 0; i < Realmachine.VIRTUAL_MACHINE_MEMORY_SIZE; i++) {
			/* get random unused block from memory */
			int blockNum = rand.nextInt(Realmachine.BLOCK_COUNT - Realmachine.PAGINATION_TABLE_SIZE) + Realmachine.PAGINATION_TABLE_SIZE;
			/* if it is occupied then search next free block */
			while (this.isBlockOccupied(blockNum)) {
				if (++blockNum >= Realmachine.BLOCK_COUNT)
					blockNum = Realmachine.PAGINATION_TABLE_SIZE;
			}
			/* get block from real memory */
			Word[] block = Realmachine.getBlock(blockNum);  
			/* set block address in paging table */
			this.table[tableNum*0x10+i].setWordHexInt(blockNum);   
			for (int j = 0; j < Realmachine.BLOCK_SIZE; j++) {
				virtualMachineMemory[i*Realmachine.BLOCK_SIZE + j] = block[j];
			}
		}
		return virtualMachineMemory;		
	}
	
	public boolean isBlockOccupied(int block) {
		/* Iterates over all pagination table */
		for (int i = 0; i < Realmachine.PAGINATION_TABLE_SIZE; i++) { 
			/* check if block occupied */
			if (this.isPagingTableBlockOccupied(i)) {
				/* then iterates over all block and searches for block */
				for(Word w : Realmachine.getBlock(i)) {
					if (w.getHexValue() == block) {
						return true;
					}
				}
			}
		}
		/* if block not found then returns false */
		return false;	
	}
	
	public boolean isPagingTableBlockOccupied(int block) {
		
		/* 
		 * if pagination tables block is occupied then its 
		 * first word cannot be 0000 
		 */
		if (this.table[block * Realmachine.BLOCK_SIZE].getHexValue() != 0)
			return true;
		return false;
	}
}
