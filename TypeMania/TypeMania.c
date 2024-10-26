/*********************************************************************************************************
This is to certify that this project is my own work, based on my personal efforts in studying and applying the concepts
learned. I have constructed the functions and their respective algorithms and corresponding code by myself. The
program was run, tested, and debugged by my own efforts. I further certify that I have not copied in part or whole or
otherwise plagiarized the work of other students and/or persons.

Rainier A. Dulatre, DLSU ID# 12371734
*********************************************************************************************************/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <conio.h>

#define PASSWORD "rain"

typedef char String100[101];
typedef char String10[11];
typedef char String30[31];

struct recordTag{
	int ID;
	String10 level;
	int PhrLen; //phrase length
	String100 phrase;
};

struct scoreTag{
	String100 PlayerName;
	int EzScore;
	int MedScore;
	int HardScore;
	int totalScore;
};


/* PlayGame contains the mechanics to play the typing game and saves the score of the game
	@param records - a structure array containing the saved records
	@param nNumPhr - the number of phrases stored in the records
*/
void
PlayGame(struct recordTag records[], 
		 int nNumPhr)
{
	
	int i;
	int nCountEz = 0, nCountMed = 0, nCountHard = 0;
	
	for(i = 0; i < nNumPhr; i++)
	{//to check if there are enough phrases for the game to run (Minimum: 3 Easy, 2 Medium, 1 Hard)
		if(strcmp(records[i].level, "easy") == 0)
		{
			nCountEz++;
		}
		if(strcmp(records[i].level, "medium") == 0)
		{
			nCountMed++;
		}
		if(strcmp(records[i].level, "hard") == 0)
		{
			nCountHard++;
		}
	}
	
	if(nCountEz < 3 || nCountMed < 2 || nCountHard < 1)
	{
		if(nCountEz < 3)
		{
			printf("\nNot Enough EASY Level Phrases to Run The Game. Add Phrases Through the MANAGE DATA Menu\n");
		}
		if(nCountMed < 2)
		{
			printf("\nNot Enough MEDIUM Level Phrases to Run The Game. Add Phrases Through the MANAGE DATA Menu\n");
		}
		if(nCountHard < 1)
		{
			printf("\nNot Enough HARD Level Phrases to Run The Game. Add Phrases Through the MANAGE DATA Menu\n");
		}
		return;
	}
	
	//game variables
	int nLives = 3;
	int nTotScore = 0;
	int nEzScore = 0;
	int nMedScore = 0;
	int nHrdScore = 0;
	int nLevel = 0;
	String100 strName;
	srand(time(NULL));
	
	int UsedPhrase[nNumPhr]; //integer array to mark the indexes of phrases already used in the game
	
	for (i = 0; i < nNumPhr; i++)
	{ //initializes the int array
        UsedPhrase[i] = 0;
    }
	
	printf("\nWelcome to Typing Game!\n\n");
	printf("Enter your name: ");
	scanf(" %[^\n]%*c", strName); //allows user to input 2 names (i.e. names separated by spaces)
	
	
	while(nLives > 0  && nLevel < 5 + nCountHard)
	{//the game loop. Continues until user has no more lives or until the hard phrases are finished
		
		printf("\n============================");
		printf("\nPlayer Name: %s\n", strName);
		printf("Score: %d\n", nTotScore);
		printf("Lives: %d\n", nLives);
		printf("============================");
		
		String100 strInput = "";
		int RandIndex; //variable for picking random phrase
		int nWrong = 0; //variable to count every wrong character input
		String100 strRandPhr;
		
		if(nLevel >= 0 && nLevel <= 2)
		{//levels 1 - 3 are easy difficulty
		
			do
			{//a loop to make sure all phrases given are unique
				RandIndex = rand() % nNumPhr;
			}while(UsedPhrase[RandIndex] == 1 || strcmp(records[RandIndex].level, "easy") != 0);
			UsedPhrase[RandIndex] = 1;
		
			printf("\n---EASY Level Phrase---\n");
			printf("Your Phrase is...\n");
			printf("\n\t%s\n\n", records[RandIndex].phrase);
			printf("Input: ");
			scanf(" %[^\n]%*c", strInput);
			
			if(strcmp(records[RandIndex].phrase, strInput) == 0)
			{
				printf("\nCORRECT!\n");
				nEzScore += 1;
				
			}
			else
			{	
				printf("\nWRONG!\n");
				strcpy(strRandPhr, records[RandIndex].phrase);
				
				if(strlen(strInput) > strlen(strRandPhr))
				{//incase of incorrect phrase wherein input characters exceed the given phrase
					for(i = 0; i < strlen(strInput); i++)
					{//checks every wrong character in user input
						if(strInput[i] != strRandPhr[i])
						{
							nWrong += 1;
						}
					}
				}
				else
				{
					for(i = 0; i < strlen(strRandPhr); i++)
					{
						if(strInput[i] != strRandPhr[i])
						{
							nWrong += 1;
						}
					}
				}
			}
			nLevel++; //level increases whether or not you got it right or wrong
		}
		else if(nLevel == 3 || nLevel == 4)
		{//levels 3 and 4 are medium difficulty
			
			do
			{
				RandIndex = rand() % nNumPhr;
			}while(UsedPhrase[RandIndex] == 1 || strcmp(records[RandIndex].level, "medium") != 0);
			UsedPhrase[RandIndex] = 1;
		
			printf("\n---MEDIUM Level Phrase---\n");
			printf("Your Phrase is...\n");
			printf("\n\t%s\n\n", records[RandIndex].phrase);
			printf("Input: ");
			scanf(" %[^\n]%*c", strInput);
				
			if(strcmp(records[RandIndex].phrase, strInput) == 0)
			{
				printf("\nCORRECT!\n");
				nMedScore += 2;
			}
			else
			{
				printf("\nWRONG!\n");
				strcpy(strRandPhr, records[RandIndex].phrase);
				
				if(strlen(strInput) > strlen(strRandPhr))
				{
					for(i = 0; i < strlen(strInput); i++)
					{
						if(strInput[i] != strRandPhr[i])
						{
							nWrong += 1;
						}
					}
				}
				else
				{
					for(i = 0; i < strlen(strRandPhr); i++)
					{
						if(strInput[i] != strRandPhr[i])
						{
							nWrong += 1;
						}
					}
				}
			}
			nLevel++;
		}
		else
		{//levels 5 and above are hard difficulty
		
			do
			{
				RandIndex = rand() % nNumPhr;
			}while(UsedPhrase[RandIndex] == 1 || strcmp(records[RandIndex].level, "hard") != 0);
			UsedPhrase[RandIndex] = 1;
		
			printf("\n---HARD Level Phrase---\n");
			printf("Your Phrase is...\n");
			printf("\n\t%s\n\n", records[RandIndex].phrase);
			printf("Input: ");
			scanf(" %[^\n]%*c", strInput);
				
			if(strcmp(records[RandIndex].phrase, strInput) == 0)
			{
				printf("\nCORRECT!\n");
				nHrdScore += 3;
			}
			else
			{
				printf("\nWRONG!\n");
				strcpy(strRandPhr, records[RandIndex].phrase);
				
				if(strlen(strInput) > strlen(strRandPhr))
				{
					for(i = 0; i < strlen(strInput); i++)
					{
						if(strInput[i] != strRandPhr[i])
						{
							nWrong += 1;
						}
					}
				}
				else
				{
					for(i = 0; i < strlen(strRandPhr); i++)
					{
						if(strInput[i] != strRandPhr[i])
						{
							nWrong += 1;
						}
					}
				}
			}
			nLevel++;
		}
		nLives -= nWrong; //minuses the total number of incorrect character input to the remaining lives
		nTotScore = nEzScore + nMedScore + nHrdScore; //totals the score
	}
	
	
	if(nLives == 3)
	{//if all phrases were typed correctly
		printf("\nCongratulations! You Beat the Game\n");
		printf("Final Score: %d!\n", nTotScore);
	}
	else
	{
		printf("\nGame Over!\n");
		printf("Final Score: %d\n", nTotScore);
	}
	
	//saves the score of the current game to score.txt
	FILE *SaveFile;
	
	if((SaveFile = fopen("score.txt", "a")) == NULL)
	{
		SaveFile = fopen("score.txt", "w");
	}
	
	fprintf(SaveFile, "%s\n%d\n%d\n%d\n%d\n\n", strName, nEzScore, nMedScore, nHrdScore, nTotScore);
	
	fclose(SaveFile);
}


/* ViewScores allows the user to view the contents of score.txt, which are saved game scores
*/
void
ViewScores()
{
	FILE *ReadSaves;
	
	ReadSaves = fopen("score.txt", "r");
	
	if(ReadSaves == NULL)
	{//returns to quiz game menu if score.txt does not exist
		printf("\nscore.txt Does Not Exist\n");
		fclose(ReadSaves);
		return;
	}
	
	//to check if the file is empty
	fseek(ReadSaves, 0, SEEK_END);
   	int FileSize = ftell(ReadSaves);
   
   	if(FileSize == 0)
	{//returns to quiz game menu if score.txt is empty
	   	printf("\nNo Scores Saved. Play The Game to Save a Score\n");
   		fclose(ReadSaves);
   		return;
	}
	
	fseek(ReadSaves, 0, SEEK_SET); //resets the file position
	
	struct scoreTag scores[100]; //stores the scores from score.txt and is useful for checking identical names
	
	String100 strName;
	int nEzScore;
	int nMedScore;
	int nHrdScore;
	int nTotScore;
	
	int nSavedScores = 0; //number of saved scores in the scores structure array
	int i, j;
	
	while(fscanf(ReadSaves, " %[^\n]%*c %d %d %d %d", strName, &nEzScore, &nMedScore, &nHrdScore, &nTotScore) == 5)
	{//continues to read the file as long as inputs are being stored in the 2 variables
		int SameName = 0;
		for(i = 0; i < nSavedScores; i++)
		{//a loop to check identical names and compares their scores
		
			if(strcmp(strName, scores[i].PlayerName) == 0)
			{
				if(nTotScore > scores[i].totalScore)
				{//if an identical name is found and has a higher score, it replaces the stored score. otherwise, it retains the storedscore
					scores[i].totalScore = nTotScore;
					scores[i].EzScore = nEzScore;
					scores[i].MedScore = nMedScore;
					scores[i].HardScore = nHrdScore;
				}
				SameName = 1;
			}
		}
		if(SameName == 0)
		{//stores the name and score in the record if no identical name found
			strcpy(scores[nSavedScores].PlayerName, strName);
			scores[nSavedScores].EzScore = nEzScore;
			scores[nSavedScores].MedScore = nMedScore;
			scores[nSavedScores].HardScore = nHrdScore;
			scores[nSavedScores].totalScore = nTotScore;
			nSavedScores++;
		}
	}
	
	//sorts player scores through selection sort
	struct scoreTag temp;
	int nMax;
	
	for(i = 0; i < nSavedScores - 1; i++)
	{
		nMax = i;
		for(j = i + 1; j < nSavedScores; j++)
		{
			if(scores[j].totalScore > scores[nMax].totalScore)
			{
				nMax = j;
			}
		}
		if(nMax != i)
		{
			temp = scores[nMax];
			scores[nMax] = scores[i];
			scores[i] = temp;
		}
	}
	
	printf("\n\t\t    Saved Scores\n");
	printf("------------------------------------------------------\n");
	printf("No.\tName\t\tEasy\tMedium\tHard\tTotal\n\n");
	
	String100 strCheckName;
	
	for(i = 0; i < nSavedScores; i++)
	{//displays the scores stored in the score record
		int CheckSpc = 0;
		strcpy(strCheckName, scores[i].PlayerName);
		
		for(j = 0; j < strlen(strCheckName); j++)
		{//this is a checker incase a player decided to put 2 names, so that the score displays properly when viewed
			if(strCheckName[j] == 32)
			{
				CheckSpc = 1;
			}
		}
		if(CheckSpc == 1){
			printf("%d.\t%s\t%d\t%d\t%d\t%d\n", i + 1, scores[i].PlayerName, scores[i].EzScore, scores[i].MedScore, scores[i].HardScore, scores[i].totalScore);
		}
		else{//for players that only used 1 name
			printf("%d.\t%s\t\t%d\t%d\t%d\t%d\n", i + 1, scores[i].PlayerName, scores[i].EzScore, scores[i].MedScore, scores[i].HardScore, scores[i].totalScore);
		}
	}
	
	fclose(ReadSaves);
	
}


/* QuizGame is the menu for the typing game. players can choose to play, view the score, or go back to main menu
	@param records - a structure array containing the saved records
	@param nNumPhr - the number of phrases stored in the records
*/
void
QuizGame(struct recordTag Records[], 
		 int nNumPhr)
{
	String100 strOption;
	
	do
	{//loops until the user chooses to return to main menu
		printf("\n=====================================\n");
		printf("\t     TypeMania\n");
		printf("=====================================\n");
		printf("Please Refer to the Options Below:\n\n");
		printf("Enter (1) to PLAY the Game\n");
		printf("Enter (2) to VIEW the Saved SCORES\n");
		printf("Enter (3) to EXIT to the Main Menu\n");
		printf("=====================================\n");
		printf("Enter an Option: ");
		scanf("%s", strOption);
		
		if(atoi(strOption) < 1 || atoi(strOption) > 3)
		{
			printf("\nThat Option is Invalid, Please Re-enter a valid one\n");
		}
		
		switch(atoi(strOption))
		{
			case 1:
				PlayGame(Records, nNumPhr);
				break;
			case 2:
				ViewScores();
				break;
		}
	}while(atoi(strOption) != 3);
}


/* AddRecord allows the user to add phrases to the program which will be used in the game
	@param records - a structure array containing the saved records
	@param pNumPhr - the number of phrases stored in the records
*/
void
AddRecord(struct recordTag records[], 
		  int *pNumPhr)
{	
	if(*pNumPhr == 100)
	{//checks if number of records reached its limit
		printf("\nThe List of Records is Already Full\n");
		printf("Delete a Record if You Wish to Add a New One\n");
		return;
	}
	
	String100 strInputPhr;
	int nCheckPhr = -1; //a variable to check if the input phrase is already existing
	int i;
	
	printf("\nEnter a Phrase to Add: ");
	scanf(" %[^\n]%*c", strInputPhr);
		
	for(i = 0; i < *pNumPhr; i++)
	{//checks if a phrase being added already exists in the records, making sure all phrases in the records are unique
		if(strcmp(strInputPhr, records[i].phrase) == 0)
		{
			nCheckPhr = i;
		}
	}
	
	if(strlen(strInputPhr) > 100)
	{//checks if a phrase being added is more than 100 characters, which is the length limit for all phrases
		printf("\nThat Phrase Exceeds the 100 Character Length Limit. Input a Different Phrase\n");
		return;
	}
	
	if(nCheckPhr >= 0)
	{
		printf("\nThat Phrase is Already Existing in this Record:\n");
		printf("-------------------------------------------------\n");
		printf("ID:\t\t\t%d\n", records[nCheckPhr].ID);
		printf("Level:\t\t\t%s\n", records[nCheckPhr].level);
		printf("Number of Characters:\t%d\n", records[nCheckPhr].PhrLen);
		printf("Phrase:\t\t\t%s\n", records[nCheckPhr].phrase);
		printf("\nPlease Input a Different Phrase\n");
	}
	else
	{//adds the phrase to the record if it is unique
		records[*pNumPhr].ID = *pNumPhr + 1;
		
		if(strlen(strInputPhr) >= 1 && strlen(strInputPhr) <= 20)
		{//1 - 20 characters for easy
			strcpy(records[*pNumPhr].level, "easy");
		}
		else if(strlen(strInputPhr) >= 21 && strlen(strInputPhr) <= 50)
		{//21 - 50 characters for medium
			strcpy(records[*pNumPhr].level, "medium");
		}
		else
		{//50+ characters for hard
			strcpy(records[*pNumPhr].level, "hard");
		}
			
		records[*pNumPhr].PhrLen = strlen(strInputPhr);
			
		strcpy(records[*pNumPhr].phrase, strInputPhr);
			
		(*pNumPhr)++; //increases the number of records for each successful addition
		
		printf("\nRecord Successfully Added!\n");
	}
}


/* EditRecord allows the user to edit the existing records/phrases stored in the program
	@param records - a structure array containing the saved records
	@param nNumPhr - the number of phrases stored in the records
*/
void
EditRecord(struct recordTag records[], 
		   int nNumPhr)
{
	if(nNumPhr == 0)
	{//this does not allow the user to edit records if there are no records
		printf("\nNo Records to Edit. Please add a Record\n");
		return;
	}
	
	int i, j;
	String100 strEditID;
	String100 strChangePhr;
	int nCheckPhr; //variable to check if an identical phrase in the record is found
	
	printf("\n");
	for(i = 0; i < nNumPhr; i++)
	{//displays all existing records
		printf("ID:\t\t\t%d\n", records[i].ID);
		printf("Level:\t\t\t%s\n", records[i].level);
		printf("Number of Characters:\t%d\n", records[i].PhrLen);
		printf("Phrase:\t\t\t%s\n\n", records[i].phrase);
	}
	
	do
	{//continuously asks the user until an ID number in the record is chosen
		printf("Enter the ID number of the Record you want to EDIT: ");
		scanf("%s", strEditID);
		
		if(atoi(strEditID) < 1)
		{
			printf("Invalid Input. ID number must be positive integer\n\n");
		}
		if(atoi(strEditID) > nNumPhr)
		{
			printf("ID number not found. Enter a Different ID number\n\n");
		}
		
	}while(atoi(strEditID) < 1 || atoi(strEditID) > nNumPhr);
	
	for(i = 0; i < nNumPhr; i++)
	{//searches for the selected ID
		if(records[i].ID == atoi(strEditID))
		{
			//displays the selected record
			printf("\nID:\t\t\t%d\n", records[i].ID);
			printf("Level:\t\t\t%s\n", records[i].level);
			printf("Number of Characters:\t%d\n", records[i].PhrLen);
			printf("Phrase:\t\t\t%s\n\n", records[i].phrase);
			
			do
			{//makes sure that the newly entered phrase is unique
				nCheckPhr = -1;
				
				printf("Enter a New Phrase for this Record: ");
				scanf(" %[^\n]%*c", strChangePhr);
				
				for(j = 0; j < nNumPhr; j++)
				{
					if(strcmp(strChangePhr, records[j].phrase) == 0)
					{
						nCheckPhr = j;
					}
				}
				
				if(nCheckPhr >= 0)
				{
					printf("\nThat Phrase Already Exists in the Records. Enter a Different Phrase\n\n");
				}
				
				if(strlen(strChangePhr) > 100)
				{
					printf("\nThat Phrase Exceeds the 100 Character Length Limit. Input a Different Phrase\n\n");
				}
				
			}while(nCheckPhr >= 0 || strlen(strChangePhr) > 100);
			
			//similar to AddRecord, level assigned to the newly entered phrase depends on its character length
			if(strlen(strChangePhr) >= 1 && strlen(strChangePhr) <= 20)
			{
				strcpy(records[atoi(strEditID) - 1].level, "easy");
			}
			else if(strlen(strChangePhr) >= 21 && strlen(strChangePhr) <= 50)
			{
				strcpy(records[atoi(strEditID) - 1].level, "medium");
			}
			else
			{
				strcpy(records[atoi(strEditID) - 1].level, "hard");
			}
			
			records[atoi(strEditID) - 1].PhrLen = strlen(strChangePhr);
			
			strcpy(records[atoi(strEditID) - 1].phrase, strChangePhr);
			
			printf("\nRecord with ID Number %d Has Been Successfully Edited!\n\n", atoi(strEditID));
		}
	}
	
	//allows the user to continue editing records before returning to manage data menu 
	String100 strOption;
	
	printf("Enter (1) to Continue EDITING Records\n");
	printf("Enter (Any Integer Number) to Return to the MANAGE DATA Menu\n");
	printf("Enter an Option: ");
	scanf("%s", strOption);
	
	if(atoi(strOption) == 1)
	{
		EditRecord(records, nNumPhr);
	}
	
}


/* DeleteRecord allows the user to delete the existing records/phrases stored in the program
	@param records - a structure array containing the saved records
	@param pNumPhr - the number of phrases stored in the records
*/
void
DeleteRecord(struct recordTag records[], 
			 int *pNumPhr)
{
	int i;
	String100 strDltID;
	
	if(*pNumPhr == 0)
	{//does not allow the user to delete records if there are no records
		printf("\nNo Records to Delete. Please Add a Record\n");
		return;
	}
	
	printf("\n");
	for(i = 0; i < *pNumPhr; i++)
	{//displays all existing records
		printf("ID:\t\t\t%d\n", records[i].ID);
		printf("Level:\t\t\t%s\n", records[i].level);
		printf("Number of Characters:\t%d\n", records[i].PhrLen);
		printf("Phrase:\t\t\t%s\n\n", records[i].phrase);
	}
	
	do
	{//continuously asks the user until an ID number in the record is chosen
		printf("Enter the ID number of the Record you want to DELETE: ");
		scanf("%s", strDltID);
			
		if(atoi(strDltID) < 1)
		{
			printf("Invalid Input. ID number must be positive integer\n\n");
		}
		if(atoi(strDltID) > *pNumPhr)
		{
			printf("ID number not found. Enter a Different ID number\n\n");
		}
		
	}while(atoi(strDltID) < 1 || atoi(strDltID) > *pNumPhr);
		
	for(i = 0; i < *pNumPhr; i++)
	{//searches for the selected ID
		if(records[i].ID == atoi(strDltID))
		{
			for(i = atoi(strDltID) - 1; i < *pNumPhr - 1; i++)
			{//readjusts the ID of the records to make sure there are no gaps
				records[i] = records[i+1];
				records[i].ID--;
			}
			(*pNumPhr)--; //decreases the number of records for each successful deletion
			
			printf("\nRecord with ID Number %d Has Been Successfully Deleted!\n\n", atoi(strDltID));
		}
	}
	
	//allows the user to continue deleting records before returning to manage data menu 
	String100 strOption;
	
	printf("Enter (1) to Continue DELETING Records\n");
	printf("Enter (Any Integer Number) to Return to the MANAGE DATA Menu\n");
	printf("Enter an Option: ");
	scanf("%s", strOption);
	
	if(atoi(strOption) == 1)
	{
		DeleteRecord(records, pNumPhr);
	}
}


/* ImportData allows the user to import records from a text file and store them in the program
	@param records - a structure array containing the saved records
	@param pNumPhr - the number of phrases stored in the records
*/
void
ImportData(struct recordTag records[], 
		   int *pNumPhr)
{
	FILE *ImportRecs;
	String100 strFile;
	String100 strOption;
	
	do
	{//loops until user chooses to re-input a valid filename
		strcpy(strOption, "");
		strcpy(strFile, "");
		
		printf("\nEnter a Filename to Import Records From: ");
		scanf(" %[^\n]%*c", strFile); //allows importing from filenames with spaces
		
		ImportRecs = fopen(strFile, "r");
	
		if(ImportRecs == NULL)
		{//allows user to re-input filename in case the filename does not exist
			printf("\nThat File Does Not Exist. Enter a Different Filename\n");
			printf("\nEnter (1) to Re-enter a Different Filename\n");
			printf("Enter (Any Number/Character) to Return to the MANAGE DATA menu\n");
			printf("Enter an Option: ");
			scanf("%s", strOption);
			
			if(atoi(strOption) < 1 || atoi(strOption) > 1)
			{
				return;
			}
		}
		
	}while(atoi(strOption) == 1);
	
	//to check if the inputted filename is empty
	fseek(ImportRecs, 0, SEEK_END);
   	int nFileSize = ftell(ImportRecs);
   
   	if(nFileSize == 0)
	{//return to manage data menu if filename is empty
	   	printf("\nThat File is Empty. Enter a Different Filename\n");
   		fclose(ImportRecs);
   		return;
	}
	
	fseek(ImportRecs, 0, SEEK_SET); //resets the file position
	
	int nImpID;
	String100 strImpLvl;
	int nImpLen;
	String100 strImpPhr;
	int i;
	int nCheckFull = 0;
	
	while(fscanf(ImportRecs, "%d %s %d %[^\n]%*c", &nImpID, strImpLvl, &nImpLen, strImpPhr) == 4)
	{//continues to read the file as long as inputs are being stored in the 4 variables
		int nPhrChecker = 0;
		if(*pNumPhr == 100)
		{//stops importing if the record list reaches its limit
			nCheckFull = 1;
		}
		else
		{
			for(i = 0; i < *pNumPhr; i++)
			{//checks if the phrase being imported is identical to a phrase in the record, making sure phrases in the record are unqiue
				if(strcmp(records[i].phrase, strImpPhr) == 0)
				{
					nPhrChecker = 1;
				}
			}
			if(strlen(strImpPhr) > 100)
			{//checks if phrase is more than 100 characters, which is the length limit for all phrases
				nPhrChecker = 1;
			}
			if(nPhrChecker == 0)
			{//imports the record if the phrase is unique
				records[*pNumPhr].ID = *pNumPhr + 1;
				strcpy(records[*pNumPhr].level, strImpLvl);
				records[*pNumPhr].PhrLen = nImpLen;
				strcpy(records[*pNumPhr].phrase, strImpPhr);
				(*pNumPhr)++; //increases the number of records for each successful importation
			}	
		}
	}
	
	fclose(ImportRecs);
	
	if(nCheckFull == 1)
	{
		printf("\nStopped Importing. Maximum Number of Records Reached\n");
	}
	else
	{
		printf("\nRecords Successfully Imported\n");
	}
	
	
}


/* ExportData allows the user to export the stored records in the program into a text file
	@param records - a structure array containing the saved records
	@param nNumPhr - the number of phrases stored in the records
*/
void
ExportData(struct recordTag records[], 
		   int nNumPhr)
{
	if(nNumPhr == 0)
	{//does not allow the user to export records if there are no records
		printf("\nNo Records to Export. Add a Record Through the MANAGE DATA menu\n");
		return;
	}
	
	FILE *ExportRecs;
	String100 strFile;
	int i;
	
	do
	{//loops until a filename unter 30 characters is entered
		printf("\nEnter a Filename to Create or Overwrite: ");
		scanf(" %[^\n]%*c", strFile); //allows exporting to filenames with spaces
		
		if(strlen(strFile) > 30)
		{
			printf("\nFilenames can only have up to 30 characters (including the file extension)\n");
		}
		
	}while(strlen(strFile) > 30);
	
	ExportRecs = fopen(strFile, "w");
	
	for(i = 0; i < nNumPhr; i++)
	{//exports the records to the file
		fprintf(ExportRecs, "%d\n%s\n%d\n%s\n\n", records[i].ID, records[i].level, records[i].PhrLen, records[i].phrase);
	}
	
	fclose(ExportRecs);
	
	printf("\nRecords Successfully Exported!\n");
	
}


/* Login asks the user for the admin password before accessing the game's data
   @returns 1 if login is successful. Otherwise, returns 0
*/
int
Login()
{
	String30 strInputPass;
	int nLogged = 0; //variable to check if login was successful or not
	int i;
	String100 strLogOption;
	
	do
	{//loops until successful login or if user chooses to return to main menu
		strcpy(strLogOption, "");
		
		printf("\nPlease Login Before Accessing the Game Data");
		printf("\n--------------------------------------------");
	
		printf("\nEnter Password: ");
		for(i = 0; i < 30; i++)
		{//for replacing the input password characters into asterisks (*)
			strInputPass[i] = getch();
			if(strInputPass[i] == 13)
			{//checks if user presses the enter button, and stops the input
				strInputPass[i] = '\0';
				i = 30;
			}
			else
			{
				printf("*");
			}
		}
	
		if(strcmp(strInputPass, PASSWORD) == 0)
		{
			printf("\n\nYou Have Successfully Logged in!\n");
			nLogged = 1;
		}
		else
		{
			printf("\n\nYou Entered the Wrong Password!\n");
			printf("\nEnter (1) to retry Logging in\n");
			printf("Enter (Any Number/Character) to return to MAIN MENU\n");
			printf("Enter Option: ");
			scanf("%s", strLogOption);
		}
	
	}while(atoi(strLogOption) == 1);
	
	return nLogged;
}


/* ManageData is the menu for all record related operations, allowing the user to add, edit, delete, import, and export records
	@param records - a structure array containing the saved records
	@param pNumPhr - the number of phrases stored in the records
*/
void
ManageData(struct recordTag records[], 
		   int *pNumPhr)
{
	String100 strOption;
	
	do
	{//loops until user chooses to return to main menu
		printf("\n=====================================\n");
		printf("\t  Manage Game Data\n");
		printf("=====================================\n");
		printf("Please Refer to the Options Below:\n\n");
		printf("Enter (1) to ADD a Record\n");
		printf("Enter (2) to EDIT a Record\n");
		printf("Enter (3) to DELETE a Record\n");
		printf("Enter (4) to IMPORT DATA\n");
		printf("Enter (5) to EXPORT DATA\n");
		printf("Enter (6) to return to the MAIN MENU\n");
		printf("=====================================\n");
		
		printf("Enter an Option: ");
		scanf("%s", strOption);
		
		if(atoi(strOption) < 1 || atoi(strOption) > 6)
		{
			printf("\nThat Option is Invalid, Please Re-enter a valid one\n");
		}
		
		switch(atoi(strOption))
		{
			case 1:
				AddRecord(records, pNumPhr);
				break;
			case 2:
				EditRecord(records, *pNumPhr);
				break;
			case 3:
				DeleteRecord(records, pNumPhr);
				break;
			case 4:
				ImportData(records, pNumPhr);
				break;
			case 5:
				ExportData(records, *pNumPhr);
				break;
		}
		
	}while(atoi(strOption) != 6);
	
}


/* ExitGame asks the user if they surely want to exit the program
	@returns 3 to exit the program and returns a number not equal to 3 or 0 (if a string or character is inputted) to return to main menu
*/
int
ExitGame()
{
	String100 strOption;
	
	printf("\n=======================================================\n");
	printf("ARE YOU SURE YOU WANT TO EXIT THE GAME?\n");
	printf("Enter (3) Again if You Wish to Exit the Game\n");
	printf("Enter (Any Number/Character) if You Wish to Keep Playing\n");
	printf("========================================================\n");
	printf("Enter an Option: ");
	scanf("%s", strOption);
	return atoi(strOption);
}


int
main()
{	
	String100 strOption;
	
	struct recordTag records[100]; //structure array of records/record storage
	int nNumPhr = 0; //number of phrases
	
	do
	{//loops until user chooses to exit the game/program
		printf("\n=========================================\n");
		printf("\tTypeMania: A Typing Game\n");
		printf("=========================================\n");
		printf("Please Refer to the Options Below:\n\n");
		printf("Enter (1) to PLAY the Game\n");
		printf("Enter (2) to MANAGE the DATA of the Game\n");
		printf("Enter (3) to EXIT the Game\n");
		printf("=========================================\n");
	
		printf("Enter an Option: ");
		scanf("%s", strOption);
		
		if(atoi(strOption) < 1 || atoi(strOption) > 3)
		{
			printf("\nThat Option is Invalid, Please Re-enter a valid one\n");
		}
		
		switch(atoi(strOption))
		{
			case 1:
				QuizGame(records, nNumPhr);
				break;
			case 2:
				if(Login() == 1)
				{//user gets to access the record data if successful login (i.e. Login() returns 1)
					ManageData(records, &nNumPhr);
				}
				break;
			case 3:
				if(ExitGame() == 3)
				{//program terminates if ExitGame() returns 3 (i.e. user re-enters 3)
					break;
				}
				else
				{
					strcpy(strOption, "0");
				}
		}
		
	}while(atoi(strOption) != 3);
	
	printf("\n\n--Thank You for Playing TypeMania!--\n\n");
	
	return 0;
}


