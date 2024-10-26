/******************************************************************
This is to certify that this project is my own work, based on my
personal efforts in studying and applying the concepts learned. I
have constructed the functions and their respective algorithms
and corresponding code by myself. The program was run, tested, and
debugged by my own efforts. I further certify that I have not
copied in part or whole or otherwise plagiarized the work of other
students and/or persons.

Rainier Antolin Dulatre, DLSU ID# 12371734
******************************************************************/

/*
	Description: A simple ticket booking program that allows the user to book a bus trip from Manila to laguna and vice versa, cancel a booking, 
	view the schedule of trips, and update the departure time.
	Programmed by: Rainier Dulatre
	Last Modified: November 27, 2023
	Version: 1.2
*/

#include <stdio.h>

/*	This function displays the main menu of the program with 5 different options, along with the program's current time
	Precondition: nCurTime is an integer within the range of 4 to 19 but not 12 nor 13
	@param nCurTime is the current time of the program displayed in thr 24-Hour Format	
*/
void MainMenu(int nCurTime){
	printf("\n==========================================================\n%s%s", "\t\tWelcome to Arrows Express!\n", 
	"==========================================================\n");
	printf("Please Refer to the Options Below:\n\n");
	printf("Enter (1) to Book a Ticket\n");
	printf("Enter (2) to Cancel a Booking\n");
	printf("Enter (3) to Display Schedule\n");
	printf("Enter (4) to Update Departure Time\n");
	printf("Enter (5) to Close Application\n\n");
	printf("Current Time (24-Hour Format): %d:00\n", nCurTime);
	printf("==========================================================\n");
}


/*	This function displays the top down view of the bus and indicates which seats are available or not
	Precondition: *cS1 to *cS14 are uppercase letters that are only either 'X' or 'O'
	@params *cS1 to *cS14 correspond to the seats of the bus, wherein *cS1 is for seat 1, *cs2 is for seat 2, until *cs14, 
	which is the 14th seat of the bus
*/
void BusLayout(char *cS1, char *cS2, char *cS3, char *cS4, char *cS5, char *cS6, char *cS7, char *cS8, char *cS9, char *cS10, 
	char *cS11, char *cS12, char *cS13, char *cS14){
	printf("\n        /\n");
	printf("  _/___/      ___        _____________________________________\n");
	printf(" [|                                                      |    |\n");
	printf(" [|      ____      ____      ____      ____      ____    |    |}\n");
	printf(" [|          |       %c |       %c |       %c |       %c |   |    |}\n", *cS2, *cS3, *cS4, *cS5);
	printf("  |        %c |     ____|     ____|     ____|     ____|   |    |\n", *cS1);
	printf("  |      ____|     ____      ____      ____        %c |   |    |\n", *cS9);
	printf("  |                  %c |       %c |       %c |     ____|   |    |\n", *cS6, *cS7, *cS8);
	printf("  |      ____      ____|     ____|     ____|       %c |   |    |\n", *cS10);
	printf("  |          |     ____      ____      ____      ____|   |    |\n");
	printf(" [|          |       %c |       %c |       %c |       %c |   |    |}\n", *cS11, *cS12, *cS13, *cS14);
	printf(" [|      ____|     ____|     ____|     ____|     ____|   |    |}\n");
	printf(" [|_____       __________________________________________|____|\n");
	printf("    \\   \\\n");
	printf("         \\\n");
	printf("Note: 'O' - Available Seats\n");
	printf("      'X' - Unavailable Seats\n");
}


/*	This function changes the value of the seats to make them either available or unavailable. This function checks whether the chosen seat
	was already taken or not booked. This function assigns an ID number to the seat chosen. This function also add or subtracts to the total
	number of tickets booked in the current bus trip.
	Precondition: cBookOption is an uppercase letter that is only either 'B' or 'C'. *nSeatAvail is an integer. *cSeat and *nIDVal is 
	an integer within the range of 1 to 14. *nTotalTix and nIDNum are integers greater than or equal to 0
	@param cBookOption is the booking option indicating whether the user wanted to book a trip (B) or cancel a booking (C)
	@param *nSeatAvail is a value that changes whenever booking or cancelling is successful or not to determine whether or not the program 
	should ask the user again to input a different seat.
	@param *cSeat is the value of the chosen seat, which is initially 'O' and changes to 'X' when booked is successful and changes back to 'O' 
	when cancelling a booking is successful
	@param *nTotalTix is the total number tickets booked on the current trip, which increments by 1 when successfully booked a trip and 
	decrements by 1 when successfully cancelled a booking
	@param nIDNum is the value that assigns an ID number to the seat chosen
	@param *nIDVal is the ID number value of a specified seat
*/
void SaveSeatVal(char cBookOption, int *nSeatAvail, char *cSeat, int *nTotalTix, int nIDNum, int *nIDVal){
	
	/* To check if the option was to book a trip or cancel a trip */
	switch(cBookOption){
		case 'B':
			/* To check if a seat is already taken or not, otherwise it changes the seat value, assigns an ID number, 
			and add to total number of tickets booked*/
			if (*cSeat == 'X'){
				printf("\nBooking Cannot Be Made as Seat is Not Available\n");
				*nSeatAvail = 0;
				break;
			} else{
				*cSeat = 'X';
				printf("\nBooking Successful\n");
				*nSeatAvail = 1;
				*nTotalTix += 1;
				*nIDVal = nIDNum;
				break;
			}
		case 'C':
			/* To check if a seat is still empty or not, otherwise it changes the seat value, removes the assigned ID number, 
			and subtracts to total number of tickets booked*/
			if (*cSeat == 'O'){
				printf("\nCannot Be Cancelled as Seat is Not Booked\n");
				*nSeatAvail = 0;
				break;
			} else{
				*cSeat = 'O';
				printf("\nSuccessfully Cancelled a Reservation\n");
				*nSeatAvail = 1;
				*nTotalTix -= 1;
				*nIDVal = 0;
				break;
			}
	}
}


/*	This function displays the booked seats along with their corresponding ID numbers inputted by the user
	Precondition: nID1 to nID14 are integers greater than or equal to 0
	@params nID1 to nID14 correspond to the ID numbers assigned to each of the 14 seats of the bus, wherein nID1 is for seat 1, nID2 is for seat 2, 
	until nID14, which is for seat 14
*/
void CheckID(int nID1, int nID2, int nID3, int nID4, int nID5, int nID6, int nID7, int nID8, int nID9, int nID10, int nID11, int nID12, 
			int nID13, int nID14){
	printf("\nBooked Seats:\n");
	
	/* To check and display if any of the passed ID parameters have an ID number stored in them */
	if(nID1 > 0){
		printf("Seat 1: ID %d\n", nID1);
	}
	if(nID2 > 0){
		printf("Seat 2: ID %d\n", nID2);
	}
	if(nID3 > 0){
		printf("Seat 3: ID %d\n", nID3);
	}
	if(nID4 > 0){
		printf("Seat 4: ID %d\n", nID4);
	}
	if(nID5 > 0){
		printf("Seat 5: ID %d\n", nID5);
	}
	if(nID6 > 0){
		printf("Seat 6: ID %d\n", nID6);
	}
	if(nID7 > 0){
		printf("Seat 7: ID %d\n", nID7);
	}
	if(nID8 > 0){
		printf("Seat 8: ID %d\n", nID8);
	}
	if(nID9 > 0){
		printf("Seat 9: ID %d\n", nID9);
	}
	if(nID10 > 0){
		printf("Seat 10: ID %d\n", nID10);
	}
	if(nID11 > 0){
		printf("Seat 11: ID %d\n", nID11);
	}
	if(nID12 > 0){
		printf("Seat 12: ID %d\n", nID12);
	}
	if(nID13 > 0){
		printf("Seat 13: ID %d\n", nID13);
	}
	if(nID14 > 0){
		printf("Seat 14: ID %d\n", nID14);
	}
}


/*	This function determines which seat value should be changed depending on the seat number that has been chosen by the user and uses 
	the SaveSeatVal() function. This function checks first if the seats are already full when trying to book a ticket and if the seats 
	are still empty when trying to cancel a booking. This function also calls the BusLayout() function to display the seats and the CheckID() 
	function to show the assigned ID numbers of booked seats. This is the function that also prompts the user to input an ID number for
	their chosen seat.
	Precondition: cBookOption is an uppercase letter that is only either 'B' or 'C'. cRoute is an uppercase letter that is only either 'M' or
	'L'. *nTotalTix is an integer greater than or equal to 0. *cS1 to *cS14 are uppercase letters that are only either 'X' or 'O'.
	*nID1 to *nID14 are integers greater than or equal to 0
	@param cBookOption is the booking option indicating whether the user wanted to book a trip (B) or cancel a booking (C) and is used in
	the function to check if seats are full or empty
	@param cRoute is the route that the user chose whether from Manila to Laguna or vice versa
	@param *nTotalTix is the total number of tickets booked on the current trip, which is passed on to the SaveSeatVal() function
	@params *cS1 to *cS14 correspond to the seats of the bus, wherein *cS1 is for seat 1, *cs2 is for seat 2, until *cs14, 
	which is the 14th seat of the bus. These are passed on to the SaveSeatVal() function depending on the seat the user chose
	@params *nID1 to *nID14 correspond to the ID numbers assigned to each of the 14 seats of the bus, wherein nID1 is for seat 1, nID2 is for 
	seat 2, until nID14, which is for seat 14. These are also passed on to the SaveSeatVal() function depending on the seat the user chose
*/
void SaveTrip(char cBookOption, char cRoute, int *nTotalTix, char *cS1, char *cS2, char *cS3, char *cS4, char *cS5, 
	char *cS6, char *cS7, char *cS8, char *cS9, char *cS10, char *cS11, char *cS12, char *cS13, char *cS14, int *nID1, int *nID2, int *nID3, 
	int *nID4, int *nID5, int *nID6, int *nID7, int *nID8, int *nID9, int *nID10, int *nID11, int *nID12, int *nID13, int *nID14){
		
	int nSeatAvail; /* A variable that helps in determining whether the program will continue to ask the user to input a seat */
	int nIDNum; /* A variable for the user input ID number, which helps assign it to a seat the user chose */
	int nSeat; /* A variable for the user to chose a seat */
	
	/* Exits the function if bus is already full when booking a trip or if bus is still empty when cancelling a trip, otherwise
	it enters a loop that asks the user to choose a seat */
	if(cBookOption == 'B' && *cS1 == 'X' && *cS2 == 'X' && *cS3 == 'X' && *cS4 == 'X' && *cS5 == 'X' && *cS6 == 'X' && *cS7 
		== 'X' && *cS8 == 'X' && *cS9 == 'X' && *cS10 == 'X' && *cS11 == 'X' && *cS12 == 'X' && *cS13 == 'X' && *cS14 == 'X'){
			printf("\nCannot Book a Reservation. The Bus for Route (%c) is Already Full\n", cRoute);
	} else if(cBookOption == 'C' && *cS1 == 'O' && *cS2 == 'O' && *cS3 == 'O' && *cS4 == 'O' && *cS5 == 'O' && *cS6 
		== 'O' && *cS7 == 'O' && *cS8 == 'O' && *cS9 == 'O' && *cS10 == 'O' && *cS11 == 'O' && *cS12 == 'O' && *cS13 == 'O' && *cS14 == 'O'){
			printf("\nCannot Cancel a Reservation. The Bus for Route (%c) is Still Empty\n", cRoute);
	} else{
		
		/* Continuously loops until the user enters a valid input and successfully books or cancels a trip */
		do{
			BusLayout(&*cS1, &*cS2, &*cS3, &*cS4, &*cS5, &*cS6, &*cS7, &*cS8, &*cS9, &*cS10, &*cS11, &*cS12, &*cS13, &*cS14);
			CheckID(*nID1, *nID2, *nID3, *nID4, *nID5, *nID6, *nID7, *nID8, *nID9, *nID10, *nID11, *nID12, *nID13, *nID14);
			
			printf("\nChoose A Bus Seat: ");
			scanf("%d", &nSeat);
			
			if(cBookOption == 'B'){
				do{
				
					printf("Enter Your ID Number: ");
					scanf("%d", &nIDNum);
				
					if(nIDNum < 10000000 || nIDNum > 99999999){
						printf("\nID Number Can Only Be Non-Negative 8 Digits. Please Try Again\n\n");
					}
				
				}while(nIDNum < 10000000 || nIDNum > 99999999);
			}
			
			/* To refer to a seat that the user chose */
			switch(nSeat){
				case 1:
					SaveSeatVal(cBookOption, &nSeatAvail, &*cS1, &*nTotalTix, nIDNum, &*nID1);
					break;
				case 2:
					SaveSeatVal(cBookOption, &nSeatAvail, &*cS2, &*nTotalTix, nIDNum, &*nID2);
					break;
				case 3:
					SaveSeatVal(cBookOption, &nSeatAvail, &*cS3, &*nTotalTix, nIDNum, &*nID3);
					break;
				case 4:
					SaveSeatVal(cBookOption, &nSeatAvail, &*cS4, &*nTotalTix, nIDNum, &*nID4);
					break;
				case 5:
					SaveSeatVal(cBookOption, &nSeatAvail, &*cS5, &*nTotalTix, nIDNum, &*nID5);
					break;
				case 6:
					SaveSeatVal(cBookOption, &nSeatAvail, &*cS6, &*nTotalTix, nIDNum, &*nID6);
					break;
				case 7:
					SaveSeatVal(cBookOption, &nSeatAvail, &*cS7, &*nTotalTix, nIDNum, &*nID7);
					break;
				case 8:
					SaveSeatVal(cBookOption, &nSeatAvail, &*cS8, &*nTotalTix, nIDNum, &*nID8);
					break;
				case 9:
					SaveSeatVal(cBookOption, &nSeatAvail, &*cS9, &*nTotalTix, nIDNum, &*nID9);
					break;
				case 10:
					SaveSeatVal(cBookOption, &nSeatAvail, &*cS10, &*nTotalTix, nIDNum, &*nID10);
					break;
				case 11:
					SaveSeatVal(cBookOption, &nSeatAvail, &*cS11, &*nTotalTix, nIDNum, &*nID11);
					break;
				case 12:
					SaveSeatVal(cBookOption, &nSeatAvail, &*cS12, &*nTotalTix, nIDNum, &*nID12);
					break;
				case 13:
					SaveSeatVal(cBookOption, &nSeatAvail, &*cS13, &*nTotalTix, nIDNum, &*nID13);
					break;
				case 14:
					SaveSeatVal(cBookOption, &nSeatAvail, &*cS14, &*nTotalTix, nIDNum, &*nID14);
					break;
			}
			
			if(nSeat < 1 || nSeat > 14){
				printf("Invalid Seat Number, Please Input a Seat Number Within the Range [1-14]\n");
			}
			
		}while((nSeat < 1 || nSeat > 14) || nSeatAvail == 0);
	}			
}


/*	This function displays the current trip and asks the user which route they would like to book for depending on the current time
	Precondition: nCurTime is an integer within the range of 4 to 19 but not 12 nor 13. nCurTrip is an integer within the range of 1 to 7.
	*cRoute is an uppercase letter that is only either 'M' or 'L'
	@param nCurTime is the current time of the program
	@param nCurTrip is the current trip of the program
	@param *cRoute is the route to be chosen by the user, whether from Manila to Laguna or vice versa
*/
void BookTicket(int nCurTime, int nCurTrip, char *cRoute){
	
	printf("\n\nCurrent Trip: (%d)\n", nCurTrip);
	printf("-------------------------------------------------------\n");
	
	/* If the current time is even, it shows that both routes are available, and only shows 1 route if the time is odd */
	if(nCurTime % 2 == 0){
		printf("Route: (M) Manila to Laguna\t\t%d:00\n", nCurTime);
		printf("       (L) Laguna to Manila\t\t%d:00\n\n", nCurTime + 1);
	} else{
		printf("Route: (L) Laguna to Manila\t\t%d:00\n\n", nCurTime);
	}

	/* If the current time is even it allows the user to pick both routes, and only allows to pick one route if the time is odd */
	if(nCurTime % 2 == 0){
		do{
			printf("Pick a Route (M/L): ");
			scanf(" %c", &*cRoute);
			
			if(*cRoute != 'M' && *cRoute != 'L'){
				printf("Invalid Option, Please Retry\n\n");
			}
			
		}while(*cRoute != 'M' && *cRoute != 'L');
	} else{
		do{
			printf("Pick a Route (M/L): ");
			scanf(" %c", &*cRoute);
			
			if(*cRoute != 'M' && *cRoute != 'L'){
				printf("Invalid Option, Please Retry\n\n");
			}
			if(*cRoute == 'M'){
				printf("Only Route Available at this Time is (L)\n\n");
			}
			
		}while(*cRoute != 'L');
	}
}


/*	This function displays the current trip and asks the user which route they would like to cancel a booking for depending on the current time
	Precondition: nCurTime is an integer within the range of 4 to 19 but not 12 nor 13. nCurTrip is an integer within the range of 1 to 7.
	*cRoute is an uppercase letter that is only either 'M' or 'L'
	@param nCurTime is the current time of the program
	@param nCurTrip is the current trip of the program
	@param *cRoute is the route to be chosen by the user, whether from Manila to Laguna or vice versa
*/
void CancelBook(int nCurTime, int nCurTrip, char *cRoute){
	printf("\nCancelling a Reservation...\n");
	printf("-------------------------------------------------------\n");
	printf("Current Trip: (%d)\n", nCurTrip);
	
	if(nCurTime % 2 == 0){
		printf("Route: (M) Manila to Laguna\t\t%d:00\n", nCurTime);
		printf("       (L) Laguna to Manila\t\t%d:00\n\n", nCurTime + 1);
	} else{
		printf("Route: (L) Laguna to Manila\t\t%d:00\n\n", nCurTime);
	}	
	
	if(nCurTime % 2 == 0){
		do{
			printf("\nPick a Route (M/L): ");
			scanf(" %c", &*cRoute);
			
			if(*cRoute != 'M' && *cRoute != 'L'){
				printf("Invalid Option, Please Retry\n");
			}
			
		}while(*cRoute != 'M' && *cRoute != 'L');
	} else{
		do{
			printf("\nPick a Route (M/L): ");
			scanf(" %c", &*cRoute);
			
			if(*cRoute != 'M' && *cRoute != 'L'){
				printf("Invalid Option, Please Retry\n");
			}
			if(*cRoute == 'M'){
				printf("Only Route Available at that Time is (L)\n");
			}
			
		}while(*cRoute != 'L');
	}
}


/*	This function displays the current time and allows the user to update it to a depature time in the future
	Precondition: *nCurTrip is an integer within the range of 1 to 7. *nTime and *nCurTime is an integer within the 
	range of 4 to 19 but not 12 nor 13.
	@param *nCurTrip is the current trip of the program, which changes once the current time changes
	@param nTime is the future departure time that the user wants to update it to
	@param *nCurTime is the current time of the program, which changes once the user input time is accepted
*/
void UpdateDept(int *nCurTrip, int nTime, int *nCurTime){
	printf("\nUpdate Departure Time\n");
	printf("-------------------------------------------------------\n");
	printf("NOTE: You Can Only Choose Future Time\n");
	printf("Current Time (24-Hour Format): %d:00\n\n", *nCurTime);
	
	/* Exits the function if the time is already the last departure time (7pm), otherwise it asks for a valid new time input */
	if(*nCurTime == 19){
		printf("Cannot Update Departure Time. The Current Time is the Last Departure Time\n");
	} else{
		do{
		printf("Choose a Different Departure Time: ");
		scanf("%d", &nTime);
		
		if(nTime <= *nCurTime){
			printf("Invalid Input, Only Choose Future Time\n\n");
		}
		if(nTime > 19){
			printf("Invalid Input, The Last Departure Time is 19:00\n\n");
		}
		if(nTime == 12 || nTime == 13){
			printf("Invalid Input, The Bus Does Not Depart at that Time\n\n");
		}
		
	}while(nTime <= *nCurTime || nTime > 19 || nTime == 12 || nTime == 13);
	
	/* Every time the current time reaches an even time, the current trip increments by 1 because an even time means a new trip */
	while(*nCurTime != nTime){
		*nCurTime += 1;
		if(*nCurTime % 2 == 0){
			*nCurTrip += 1;
		}
	}
	
	*nCurTime = nTime;
	
	}
}


/*	This function displays the schedule of the trips and their availability depending on the current time of the program
	Precondition: nCurTime is an integer within the range of 4 to 19
	@param nCurTime is the current time of the program
	@return 
*/
void DispSched(int nCurTime){
	int nTripNum, nEvenTime = 4, nOddTime = 5;
	
	printf("\nSchedule of Bus Trips:\n");
	printf("---------------------------------------------------------------------\n");
	printf("          Route\t\t    Departure Times\t   Availability\n");
	printf("---------------------------------------------------------------------");
	
	/* For every trip number, it displays the two routes and checks if the current time is equal to or less than the even/odd time, which 
	increments by 2 each iteration, to determine their availability */
	for(nTripNum = 1;nTripNum <= 7;nTripNum++){
		
		printf("\n(%d) ", nTripNum);
		printf("Manila to Laguna\t\t %d:00\t\t   ", nEvenTime);
		
		if(nCurTime == nEvenTime){
			printf("CURRENT TRIP");
		} else if(nCurTime < nEvenTime){
			printf("AVAILABLE");
		} else{
			printf("UNAVAILABLE");
		}
		
		printf("\n");
		printf("    Laguna to Manila\t\t %d:00\t\t   ", nOddTime);
		
		if(nCurTime == nOddTime){
			printf("CURRENT TRIP");
		} else if(nCurTime < nOddTime){
			printf("AVAILABLE");
		} else{
			printf("UNAVAILABLE");
		}
		
		printf("\n");
		nEvenTime += 2;
		nOddTime += 2;
		
		/* Since there is no scheduled trip for 12pm and 1pm, +2 is added to the even and odd time when it reaches 12 and 13 */
		if(nEvenTime == 12){
			nEvenTime += 2;
		}
		if(nOddTime == 13){
			nOddTime +=2;
		}
	}
}


/*	This function warns the user about closing the program and determines whether the program will close or not
	Precondition: nOption is an integer within the range of 1 to 5
	@param nOption is the option picked by the user in the main menu of the program
	@return an integer that determines whether the app will close or not
*/
int CloseApp(int nOption){
	printf("\nWarning! Closing the Application Deletes the Records (Bookings)\n");
	printf("Enter (5) Again if You Wish to Close the Application\n");
	printf("Enter (Any Integer Number) if You Wish to Keep Using the Application\n");
	printf("---------------------------------------------------------------------\n");
	printf("Enter a Number: ");
	scanf("%d", &nOption);
	return nOption;
}


int main(){
	
	int nOption; /* Variable for the options in the main menu of the program */
	int nCurTime = 4; /* Variable for the current time of the program. Default is 4, which is 4am */
	int nCurTrip = 1; /* Variable for the current trip of the program. Default is 1, which is the first trip */
	int nTime = 0; /* Variable for user input time when the user want to change departure times */
	char cRoute; /* Variable for the route to be chosen by the user, whether from Manila to laguna or vice versa */
	int nTotalTix = 0; /* Variable for the total number of tickets booked in the current trip */
	
	/* Seat and ID number variables for the Manila to Laguna route */
	char cTMS1, cTMS2, cTMS3, cTMS4, cTMS5, cTMS6, cTMS7, cTMS8, cTMS9, cTMS10, cTMS11, cTMS12, cTMS13, cTMS14;
	cTMS1 = cTMS2 = cTMS3 = cTMS4 = cTMS5 = cTMS6 = cTMS7 = cTMS8 = cTMS9 = cTMS10 = cTMS11 = cTMS12 = cTMS13 = cTMS14 = 'O';
	int nIDM1, nIDM2, nIDM3, nIDM4, nIDM5, nIDM6, nIDM7, nIDM8, nIDM9, nIDM10, nIDM11, nIDM12, nIDM13, nIDM14;
	nIDM1 = nIDM2 = nIDM3 = nIDM4 = nIDM5 = nIDM6 = nIDM7 = nIDM8 = nIDM9 = nIDM10 = nIDM11 = nIDM12 = nIDM13 = nIDM14 = 0;
	
	/* Seat and ID number variables for the Laguna to Manila route */
	char cTLS1, cTLS2, cTLS3, cTLS4, cTLS5, cTLS6, cTLS7, cTLS8, cTLS9, cTLS10, cTLS11, cTLS12, cTLS13, cTLS14;
	cTLS1 = cTLS2 = cTLS3 = cTLS4 = cTLS5 = cTLS6 = cTLS7 = cTLS8 = cTLS9 = cTLS10 = cTLS11 = cTLS12 = cTLS13 = cTLS14 = 'O';
	int nIDL1, nIDL2, nIDL3, nIDL4, nIDL5, nIDL6, nIDL7, nIDL8, nIDL9, nIDL10, nIDL11, nIDL12, nIDL13, nIDL14;
	nIDL1 = nIDL2 = nIDL3 = nIDL4 = nIDL5 = nIDL6 = nIDL7 = nIDL8 = nIDL9 = nIDL10 = nIDL11 = nIDL12 = nIDL13 = nIDL14 = 0;

	/* To continuously loop the main menu until the user pick option 5, which is to close the program */
	do{
		MainMenu(nCurTime);

		printf("Enter an Option: ");
		scanf("%d", &nOption);
		
		if(nOption < 1 || nOption > 5){
			printf("That is Not a Valid Option, Please Re-enter Another\n\n");
		}
		
		/* To check which option the user chose in the main menu */
		switch(nOption){
			case 1:
				/* To ask the user for a route and determine which variables will be passed depending on the route chosen */
				BookTicket(nCurTime, nCurTrip, &cRoute);
				switch(cRoute){
					case 'M':
						SaveTrip('B', cRoute, &nTotalTix, &cTMS1, &cTMS2, &cTMS3, &cTMS4, &cTMS5, &cTMS6, &cTMS7, &cTMS8, &cTMS9, 
						&cTMS10, &cTMS11, &cTMS12, &cTMS13, &cTMS14, &nIDM1, &nIDM2, &nIDM3, &nIDM4, &nIDM5, &nIDM6, &nIDM7, &nIDM8, &nIDM9, 
						&nIDM10, &nIDM11, &nIDM12, &nIDM13, &nIDM14);
						break;
					case 'L':
						SaveTrip('B', cRoute, &nTotalTix, &cTLS1, &cTLS2, &cTLS3, &cTLS4, &cTLS5, &cTLS6, &cTLS7, &cTLS8, &cTLS9, 
						&cTLS10, &cTLS11, &cTLS12, &cTLS13, &cTLS14, &nIDL1, &nIDL2, &nIDL3, &nIDL4, &nIDL5, &nIDL6, &nIDL7, &nIDL8, &nIDL9, 
						&nIDL10, &nIDL11, &nIDL12, &nIDL13, &nIDL14);
						break;
				}
				break;
			case 2:
				CancelBook(nCurTime, nCurTrip, &cRoute);
				switch(cRoute){
					case 'M':
						SaveTrip('C', cRoute, &nTotalTix, &cTMS1, &cTMS2, &cTMS3, &cTMS4, &cTMS5, &cTMS6, &cTMS7, &cTMS8, &cTMS9, 
						&cTMS10, &cTMS11, &cTMS12, &cTMS13, &cTMS14, &nIDM1, &nIDM2, &nIDM3, &nIDM4, &nIDM5, &nIDM6, &nIDM7, &nIDM8, &nIDM9, 
						&nIDM10, &nIDM11, &nIDM12, &nIDM13, &nIDM14);
						break;
					case 'L':
						SaveTrip('C', cRoute, &nTotalTix, &cTLS1, &cTLS2, &cTLS3, &cTLS4, &cTLS5, &cTLS6, &cTLS7, &cTLS8, &cTLS9, 
						&cTLS10, &cTLS11, &cTLS12, &cTLS13, &cTLS14, &nIDL1, &nIDL2, &nIDL3, &nIDL4, &nIDL5, &nIDL6, &nIDL7, &nIDL8, &nIDL9, 
						&nIDL10, &nIDL11, &nIDL12, &nIDL13, &nIDL14);
						break;
				}
				break;
			case 3:
				DispSched(nCurTime);
				break;
			case 4:
				/* To ask the user for a future departure time and if the departure time is even, it resets the values to default because
				changing the time to an even time is a new trip */
				UpdateDept(&nCurTrip, nTime, &nCurTime);
				if (nCurTime % 2 == 0){
					cTMS1 = cTMS2 = cTMS3 = cTMS4 = cTMS5 = cTMS6 = cTMS7 = cTMS8 = cTMS9 = cTMS10 = cTMS11 = cTMS12 = cTMS13 = cTMS14 = 'O';
					nIDM1 = nIDM2 = nIDM3 = nIDM4 = nIDM5 = nIDM6 = nIDM7 = nIDM8 = nIDM9 = nIDM10 = nIDM11 = nIDM12 = nIDM13 = nIDM14 = 0;
					cTLS1 = cTLS2 = cTLS3 = cTLS4 = cTLS5 = cTLS6 = cTLS7 = cTLS8 = cTLS9 = cTLS10 = cTLS11 = cTLS12 = cTLS13 = cTLS14 = 'O';
					nIDL1 = nIDL2 = nIDL3 = nIDL4 = nIDL5 = nIDL6 = nIDL7 = nIDL8 = nIDL9 = nIDL10 = nIDL11 = nIDL12 = nIDL13 = nIDL14 = 0;
					nTotalTix = 0;
				}
				break;
			case 5:
				if (CloseApp(nOption) == 5){
					break;
				}
				else{
					nOption = 0;
				}
		}	
	}while(nOption != 5);
	
	printf("\n==========================================================\n");
	printf("\t   Thank You for Using Arrows Express!\n");
	printf("\t     We Hope to Serve You Again Soon\n\n");
	printf("\t     Total Number of Tickets Booked: %d\n\n", nTotalTix);
	printf("==========================================================\n");
	
	return 0;
}
