import java.util.Scanner;
import java.util.Random;

public class NumberPuzzle
{

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int i, j, inversion=0, position=0, temp;
		int[] puzzle;
		char command;
		puzzle = new int[9];
		puzzle = generatePuzzle(); //call function to generate a random puzzle.
		//count how many inversion, and if the element=9(mean the space), skip it.
		for(i=0; i<9; i++) for(j=i+1; j<9; j++)	if(puzzle[i]!=9 && puzzle[j]!=9) if(puzzle[i]>puzzle[j]) inversion++;
		if(inversion%2==0) System.out.println("Having solusion."); //if inversions  are even, there is a solution.
		else System.out.println("Having no solusion."); //if inversions are odd, there is not a solution.
		//print the puzzle.
		for(i=0; i<3; i++)
		{
			for(j=0; j<3; j++)
			{
				if(puzzle[i*3+j]==9) System.out.print(" "); //if number=9, print a space.
				else System.out.print(puzzle[i*3+j]); //otherwise, print the number directly.
			}
			System.out.print("\n"); //change line every 3 number.
		}
		while(true) //control.
		{
			System.out.print("Control via WASD: ");
			if(!in.hasNext()) break; //if there is not input anymore, leave the loop.
			command = in.next().charAt(0);
			
			for(i=0; i<9; i++) if(puzzle[i]==9) position=i; //first get the position of the space.
			
			if(command=='w' || command=='W') //if w, the space need to go down.
			{
				if(position<6) //if the space isn't at the bottom row, we can move it down.
				{
					temp = puzzle[position]; //change it with the number under it. (+3)
					puzzle[position]=puzzle[position+3];
					puzzle[position+3]=temp;
				}
			}
			if(command=='a' || command=='A') //if a, the space need to go right.
			{
				if(position%3!=2) //if the space isn't at the right column, we can move it right.
				{
					temp = puzzle[position]; //change it with the number right to it. (+1)
					puzzle[position]=puzzle[position+1];
					puzzle[position+1]=temp;
				}
			}
			if(command=='s' || command=='S') //if s, the space need to go up.
			{
				if(position>2) //if the space isn't at the top row, we can move it up.
				{
					temp = puzzle[position]; //change it with the number above it. (-3)
					puzzle[position]=puzzle[position-3];
					puzzle[position-3]=temp;
				}
			}
			if(command=='d' || command=='D') //if d, the space need to go left.
			{
				if(position%3!=0) //if the space isn't at the left column, we can move it left.
				{
					temp = puzzle[position]; //change it with the number left to it. (-1)
					puzzle[position]=puzzle[position-1];
					puzzle[position-1]=temp;
				}
			}
			
			for(i=0; i<3; i++) //then print the result after moving.
			{
				for(j=0; j<3; j++)
				{
					if(puzzle[i*3+j]==9) System.out.print(" ");
					else System.out.print(puzzle[i*3+j]);
				}
				System.out.print("\n");
			}
		}
		
		in.close();
		
	}
	
	public static int[] generatePuzzle()
	{
		Random random = new Random();
		int[] numberBox, puzzle;
		int i, select, temp;
		numberBox = new int[9];
		puzzle = new int[9];
		for(i=0; i<9; i++) numberBox[i]=i+1; //create a Box that have number 1~9.
		for(i=0; i<9; i++) //get all number in the Box, and 9 means space.
		{
			select = random.nextInt(9-i); //select get a random value, the random range will -1 every cycle.
			puzzle[i] = numberBox[select]; //choose the "select" element in the Box.
			temp = numberBox[select]; //after choosing the "select" element, replace it with the last element in the Box.
			numberBox[select] = numberBox[8-i];
			numberBox[8-i] = temp;
			//next cycle won't be possible to choose the same number because the random range -1.
		}
		return puzzle;
	}
}
