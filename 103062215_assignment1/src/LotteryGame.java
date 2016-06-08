import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class LotteryGame
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int[] lotteryNum, yourNum;
		int i, special, correctNum, find;
		lotteryNum = new int[6];
		yourNum = new int[5];
		lotteryNum = generateNonRepeatedLottery(); //call function to generate a random lottery without redundancy.
		
		System.out.print("The numbers are:");
		for(i=0; i<6; i++) System.out.print(" " + lotteryNum[i]); //print the correct number.
		System.out.print("\n");

		while(true)
		{
			special = 0;
			correctNum = 0;	
			System.out.print("Please enter 5 numbers: ");
			if(!in.hasNextInt()) break; //if there is not input anymore, leave the loop.
			for(i=0; i<5; i++)
			{
				yourNum[i] = in.nextInt(); //get input.
				if(yourNum[i] == lotteryNum[5]) special=1; //if input match the special number, special will be 1.
				else
				{
					find = Arrays.binarySearch(lotteryNum, yourNum[i]);
					if(find>=0)correctNum++; //if input match a normal number, correctNum will +1.
				}
			}
			
			if(special==1) //when special=1.
			{
				if(correctNum>0) System.out.println("You win special prize!"); //if correctNum>0, you get the special prize.
				else System.out.println("Try again next time!"); //otherwise, get no prize.
			}
			else //when special=0.
			{
				if(correctNum<3) System.out.println("Try again next time!"); //if correct<3, you get no prize.
			}
			if(correctNum==5) System.out.println("You win A prize!"); //if correct=5, you get A prize.
			if(correctNum==4) System.out.println("You win B prize!"); //if correct=4, you get B prize.
			if(correctNum==3) System.out.println("You win C prize!"); //if correct=3, you get C prize.
		}
		
		in.close();
		
	}
	
	public static int[] generateNonRepeatedLottery()
	{
		Random random = new Random();
		int[] lotteryBox, lotteryNum;
		int i, select, temp;
		lotteryBox = new int[100];
		lotteryNum = new int[6];
		for(i=0; i<100; i++) lotteryBox[i]=i; //create a array having 0~99.
		for(i=0; i<6; i++) //get six number from the lotteryBox.
		{
			select = random.nextInt(100-i); //select get a random value, the random range will -1 every cycle.
			lotteryNum[i] = lotteryBox[select]; //choose the "select" element in lotterBox.
			temp = lotteryBox[select]; //after choosing the "select" element, replace it with the last element in lotterBox.
			lotteryBox[select] = lotteryBox[99-i];
			lotteryBox[99-i] = temp;
			//next cycle won't be possible to choose the same number because the random range -1.
		}
		Arrays.sort(lotteryNum, 0, 5); //sort first five normal number.
		return lotteryNum;
	}
	
}
