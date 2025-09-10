typedef char* string;

typedef struct{
	string tokens[256];
	int head;
	int tail;
} queue;

void createQueue(queue *Q);
void enqueue(queue *Q, string token);
char* dequeue(queue *Q);
char* queueHead(queue *Q);
char* queueTail(queue *Q);
int queueEmpty(queue *Q);
int queueFull(queue *Q);
