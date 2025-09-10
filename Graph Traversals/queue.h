//Used from MCO1 CCDSALG SY 2023 - 2024 under Mr. Florante Salvador
#include "graph.h"

typedef struct{
	String tokens[MAX_VERTICES];
	int head;
	int tail;
} queue;

void createQueue(queue *Q);
void enqueue(queue *Q, String token);
char* dequeue(queue *Q);
char* queueHead(queue *Q);
char* queueTail(queue *Q);
int queueEmpty(queue *Q);
int queueFull(queue *Q);
