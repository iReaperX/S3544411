class RobotControl
{
   private Robot r;
   public RobotControl(Robot r)
   {
       this.r = r; 
   }

   public void control(int barHeights[], int blockHeights[], int required[], boolean ordered)
   {
	   
	   // The first past can be solved easily with out any arrays as the height of bars and blocks are fixed.
	   // Use the method r.up(), r.down(), r.extend(), r.contract(), r.raise(), r.lower(), r.pick(), r.drop()
	   // The code below will cause first arm to be moved up, the second arm to the right and the third to be lowered. 
	   
		int h = 2; // Initial height of arm 1
		int w = 1; // Initial width of arm 2
		int d = 0; // Initial depth of arm 3

		int sourceHt = 0; // Initial sum of all the blockHt
		int clearence = 0; // Initial clearance value
		int targetColHt = 1;
		int extendAmt = 10;
		int contractAmt = 1;
		int nextColumn = 3; // used for size 3 blocks (moving to next column
							// after the one before is full)
		int currentBar = 0; // used for size 3 blocks (next in the array)
		int targetBlockHt = 1; // Initial value of dropping location for size 1
								// blocks

		for (int i = blockHeights.length - 1; i >= 0; i--) {
			// Sum of all blocksHt in the array
			sourceHt += blockHeights[i];
		}

		for (int i = blockHeights.length - 1; i >= 0; i--) {
			// taking the topmost block then working its way down
			int blockHt = blockHeights[i];

			for (int k = 0; k < barHeights.length; k++) {
				// To find maximum barHeights so there are no obstructions
				if (barHeights[k] > clearence) {
					clearence = barHeights[k] + blockHeights[i] + blockHt;
				}
			}

			if (sourceHt > clearence) {
				clearence = sourceHt;
			}

			// Raise it high enough - assumed max obstacle = 4 < sourceHt

			while (h < clearence + 1) {
				// Raising 1
				r.up();

				// Current height of arm1 being incremented by 1
				h++;
			}
			System.out.println("Debug 1: height(arm1)= " + h + " width (arm2) = " + w + " depth (arm3) =" + d);
			// extends arm towards source blocks
			while (w < extendAmt) {
				r.extend();
				w++;
				contractAmt++;
			}

			while (h - d > sourceHt + 1) {
				// lowering third arm to grab block
				r.lower();

				// current depth of arm 3 being incremented
				d++;
			}
			r.pick();
			
			
			// When you pick the top block height of source decreases
			sourceHt -= blockHt;
			
			// raising third arm all the way until d becomes 0
						while (d > 0) {
							r.raise();
							d--;
						}
			
			while (contractAmt > targetColHt)
			{
				r.contract();
				contractAmt--;
				w--;
			}
			
			while ((h - 1) - d - blockHt > targetBlockHt - 1){
				r.lower();
				d++;
			}
			targetBlockHt += blockHt;
			r.drop();
			
			while (d > 0) {
				r.raise();
				d--;
			}
			}
		

	   // Part B requires you to access the array barHeights passed as argument as the robot arm must move
	   // over the bars
	   
	     
	   
	   
	   // The third part requires you to access the arrays barHeights and blockHeights 
	   // as the heights of bars and blocks are allowed to vary through command line arguments
	   

	   
	   
	   // The fourth part allows the user  to specify the order in which bars must 
	   // be placed in the target column. This will require you to use the use additional column
	   // which can hold temporary values
	   

	   
	   
	   
	   // The last part requires you to write the code to move from source column to target column using
	   // an additional temporary column but without placing a larger block on top of a smaller block 
	   
   }
}



